《RocketMQ技术内幕：RocketMQ架构设计与实现原理》丁威 周继锋 著 机械工业出版社

## 第1章 阅读源代码前的准备
### 1.3 RocketMQ的设计理念和目标
设计目标
1. 架构模式
2. 顺序消息
3. 消息过滤
4. 消息存储
5. 消息高可用
6. 消息到达低延迟
7. 确保消息必须被消费一次
8. 回溯消息
9. 消息堆积
10. 定时消息
11. 消息重试机制

## 第2章 RocketMQ路由中心NameServer
### 2.1 NameServer架构设计
NameServer与每台Broker服务器保持长连接，并间隔30s检测Broker是否存活。
路由变化不会马上通知消息生产者，在消息发送端提供**容错机制**保证消息发送的高可用。

NameServer本身的高可用通过部署多台NameServer服务器来实现，但彼此之间互不通信。
### 2.2 NameServer启动流程
### 2.3 NameServer路由注册、故障剔除
#### 2.3.1 路由元信息
#### 2.3.2 路由注册
#### 2.3.3 路由删除
#### 2.3.4 路由发现

## 第3章 RocketMQ消息发送
### 3.1 漫谈RocketMQ消息发送
- 同步 sync  同步等待
- 异步 async 异步回调
- 单向 oneway

### 3.2 认识RocketMQ消息
org.apache.rocketmq.common.message.Message
- String topic
- int flag
- Map<String, String> properties
  - tag
  - keys 索引键
  - waitStoreMsgOK 消息发送时是否等消息存储完成后再返回
  - delayTimeLevel 延迟级别
- byte[] body
- String transactionId

### 3.3 生产者启动流程
消息生产者启动流程
1. 检查productGroup
2. 创建MQClientInstance实例
3. 向MQClientInstance注册
4. 启动MQClientInstance

### 3.4 消息发送基本流程
1. 消息长度验证
2. 查找主题路由信息
   - producer未缓存，向NameServer查询该topic的路由信息
   - producer已缓存
3. 选择消息队列 根据路由信息选择消息队列，返回的消息队列按照broker序号排序
   - 重试机制
4. 消息发送
   1. 根据MessageQueue获取Broker的网络地址
   2. 消息分配全局唯一ID
   3. 如果注册了消息发送钩子函数，则执行消息发送之前的增强逻辑
   4. 构造发送请确保
   5. 根据消息发送方式，同步、异步、单向进行网络传输

### 3.5 批量消息发送
将同一主题的多条消息一起打包发送到消息服务器，减少网络调用次数，提高网络传输效率。（请求合并）

## 第4章 RocketMQ消息存储
MQ中间件存储模型，分为需要持久化和不需要持久化的两种模式。

从存储方式和效率来看，文件系统高于KV存储，KV存储又高于关系型数据库，直接操作文件系统肯定是最快的，但可靠性却是最低的，
关系型数据库的性能和可靠性与文件系统恰恰相反。
### 4.1 存储概要设计
RocketMQ主要存储的文件包括Commitlog文件、ConsumeQueue文件、IndexFile文件。
RocketMQ将所有主题的消息存储在同一个文件中，确保消息发送时顺序写文件，尽最大的能力确保消息发送的高性能与高吞吐量。
为了提高消息消费的效率，RocketMQ引入了ConsumeQueue消息队列文件，每个消息主题包含多个消息消费队列，每一个消息队列有一个消息文件。
IndexFile索引文件，主要设计理念就是为了加速消息的检索性能，根据消息的属性快速从Commitlog文件中检索消息。

1. CommitLog 消息存储文件
2. ConsumeQueue  消息到达Commitlog文件后，将异步转发到消息消费队列
3. IndexFile 消息索引文件，存储消息key与offset的对应关系
4. 事务状态服务
5. 定时消息服务 每一个延迟级别对应一个消息消费队列，存储延迟队列的消息拉取进度

### 4.2 初识消息存储
org.apache.rocketmq.store.DefaultMessageStore

### 4.3 消息发送存储流程
org.apache.rocketmq.store.DefaultMessageStore#putMessage
1. 如果当前Broker停止工作或Broker为SLAVE角色或当前Rocket不支持写入则拒绝写入；消息主题长度超过256字符、消息属性长度超过65536字符
2. 如果延迟级别大于0
3. 获取当前可以写入的Commitlog文件，每一个文件默认1G，一个文件写满后写入另一个，以该文件的偏移量为文件名
4. 在写入Commitlog之前，先申请putMessageLock，串行
5. 设置消息的存储时间
6. 将消息追加到MappedFile中
7. 创建全局唯一消息ID，消息ID16字节（4字节IP、4字节端口号、8字节消息偏移量）
8. 获取该消息队列的偏移量
9. 根据消息体的长度、主题的长度、属性的长度结合消息存储格式计算消息长度
10. 如果消息长度 + END_FILE_MIN_BLANK_LENGTH大于Commitlog文件的空闲空间，返回END_OF_FILE，Broke会重新创建一个新的文件
11. 将消息内容存储到ByteBuffer中，然后创建AppendMessageResult
12. 更新消息队列逻辑偏移量
13. 处理完消息追加逻辑后将释放putMessageLock锁
14. DefaultAppendMessageCallback#doAppend只是将消息追加在内存，需要根据是同步刷盘还是异步刷盘方式，将内存中的数据持久化到磁盘。

