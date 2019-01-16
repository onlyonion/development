《Netty权威指南 第2版》 李林锋

* 基础篇 bio、nio、aio
* 中级篇 netty入门应用、编解码
* 高级篇 netty多协议开发，http、websocket、私有协议
* 源码篇 ByteBuff、Channel、Unsafe、ChannelPipeline和ChannelHandler、EventLoop和EventLoopGroup、Future和Promise
* 高级特性 架构剖析、Java多线程应用、高性能之道、可靠性、安全性

## 1 Java的I/O演进之路
### 1.1 IO基础入门
#### 1.1.1 Linux网络IO模型
应用程序进程，内核进程
1. 系统调用，等待数据包
2. 数据包到达，复制到应用程序缓冲区

recvfrom
* 阻塞
* 非阻塞
* IO复用模型
* 信号驱动IO模型

## 2 NIO入门
