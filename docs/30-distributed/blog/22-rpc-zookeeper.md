# rpc 注册中心

## 软负载中心
* 数据聚合
* 订阅关系
* 服务上下限感知
* 数据分发 压缩、增量
* 服务路由
* 集群 数据统一存储、分布式对等存储
 
## 分布式锁
分布式锁主要用于在分布式环境中保护跨进程、跨主机、跨网络的共享资源实现互斥访问，以达到保证数据的一致性。

分布式锁的实现多种方案：
1.	基于数据库实现分布式锁
2.	基于缓存（redis, memcached, tair）实现分布式锁
3.	基于Zookeeper临时有序节点实现分布式锁 curator

理想状态下的分布式锁
1.	保证在分布式部署的应用集群中，同一个方法在同一时间只能被一台机器上的一个线程执行
2.	可重入锁（避免死锁）
3.	阻塞锁（根据业务需求考虑要不要这条）
4.	有高可用的获取锁和释放锁功能
5.	获取锁和释放锁的性能要好


[zookeeper 负载均衡 核心机制-实现原理 包含ZAB协议](https://www.cnblogs.com/aspirant/p/9088322.html)