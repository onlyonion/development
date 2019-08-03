《RocketMQ实战与原理解析》 杨开元 机械工业出版社

# Content
* RocketMQ实战 配置使用、发送、接收消息
* 分布式消息队列的协调者 NameServer
* 消息队列核心机制 存储和发送、高可用、同步刷盘和异步刷盘、同步复制和异步复制
* 可靠性优先 顺序、重复、动态增减机器、各种故障
* 吞吐量优先 Broker端消息过滤、Comsumer负载均衡
* 和其他系统的交互 Springboot、云上RocketMQ
* 源码分析 NameServer源码、整体流程、消息并发处理、主从同步机制、Netty通信

# 第一部分 RocketMQ实战
## 第1章 快速入门
### 1.1 消息队列功能介绍
* 应用解耦 高内聚、低耦合；故障系统恢复后，处理消息队列的信息即可
* 流量消峰（异步） 大流量冲击，请求暂存；最终一致性
* 消息分发 不必和数据强关联

### 1.2 RocketMQ简介
* Notify 推模型，解决了事务消息
* MetaQ 拉模型，解决了顺序消息和还聊堆积问题
* RocketMQ 基于长轮询的拉取方式，兼有两者的优点
  
比起Kafka的Scala语言和RabbitMQ的Erlang语言，更容易定制开发
### 1.3 快速上手RocketMQ
先启动NameServer，再启动Broker

## 第2章 生产环境下的配置和使用
搭建高可用的分布式消息队列及其，以及RocketMQ的Comsumer和Producer的使用方法与常用命令
### 2.1 RocketMQ各部分角色介绍
邮政系统：发信者、收信者、负责暂存和传输的邮局、负责协调各个地方邮局的管理机构
* Producer
* Consumer
* Broker 暂存，每个Broker可部署一个或多个slave
* NameServer 协调者
* Topic 区分不同类型的消息
* MessageQueue 一个Topic设置一个或多个MessageQueue（类似分区或Partition）

### 2.2 多机集群配置和部署
两台物理机，搭建双主、双从、无单点故障的高可用RocketMQ集群
#### 2.2.1 启动多个NameServer和Broker
启动两个NameServer，每个机器上都要分别启动一个Master角色的Broker和一个Slave角色的Broker，并互为主备。
#### 2.2.2 配置参数介绍
```sh
namesrvAddr=ip1:port;ip2:port
brokerClusterName=DefaultCluster
brokerName=broker-a
brokerId=0
fileReservedTime=48
deleteWhen=48
brokerRole=SYNC_MASTER
flushDiskType=ASYNC_FLUSH
listenPort=10911
storePathRootDir=/home/rocketmq/store-a
```
### 2.3 发送／接收消息示例
### 2.4 常用管理命令
MQAdmin 是 RocketMQ 自带的命令行管理工具，在 bin 目录下，运行 mqadmin 即可执行。使用 mqadmin 命令，
可以进行创建、修改 Topic ，更新 Broker 的配置信息，查询特定消息等各种操作 。
### 2.5 通过图形界面管理集群

## 第3章 用适合的方式发送和接收消息
### 3.1 不同类型的消费者
* DefaultMQPushConsumer 由系统控制读取操作，收到消息后自动调用传入的处理方法来处理
* DefaultMQPullConsumer 读取操作中的大部分功能由使用者自主控制

### 3.2 不同类型的生产者
#### 3.2.1 DefaultMQProducer
#### 3.2.2 发送延迟消息
通过设置延迟级别控制延迟时间。`setDelayTimeLevle(3)`

#### 3.2.4 对事务的支持
采用两阶段提交的方式来实现事务，TransactionMQProducer处理。**2PC + 定时回查**
1. 发送方向RocketMQ发送“待确认”消息
2. RocketMQ将收到的“待确认”消息持久化后，向发送方向恢复消息已经发送成功，此时第一阶段消息发送完成。
3. 发送方开始执行本地事务逻辑
4. 发送方根据本地事务执行结果想RocketMQ发送二次确认（Commit或Rollback）消息，RocketMQ收到Commit状态则将第一阶段消息标记为可投递，订阅方将能够接收到该消息；收到Rollback状态则删除第一阶段的消息，订阅方接收不到消息。
5. 异常情况，服务器定时回查“待确认”的消息
6. 发送方接收到回查请求
7. RocketMQ接收到回查请求后，按照4逻辑处理

### 3.3 如何存储队列位置信息
### 3.4 自定义日志输出
Log配置文件的设置可以通过JVM启动参数、环境变量、代码中的设置语句这三种方式来配置。

## 第4章 分布式消息队列的协调者
NameServer维护节点角色变动信息、状态信息。
### 4.1 NameServer的功能
NameServer是整个消息队列的状态服务器，集群的各个组件通过它来了解全局的信息。
同时，各个角色的机器都要定期向NameServer**上报**自己的状态，超时不上报的话，NameServer会认为某个机器出故障不可用了，
其他的组件会把这个机器从可用列表里移除。

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

