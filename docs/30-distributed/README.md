## 分布式系统

### 特点
* 对等：各个节点没有主次之分
* 分布：在空间上随意分布，水平扩展，比如我们sinai随意再lf和dx随意添加机器
* 并发：对等的节点可能并发访问访问共享资源，如同时访问redis，db，或一个接口
* 缺乏全局的时钟：因为分布和对等性，你不知道两个事件发生的前后顺序
* 故障不固定：很依赖节点之间的通信，因为网路分区或通信异常会导致机器状态不一致

### 理论
* [理论基础](30-distributed/10-theme.md) ACID、CAP、BASE

## 分布式中间件

### 网络编程
异步非阻塞，文件IO、网络IO
* 高性能NIO框架 netty [《Netty权威指南（第2版）》李林锋](/99-book/notes/21-server/Netty权威指南.md)
* [《实战Nignx 取代Apache的高性能Web服务器》](/99-book/notes/21-server/实战Nignx.md)
* 异步事件驱动 nodejs [《深入浅出Node.js》朴灵](/99-book/notes/60-js/深入浅出NodeJS.md)

### 服务框架
* [rpc框架](/docs/30-distributed/20-service.md) dubbo、motan、gRPC
* 注册中心 zookeeper、consul
* 配置中心 diamond
* 软负载均衡 
* 服务网关

### 服务治理
* 可用性
  * 集群 负载均衡、反向代理、主从、冷备热备、冗余
  * 超时、重试、幂等、限流、降级、熔断
  * 资源隔离、流量调度、容错、开关
* 并发
  * 缓存、异步、队列
  * 链路跟踪、链路压测
  
### 缓存
* 存储器的层次结构 CPU -> Register -> L1/L2/L3 -> 内存 -> 磁盘缓存 -> 磁盘
* 堆缓存、堆外缓存、磁盘缓存、分布式缓存
* 多级缓存 应用级缓存、http缓存（http缓存、httpClient缓存、NginxHTTP缓存、Nginx代理层缓存）
* 分布式 Session；一致性哈希
* 缓存使用方式
  * Cache-Aside 业务代码直接维护缓存
  * Cache-As-SoR 把Cache看做SoR，所有操作都是对Cache进行，然后Cache委托给SoR进行真实的读写；直读、只写、回写
* ehcahe
* guava
* [NOSQL](/docs/30-distributed/60-cache.md)
  * memcached
  * redis
  * mongodb

### 消息队列
缓冲队列、任务队列、消息队列、优先级队列、阻塞队列
* 使用场景 应用解耦合、服务通信、[异步任务](/docs/30-distributed/50-mq.md) 、削峰填谷、消息广播
* 消息协议 JMS、AMQP
* 常用队列
  * rabbitmq 单机吞吐量万级
  * activeMQ 单机吞吐量万级
  * rocketmq 单机吞吐量十万级
  * kafka scala单机吞吐量十万级、zookeeper分布式协调、消息可能会重复但不会丢失

### 分布式数据库
分而治之，分片与汇总
* 数据库中间件
  * 读写分离
  * 分库分表 数据拆分
* 分布式事务，业务一致性
  * 2PC、3PC、Paxos
  * TCC
  * mq-tx
  * Saga

### 分布式文件系统
* fastDFS

### 分布式计算
Mapreduce, storm

### 日志
日志收集 --> 日志推送 --> 链路跟踪 --> 调用链分析
* elk (Elasticsearch, Logstash, Kibana)
* elk-kafka
* elasticsearch
* lucene
* storm

## book
* [《分布式系统技术内幕》张军 首都经济贸易大学出版社](/99-book/notes/30-distributed/分布式系统技术内幕.md)
* [《dubbo源码解析2.0》](/99-book/notes/30-distributed/dubbo.md)
* [《Redis设计与实现》黄建宏](/99-book/notes/30-distributed/Redis设计与实现.md)
* [《RocketMQ实战与原理解析》杨开元](/99-book/notes/30-distributed/RocketMQ实战与原理解析.md)
* [《从Paxos到Zookeeper 分布式一致性原理与实践》倪超](/99-book/notes/30-distributed/从Paxos到ZooKeeper.md)
* [《大型网站系统与Java中间件实践》曾宪杰](/99-book/notes/30-distributed/大型网站系统与Java中间件实践.md)
* [《亿级流量网站架构核心技术 跟开涛学搭建高可用高并发系统》 张开涛](/99-book/notes/40-architecture/亿级流量网站架构核心技术.md)