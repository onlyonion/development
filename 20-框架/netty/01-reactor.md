
连接、io读、io写

* 单线程 reactor
它是由一个线程来接收客户端的连接，并将该请求分发到对应的事件处理 handler 中，整个过程完全是异步非阻塞的；并且完全不存在共享资源的问题。所以理论上来说吞吐量也还不错。

但由于是一个线程，对多核 CPU 利用率不高，一旦有大量的客户端连接上来性能必然下降，甚至会有大量请求无法响应。
最坏的情况是一旦这个线程哪里没有处理好进入了死循环那整个服务都将不可用！

* 多线程 reactor

其实最大的改进就是将原有的事件处理改为了多线程。
可以基于 Java 自身的线程池实现，这样在大量请求的处理上性能提示是巨大的。
虽然如此，但理论上来说依然有一个地方是单点的；那就是处理客户端连接的线程。
因为大多数服务端应用或多或少在连接时都会处理一些业务，如鉴权之类的，当连接的客户端越来越多时这一个线程依然会存在性能问题。

* 主从多线程 reactor

该模型将客户端连接那一块的线程也改为多线程，称为主线程。
同时也是多个子线程来处理事件响应，这样无论是连接还是事件都是高性能的。

* netty
boss Reactor 模型中处理客户端连接的线程池
work Reactor 模型中处理事件的线程池

单线程模型：

```
private EventLoopGroup group = new NioEventLoopGroup();
ServerBootstrap bootstrap = new ServerBootstrap()
                .group(group)
                .childHandler(new HeartbeatInitializer());
```

多线程模型：

```
private EventLoopGroup boss = new NioEventLoopGroup(1);
private EventLoopGroup work = new NioEventLoopGroup();
ServerBootstrap bootstrap = new ServerBootstrap()
                .group(boss,work)
                .childHandler(new HeartbeatInitializer());
```
                
主从多线程：

```
private EventLoopGroup boss = new NioEventLoopGroup();
private EventLoopGroup work = new NioEventLoopGroup();
ServerBootstrap bootstrap = new ServerBootstrap()
                .group(boss,work)
                .childHandler(new HeartbeatInitializer());
```
