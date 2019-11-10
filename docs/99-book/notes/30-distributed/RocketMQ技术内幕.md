《RocketMQ技术内幕：RocketMQ架构设计与实现原理》丁威 周继锋 著 机械工业出版社

- 路由中心NameServer
- 消息发送、消息存储、消息消费
- 消息过滤、主从同步、事务消息

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
1. 解析配置文件，需要填充NameServerConfig、NettyServerConfig属性值。
2. 根据启动属性创建NamesrvConroller实例，并初始化该实例。

如果代码中使用了线程池，一种优雅停机的方式就是注册一个JVM钩子函数，在JVM进程关闭之前，现将线程池关闭，及时释放资源。
### 2.3 NameServer路由注册、故障剔除
#### 2.3.1 路由元信息
- topicQqueueTable 主题队列路由信息
- brokerAddrTable 
- clusterAddrTable
- brokerLivedTable
- filterServerTalbe

#### 2.3.2 路由注册
1. Broker发送心跳包
2. NameServer处理心跳包

#### 2.3.3 路由删除
Broker每个30s向NameServer发送一个心跳包，心跳包中包含BrokerId、Broker地址、Broker所属集群的名称、Broker管理的FilterServer列表。

NameServer会每个10s扫描brokerLiveTable状态表，如果BrokerLive的lastUpdateTimestamp的时间戳距当前时间超过120s，则认为broker失效，
移除该broker，关闭与broker连接，同时更新topicQueueTable、brokerAddrTable、brokerLivedTable、filterServerTable。

#### 2.3.4 路由发现
RocketMQ路由发现是非实时的，当Topic路由出现变化后，NameServer不主动推送给客户端，而是由客户端定时拉取主题最新的路由。
根据主题名拉取路由信息的命令编码为：GET_ROUTEINFO_BY_TOPIC。
1. 根据RouterInfoManager的方法，从路由表topicQueueTable、brokerAddrTable、FilterServerTable中分别填充TopicRouteData
2. 如果找到主题对应的路由信息并且该主题为顺序消息，则从NameServerKVconfig中获取关于顺序消息相关的配置填充路由信息。

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
- MQPushConsumer
- DefaultMQPushConsumer

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
RocketMQ消息队列重新分布式由RebalanceService线程来实现的。一个MQClientInstance持有一个RebalanceService实现，并随着MQClientInstance的启动而启动。

每个DefaultMQPushConsumerImpl都持有一个单独的RebalanceImpl对象，该方法主要是遍历订阅信息对每个主题的队列进行重新负载。
1. 从主题订阅信息缓存表中获取主题的队列信息
2. 首先对cidAll，mqAll排序，同一个消费组内看到的视图保持一致，确保同一个消费队列不会被多个消费组分配
   - 分配算法：平均分配、平均轮询分配、一致性hash、根据配置、根据Broker部署机房名
3. ConcurrentMap<MessageQueue, ProcessQueue> processQueueTable，当前消费者负载的消费队列缓存
4. 遍历本次负载分配到的队列集合
5. 将PullRequest加入到PullMessageService中，以便唤醒PullMessageService线程。

### 5.6 消息消费过程
ConsumeMessageService#submitComsumeRequest进行消息消费，支持顺序消费与并发消费。
#### 5.6.1 消息消费
ConsumeMessageConcurrentlyService#submitConsumeRequest提交消费请求
1. 消费批次
2. 如果拉取的消息条数大于consumeMessageBatchMaxSize，则对拉取消息进行分页
3. 进入具体消息消费会先检查processQueue的dropped
4. 执行消息消费钩子函数ConsumeMessageHook#consumeMessageBefore函数
5. 恢复重试消费主题名
6. 执行具体的消息消费，调用应用消息监听器的consumeMessage方法
7. 执行消息消费钩子函数ConsumeMessageHook#consumeMessageAfter函数
8. 执行业务消费后，在处理结果前再次验证一下ProcessQueue的isDropped状态值
9. 根据消息监听器返回的结果，计算ackIndex
10. 如果是集群模式，业务方返回RECOSUME_LATER，消息并不会重新被消费，
11. 从ProcessQueue中一次这批消息

