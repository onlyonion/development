
### 数据可靠性
RocketMQ支持异步实时刷盘，同步刷盘，同步Replication，异步Replication
Kafka使用异步刷盘方式，异步Replication

### 性能对比
Kafka单机写入TPS约在百万条/秒，消息大小10个字节
RocketMQ单机写入TPS单实例约7万条/秒，单机部署3个Broker，可以跑到最高12万条/秒，消息大小10个字节

> 总结：Kafka的TPS跑到单机百万，主要是由于Producer端将多个小消息合并，批量发向Broker。

#### RocketMQ为什么没有这么做？
1. Producer通常使用Java语言，缓存过多消息，GC是个很严重的问题
2. Producer调用发送消息接口，消息未发送到Broker，向业务返回成功，此时Producer宕机，会导致消息丢失，业务出错
3. Producer通常为分布式系统，且每台机器都是多线程发送，我们认为线上的系统单个Producer每秒产生的数据量有限，不可能上万。
4. 缓存的功能完全可以由上层业务完成。


### 严格的消息顺序
Kafka支持消息顺序，但是一台Broker宕机后，就会产生消息乱序
RocketMQ支持严格的消息顺序，在顺序消息场景下，一台Broker宕机后，发送消息会失败，但是不会乱序

> Mysql Binlog分发需要严格的消息顺序


[kafka对比RocketMQ](https://blog.csdn.net/u014411730/article/details/78616019) 