《Netty权威指南 第2版》 李林锋
- Java高性能NIO通信首选框架
- 大数据时代构建高可用分布式系统利器

李林锋，华为技术有限公司平台中间件架构与设计部设计师。

* 基础篇 走进Java NIO  bio、nio、aio
  * 入门篇 NettyNIO开发指南 netty入门应用、TCP粘包拆包、分隔符和定长符解码器
  * 中级篇 Netty编解码开发指南 编解码、messagePack、GoogleProtobuf、JBossMarshalling
  * 高级篇 netty多协议开发和应用 http、websocket、私有协议
* 源码篇 
  * ByteBuff
  * Channel、Unsafe
  * ChannelPipeline和ChannelHandler
  * EventLoop和EventLoopGroup
  * Future和Promise
* 高级特性 架构剖析、Java多线程应用、高性能之道、可靠性、安全性

# 基础篇 

## 第1章 Java的I/O演进之路
### 1.1 IO基础入门
#### 1.1.1 Linux网络IO模型
应用程序进程与内核进程交互，两个阶段
1. 系统调用，等待数据准备就绪
2. 数据包到达，数据从内核复制到用户空间

Unix网络编程5种I/O模型
* 阻塞模型 recvfrom系统调用直到**数据报到达**并且被复制到**应用进程缓冲区**中或者发生错误才返回，此期间一直会**等待**。
* 非阻塞模型 recvfrom从应用层到内核，如果该缓冲区没有数据的话，直接返回一个错误，**轮询检查这个状态**，看内核是不是有数据到来
* IO复用模型 
  - select/poll，一个或多个fd传递给select或poll系统调用，阻塞在select操作上，侦测多个fd是否处于就绪状态。select/poll顺序扫描fd是否就绪，支持的fd数量有限。
  - epoll，基于事件驱动方式代替顺序扫描，有fd就绪时，立即回调函数rollback
* 信号驱动IO模型 首先开启套接口**信号驱动IO**功能，并通过系统调用sigaction执行一个信号处理函数（此系统调用立即返回，进程继续工作，它是非阻塞的）。
  当数据准备就绪时，就为该进程生成一个signal信号，通过信号回调通知应用程序调用recvfrom来读取数据，并通知主循环函数处理数据。
* 异步IO 告知内核启动某个操作，并让内核在整个操作完成后（包括将数据从内核复制到用户自己的缓冲区）通知我们。
  这种模型与信号驱动模型的主要区别是：信号驱动IO由内核通知我们何时可以开始一个IO操作；异步IO模型由内核通知我们IO操作何时已经完成。

#### 1.1.2 IO多路复用技术
通过把多个IO的阻塞复用到同一个select的阻塞上，从而使得系统在单线程的情况下可以处理多个客户端请求。

epoll的改进：
1. 支持一个进程打开的socket描述符不受限制（仅受限于操作系统的最大文件句柄数）；
2. IO效率不会随着FD数量的增加而线性下降；
3. 使用mmap加速内核与用户空间的消息传递。通过内核和用户空间mmap同一块内存来实现；
4. epoll的API更加简单

mmap()系统调用使得进城之间通过映射同一个普通文件实现共享内存。
## 第2章 NIO入门
### 2.1 传统的BIO编程
* 一个独立的Acceptor线程负责监听客户端连接
* 它为每个连接请求创建一个新的线程进行链路处理，处理完成之后，通过输出流返回应答给客户端
  
### 2.2 伪异步I/O编程
将客户端的Socket封装成一个task投递到后端的线程池中处理。

* Acceptor阻塞的，一个客户端阻塞，影响后续请求
* 流是阻塞的
* 线程池里的阻塞队列

### 2.3 NIO编程
* 缓冲区Buffer 一个对象，包含一些要写入或者读出的文件。实质上是数组。
* 通道Channel ServerSocketChannel、SocketChannel 双向、读写可同时进行
* Selector 
  * 不断轮询注册在其上的Channel
  * 如果某个Channel发生读写事件，Channel就处于就绪状态，会被Selector轮询出来。
  * 通过selectionKey可以获取就绪的Channel集合，进行后续的IO操作

