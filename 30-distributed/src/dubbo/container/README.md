## package
```
jetty
log4j
logback
page
spring
Container
Main
```
## Container

生命周期的简单封装，仅有启动、停止方法，对比tomcat和spring生命周期管理、容器概念，dubbo的容器较为简单。

```yuml
// {type:class}

// 容器
[Container||+start();+stop()]

[Container]^-[LogbackContainer]
[Container]^-[Log4jContainer]
[Container]^-[JettyContainer]
[Container]^-[SpringContainer]

[SpringContainer]++-[ClassPathXmlApplicationContext]

```