#### 5.6.2 消息确认(ACK)
#### 5.6.3 消费进度管理
广播模式：消息进度独立存储。

集群模式：消费进度保持在一个每个消费者都能够访问到的地方。

### 5.7 定时消息机制
定时消息是指消息发送到Broker后，并不立即被消费者消费而是要等到特定的时间后才能被消费。
RocketMQ只支持特定级别的延迟消息。

ScheduleMessageService定时消息实现类

#### 5.7.1 load方法
#### 5.7.2 start方法
#### 5.7.3 定时调度逻辑
ScheduleMessageService的start方法启动后，会为每一个延迟级别创建一个调度任务，每一个延迟级别其实对应SCHEDULE_TOPIC_XXXX主题下的一个消息消费队列。

DeliverDelayedMessageTimerTask
### 5.8 消息过滤机制
MessageFilter。RocketMQ消息过滤方式不同于其他消息中间件，是在订阅时做过滤。

### 5.9 顺序消息
!> RocketMQ支持局部消息顺序消费，可以确保同一个消息消费队列中的消息被顺序消费，如果需要做到全局顺序消费则可以将主题配置成一个队列。

根据并发消息消费的流程，消息消费包含4个步骤：消息队列负载、消息拉取、消息消费、消息消费进度存储。
#### 5.9.1 消息队列负载
!> 顺序消息消费与并发消息消费的第一个关键区别：顺序消息在创建消息队列拉取任务时需要在Broker服务器锁定该消息队列。

#### 5.9.2 消息拉取
PullMessageService线程负责
#### 5.9.3 消息消费
ConsumeMessageOrderlyService
#### 5.9.4 消息队列锁实现
顺序消息消费的各个环节基本都是围绕消息消费队列（ConsumeQueue）与消息处理队列（ProcessQueue）展开的。
拉取时，判断ProcessQueue的locked是否为true

### 5.10 小结
消息队列负载由RebalanceService线程默认每隔20s进行一次消息队列负载，
根据当前消费组内**消费者个数**与**主题队列数量**按照某一种负载算法进行队列分配，
分配原则为同一个消费者可以分配多个消息消费队列，同一个消息消费队列同一时间只会分配给一个消费者。

消息拉取由PullMessageService线程根据RebalanceService线程创建的拉取任务进行拉取，
默认一批拉取32条消息，提交给消费者消费线程池后继续下一次消息拉取。
如果消息消费过慢产生消费堆积会触发消息消费拉取流控。

顺序消费一般采用集群模式，是指消息消费者内的线程池中的线程对消息消费队列只能串行消费。
与并发消息消费最本质的区别是消息消费时必须成功**锁定**消息消息队列，在Broker端会存储消息消费队列的锁占用情况。

## 第6章 消息过滤FilterServer
并不是所有的消息都需要进行消费的
### 6.1 ClassFilter运行机制
基于类模式过滤是指在Broker端运行1个或多个消息过滤服务器（FilterServer），
RocketMQ允许消息消费者自定义消息过滤实现类并将其代码上传到FilterServer上，消费者向FilterServer拉取消息，
FilterServer将消费者的拉取命令转发给Broker，然后对返回的消息执行消息过滤逻辑，最终将消息返回给消费端。

通常消息消费是直接向Broker订阅主题然后从Broker上拉取消息，类模式的一个特别之处在于消息消费者是从FilterServer拉取消息。
### 6.2 FilterServer注册剖析
FilterServer在启动时会创建一个定时调度任务，每个10s向Broker注册自己。
### 6.3 类过滤模式订阅机制
### 6.4 消息拉取
RocketMQ消息的过滤发生在消息消费的时候，PullMessageService线程默认从Broker上拉取消息，执行相关的过滤逻辑。
在FilterServer过滤模式下，PullMessageService线程是如何将拉取地址原来的Broker地址转换成FilterServer地址呢？

