《RocketMQ实战与原理解析》 杨开元 机械工业出版社

* RocketMQ实战 配置使用、发送、接收消息、可靠消息、吞吐量优先、与框架整合
* 源码分析 NameServer源码、消费路、主从同步机制、Netty通信

# 第一部分 RocketMQ实战
## 第1章 快速入门
### 1.1 消息队列功能介绍
* 应用解耦
* 流量消峰（异步）
* 消息分发

### 1.2 RocketMQ简介
比起Kafka的Scala语言和RabbitMQ的Erlang语言，更容易定制开发

## 第2章 生产环境下的配置和使用
### 2.1 RocketMQ各部分角色介绍
* Producer
* Consumer
* Broker 暂存
* NameServer 协调者
* Topic
* MessageQueue

### 2.2 多机集群配置和部署
### 2.3 发送／接收消息示例
### 2.4 常用管理命令
MQAdmin 是 RocketMQ 自带的命令行管理工具，在 bin 目录下，运行 mqadmin 即可执行。使用 mqadmin 命令，
可以进行创建、修改 Topic ，更新 Broker 的配置信息，查询特定消息等各种操作 。
### 2.5 通过图形界面管理集群

## 第3章 用适合的方式发送和接收消息
### 3.1 不同类型的消费者
* DefaultMQPushConsumer
* DefaultMQPullConsumer

### 3.2 不同类型的生产者
#### 3.2.1 DefaultMQProducer
#### 3.2.2 发送延迟消息
通过设置延迟级别控制延迟时间

#### 3.2.4 对事务的支持

## 第4章 分布式消息队列的协调者
NameServer维护节点角色变动信息、状态信息。
### 4.1 NameServer的功能
### 4.2 各个角色的交互流程
### 4.3 底层通信机制
#### 4.3.1 Remoting模块
#### 4.3.2 协议设计和编解码
#### 4.3.3 Netty库

## 第5章 消息队列的核心机制
Broker是RocketMQ的核心，接收Producer发过来的消息、处理Consumer的消费消息请求、消息的持久化存储、消息的HA机制以及服务端过滤功能等。

### 5.1 消息存储和发送
一台服务器把本地磁盘文件的内容发送到客户端，一般分为两个步骤：
1. read(file, tmp_buf, len) 读取本地文件内容
2. write(socket, tmp_buf, len) 将读取的内容通过网络发送出去

4次数据复制：
* 从磁盘复制数据到内核态内存，
* 从内核态内存复制到用户态内存；
* 然后从用户态内存复制到网络驱动的内核态内存，
* 最后从**网络驱动的内核态**内存复制到网卡中进行传输。

通过使用mmap的方式，可以省去向用户态的内存复制，提高速度。“零拷贝”

### 5.2 消息存储结构
RocketMQ消息的存储是由ComsumeQueue和CommitLog配合完成的。消息真正的物理文件是CommitLog，ComsumeQueque是消息的逻辑队列，类似数据库的索引文件，存储的是指向物理存储的地址。

### 5.3 高可用性机制
### 5.4 同步刷盘和异步刷盘
### 5.5 同步复制和异步复制

## 第6章 可靠性优先的使用场景
### 6.1 顺序消息
#### 6.1.1 全局顺序消息
指某个 Topic 下的所有消息都要保证顺序
#### 6.1.2 部分顺序消息
只要保证每一组消息被顺序消费即可，需要发送端和消费端配合处理。

在发送端，要做到把统一业务ID的消息发送到同一个MessageQueue；在消费过程中，要做到从同一个MessageQueue读取的消息不被并发处理，这样才能达到部分有序。

### 6.2 消息重复问题
* 保证消费逻辑的幂等性（多次调用和一次调用效果相同）
* 维护消息消费记录，消费前查询这个消息是否被消费过

### 6.3 动态增减机器
#### 6.3.1 动态增减NameServer
#### 6.3.2 动态增减Broker

### 6.4 各种故障对消息的影响
### 6.5 消息优先级

