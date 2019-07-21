io.netty.bootstrap.ServerBootstrap

## hierachy
```
AbstractBootstrap (io.netty.bootstrap)
    Bootstrap (io.netty.bootstrap)
    ServerBootstrap (io.netty.bootstrap)
```

## define


## reactor

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

### 单线程模型：

```java
private EventLoopGroup group = new NioEventLoopGroup();
ServerBootstrap bootstrap = new ServerBootstrap()
                .group(group)
                .childHandler(new HeartbeatInitializer());
```

### 多线程模型：

```java
private EventLoopGroup boss = new NioEventLoopGroup(1); // 1个boss
private EventLoopGroup work = new NioEventLoopGroup();
ServerBootstrap bootstrap = new ServerBootstrap()
                .group(boss,work)
                .childHandler(new HeartbeatInitializer());
```
                
### 主从多线程：

```java
private EventLoopGroup boss = new NioEventLoopGroup();
private EventLoopGroup work = new NioEventLoopGroup();
ServerBootstrap bootstrap = new ServerBootstrap()
                .group(boss,work)
                .childHandler(new HeartbeatInitializer());
```

## demo
```java
public class Demo {
    public static void main(String[] args) {
        // 服务端代码Serve
        EventLoopGroup pGroup = new NioEventLoopGroup(); //线程组：用来处理网络事件处理（接受客户端连接）
        EventLoopGroup cGroup = new NioEventLoopGroup(); //线程组：用来进行网络通讯读写
            
        //Bootstrap用来配置参数
        ServerBootstrap b = new ServerBootstrap();
        b.group(pGroup, cGroup)
            .channel(NioServerSocketChannel.class) //注册服务端channel
            /**
            * BACKLOG用于构造服务端套接字ServerSocket对象，标识当服务器请求处理线程全满时，
            * 用于临时存放已完成三次握手的请求的队列的最大长度。如果未设置或所设置的值小于1，将使用默认值50。
            * 服务端处理客户端连接请求是顺序处理的，所以同一时间只能处理一个客户端连接，多个客户端来的时候，
            * 服务端将不能处理的客户端连接请求放在队列中等待处理，backlog参数指定了队列的大小
            */
            .option(ChannelOption.SO_BACKLOG, 1024)
            //设置日志
            .handler(new LoggingHandler(LogLevel.INFO))
            .childHandler(new ChannelInitializer<SocketChannel>() {
            protected void initChannel(SocketChannel sc) throws Exception {
                //marshaliing的编解码操作,要传输对象，必须编解码
                sc.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
                sc.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
                //5s没有交互，就会关闭channel
                sc.pipeline().addLast(new ReadTimeoutHandler(5)); 
                sc.pipeline().addLast(new ServerHandler());   //服务端业务处理类
            }
        });
        ChannelFuture cf = b.bind(8765).sync();
        
        cf.channel().closeFuture().sync();
        pGroup.shutdownGracefully();
        cGroup.shutdownGracefully();
    }
}
```

NIO通讯服务端步骤：
1. 创建ServerSocketChannel,为它配置非阻塞模式
2. 绑定监听，配置TCP参数，录入backlog大小等
3. 创建一个独立的IO线程，用于轮询多路复用器Selector
4. 创建Selector，将之前的ServerSocketChannel注册到Selector上，并设置监听标识位SelectionKey.ACCEPT
5. 启动IO线程，在循环体中执行Selector.select()方法，轮询就绪的通道
6. 当轮询到处于就绪的通道时，需要进行判断操作位，如果是ACCEPT状态，说明是新的客户端介入，则调用accept方法接受新的客户端。
7. 设置新接入客户端的一些参数，并将其通道继续注册到Selector之中。设置监听标识等
8. 如果轮询的通道操作位是READ，则进行读取，构造Buffer对象等
9. 更细节的还有数据没发送完成继续发送的问题


Netty实现通讯的步骤：
1. 创建两个NIO线程组，一个专门用来网络事件处理（接受客户端连接），另一个则进行网络通讯读写
2. 创建一个ServerBootstrap对象，配置Netty的一系列参数，例如接受传入数据的缓存大小等。
3. 创建一个实际处理数据的类ChannelInitializer,进行初始化的准备工作，比如设置传入数据的字符集，格式，实现实际处理数据的接口。
4. 绑定端口，执行同步阻塞方法等待服务器启动即可。


[Netty](https://blog.csdn.net/javadhh/article/details/66477423 )