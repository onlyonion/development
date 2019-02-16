# Spring Cloud

1. Eureka 注册中心
2. Ribbon 集成REST实现负载均衡
3. Fegion 声明式服务调用
4. Hystrix 服务熔断降级
5. Zuul 实现微服务网关
6. Config 分布式统一配置中心
7. Sleuth 调用链路跟踪
8. BUS 消息总线

### Docker 虚拟化

### consul
consul是google开源的一个使用go语言开发的服务发现、配置管理中心服务。内置了服务注册与发现框架、分布一致性协议实现、健康检查、Key/Value存储、多数据中心方案，不再需要依赖其他工具（比如ZooKeeper等）。服务部署简单，只有一个可运行的二进制的包。

## servitization
服务化的核心就是将传统的一站式应用根据业务拆分成一个一个的服务，而微服务在这个基础上要更彻底地去耦合（不再共享DB、KV，去掉重量级ESB），并且强调DevOps和快速演化

DevOps是英文Development和Operations的合体，他要求开发、测试、运维进行一体化的合作，进行更小、更频繁、更自动化的应用发布，以及围绕应用架构来构建基础设施的架构。
这就要求应用充分的内聚，也方便运维和管理。这个理念与微服务理念不谋而合。

微服务背后一个重要的理念就是持续集成、快速交付，而在服务内部使用一个统一的技术框架

## provider, consumer, eureka
* Service Provider： 暴露服务的提供方。
* Service Consumer：调用远程服务的服务消费方。
* EureKa Server： 服务注册中心和服务发现中心。

![spring-cloud-provider-consumer-eureka](./img/spring-cloud-provider-consumer-eureka.png) 

## spring cloud 微服务框架技术标准分析
* 核心-服务治理
* 消息组件
* 配置中心
* 安全控制
* 集群工具
* 命令行工具
* 分布式链路监控

![spring cloud 微服务框架技术标准分析](./img/spring-cloud-knowledge.jpg) 

## 一统江湖微服务架构之spring cloud

![spring-cloud-structure](./img/spring-cloud-structure.jpg)

## other
[Dubbo 和 Spring Cloud 微服务架构到底孰优孰劣](https://blog.csdn.net/lijinzhou2017/article/details/78718217)  

## idea dev
* Core
* Web
* Template Engines
* SQL
* NoSQL
* Integration
* Cloud Core
* Cloud Support
* Cloud Config
* Cloud Discovery
* Cloud Routing
* Cloud Circuit Breaker
* Cloud Tracing
* Cloud Messaging
* Cloud AWS
* Cloud Contract
* Pivotal Cloud Foundry
* Azure
* Spring Cloud GCP
* I/O
* Ops