### 2.4 AIO编程
* 通过`Future`类表示异步操作的结果
* 执行异步操作时候传入一个java.nio.channels
* `Completionhandler`接口的实现类作为操作完成的回调，对应unix网络编程中的事件驱动IO（AIO），不需要多路复用器（Selector）。

```java
public interface Future<V> {
    boolean cancel(boolean mayInterruptIfRunning);
    boolean isCancelled();
    boolean isDone();
    V get() throws InterruptedException, ExecutionException;
    V get(long timeout, TimeUnit unit)
        throws InterruptedException, ExecutionException, TimeoutException;
}
public interface CompletionHandler<V,A> {
    void completed(V result, A attachment);
    void failed(Throwable exc, A attachment);
}
```
### 2.5 4中IO的对比

|                  | 同步阻塞I/O | 伪异步I/O | 非阻塞I/O     | 异步I/O                              |
| :--------------- | :---------- | :-------- | :------------ | :----------------------------------- |
| 客户端 : I/O线程 | 1:1         | M:N       | M:1           | M:0(不需要启动额外I/O线程，异步回调) |
| I/O类型（阻塞）  | 阻塞        | 阻塞      | 非阻塞        | 非阻塞                               |
| I/O类型（同步）  | 同步        | 同步      | 同步-多路复用 | 异步                                 |

# 入门篇 NettyNIO 开发指南
## 第3章 Netty入门应用
## 第4章 TCP粘包/拆包问题解决之道
### 4.1 TCP粘包/拆包
TCP协议，面向连接、面向流的端到端的全双工的可靠传输协议。
面向流，没有界限的字节流TCP底层并不了解上层业务数据的具体含义，它会根据TCP缓冲区的实际情况进行**包的划分**。
在业务上认为，一个完整的包可能被TCP拆分成多个包进行发送，也有可能把多个小的包封装成一个大的数据包发送。

#### 4.1.2 TCP粘包/拆包发生的原因
1. 应用程序write写入的字节大小大于套接口发送缓冲区大小
2. 进行MSS大小的TCP分段
3. 以太网帧的payload大于MTU进行IP分片

概念：
* MSS：Maximum Segment Size ，**TCP提交给IP层**最大分段大小，不包含TCP Header和 TCP Option，只包含TCP Payload ，MSS是TCP用来限制application层最大的发送字节数。如果底层物理接口MTU= 1500 byte，则 MSS = 1500- 20(IP Header) -20 (TCP Header) = 1460 byte，如果application 有2000 byte发送，需要两个segment才可以完成发送，第一个TCP segment = 1460，第二个TCP segment = 540。
* payload 有效载荷，指除去协议首部之外实际传输的数据
* MTU： Maximum Transmit Unit，最大传输单元，即**物理接口（数据链路层）提供给其上层（通常是IP层）**最大一次传输数据的大小；以普遍使用的以太网接口为例，缺省MTU=1500 Byte，这是以太网接口对IP层的约束，如果IP层有<=1500 byte 需要发送，只需要一个IP包就可以完成发送任务；如果IP层有> 1500 byte 数据需要发送，需要分片才能完成发送，这些分片有一个共同点，即IP Header ID相同。

#### 4.1.3 粘包问题的解决策略
由于底层的TCP协议无法理解上层的业务数据，所以在底层无法保证数据包不被拆分和重组，只能通过上层的应用层协议栈设计解决。
1. 消息定长，如空位补空格
2. 包尾增加回车换行符进行分割
3. 消息分为消息头和消息体，消息头中包含表示消息的总长度的字段
4. 更复杂的应用层协议

### 4.3 利用LineBasedFrameDecoder解决TCP粘包问题
#### 4.3.4 LineBasedFrameDecoder和StringDecoder的原理分析
* LineBasedFrameDecoder 依次遍历ByteBuf的可读字节，`\n`或者`\r\n`就以此位置为结束为hi
* StringDecoder 接收到的对象转换成字符串，然后继续调用后面的Handler

