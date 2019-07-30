## redis


## redis client
- jedis：是Redis的Java实现客户端，提供了比较全面的Redis命令的支持

使用阻塞的I/O，且其方法调用都是同步的，程序流需要等到sockets处理完I/O才能执行，不支持异步。
Jedis客户端实例不是线程安全的，所以需要通过连接池来使用Jedis。

- Redisson：实现了分布式和可扩展的Java数据结构。

基于Netty框架的事件驱动的通信层，其方法调用是异步的。Redisson的API是线程安全的，所以可以操作单个Redisson连接来完成各种操作

- Lettuce：高级Redis客户端，用于线程安全同步，异步和响应使用，支持集群，Sentinel，管道和编码器。

基于Netty框架的事件驱动的通信层，其方法调用是异步的。Lettuce的API是线程安全的，所以可以操作单个Lettuce连接来完成各种操作

## links
* [《Redis 设计与实现》黄建宏](/99-book/notes/30-distributed/Redis设计与实现.md)