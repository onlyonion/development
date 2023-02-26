# 注册中心
服务注册中心本质上是为了解耦`服务提供者`和`服务消费者`。
- 管理微服务提供者的注册与发现
  - 微服务的提供者的数量和分布往往是动态变化的
  - 支持弹性扩缩容特性
- 负载均衡，静态LB机制不再适用

### 服务注册中心
- 应用内：集成到应用中，Eureka、ZooKeeper、Etcd
- 应用外：把应用当成黑盒，通过应用外的某种机制将服务注册到注册中心，最小化对应用的侵入性，比如Airbnb的SmartStack，HashiCorp的Consul
- DNS：将服务注册为DNS的SRV记录，严格来说，是一种特殊的应用外注册方式，SkyDNS是其中的代表。缺陷：固有的缓存

### Overview

| regsitry  | group     | language | CAP                   | note          |
| :-------- | :-------- | :------- | :-------------------- | :------------ |
| zookeeper | apache    | java     | CP(ZAB)               |               |
| eureka    | netflix   | java     | AP                    |               |
| nacos     | alibaba   | java     | AP(distro) + CP(raft) | config-center |
| consul    | hashicorp | go       | CP                    |               |
| etcd      | coreos    | go       | CP                    |               |