## 第7章 吞吐量优先的使用场景
### 7.1 在Broker端进行消息过滤
#### 7.1.1 消息的Tag和Key
#### 7.1.2 通过Tag进行过滤
#### 7.1.3 用SQL表达式的方式进行过滤
#### 7.1.4 FilterServer方式过滤

### 7.2 提高Consumer处理能力
1. 提高消息并行度
2. 以批量方式进行消费
3. 检测延时情况，跳过非重要消息
   
### 7.3 Consumer的负载均衡
#### 7.3.1 DefaultMQPushConsumer的负载均衡
#### 7.3.2 DefaultMQPullConsumer的负载均衡

### 7.4 提高 Producer 的发送速度
### 7.5 系统性能调优的一般流程
1. 使用top命令查看cpu和内存的使用率
2. 使用linux的sar命令查看网卡使用情况 iperf3验证网卡是否到达了极限值  netstat -t 查看网卡的连接情况
3. 使用iostat查看磁盘的使用情况

对于java程序，jvisualvm、jstack、perfj等

## 第8章 和其他系统交互
### 8.1 在 SpringBoot 中使用 RocketMQ
#### 8.1.1 直接使用
#### 8.1.2 通过Spring Messaging方式使用
### 8.2 直接使用云上 RocketMQ
### 8.3 RocketMQ 与 Spark 、 Flink 对接
### 8.4 自定义开发运维工具
#### 8.4.1 开源版运维工具功能介绍
#### 8.4.2 基于tools模块开发自定义运维工具

# 第二部分 RocketMQ源码分析
## 第9章 首个Apache中间件顶级项目
### 9.3 源码结构
broker实现了消息队列的主题、client、common、namesrv、remoting、store、tools

## 第10章 NameServer源码解析
### 10.1 模块人口代码的功能
### 10.2 NameServer 的总控逻辑
### 10.3 核心业务逻辑处理
### 10.4 集群状态存储


## 第11章 最常用的消费类
### 11.1 整体流程
### 11.2 消息的并发处理
### 11.3 生产者消费者的底层类
#### 11.3.1 MQClientlnstance 类的创建规则
#### 11.3.2 MQClientlnstance 类的功能

## 第12章 主从同步机制
### 12.1 同步属性信息
### 12.2 同步消息体
### 12.3 sync_master 和 async_master

## 第13章 基于Netty的通信实现
### 13.l Netty 介绍
异步事件驱动

### 13.2 Netty架构总览
* core 扩展的事件模型、通用通信api、零拷贝、丰富的字节缓冲
* transport services Socket&Datagram（TCP与UDP）、Http tunnel、in-vm pipe 虚拟机内部管道
* protocol support

#### 13.2.1 重新实现 Byte Buffer
ByteBuf 实现的是一个非常轻量级的字节数组包装器 。 ByteBuf 有读操作和写操作，为了便于用户使用，
该缓冲区维护了读索引和写索引 。 
#### 13.2.2 统一的异步 I/0 接口
* 基于nio的tcp/ip传输 io.netty.channel.nio
* 基于oio的tcp/ip传输 io.netty.channel.oio
* 基于oio的udp/ip传输 io.netty.channel.oio
* 本地传输 io.netty.channel.local

#### 13.2.3 基于拦截链模式的事件模型
ChannelPipeline内部的一个ChannelEvent被一组ChannelHandler处理。这个管道是InterceptingFilter拦击过滤器模式的一种高级实现。

#### 13.2.4 高级组件
* codec 编解码框架
* ssl/tls 安全框架
* http 实现
* websocket

### 13.3 Netty 用法示例
### 13.4 RocketMQ 基于 Netty 的通信功能实现
#### 13.4.1 顶层抽象类
RemotingServer、RemotingClient
#### 13.4.2 自定义协议
invokeSync
invokeOneway

NettyRemotingAbstract
#### 13.4.3 基于Netty的Server和Client
NettyRemotingServer
NettyRemotingClient