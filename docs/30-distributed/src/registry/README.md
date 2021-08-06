
服务注册中心本质上是为了解耦服务提供者和服务消费者。
- 支持弹性扩缩容特性
- 微服务的提供者的数量和分布往往是动态变化的
- 静态LB机制不再适用
- 管理微服务提供者的注册与发现

### 服务注册中心
- 应用内：集成到应用中，Eureka、ZooKeeper、Etcd
- 应用外：把应用当成黑盒，通过应用外的某种机制将服务注册到注册中心，最小化对应用的侵入性，比如Airbnb的SmartStack，HashiCorp的Consul
- DNS：将服务注册为DNS的SRV记录，严格来说，是一种特殊的应用外注册方式，SkyDNS是其中的代表。缺陷：固有的缓存

### overview
- hashicorp consul
- apache zookeeper
- coreos etcd
- netflix eureka
- alibaba nacos，Nacos = Spring Cloud注册中心 + Spring Cloud配置中心。

