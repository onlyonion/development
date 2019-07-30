netty中的设计模式
## 对象池

## Reactor模式的使用

Netty 底层事件的收发机制是多线程的 Reactor 模式的应用。
reactor设计模式，是一种基于事件驱动的设计模式。
Reactor框架是ACE各个框架中最基础的一个框架，其他框架都或多或少地用到了Reactor框架。 
在事件驱动的应用中，将一个或多个客户的服务请求分离（demultiplex）和调度（dispatch）给应用程序。
在事件驱动的应用中，同步地、有序地处理同时接收的多个服务请求。 
reactor模式与外观模式有点像。不过，观察者模式与单个事件源关联，而反应器模式则与多个事件源关联 。
当一个主体发生改变时，所有依属体都得到通知。

## 工厂模式

## 单

## 适配器

## 装饰器

## 代理

## 策略模式
DefaultEventExecutorChooserFactory 

## 模板方法
ServerBootstrap 和 Bootstrap 继承 AbstractBootstrap 父类抽象类，并实现init() 和clone()方法。

## 观察者模式
netty中常用到观察者模式的就是异步处理，例如：用于处理网络链接完成的监听：
抽象主题角色是ChannelPromise， 抽象观察者角色是ChannelFutureListener，
具体主题角色是channel生成的DefaultChannelPromise，具体观察者角色是此处的匿名内部类。
在netty中使用观察者模式，可以充分利用NioEventLoopGroup这一线程池的资源，避免启动线程阻塞在网络链接上。

## 迭代器模式
AbstractByteBuf.forEachByte()

netty里面的CompositeByteBuf这个零拷贝的实现，就使用了迭代器模式

## 职责链
pipeline 上事件的传播；**流水线**
