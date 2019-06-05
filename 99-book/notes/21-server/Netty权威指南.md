《Netty权威指南 第2版》 李林锋
- Java高性能NIO通信首选框架
- 大数据时代构建高可用分布式系统利器

李林锋，华为技术有限公司平台中间件架构与设计部设计师。

* 基础篇 走进Java NIO  bio、nio、aio
* 入门篇 NettyNIO开发指南 netty入门应用、TCP粘包拆包、分隔符和定长符解码器
* 中级篇 Netty编解码开发指南 编解码、messagePack、GoogleProtobuf、JBossMarshalling
* 高级篇 netty多协议开发和应用 http、websocket、私有协议
* 源码篇 ByteBuff、Channel、Unsafe、ChannelPipeline和ChannelHandler、EventLoop和EventLoopGroup、Future和Promise
* 高级特性 架构剖析、Java多线程应用、高性能之道、可靠性、安全性

************************************************************************************************************************

# 基础篇 

## 第1章 Java的I/O演进之路
### 1.1 IO基础入门
#### 1.1.1 Linux网络IO模型
应用程序进程与内核进程交互，两个阶段
1. 系统调用，等待数据准备就绪
2. 数据包到达，数据从内核复制到用户空间

Unix网络编程5种I/O模型
* 阻塞模型 recvfrom系统调用直到数据报到达并且被复制到应用进程缓冲区中或者发生错误才返回，此期间一直会**等待**。
* 非阻塞模型 recvfrom从应用层到内核，如果该缓冲区没有数据的话，直接返回一个错误，**轮询检查这个状态**，看内核是不是有数据到来
* IO复用模型 
  - select/poll，一个或多个fd传递给select或poll系统调用，阻塞在select操作上，侦测多个fd是否处于就绪状态。select/poll顺序扫描fd是否就绪，支持的fd数量有限。
  - epoll，基于事件驱动方式代替顺序扫描，有fd就绪时，立即回调函数rollback
* 信号驱动IO模型 首先开启套接口信号驱动IO功能，并通过系统调用sigaction执行一个信号处理函数（此系统调用立即返回，进程继续工作，它是非阻塞的）。
  当数据准备就绪时，就为该进程生成一个sigio信号，通过信号回调通知应用程序调用recvfrom来读取数据，并通知主循环函数处理数据。
* 异步IO 告知内核启动某个操作，并让内核在整个操作完成后（包括将数据从内核复制到用户自己的缓冲区）通知我们。
  这种模型与信号驱动模型的主要区别是：信号驱动IO由内核通知我们何时可以开始一个IO操作；异步IO模型由内核通知我们IO操作何时已经完成。

#### 1.1.2 IO多路复用技术
通过把多个IO的阻塞复用到同一个select的阻塞上，从而使得系统在单线程的情况下可以处理多个客户端请求。

epoll的改进：
1. 支持一个进程打开的socket描述符不受限制（仅受限于操作系统的最大文件句柄数）；
2. IO效率不会随着FD数量的增加而线性下降；
3. 使用mmap加速内核与用户空间的消息传递。通过内核和用户控件mmap同一块内存来实现；
4. epoll的API更加简单

## 第2章 NIO入门
### 2.1 传统的BIO编程
### 2.2 伪异步I/O编程
### 2.3 NIO编程
### 2.4 AIO编程
* 通过Future类表示异步操作的结果
* 执行异步操作时候传入一个java.nio.channels
* Completionhandler接口的实现类作为操作完成的回调
对应unix网络编程中的事件驱动IO（AIO），不需要多路复用器（Selector）。

```java

public interface CompletionHandler<V,A> {
    void completed(V result, A attachment);
    void failed(Throwable exc, A attachment);
}

```

# 入门篇 NettyNIO 开发指南
## 第3章 Netty入门应用
## 第4章 TCP粘包/拆包问题解决之道
### 4.1 TCP粘包/拆包
1. TCP协议，面向连接、面向流的端到端的全双工的可靠传输协议
2. 面向流，没有界限的字节流TCP底层并不了解上层业务数据的具体含义，它会根据TCP缓冲区的实际情况进行包的划分
3. 在业务上认为，一个完整的包可能被TCP拆分成多个包进行发送，也有可能把多个小的包封装成一个大的数据包发送。

#### 4.1.3 粘包问题的解决策略
由于底层的TCP协议无法理解上层的业务数据，所以在底层无法保证数据包不被拆分和重组，只能通过上层的应用层协议栈设计解决。
1. 消息定长，如空位补空格
2. 包尾增加回车换行符进行分割
3. 消息分为消息头和消息体，消息头中包含表示消息的总长度的字段
4. 更复杂的应用层协议

## 第5章 分隔符和定长解码器的应用


************************************************************************************************************************

# 中级篇 Netty 编解码开发指南
## 第6章 编解码技术
### 6.1 Java序列化的缺点
#### 6.1.1 无法跨语言
#### 6.1.2 序列化后的码流太大
#### 6.1.3 序列化性能太低

## 第7章 MessagePack编解码
## 第8章 Google Protobuf编解码
## 第9章 JBoss Marshalling编解码

************************************************************************************************************************

# 高级篇 Netty 多协议开发和应用
## 第10章 HTTP协议开发应用

## 第11章 WebSocket协议开发

## 第12章 私有协议栈开发
绝大多数的私有协议传输层都是基于TCP/IP，利用Netty的NIO TCP协议栈可以方便的进行定制和开发。

## 第13章 服务端创建
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

## 第14章 客户端创建
#### 14.2.1 Netty客户端的创建时序图
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



************************************************************************************************************************

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
### 17.1 ChannelPipeline功能说明
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

#### 18.1.3 诸多Reactor多线程模型
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


## 第19章 Future和Promise
### 19.1 Future功能
用于代表异步操作的结果。
* get() 获取异步操作的结果，操作尚未完成会阻塞当前调用的线程；get(long, TimeUnit)
* isDone() 
* cancel() 尝试取消异步操作，结果未知。

### 19.2 ChannelFuture源码分析
### 19.3 Promise功能介绍
Promise是可写的Future，用于设置IO操作的结果
### 19.4 Promise源码分析
### 19.5 总结
* 本质上都是异步I/O操作结果的通知回调类。
* JDK Future-Listener机制 当一个线程执行结束的时候，通知注册的所有listener同步执行回调功能
* 通过增加监听器Listener的方式接收异步IO操作结果的通知，而不是调用wait或者sync阻塞用户线程



************************************************************************************************************************

# 架构和行业应用篇 Netty 高级特性

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
2. cas和原子类的广泛应用
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
