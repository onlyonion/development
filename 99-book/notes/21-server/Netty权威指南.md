《Netty权威指南 第2版》 李林锋

* 基础篇 bio、nio、aio
* 入门篇 NettyNIO 开发指南
* 中级篇 netty入门应用、编解码
* 高级篇 netty多协议开发，http、websocket、私有协议
* 源码篇 ByteBuff、Channel、Unsafe、ChannelPipeline和ChannelHandler、EventLoop和EventLoopGroup、Future和Promise
* 高级特性 架构剖析、Java多线程应用、高性能之道、可靠性、安全性

# 基础篇 走进Java NIO

## 第1章 Java的I/O演进之路
### 1.1 IO基础入门
#### 1.1.1 Linux网络IO模型
应用程序进程与内核进程交互，两个阶段
1. 系统调用，等待数据准备就绪
2. 数据包到达，数据从内核复制到用户空间

Unix网络编程5种I/O模型
* 阻塞模型 recvfrom系统调用知道数据报到达并且被复制到应用进程缓冲区中或者发生错误才返回，此期间一直会等待。
* 非阻塞模型 recvfrom从应用层到内核，如果该缓冲区没有数据的话，直接返回一个错误，轮询检查这个状态，看内核是不是有数据到来
* IO复用模型 
  - select/poll，一个或多个fd传递给select或poll系统调用，阻塞在select操作上，侦测多个fd是否处于就绪状态。select/poll顺序扫描fd是否就绪，支持的fd数量有限。
  - epoll，基于事件驱动方式代替顺序扫描，有fd就绪时，立即回调函数rollback
* 信号驱动IO模型 首先开启套接口信号驱动IO功能，并通过系统调用sigaction执行一个信号处理函数（此系统调用立即返回，进程继续工作，它是非阻塞的）。当数据准备就绪时，就为该进程生成一个sigio信号，通过信号回调通知应用程序调用recvfrom来读取数据，并通知主循环函数处理数据。
* 异步IO 告知内核启动某个操作，并让内核在整个操作完成后（包括将数据从内核复制到用户自己的缓冲区）通知我们。
这种模型与信号驱动模型的主要区别是：信号驱动IO由内核通知我们何时可以开始一个IO操作；异步IO模型由内核通知我们IO操作何时已经完成。

#### 1.1.2 IO多路复用技术
通过把多个IO的阻塞复用到同一个select的阻塞上，从而使得系统在单线程的情况下可以处理多个客户端请求。

epoll的改进：
1. 支持一个进程打开的socket描述符不受限制（仅受限于操作系统的最大文件句柄数）
2. IO效率不会随着FD数量的增加而线性下降。
3. 使用mmap加速内核与用户空间的消息传递。通过内核和用户控件mmap同一块内存来实现。
4. epoll的API更加简单

## 第2章 NIO入门

# 入门篇 NettyNIO 开发指南
## 第3章 Netty入门应用
## 第4章 TCP粘包/拆包问题解决之道
## 第5章 分隔符和定长解码器的应用

# 中级篇 Netty 编解码开发指南
## 第6章 编解码技术
## 第7章 MessagePack编解码
## 第8章 Google Protobuf编解码
## 第9章 JBoss Marshalling编解码

# 高级篇 Netty 多协议开发和应用
## 第10章 HTTP协议开发应用
## 第11章 WebSocket协议开发
## 第12章 私有协议栈开发
## 第13章 服务端创建
## 第14章 客户端创建

# 源码分析篇 Netty 功能介绍和源码分析
## 第15章 ByteBuf和相关辅助类
## 第16章 Channel和Unsafe
## 第17章 ChannelPipeline和ChannelHandler
## 第18章 EventLoop和EventLoopGroup
## 第19章 Future和Promise

# 架构和行业应用篇 Netty 高级特性
## 第20章 Netty架构剖析
## 第21章 Java多线程编程在Netty中的应用
## 第22章 高性能之道
## 第23章 可靠性
## 第24章 安全性
## 第25章 Netty未来展望

## 附录 Netty参数配置表