## 第5章 分隔符和定长解码器的应用
TCP以流的方式进行数据传输，上层的应用协议为了对消息进行区分，采用4种方式：
1. 消息长度固定，累计读取到长度综合为定长LEN的报文后，就认为读取到了一个完整的消息；将计数器置位，重新读取下一个数据报
2. 将回车换行符作为消息结束符，例如FTP
3. 将特殊的分隔符作为消息的结束标志，回车换行符就是一种特殊的结束分隔符
4. 通过在消息头中定义长度字段来表示消息的总长度

### 5.1 DelimiterBasedFrameDecoder应用开发
```java
ByteBuf delimiter = Unpooled.copiedBuffer("$_").getBytes();
SocketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, delimiter))
```
### 5.2 FixedLengthFrameDecoder应用开发
```java
new FixedLengthFrameDecoder(20);
```

# 中级篇 Netty 编解码开发指南
## 第6章 编解码技术
Java序列化的目的：网络传输、对象持久化。
### 6.1 Java序列化的缺点
#### 6.1.1 无法跨语言
#### 6.1.2 序列化后的码流太大
#### 6.1.3 序列化性能太低
### 6.2 业界主流的编解码框架
#### 6.2.1 Google的Protobuf介绍
将数据结构以.proto文件进行描述，通过代码生成工具可以生成对应的数据结构POJO对象和Protobuf相关的方法和操作
* 结构化数据存储格式
* 高小的编解码性能
* 语言无关、平台无关、扩展性好
* 官方支持Java、C++和Python三种语言

XML，可读性和可扩展非常好，但是XML解析时间开销和XML为了可读性而牺牲的空间开销都非常大，不适合高性能的通信协议。
#### 6.2.2 Facebook的Thrift介绍
Thrift主要又5部分组成
1. 语言系统以及IDL编译器
2. TProtocol：RPC的协议层
3. TTransport：RPC的传输层
4. TProcessor：作为协议层和用户提供的服务实现之间的纽带
5. TServer：聚合TProtocol、TTransport和Tprocessor等对象

Thrift通过IDL描述接口和数据结构定义，它支持8种Java基本类型、Map、Set和List，支持可选和必选定义。
支持三种地拿一下呢的编解码方式：通用的二进制编解码、压缩二进制编解码、优化的可选字段压缩编解码

#### 6.2.3 JBoss Marshalling介绍
优点
* 可插拔的类解析器
* 可插拔的对象替换技术，不需要通过继承的方式
* 可插拔的预定义类缓存表
* 无需事先java.io.Serializable接口
* 通过缓存技术提升对象的序列化性能

## 第7章 MessagePack编解码
### 7.1 MessagePack介绍
### 7.2 MessagePack编码器和解码器开发
### 7.3 粘包/半包支持


## 第8章 Google Protobuf编解码
跨语言、结构化的数据序列化框架。
### 8.1 Protobuf的入门
### 8.2 Netty的Protobuf服务端开发
### 8.3 Protobuf的使用注意事项
仅负责解码，不支持读半包。
使用Netty提供的ProtobufVarint32FrameDecoder

## 第9章 JBoss Marshalling编解码
与java.io.Serializable接口兼容，增加可调参数和附加特性。
### 9.1 Marshalling开发环境准备
### 9.2 Netty的Marshalling服务端开发
### 9.3 Netty的Marshalling客户端开发
### 9.4 运行Marshalling客户端和服务端例程

# 高级篇 Netty 多协议开发和应用
## 第10章 HTTP协议开发应用
### 10.1 HTTP协议介绍
### 10.2 Netty HTTP服务端入门开发
### 10.3 Netty HTTP+XML协议栈开发

## 第11章 WebSocket协议开发
WebSocket的特点
* 单一的TCP连接，此阿勇全双工模式通信
* 对代理、防火墙和路由器透明
* 无头部消息、Cookie和身份验证
* 无安全开销
* 通过 ping/pong帧保持链路激活
* 服务端推送

### 11.1 HTTP协议的弊端
### 11.2 WebSocket入门
### 11.3 Netty WebSocket协议开发


