
## 微服务由来

* 一套小服务来开发单个应用的方式途径
* 使用轻量级机制通信，通常是HTTP API **通用传输协议**
* 不同的编程语言实现 **跨语言**
* 不同数据存储技术 **跨数据库**

SOA 总线模式，与某种技术栈强绑定的，J2EE
单体架构 规模比较小的情况下工作情况良好，但是随着系统规模的扩大，它暴露出来的问题也越来越多

## 微服务与SOA区别
微服务，从本质意义上看，还是 SOA 架构。

* 微服务并不绑定某种特殊的技术（编程语言）
* Restful架构风格统
* 扩展性强

## 微服务本质

松耦合、高内聚、统一的整体

* 统一的基础的架构 松耦合、统一的整体
* 目的是有效的拆分应用，实现敏捷开发和部署
* inter-operate, not integrate inter-operate是定义好系统的边界和接口，在一个团队内全栈，让团队自治

## 微服务设计原则

* 单一职责原则
* 服务自治原则
* 轻量级通信原则
* 接口明确原则

## 微服务开发框架

* Spring Cloud：http://projects.spring.io/spring-cloud（现在非常流行的微服务架构）
* Dubbo：http：//dubbo.io
* Dropwizard：http://www.dropwizard.io （关注单个微服务的开发）
* Consul、etcd&etc.（微服务的模块）

## Sprint cloud 和 Sprint boot区别
* Spring Boot:
旨在简化创建产品级的Spring应用和服务，简化了配置文件，使用嵌入式web服务器，含有诸多开箱即用微服务功能，可以和spring cloud联合部署。
* Spring Cloud：
微服务工具包，为开发者提供了在分布式系统的配置管理、服务发现、断路器、智能路由、微代理、控制总线等开发工具包。


[摘自-什么是微服务](https://blog.csdn.net/wuxiaobingandbob/article/details/78642020?locationNum=1&fps=1)
