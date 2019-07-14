
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