## 第12章 私有协议栈开发
绝大多数的私有协议传输层都是基于TCP/IP，利用Netty的NIO TCP协议栈可以方便的进行定制和开发。
### 12.2 Netty协议栈功能设计
#### 12.2.1 网络拓扑图
#### 12.2.2 协议栈功能描述
#### 12.2.3 通信模型
#### 12.2.4 消息定义
#### 12.2.5 Netty协议支持的字段类型
#### 12.2.6 Netty协议的编解码规范

|            | 描述     | 编码                                  | 解码               |
| :--------- | :------- | :------------------------------------ | :----------------- |
| crcDode    | 校检码   | ByteBuffer.putInt                     | ByteBuffer.getInt  |
| length     | 消息长度 | ByteBuffer.putInt                     | ByteBuffer.getInt  |
| sessionID  |          | ByteBuffer.putLong                    | ByteBuffer.getLong |
| type       |          | ByteBuffer.put(byte)                  | ByteBuffer.get()   |
| priority   |          | ByteBuffer.put(byte)                  | ByteBuffer.get()   |
| attachment | 变长     | ByteBuffer.putInt                     | ByteBuffer.getInt  |
| body       |          | Marshalling序列化为byte[]，写入缓冲区 | Marshalling解码    |

#### 12.2.7 链路的建立
#### 12.2.8 链路的关闭
#### 12.2.9 可靠性设计
1. 心跳机制 空闲时采用心跳机制检测链路互通性
2. 重连机制 如果链路中断，等待internal时间后，由客户端发起重连操作，如果重连失败，间隔再次发起重连，直到重连成功。
3. 重复登录保护
4. 消息缓存重发

#### 12.2.10 安全性设计
基于IP地址的安全认证机制，服务端对握手请求消息的IP地址进行合法性校检
#### 12.2.11 可扩展性设计
attachment字段自定义扩展
### 12.3 Netty协议栈开发
#### 12.3.1 数据结构定义
#### 12.3.2 消息编解码
#### 12.3.3 握手和安全认证
握手的发起是在客户端和服务端TCP链路建立成功通道激活时，握手消息的接入和安全认证在服务端处理。
`LoginAuthReqHandler`
`LoginAuthRespHandler`

#### 12.3.4 心跳检测机制
握手成功之后，由客户端主动发送心跳消息，服务端接收到心跳消息之后，返回心跳应答消息。
由于心跳消息的目的是为了检测链路的可用性，因此不需要携带消息体。
#### 12.3.5 断连重连
当客户端感知断连事件之后，释放资源，重新发起连接。监听断连事件，如果Channel关闭，则执行后续的重连任务。
服务端感知到断连事件之后，需要清空缓存的登录认证注册信息，保证后续客户端能够正常重连。
#### 12.3.6 客户端代码
#### 12.3.7 服务端代码
### 12.4 运行协议栈
* 正常情况 客户端和服务端握手成功，双方可以互发心跳，链路正常
* 服务端宕机
* 客户端宕机 服务端需要能够清除缓存信息，允许客户端重新登录

## 第13章 服务端创建
### 13.1 原生NIO类库的复杂性
### 13.2 Netty服务端创建源码分析
#### 13.2.1 Netty服务端的创建时序图
```mermaid
sequenceDiagram
    Actor->>ServerBootstrap: 1.创建ServerBootstrap实例
    ServerBootstrap->>EventLoopGroup: 2. 设置并绑定Reactor线程池
    EventLoopGroup->>NioServerSocketChannel: 3. 设置并绑定服务端Channel
    EventLoopGroup->>ChannelPipeline: 4. TCP链路建立时创建ChannelPipeline

    ServerBootstrap->>ChannelHandler: 5. 添加并设置ChannelHandler
    ServerBootstrap->>ServerBootstrap: 6. 绑定监听端口并启动服务端

    EventLoopGroup->>EventLoopGroup: 7. Selector轮询
    EventLoopGroup->>ChannelPipeline: 8. 网络事件通知
    ChannelPipeline->>ChannelHandler: 9. 执行Netty系统和业务HandlerChannel
```
### 13.3 客户端接入源码分析

