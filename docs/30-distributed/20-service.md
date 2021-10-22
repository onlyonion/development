# 服务框架
* 服务框架
* 服务治理
* 网络间进程调用

## Role
- 服务提供者
- 服务消费者
- 注册中心

## RPC
RPC是远程过程调用的简称，广泛应用在大规模分布式应用中，作用是有助于系统的垂直拆分，使系统更易拓展。
Java中的RPC框架比较多，各有特色，广泛使用的有RMI、Hessian、Dubbo等。

RPC的主要依赖技术
* 序列化、反序列化
* 传输协议

## 服务实现
* RMI的序列化和反序列化是JAVA自带的
* Hessian里的序列化和反序列化是私有的，传输协议则是HTTP
* Dubbo的序列化可以多种选择，一般使用Hessian的序列化协议，传输则是TCP协议，使用了高性能的NIO框架Netty。
* pigeon大众点评分布式服务治理框架
* motan
* gRPC A high-performance, open-source universal RPC framework

对于序列化，我还了解一些，像Google的ProBuffer、JBossMarshalling和Apache Thrift等

| methods             | serialize    | protocol   |
|---------------------|--------------|------------|
| dubbo               | 二进制序列化 | tcp协议    |
| http invoker        | 二进制序列化 | http协议   |
| hessian             | 二进制序列化 | http协议   |
| WebServices         | 文本序列化   | http协议   |
| Pigeon 美团大众点评 | hessian      | tcp(netty) |

## 服务治理
* 集群、目录、路由、负载均衡
* 超时、重试、幂等
* 限流、降级、熔断、资源隔离

### 注册
注册中心-注册表，如Zookeeper，Consul，Eureka

[zookeeper 负载均衡 核心机制-实现原理 包含ZAB协议](https://www.cnblogs.com/aspirant/p/9088322.html)

### 配置
分布式配置
* 不同环境的不同配置
* 不用重新打包发布
* 自动更新配置文件信息

| 注册中心 				| 配置存储 		| 时效性 				|
| ------ 				| ------ 		| ------ 				|
| disconf 				| zookpeer 		| 实时推送 				| 
| zookpeer 				| Slytherin 	| 实时推送 				|
| diamond 				| mysql			| 每隔15s拉一次全量数据	|
| Spring Cloud Config	| git 			| 人工批量刷新 			|
| consul	|  			|  			|

### 监控
传统的开源监控系统代表有：Cacti、Nagios、Zabbix等
先进的开源时间序监控系统代表有：OpenTSDB、Open-falcon、Prometheus等

### 网关
网关的作用，后端服务器可以专心处理业务请求，节省了大量连接管理的开销

[api-gateway](https://www.cnblogs.com/savorboard/p/api-gateway.html)

API网关是一个服务器，是系统的唯一入口。从面向对象设计的角度看，它与外观模式类似。API网关封装了系统内部架构，
为每个客户端提供一个定制的API。它可能还具有其它职责，如身份验证、监控、负载均衡、缓存、请求分片与管理、静态响应处理。

API网关方式的核心要点是，所有的客户端和消费端都通过统一的网关接入微服务，在网关层处理所有的非业务功能。通常，
网关也是提供REST/HTTP的访问API。服务端通过API-GW注册和管理服务。

### 链路
链路追踪、链路压测

- Google Dapper
- Twitter Zipkin
- Apache HTrace
- pinpoint

### 日志
Elasticsearch , Logstash, Kibana 

[elk](https://www.cnblogs.com/aresxin/p/8035137.html) 

建立集中式日志收集系统，将所有节点上的日志统一收集，管理，访问。

* LogStash/Beats: 负责数据的收集与处理
* ElasticSearch: 一个开源的分布式搜索引擎，负责数据的存储、检索和分析
* Kibana: 提供了可视化的界面（ Web界面）。负责数据的可视化操作