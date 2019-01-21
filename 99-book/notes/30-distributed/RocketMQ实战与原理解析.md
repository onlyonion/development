《RocketMQ实战与原理解析》 杨开元 机械工业出版社

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

### 4.3 底层通信机制
#### 4.3.1 Remoting模块
#### 4.3.2 协议设计和编解码
#### 4.3.3 Netty库

## 第5章 消息队列的核心机制
### 5.1 消息存储和发送
### 5.2 消息存储结构
### 5.3 高可用性机制
### 5.4 同步刷盘和异步刷盘
### 5.5 同步复制和异步复制

## 第6章 可靠性优先的使用场景
### 6.1 顺序消息
### 6.2 消息重复问题
* 保证消费逻辑的幂等性
* 维护消息消费记录

## 第7章 吞吐量优先的使用场景
### 7.3 Consumer的负载均衡

## 第8章 和其他系统交互

# 第二部分 RocketMQ源码分析

## 第9章 首个Apache中间件顶级项目

## 第10章 NameServer源码解析

## 第11章 最常用的消费类

## 第12章 主从同步机制

## 第13章 基于Netty的通信实现

### 13.2 Netty架构总览
* core 
* transport services
* protocol support