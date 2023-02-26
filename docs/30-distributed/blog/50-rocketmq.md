
## role
* NameServer
* Producer
* Consumer
* Broker

## rocketmq 特性
* 单机支持1万以上持久队列
* 刷盘策略
* 消息过滤
* 长轮询pull
* 发送消息负载均衡  路由哪个broker, 哪个queue
* 消费消息的负载均衡
* HA，同步双写，异步复制

```java
 // AllocateMessageQueueAveragely.allocate实现了consumer消费的默认负载均衡算法
// 基本原则，每个队列只能被一个consumer消费
// 当messageQueue个数小于等于consume的时候，排在前面（在list中的顺序）的consumer消费一个queue，index大于messageQueue之后的consumer消费不到queue，也就是为0
// 当messageQueue个数大于consumer的时候，分两种情况
// 当有余数（mod > 0）并且index < mod的时候，当前comsumer可以消费的队列个数是 mqAll.size() / cidAll.size() + 1
// 可以整除或者index 大于余数的时候，队列数为：mqAll.size() / cidAll.size()
```
## rocketmq 高可用
RocketMQ做了以下的事情来保证系统的高可用
* 多master部署，防止单点故障
* 消息冗余（主从结构），防止消息丢失
* 故障恢复

### Producer与broker间的心跳
Producer轮询某topic下的所有队列的方式来实现发送方的负载均衡

```groovy
// 轮询实现
List<MessageQueue> messageQueueList
AtomicInteger sendWitchQueue //自增整型

(++sendWitchQueue)% messageQueueList.size
```

Rocketmq能够保证消息严格顺序，但是Rocketmq需要producer保证顺序消息按顺序发送到同一个queue中

## links
- [RocketMQ详解](https://zhuanlan.zhihu.com/rocketmq)
  - 水平扩展及负载均衡详解
  - 消息ACK机制及消费进度管理
  - 消息文件过期原理
  - 通信协议
  - 消息高可靠

### Topic 
标识一类消息的逻辑名字，消息的逻辑管理单位。无论消息生产还是消费，都需要指定Topic。

### Tag
RocketMQ支持给在发送的时候给topic打tag，同一个topic的消息虽然逻辑管理是一样的。但是消费topic1的时候，如果你订阅的时候指定的是tagA，那么tagB的消息将不会投递。

### Message Queue
简称Queue或Q。消息物理管理单位。一个Topic将有若干个Q。若Topic同时创建在不同的Broker，则不同的broker上都有若干Q，消息将物理地存储落在不同Broker结点上，具有水平扩展的能力。
无论生产者还是消费者，实际的生产和消费都是针对Q级别。例如Producer发送消息的时候，会预先选择（默认轮询）好该Topic下面的某一条Q地发送；Consumer消费的时候也会负载均衡地分配若干个Q，只拉取对应Q的消息。
每一条message queue均对应一个文件，这个文件存储了实际消息的索引信息。并且即使文件被删除，也能通过实际纯粹的消息文件（commit log）恢复回来。


### Broker负载均衡
由于压力分摊到了不同的queue,不同的queue实际上分布在不同的Broker group，也就是说压力会分摊到不同的broker进程，这样消息的存储和转发均起到了负载均衡的作用。

### Producer
Producer端，每个实例在发消息的时候，默认会轮询所有的message queue发送，以达到让消息平均落在不同的queue上。
而由于queue可以散落在不同的broker，所以消息就发送到不同的broker下。

### 消费完后的消息去哪里了？
消息的存储是一直存在于CommitLog中的，由于CommitLog是以文件为单位（而非消息）存在的，而且CommitLog的设计是只允许顺序写，且每个消息大小不定长，
所以这决定了消息文件几乎不可能按照消息为单位删除（否则性能会极具下降，逻辑也非常复杂）。

所以消息被消费了，消息所占据的物理空间也不会立刻被回收。但消息既然一直没有删除，那RocketMQ怎么知道应该投递过的消息就不再投递？
客户端自身维护。
客户端拉取完消息之后，在响应体中，broker会返回下一次应该拉取的位置，PushConsumer通过这一个位置，更新自己下一次的pull请求。这样就保证了正常情况下，消息只会被投递一次。

这样设计带来的好处？
1. 一个消息很可能需要被N个消费组（设计上很可能就是系统）消费，但消息只需要存储一份，消费进度单独记录即可。
2. 由于消费从哪里消费的决定权一直都是客户端决定，所以只要消息还在，就可以消费到，这使得RocketMQ可以支持其他传统消息中间件不支持的回溯消费。
3. 消息索引服务。只要消息还存在就能被搜索出来。所以可以依靠消息的索引搜索出消息的各种原信息，方便事后排查问题。

消息文件默认是1GB，删除时候，IO的压力是大

RocketMQ官方建议Linux下文件系统改为Ext4，对于文件删除操作，相比Ext3有非常明显的提升。


