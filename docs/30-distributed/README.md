## [分布式系统](/30-distributed/00-distributed.md)
* [基础理论](/30-distributed/10-theme.md) ACID，CAP, BASE
* 一致性算法，2PC、3PC、Paxos；
* TCC、mq-tx
* Saga
* 一致性哈希
* 分而治之，分片与汇总

## 分布式中间件
* [服务框架](/docs/30-distributed/20-service.md)
  * rpc框架 dubbo
  * 注册中心 zookeeper
  * 配置中心 diamond
  * 负载均衡
  * 服务网关
* [分布式缓存](/docs/30-distributed/60-cache.md)
  * redis
  * mongodb
* [分布式消息服务](/docs/30-distributed/50-mq.md) 异步
  * rabbitmq
  * rocketmq
  * kafka
* 分布式数据库 数据库中间件（拆分、复制）
* 软负载均衡 nginx
* 高性能NIO框架 netty
* 分布式文件系统
* 分布式计算 Mapreduce, storm

## 服务治理
* 超时、重试、幂等
* 限流、降级、熔断
* 流量调度
* 资源隔离
* 业务异步、业务开关
* 链路跟踪、链路压测
* 分布式事务 业务一致性平台

## 集群
* 集群
* 主从
* 冷备热备
* 冗余或者复制集Replication，集群Cluster

## log
日志收集 --> 日志推送 --> 链路跟踪 --> 调用链分析
* elk (Elasticsearch, Logstash, Kibana)
* elk-kafka
* elasticsearch
* lucene
* storm