### 4.4 存储文件组织与内存映射
RocketMQ通过使用**内存映射文件**来提高IO访问性能，无论是Commitlog、ConsumeQueue还是IndexFile，单个文件都被设计为固定长度，
如果一个文件写满以后再创建一个新文件，文件名就为该文件第一条消息对应的全局物理偏移量。

1. MappedFileQueue映射文件队列  MappedFile的管理容器，对存储目录的封装。
2. MappedFile内存映射文件
3. TransientStorePool 暂存的存储池

### 4.5 RocketMQ存储文件
```
commitlog
config
    consumerFilter.json 主题消息过滤信息
    consumerOffset.json 集群消费模式消息消费进度
    delayOffset.json 延时消息队列拉取进度
    subscriptionGroup.json
    topics.json
consumequeue
    TopicTest   主题
        0           主题的消息队列
            00000000000000
        1
        2
index 
abort
checkpoint
```
1. Commitlog文件 存储消息，每一条消息长度不相同，默认1G
2. ConsumeQueue文件 每个条目：commitlogoffset + size + taghashcode （8 + 4 + 8 byte）；默认包含30万个条目。
   ConsumeQueue即为Commitlog的索引文件
3. Index索引文件 hash索引（hash槽与hash冲突的链表结构）
   - indexHeader + hash slot + index 大小：40Btye + 500w + 2000w
4. checkpoint文件 记录Commitlog、ConsumeQueue、Index文件的刷盘时间，文件固定长度为4k，只用前24字节
   - physicalMsgTimestamp + logicMsgTimestamp + indexMsgTimestamp 

### 4.6 实时更新消息消费队列与索引文件
通过开启一个线程ReputMessageService来准实时转发Commitlog文件更新事件，相应的任务处理器根据转发的消息及时更新ComsumeQueue、IndexFile
1. 根据消息更新ConsumeQueue
2. 根据消息更新Index索引文件
   - 获取或创建IndexFile文件并获取所有文件最大的物理偏移量
   - 如果消息的唯一键不为空，则添加到hash索引中，以便加速根据唯一键检索消息
   - 构建索引建

### 4.7 消息队列与索引文件恢复
!> 由于RocketMQ存储首先将消息全量存储在Commitlog文件中，然后异步生成转发任务更新ConsumeQueue、Index文件，如果消息成功存储到Commitlog文件，
转发任务未成功进行，此时Broker宕机会导致Commitlog、ConsumeQueue、IndexFile文件**数据不一致**。

1. 判断上一次退出是否正常。正常退出会删除abort文件。
2. 加载延迟队列
3. 加载Commitlog文件
4. 加载消息消费队列
5. 加载存储检测点，检测点主要记录commitlog、consumequeue、index文件的刷盘点
6. 加载索引文件
7. 根据Broker是否正常停止执行不同的恢复策略
8. 恢复ConsumeQueue

#### 4.7.1 Broker正常停止文件恢复
#### 4.7.2 Broker异常停止文件恢复

### 4.8 文件刷盘机制
#### 4.8.1 Broker同步刷盘
#### 4.8.2 Broker异步刷盘

### 4.9 过期文件删除机制
顺序写：写操作全部落在最后一个Commitlog或ConsumeQueue文件上，之前的文件在下一个文件创建后将不会再被更新。

## 第5章 RocketMQ消息消费 
### 5.1 RocketMQ消息消费概述
- 消费分组
- 消费模式：集群、广播。
  集群负载遵循思想：一个消息队列同一时间只允许被一个消费者消费，一个消费者可以消费多个消息队列。
- 传送方式：推模式、拉模式。
  - RocketMQ消息推模式的实现基于拉模式，在拉模式上包装一层，一个拉取任务完成之后开始下一个拉取任务。
  - 消息拉模式，客户端手动调用消息拉取API
- 顺序消费
  - 局部顺序 保证同一个消息队列上的消息顺序消费
  - 全局顺序
- 消息过滤：表达式过滤与类过滤模式

### 5.2 消息消费者初探
MQPushConsumer

DefaultMQPushConsumer

### 5.3 消费者启动流程
DefaultMQPushConsumerImpl.start()
1. 构建主题订阅信息SubscriptionData并加入到RebalanceImpl的订阅消息中
2. 初始化MQClientInstance、RebalanceImpl等
3. 初始化消费进度。如果是集群模式，消息进度保存在Broker上；如果是广播模式，消息进度存储在消费端。
4. 根据是否是顺序消息，创建消费端线程服务
5. 向MQClientInstance注册消费者，并启动MQClientInstance，在一个JVM中的所有消费者、生产者持有同一个MQClientInstance

### 5.4 消息拉取
消费方式：广播模式与集群模式。
集群模式，同一个消费组内有多个消费者，同一个主题存在多个消息队列。

