
## 基础理论

* ACID，CAP, BASE
* 一致性算法，2PC、3PC、Paxos；TCC、mq-tx

## 分布式中间件

* 服务框架
* 分布式缓存 redis
* 分布式消息服务 异步
* 分布式计算 Mapreduce, storm
* 分布式文件系统
* 分布式数据库 数据库中间件（拆分、复制）
* 软负载均衡 nginx
* 高性能NIO框架 netty

## 集群
* 集群
* 主从
* 冷备热备
* 大数据 Hadoop, hbase

### 服务治理

* 超时
* 重试
* 幂等
* 熔断
* 限流
* 降级
* 资源隔离
* 流量调度
* 业务异步、业务开关
* 链路跟踪、链路压测
* 分布式事务 业务一致性平台

| com    | 配置中心  | 文件系统 | 数据库    | 分布式计算 |
|:-------|:----------|:---------|:----------|:-----------|
| google | chubby    | gfs      | big-table | map-reduce |
| apache | zookeeper | hadoop   | hbase     | map-reduce |

zookeeper是分布式系统中一个负载均衡框架，google的chubby的一个开源实现，是是Hadoop和Hbase的重要组件。
同样的在http中，常听说的nginx也是一个负载均衡服务器，它面向的是分布式web服务器