## 第14章 客户端创建
### 14.1 Netty客户端创建流程分析
#### 14.1.1 Netty客户端的创建时序图
```mermaid
sequenceDiagram
    Actor->>Bootstrap: 1.创建Bootstrap实例
    Bootstrap->>NioEventLoopGroup: 2. 构造IO线程组
    EventLoopGroup->>NioSocketChannel: 3. 创建NioSocketChannel
    NioSocketChannel->>ChannelPipeline: 4. 创建默认的DefaultPipeLine

    NioSocketChannel->>NioSocketChannel: 5. 异步发起TCP连接
    NioSocketChannel->>EventLoopGroup: 6. 注册连接操作位到多路复用器

    EventLoopGroup->>EventLoopGroup: 7. 批量连接结果事件
    EventLoopGroup->>ChannelPipeline: 8. 发送连接成功事件
    ChannelPipeline->>ChannelHandler: 9. 调用用户ChannelHandler
```
### 14.2 Netty客户端创建源码分析
#### 14.2.1 客户端连接辅助类Bootstrap
#### 14.2.2 客户端连接操作
#### 14.2.3 异步连接结果通知
#### 14.2.4 客户端连接超时机制

# 源码分析篇 Netty 功能介绍和源码分析
## 第15章 ByteBuf和相关辅助类

## 第16章 Channel和Unsafe
### 16.1 Channel功能说明
io.netty.channel.Channel是Netty网络操作抽象类，聚合了一组功能，包括但不限于网络的读、写，客户端发起连接，主动关闭连接，链路关闭，
获取同学双方的网络地址等。也包含了Netty框架相关的一些功能，包括获取该Channel的EventLoop，获取缓分配器ByteBufAllocator和pipeline等。
#### 16.1.2 Channel的功能介绍
1. 网络IO操作
2. 其他常用的API功能说明

### 16.2 Channel源码分析
### 16.3 Unsafe功能说明
实际的IO读写操作都是由Unsafe接口负责完成的。
### 16.4 Unsafe源码分析

## 第17章 ChannelPipeline和ChannelHandler
Netty的ChannelPipeline和ChannelHandler机制类似于Servlet和Filter过滤器，这类拦截器实际上是职责链模式的一种变形。
### 17.1 ChannelPipeline功能说明
ChannelPipeline是ChannelHandler的容器，它负责ChannelHandler的管理和事件拦截与调度。
#### 17.1.1 ChannelPipeline的事件处理
#### 17.1.2 自定义拦截器
ChannelPipeline通过ChannelHandler接口来实现时间的拦截和处理。
#### 17.1.3 构建pipeline
### 17.2 ChannelPipeline源码分析
实际上是一个ChannelHandler的容器，内部维护了一个ChannelHandler的链表和迭代器，可以方便地实现ChannelHandler查找、添加、替换和删除。
### 17.3 ChannelHandler功能说明
类似于Servlet的Filter过滤器，负责对IO时间或者IO操作进行拦截和处理，可以选择性地拦截和处理自己感兴趣的时间，也可以透传和终止事件的传递。
基于ChannelHandler接口，用户可以方便地进行业务逻辑定制，例如打印日志、统一异常信息、性能统计和消息编解码等。
#### 17.3.1 ChannelHandlerAdapter功能说明 
### 17.4 ChannelHandler源码分析

## 第18章 EventLoop和EventLoopGroup
I/O线程模型
### 18.1 Netty线程模型
#### 18.1.1 Reactor单线程模型
* Acceptor线程
* IO线程

#### 18.1.2 Reactor多线程模型
* Acceptor线程
* 一组IO线程池

#### 18.1.3 主从Reactor多线程模型
* Acceptor线程池 客户端的登录、握手、安全认证，一旦链路建立成功，就将两路注册到后端subReactor线程池
* IO线程池

#### 18.1.4 Netty的线程模型
接收客户端请求的线程池的职责：
1. 接收客户端TCP连接，初始化Channel参数
2. 将链路状态变更事件通知给ChannelPipeline

IO线程池职责：
1. 异步读取通信对端的数据报，发送读事件到ChannelPipeline
2. 异步发送消息到通信对端，调用ChannelPipeline的消息发送接口
3. 执行系统调用Task
4. 执行定时任务Task，例如链路空闲状态监测定时任务