从MQClientInstance的启动流程中可以看书，RocketMQ使用一个单独的线程PullMessageService来复制消息拉取。
#### 5.4.1 PullMessageService实现机制
PullMessageService继承ServiceThread

#### 5.4.2 ProcessQueue实现机制
ProcessQueue是MessageQueue在消费端的重现、快照。
- PullMessageService从消息服务器默认每次拉取32条消息，按消息的队列偏移量存放在ProcessQueue中。
- PullMessageService然后将消息提交到消费者消费线程池，消息成功消费后从ProcessQueue中移除。

#### 5.4.3 消息拉取基本流程
1. 消息拉取客户端**封装消息拉取请求** DefaultMQPushConsumerImpl#pullMessage
   - 从PullRequest中获取ProcessQueue
   - 拉取流控
   - 拉取该主题订阅信息
   - 构建消息拉取系统标记
   - 调用PullAPIWrapper.pullkernelImpl方法后与服务端交换
   - 根据brokerName、BrokerId从MQClientInstance中获取Broker地址
   - 过滤器模式处理
2. 消息服务器**查找并返回消息**
   - 根据订阅信息，构建消息过滤器
   - 调用MessageStore.getMessage查找消息
   - 根据主题名称与队列编号获取消息消费队列
   - 消息偏移量异常情况校对下一次拉取偏移量
   - 如果待拉取偏移量大于minOffset摈弃小鱼maxOffset，从当前offset处尝试拉取32条消息，根据消息队列偏移量从commitlog文件中查找消息
   - 根据PullRequest填充responseHeader的nextBigOffet、minOffset、maxOffset
   - 根据主从同步延迟，如果从节点数据包含下一次拉取的偏移量，设置下一次拉取任务的brokerId
   - 根据GetmessageResult编码转换成关系
   - 如果commitlog标记可用并且当前节点为主节点，则更新消息消费进度
3. 消息拉取客户端**处理返回的消息** MQClientAPIImpl#pullMessageAsync、NettyRemotingClient在收到服务端响应之后回调PullCallback
   - 根据响应结果解码成PullRequestExt对象
   - 调用pullAPIWrapper的processPullRequest将消息字节数组解码成消息列表填充msgfoundList，并对象消息进行消息过滤
   - 更新pullRequest的下一次拉取偏移量
   - 首先将拉取到的消息存入ProcessQueue，然后将拉取到的消息提交到ConsumeMessageService中供消费者消费。
   - 将消息提交给消费者线程之后PullCallBack将立即返回
4. 消息拉取长轮询机制分析
5. PullRequestHoldService线程详解
6. DefaultMessageStore$ReputMessageService


### 5.5 消息队列负载与重新分布机制
### 5.6 消息消费过程
#### 5.6.1 消息消费
#### 5.6.2 消息确认(ACK)
#### 5.6.3 消费进度管理
### 5.7 定时消息机制
#### 5.7.1 load方法
#### 5.7.2 start方法
#### 5.7.3 定时调度逻辑

### 5.8 消息过滤机制
### 5.9 顺序消息
#### 5.9.1 消息队列负载
#### 5.9.2 消息拉取
#### 5.9.3 消息消费
#### 5.9.4 消息队列锁实现

## 第6章 消息过滤FilterServer
并不是所有的消息都需要进行消费的
### 6.1 ClassFilter运行机制
通常消息消费是直接向Broker订阅主题然后从Broker上拉取消息，类模式的一个特别之处在于消息消费者是从FilterServer拉取消息。
### 6.2 FilterServer注册剖析
FilterServer在启动时会创建一个定时调度任务，每个10s向Broker注册自己。
### 6.3 类过滤模式订阅机制
### 6.4 消息拉取

## 第7章 RocketMQ主从同步(HA)机制 
### 7.1 RocketMQ主从复制原理
Broker主备机制：消息消费到达**主服务器**需要将消息同步到**从服务器**，如果主服务器Broker宕机后，消息消费者可以从**从服务器**拉取消息。
### 7.2 RocketMQ读写分离机制
消息消费是基于消息消费队列MessageQueue（topic、brokerName、queueId）。

如果主服务器反面则建议下一次从**从服务器**拉取消息

## 第8章 RocketMQ事务消息 
### 8.1 事务消息实现思想
### 8.2 事务消息发送流程
### 8.3 提交或回滚事务
### 8.4 事务消息回查事务状态

## 第9章 RocketMQ实战 
### 9.1 消息批量发送
SendResult send(Collection<Message> msgs)

### 9.2 消息发送队列自选择
SendResult send(Message msg, MessageQueueSelector selector, Object arg)
### 9.3 消息过滤
1. TAG模式过滤
2. SQL表达模式过滤
3. 类过滤模式 实现消息过滤器MessageFilter

### 9.4 事务消息
### 9.5 Spring整合RocketMQ
### 9.6 Spring Cloud整合RocketMQ
### 9.7 RocketMQ监控与运维命令
### 9.8 应用场景分析
- 异步调用、应用解耦
- 可靠消息最终一致性
- 数据同步

