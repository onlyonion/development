《dubbo源码解析2.0》


## 1 源码阅读路径
## 2 背景
## 3 Dubbo 架构
## 4 HelloWorld 例子
## 5 源文件概述

## 6 核心机制分析
### 6.1 设计模式
#### 1. 工厂模式
#### 2. 装饰器模式
ProtocolFilterWrapper.buildInvokerChain  group=provider
EchoFilter -> ClassLoaderFilter -> GenericFilter -> ContextFilter -> ExceptionFilter -> TimeoutFilter -> MonitorFilter -> TraceFilter

ConsumerContextFilter -> FutureFilter -> MonitorFilter

#### 3. 责任链
#### 4. 观察者模式
#### 5. 动态代理

### 6.2 Bean加载
#### 6.2.1 Spring可扩展Schema
#### 6.2.2 Spring加载bean流程

### 6.3 Extension机制
#### 6.3.1 Java SPI
#### 6.3.2 扩展点

### 6.4 代理
#### 6.4.1 Invoker调用
#### 6.4.2 JDK代理
#### 6.4.3 Javaassist代理

### 6.5 远程调用流程
#### 6.5.1 通信过程
#### 6.5.2 序列化
#### 6.5.3 Encode和Decode

## 7 过程分析
### 7.1 Refer & export
### 7.2 Registry
### 7.3 集群&容错
### 7.4 telnet