4次数据复制：磁盘 -> 内核态 -> 用户态 -> 网络驱动内核态 -> 网卡
* 从磁盘复制数据到内核态内存，
* 从内核态内存复制到用户态内存；
* 然后从用户态内存复制到网络驱动的内核态内存，
* 最后从**网络驱动的内核态**内存复制到网卡中进行传输。

通过使用mmap的方式，可以省去向用户态的内存复制，提高速度。“零拷贝”

### 5.2 消息存储结构
RocketMQ消息的存储是由ComsumeQueue和CommitLog配合完成的。消息真正的物理文件是CommitLog，ComsumeQueque是消息的逻辑队列，类似数据库的索引文件，存储的是指向物理存储的地址。

### 5.3 高可用性机制
RocketMQ 分布式集群是通过 Master 和 Slave 的配合达到高可用性的，首先说一下 Master 和 Slave 的区别：
在 Broker 的配 置 文件中，参数 brokerId的值为 0 表明这个 Broker 是 Master，大于 0 表 明这个 Broker 是 Slave ，
同时 brokerRole 参数 也会说明这个 Broker 是 Master 还是 Slave 。

!> 如何达到发送端的高可用性呢？创建Topic的时候，把Topic的多个MessageQueue创建在多个Broker组上（相同的Brokder名称，不同brokderId的机器组成一个Broker组），
这样当一个Broker组的Master不可用后，其他组的Master仍然可用，Producer仍然可以发送消息。


### 5.4 同步刷盘和异步刷盘
- 异步刷盘 在返回写成功状态时，消息可能**只是被写入了内存的pagecache**，写操作的返回快，吞吐量大；当内存里的消息量累积到一定程度时，统一出发写磁盘动作，快速写入
- 同步刷盘 在返回ie成功状态时，消息**已经被写入磁盘**。具体流程是，消息写入内存的pagecache后，立刻通知刷盘线程刷盘，然后等待刷盘完成

### 5.5 同步复制和异步复制
Broker组主从模式，消息需要从主复制到从上
- 同步复制 等master和slave均写成功后才反馈给客户端写成功状态
- 异步复制 只要master写成功即可反馈给客户端写成功状态

master和slave配置成async_flush的刷盘方式，主从之间配置成sync_master的复制方式，这样即使有一台机器出故障，仍然能保证数据不丢。

!> 可靠消息：主从模式 + 异步刷盘 + 同步复制

## 第6章 可靠性优先的使用场景
### 6.1 顺序消息
#### 6.1.1 全局顺序消息
指某个 Topic 下的所有消息都要保证顺序
#### 6.1.2 部分顺序消息
只要保证每一组消息被顺序消费即可，需要发送端和消费端配合处理。

在发送端，要做到把统一业务ID的消息发送到同一个MessageQueue；在消费过程中，要做到从同一个MessageQueue读取的消息不被并发处理，这样才能达到部分有序。

### 6.2 消息重复问题
“有且仅有一次”太困难。RocketMQ 选择了确保一定投递，保证消息不丢失，但有可能造成消息重复。网络波动情况下。
* 保证消费逻辑的幂等性（多次调用和一次调用效果相同）
* 维护消息消费记录，消费前查询这个消息是否被消费过

### 6.3 动态增减机器
#### 6.3.1 动态增减NameServer
NameServer 是 RocketMQ 集群的协调者，集群的各个组件是通过NameServer 获取各种属性和地址信息的。 
Broker定期上报状态信息；客户端（Producer、Comsumer）通过 NameServer 获取最新的状态信息。

#### 6.3.2 动态增减Broker

### 6.4 各种故障对消息的影响

* 硬件故障
如果 Master 和 Slave 机器间配置成同步复制方式，也可以达到消息不丢失的效果。
如果 Master 和 Slave机器间是异步复制，两次 Sync 间的消息会丢失

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
* core 核心，扩展的事件模型、通用通信api、零拷贝、丰富的字节缓冲
* transport 传输层，services Socket&Datagram（TCP与UDP）、Http tunnel、in-vm pipe 虚拟机内部管道
* protocol 应用层，support

#### 13.2.1 重新实现 Byte Buffer
ByteBuf 实现的是一个非常轻量级的字节数组包装器 。 ByteBuf 有读操作和写操作，为了便于用户使用，
该缓冲区维护了读索引和写索引 。 
#### 13.2.2 统一的异步 I/0 接口
* 基于nio的tcp/ip传输 io.netty.channel.nio
* 基于oio的tcp/ip传输 io.netty.channel.oio
* 基于oio的udp/ip传输 io.netty.channel.oio
* 本地传输 io.netty.channel.local

#### 13.2.3 基于拦截链模式的事件模型
ChannelPipeline内部的一个ChannelEvent被一组ChannelHandler处理。这个管道是InterceptingFilter（拦截过滤器）模式的一种高级实现。

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