### 18.2 NioEventLoop源码分析
* 负责IO的读写
* 系统Task，NioEventLoop.execute(Runnable task)
* 定时任务，NioEventLoop.schedule(Runnable command, long delay, TimeUnit unit)

## 第19章 Future和Promise
### 19.1 Future功能
用于代表异步操作的结果。
* get() 获取异步操作的结果，操作尚未完成会阻塞当前调用的线程；get(long, TimeUnit)
* isDone() 
* cancel() 尝试取消异步操作，结果未知。

### 19.2 ChannelFuture源码分析
### 19.3 Promise功能介绍
Promise是可写的Future，用于设置IO操作的结果。
### 19.4 Promise源码分析
### 19.5 总结
* 本质上都是异步I/O操作结果的通知回调类。
* JDK Future-Listener机制 当一个线程执行结束的时候，通知注册的所有listener同步执行回调功能
* 通过增加监听器Listener的方式接收异步IO操作结果的通知，而不是调用wait或者sync阻塞用户线程

# 架构和行业应用篇 Netty高级特性
架构、性能、可靠性、安全性
## 第20章 Netty架构剖析
### 20.1 Netty逻辑架构
#### 20.1.1 Reactor通信调度层
* Reactor线程NioEventLoop及其父类，
* NioSocketChannel/NIOServerSocketChannel及其父类，
* ByteBuffer及其衍生出来的各种Buffer，
* Unsafe及其各种内部类。

该层主要职责就是监听网络的读写和连接操作，负责将网络层的数据读取到内存缓冲区中，
然后触发各种网络事件，例如连接创建、连接激活、读事件、写事件，
将这些事件触发到pipeline中由pipeline管理的职责链来进行后续的处理。
#### 20.1.2 职责链ChannelPipeline
负责事件在职责链中的有序传播，同时负责动态地编排职责链。
职责链可以选择监听和处理自己关心的事件，它可以拦截处理和向后/向前传播事件。
#### 20.1.3 业务逻辑编排层（Service ChannelHandler）

### 20.2 关键架构质量属性
#### 20.2.1 高性能
#### 20.2.2 可靠性
1. 链路有效性检测
2. 内存保护机制
3. 优雅停机

优雅停机指的是当系统退出时，JVM通过注册的ShutdownHook拦截到退出信号量，
然后执行退出操作，释放相关模块的资源占用，将缓冲区的消息处理完成或者清空，将待刷新的数据持久化到磁盘或者数据库中，
等到资源回收和缓冲区消息处理完成之后，再退出。
#### 20.2.3 可定制性
* 责任链模型
* 基于接口的开发
* 提供大量的工厂类
* 提供大量的系统参数供用户按需设置

#### 20.2.4 可扩展性

## 第21章 Java多线程编程在Netty中的应用
### 21.1 Java内存模型与多线程编程
#### 21.1.1 硬件的发展和多任务处理
#### 21.1.2 Java内存模型
1. 工作内存和主内存
2. Java内存交互协议 lock unlock read load use assign store write
3. Java的线程 Java的采用内核线程实现

### 22.2 Netty的并发编程实践
#### 22.2.1 对共享的可变数据进行正确的同步
synchronized
#### 22.2.2 正确的使用锁
#### 22.2.3 volatile的正确使用
#### 22.2.4 CAS指令和原子类
#### 22.2.5 线程安全类的应用
* Executor Framework
* 并发集合 
* 同步器
* 原子包装类

#### 22.2.6 读写锁的应用
轻量级细粒度的锁，提升并发访问性能和吞吐量，读多写少的场景下。
#### 22.2.7 线程安全性文档说明
#### 22.2.8 不要依赖线程优先级

## 第22章 高性能之道
### 22.1 RPC调用性能模型分析
#### 22.1.1 传统RPC调用性能差
1. 网络传输方式 同步阻塞IO
2. 序列化性能
3. 线程模型 一对一模型，一个请求一个线程

