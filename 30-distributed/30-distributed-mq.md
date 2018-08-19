
消息中间件主要是实现分布式系统中解耦、异步消息、流量销锋、日志处理等场景

## jms

### 基本概念
* 发送者（ Sender）
* 接收者（ Receiver）
* 点对点（ Point-to-Point(P2P) ）
* 发布订阅（ Publish/Subscribe(Pub/Sub) ）
* 消息队列（Queue）  一个容纳那些被发送的等待阅读的消息的区域。与队列名字所暗示的意思不同，消息的接受顺序并不一定要与消息的发送顺序相同。一旦一个消息被阅读，该消息将被从队列中移走。
* 主题（Topic）
* 发布者（Publisher）
* 订阅者（Subscriber）

### 对象模型

* ConnectionFactory  创建Connection对象的工厂，针对两种不同的jms消息模型，分别有QueueConnectionFactory和TopicConnectionFactory两种。可以通过JNDI来查找ConnectionFactory对象。
* Destination 消息生产者的消息发送目标（Queue、Topic）或者说消息消费者的消息来源（队列或主题）。
* Connection QueueConnection和TopicConnection
* Session QueueSession和TopicSession
* 消息的生产者 QueueSender和TopicPublisher
* 消息消费者 QueueReceiver和TopicSubscriber
* MessageListener 如果注册了消息监听器，一旦消息到达，将自动调用监听器的onMessage方法

### 消息消费

* 同步 订阅者或接收者调用receive方法来接收消息，receive方法在能够接收到消息之前（或超时之前）将一直阻塞
* 异步 订阅者或接收者可以注册为一个消息监听器。当消息到达之后，系统自动调用监听器的onMessage方法。


## 1. rabbitmq

### 交换器

1.	direct 路由键匹配-一个消息到一个队列
2.	fanout 将收到的消息广播到绑定的队列上-一个消息到多个队列
3.	topic 不同源头的消息能够到达同一个队列-多个消息到一个队列
4.	header

## 2. rocketmq

* 能够保证严格的消息顺序（需要集群的支持）
* 提供丰富的消息拉取模式（可以任意定义你的拉取方式，exmaple中也提供了一个很好的例子）
* 高效的订阅者水平扩展能力（通过一个consumerGroup的方式做到consumer的方便扩容）
* 实时的消息订阅机制（消息的实时推送，类似于上面咱们的异步消费的方式）
* 亿级消息堆积能力（轻松完成系统销锋）

### 专有术语
* NameServer 注册中心
* Push Consumer Consumer 的一种，应用通常通过 Consumer 对象注册一个 Listener 接口，一旦收到消息，Consumer 对象立刻回调 Listener 接口方法，类似于activemq的方式
* Pull Consume Consumer 的一种，应用通常主动调用 Consumer 的拉消息方法从 Broker 拉消息，主动权由应用控制
* Producer Group
* Consumer Group
* Broker 消息中转的角色，负责存储消息（实际的存储是调用的store组件完成的），转发消息，一般也成为server，通jms中的provider
* Message Filter 可以实现高级的自定义的消息过滤，java编写
* Master/Slave 集群的主从关系，broker的name相同，brokerid=0的为主，大于0的为从

## 3. kafka

高吞吐量的分布式发布订阅消息系统，可扩展性，持久性、可靠性，容错性，高并发

### 使用场景
* 日志收集：一个公司可以用Kafka可以收集各种服务的log，通过kafka以统一接口服务的方式开放给各种consumer，例如hadoop、Hbase、Solr等。
* 消息系统：解耦和生产者和消费者、缓存消息等。
* 用户活动跟踪：Kafka经常被用来记录web用户或者app用户的各种活动，如浏览网页、搜索、点击等活动，这些活动信息被各个服务器发布到kafka的topic中，然后订阅者通过订阅这些topic来做实时的监控分析，或者装载到hadoop、数据仓库中做离线分析和挖掘。
* 运营指标：Kafka也经常用来记录运营监控数据。包括收集各种分布式应用的数据，生产各种操作的集中反馈，比如报警和报告。
* 流式处理：比如spark streaming和storm
* 事件源

### 名词解释
* producer
* consumer
* topic: 消息以topic为类别记录,Kafka将消息种子(Feed)分门别类,每一类的消息称之为一个主题(Topic)。
* broker：以集群的方式运行,可以由一个或多个服务组成，每个服务叫做一个broker;消费者可以订阅一个或多个主题(topic),并从Broker拉数据,从而消费这些已发布的消息。
      每个消息（也叫作record记录,也被称为消息）是由一个key，一个value和时间戳构成。
      
      
[消息中间件](https://blog.csdn.net/zhangll_2008/article/details/78657177)       
