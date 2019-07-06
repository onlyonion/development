[精通Dubbo——dubbo2.0源码中的设计模式与SPI介绍](https://blog.csdn.net/fuyuwei2015/article/details/72857722)

## 工厂模式
Java SPI

## 装饰器模式
工厂模式的时候我们也提到了装饰器模式，Dubbo 在启动和调用阶段都大量使用了装饰器模式。 

## 责任链模式
Provider 提供的调用链为例，具体的调用链代码是在 ProtocolFilterWrapper 的 buildInvokerChain 完成的，具体是将注解中含 
有 group=provider 的 Filter 实现，按照 order 排序，最后的调用顺序是查看文本打印 

EchoFilter -> ClassLoaderFilter -> GenericFilter -> ContextFilter -> ExceptionFilter -> TimeoutFilter -> MonitorFilter -> TraceFilter

ClassLoaderFilter则只是在主功能上添加了功能，更改当前线程的 ClassLoader，这是典型的装饰器模式

装饰器和责任链模式的混合使用

## 观察者模式

在dubbo provider服务启动时候要向注册中心注册自己的服务，在dubbo consumer向注册中心订阅服务时则是一种观察者模式，他开启了一个listener，
注册中心会每 5 秒定时检查是否有服务更新，如果有更新，向该服务的提供者发送一个 notify 消息， provider 接受到 notify 消息后，
即运行 NotifyListener 的 notify 方法，执行监听器方法。

## 动态代理模式
在代理模式（Proxy Pattern）中，一个类代表另一个类的功能 
Dubbo 扩展 jdk spi 的类 ExtensionLoader 的 Adaptive 实现是典型的动态代理实现。