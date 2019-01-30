
轻量级的服务网关，技术选型如下：

* 开发语言：java + groovy，groovy的好处是网关服务不需要重启就可以动态的添加filter来实现一些功能；
* 微服务基础框架：springboot；
* 网关基础组件：netflix zuul；
* 服务注册中心：consul；
* 权限校验：jwt；
* API监控：prometheus + grafana；
* API统一日志收集：logback + ELK；
* 压力测试：Jmeter；

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


### api gateway
网关服务是单一访问点，并充当多项服务的代理。
服务网关启用了跨所有服务的路由转发、过滤和公共处理等。
在微服务实践中远不止这点功能，它可以做到统一接入、流量管控、安全防护、业务隔离等功能。

服务网关 = 路由转发 + 过滤器

[服务网关](https://www.cnblogs.com/java-zhao/p/6716059.html)

