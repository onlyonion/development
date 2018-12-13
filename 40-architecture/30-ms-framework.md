
### api gateway
网关服务是单一访问点，并充当多项服务的代理。
服务网关启用了跨所有服务的路由转发、过滤和公共处理等。
在微服务实践中远不止这点功能，它可以做到统一接入、流量管控、安全防护、业务隔离等功能。

服务网关 = 路由转发 + 过滤器

[服务网关](https://www.cnblogs.com/java-zhao/p/6716059.html)



***

轻量级的服务网关，技术选型如下：

* 开发语言：java + groovy，groovy的好处是网关服务不需要重启就可以动态的添加filter来实现一些功能；
* 微服务基础框架：springboot；
* 网关基础组件：netflix zuul；
* 服务注册中心：consul；
* 权限校验：jwt；
* API监控：prometheus + grafana；
* API统一日志收集：logback + ELK；
* 压力测试：Jmeter；