## 第7章 RocketMQ主从同步(HA)机制 
### 7.1 RocketMQ主从复制原理
Broker主备机制：消息消费到达**主服务器**需要将消息同步到**从服务器**，如果主服务器Broker宕机后，消息消费者可以从**从服务器**拉取消息。
#### 7.1.1 HAService整体工作机制
#### 7.1.2 AcceptSocketService实现原理
#### 7.1.3 GroupTransferService实现原理
#### 7.1.4 HAClient实现原理
#### 7.1.5 HAConnection实现原理

### 7.2 RocketMQ读写分离机制
消息消费是基于消息消费队列MessageQueue（topic、brokerName、queueId）。

如果主服务器反面则建议下一次从**从服务器**拉取消息

## 第8章 RocketMQ事务消息 
### 8.1 事务消息实现思想
1. perpare消息
2. RocketMQ 定时任务回查

### 8.2 事务消息发送流程
1. TransactionMQProducer
2. TransactionListener

事务发送流程
1. 首先为消息添加属性，TRAN_MSG和PGROUP，分别表示消息为prepare消息、消息所属消息生产者组，通过同步调用的方式向RockeMQ发送消息。
   设置生产者组的目的是在查询事务消息本地事务状态时，从该生产者组中随机萱蕚一个消息生产者即可。
2. 根据消息发送结果执行相应的操作
   - 如果消息发送成功，则执行TransactionListener#executeLocalTransaction，记录事务消息的本地事务状态，并且该方法与业务方法处于同一事务中，
   - 如果消息发送失败，则设置本次事务桩体为LocalTransactionState.ROLLBACK_MESSAGE.
3. 结束事务。根据第二步返回的事务状态执行提交、回滚或暂时不处理事务。

### 8.3 提交或回滚事务
### 8.4 事务消息回查事务状态
1. RocketMQ通过TransactionalMessageCheckService线程定时去检测RMQ_SYS_TRANS_HALF_TOPIC主题中的消息，回查消息的事务状态。
默认检测频率1分钟。
2. TransactionalMessageService#check
   - 查询RMQ_SYS_TRANS_HALF_TOPIC主题下的消息队列，该主题是prepare下线的存储队列
   - 遍历每一个消息消费队列，每个消息消费队列的处理时间为60s
3. AbstractTransactionMessageCheckListener#resolveHalfMsg 展示异步发送消息回查消息进度
4. AbstractTransactionMessageCheckListener#sendCheckMessage 组装回查请求命令，根据生产者，选择网络通道，从Broker向生产者发送回查事务状态
5. ClientRemotingProcessor#checkTransactionState
6. DefualtMQProducerImpl#checkTransactionState
7. TransactionListener#checkLocalTransaction
8. 根据事务状态发送END_TRANSACTION 发送END_TRANSACTION 命令给Broker，如果发哦少年宫UNDOWN，broker不会做任何动作只会打印info日志

## 第9章 RocketMQ实战 
### 9.1 消息批量发送
SendResult send(Collection<Message> msgs)

### 9.2 消息发送队列自选择
SendResult send(Message msg, MessageQueueSelector selector, Object arg)
### 9.3 消息过滤
#### 9.3.1 TAG模式过滤
#### 9.3.2 SQL表达模式过滤
#### 9.3.3 类过滤模式
实现消息过滤器MessageFilter

### 9.4 事务消息
### 9.5 Spring整合RocketMQ
### 9.6 Spring Cloud整合RocketMQ
### 9.7 RocketMQ监控与运维命令
#### 9.7.1 RocktetMQ监控平台搭建
#### 9.7.2 RocketMQ管理命令

### 9.8 应用场景分析
- 异步调用、应用解耦
- 可靠消息最终一致性
- 数据同步