### 22.2 Netty高性能之道
#### 22.2.1 异步非阻塞通信
#### 22.2.2 高效的Reactor线程模型
1. Reactor单线程模型
2. Reactor多线程模型
3. 主从Reactor多线程模型

#### 22.2.3 无锁化的串行设计
* 串行化设计 消息的处理尽可能在同一个线程内完成，期间不进行线程切换，这样就避免了多线程竞争和同步锁。
启动多个串行化的线程并行运行。局部无锁化的串行设计比“一队列--多个工作线程”模型性能更优。

NioEventLoop读取到消息之后，直接调用ChannelPipeline的fireChannelRead(Object msg)，只要用户不主动切换线程，
一直会有NioEventLoop调用到用户的Handler，期间不进行线程切换。

#### 22.2.4 高效的并发编程
1. volatile的大量、正确使用
2. cas和原子类的广泛应用（无锁并发设计）
3. 线程安全容器的使用
4. 通过**读写锁**提升并发性能

#### 22.2.5 高性能的序列化框架
1. 序列化后的码流大小（网络宽带的占用）
2. 序列化&反序列化的性能（CPU资源占用）
3. 是否支持跨语言（异构系统的对接和开发语言切换）

#### 22.2.6 零拷贝
1. ByteBuffer 采用direct buffers，使用堆外直接内存进行socket读写，不需要进行字节缓冲区的二次拷贝。
heap buffers，jvm将堆内存buffer拷贝一份到直接内存中，然后才写入socket。
2. CompositeByteBuf 将多个byteBuf封装成一个ByteBuf，对外提供统一封装后的ByteBuf接口
3. DefaultFileRegion 通过transferTo方法将文件发送到目标Channel中。

#### 22.2.7 内存池
堆外内存的回收耗时。Netty提供基于内存池的缓冲区重用机制。
#### 22.2.8 灵活的TCP参数配置能力
1. SO_RECVBUF SO_SNDBUF
2. SO_TCPNODELAY
3. 软中断

### 22.3 主流NIO框架性能对比

## 第23章 可靠性
### 23.1 可靠性需求
#### 23.1.2 Netty可靠性需求
Netty应用场景：
1. rpc框架的基础网络通信框架：主要用于分布式节点之间的通信和数据交换 dubbo, rocketmq, hadoop, avro
2. 私有协议的基础通信框架 thrift, dubbo
3. 共有协议的基础通信框架 http, smpp

### 23.2 高可靠性设计
#### 23.2.1 网络通信类故障
#### 23.2.2 链路的有效性检测
#### 23.2.3 Reactor线程的保护
#### 23.2.4 内存保护
#### 23.2.5 流量整形
#### 23.2.6 优雅停机接口

## 第24章 安全性
### 24.2 Netty SSL 安全特性
#### 24.2.1 SSL 单向认证
客户端只验证服务端的合法性，服务端不验证客户端。
#### 24.2.2 SSL 双向认证
服务端也对客户端进行安全认证。这就意味着客户端的自签名证书也需要导入到服务端的数字证书仓库中。
#### 24.2.3 第三方CA认证
### 24.3 Netty SSL 源码分析
### 24.4 Netty 扩展的安全特性
#### 24.4.1 IP地址黑名单机制
链路注册、链路激活、消息读取、消息发送的时候对对端的IP地址进行校检，如果在黑名单列表中，则拒绝当前操作，并关闭链路，打印日志。
#### 24.4.2 接入认证

## 第25章 Netty未来展望

## 附录 Netty参数配置表

| key                                   | desc                                              |
| :------------------------------------ | :------------------------------------------------ |
| io.netty.selectorAutoRebuildThreshold | 重建selector阈值，修复JDK NIO死循环问题，默认512  |
| io.netty.threadLocalDirectBufferSize  | 线程本地变量直接内存缓冲区大小，默认64KB          |
| io.netty.eventLoopThreads             | Reactor线程NioEventLoop的格式，默认值 CPU个数 * 2 |
| io.netty.noPreferDirect               | 是否允许通过底层API直接访问直接内存，默认允许     |
| io.netty.noUnsafe                     | 是否允许使用sun.misc.Unsafe，默认允许             |
