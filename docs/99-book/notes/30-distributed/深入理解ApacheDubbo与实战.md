《深入理解Apache Dubbo与实战》诣极，林琳 著

- 注册中心
- 扩展点加载、启停原理、远程调用
- 集群容器 容错机制、Directory、路由、负载均衡、Merger、Mock
- 扩展点
- 高级特性
- 过滤器

## 第1章 Dubbo 高性能RPC通信框架
### 1.1 应用架构演进过程
#### 1.1.1 单体应用
1. JEE时期
2. MVC框架时期

#### 1.1.2 分布式应用
1. 早期SOA
2. 微服务化
3. 云原生

云原生技术有利于各组织在公有云、私有云和混合云等新型动态环境中，**构建和运行**可弹性扩展的应用。
云原生的代表技术包括容器、服务网络、微服务、不可变基础设施和声明式API。

### 1.2 Dubbo简介
### 1.3 Dubbo总体大图

## 第2章 开发第一款Dubbo应用程序
### 2.1 配置开发环境
### 2.2 基于XML配置实现
### 2.3 基于注解实现
### 2.4 基于API实现
### 2.5 构建并运行

## 第3章 Dubbo注册中心
### 3.1 注册中心概述
#### 3.1.1 工作流程
#### 3.1.2 数据结构
#### 3.1.3 ZooKeeper原理概述
#### 3.1.4 Redis原理概述

### 3.2 订阅/发布
#### 3.2.1 ZooKeeper的实现
#### 3.2.2 Redis的实现

### 3.3 缓存机制
#### 3.3.1 缓存的加载
#### 3.3.2 缓存的保存与更新

### 3.4 重试机制
### 3.5 设计模式
#### 3.5.1 模板模式
#### 3.5.2 工厂模式

## 第4章 Dubbo扩展点加载机制
### 4.1 加载机制
#### 4.1.1 JavaSPI
```java
// jdbc
ServiceLoader<Driver> serviceLoader = ServiceLoader.load(Driver.class);
```
#### 4.1.2 扩展点加载机制的改进
1. JDK标准的SPI会一次性实例化扩展点所有实现
2. 如果扩展点加载失败，则连扩展点的名称都获取不到
3. 增加了对扩展IoC和AOP的支持

#### 4.1.3 扩展点的配置规范
#### 4.1.4 扩展点的分类与缓存
#### 4.1.5 扩展点的特性
1. 自动包装
2. 自动加载 setter方法，框架会自动注入对应的扩展点实现类。如果有多种实现，需要自适应。
3. 自适应 setter多个实现类，URL中的参数来确定使用哪个具体的实现类。只能激活一个具体的实现类。
4. 自动激活 默认可以激活不同的扩展点实现。

### 4.2 扩展点注解
### 4.3 ExtensionLoader的工作原理
### 4.4 扩展点动态编译的实现

## 第5章 Dubbo启停原理解析
### 5.1 配置解析
#### 5.1.1 基于schema设计解析
#### 5.1.2 基于XML配置原理解析
#### 5.1.3 基于注解配置原理解析

### 5.2 服务暴露的实现原理
#### 5.2.1 配置承载初始化
#### 5.2.2 远程服务的暴露机制
整体分为两大部分
- 将持有的服务实例通过代理转换成Invoker。Invoker可能是本地实现、远程实现、集群实现。
- 把Invoker通过具体的协议转换成Exporter

Dubbo支持多注册中心同时写，如果配置了服务同时注册了多个注册中心，则会以ServiceConfig#doExportUrls中依次暴露。
```java
private void doExporrUrls() {
    List<URL> registryURLs = loadRegistries(true);
    for (ProtocolConfig protocolConfig : protocols) {
        doExportUrlsFor1Protocol(protocolConfig, regisryURLs);
    }
}
```
Dubbo也支持相同服务暴露多个协议，比如同时暴露Dubbo和REST协议。

#### 5.2.3 本地服务的暴露机制

### 5.3 服务消费的实现原理
#### 5.3.1 单注册中心消费原理
#### 5.3.2 多注册中心消费原理
#### 5.3.3 直连服务消费原理

### 5.4 优雅停机实现原理
1. 收到进程退出信号，Spring容器会触发容器销毁事件
2. provider端会取消注册服务元数据信息
3. consumer端会收到最新服务地址列表
4. Dubbo协议会发发送**readonly事件报文**通知consumer服务不可用
5. 服务端等待已经执行的任务结束并拒绝新的任务执行

## 第6章 Dubbo远程调用
Dubbo调用流程、Dubbo内部协议、编解码、线程模型
### 6.1 Dubbo调用介绍
首先在客户端启动时会从注册中心拉取和订阅对应的服务列表，Cluster会把拉取的服务列表聚合成一个Invoker，
每次RPC调用前会通过Directory#list获取providers地址，获取这些服务列表给后续路由和复制均衡使用。

在Dubbo发起服务调用时，所有路由和复制均衡都是在客户端实现的。
客户端经过路由和负载均衡后，会将请求交给地城IO线程池处理，IO线程池主要负责处理读写、序列化和发序列化等逻辑。

### 6.2 Dubbo协议详解
Dubbo协议，协议头（16Byte = 4 * 4Byte = 4 * 32bit）、协议体。
- 消息头
  - 4Byte 魔法数高位、魔法数地位、请求响应、需要往返、事件、序列化ID、状态
  - 8Byte RPC请求ID
  - 4Byte 消息体数据长度
- 消息体
  - dubbo version、service name、service version、method name、parametertypes、arguments、attachments

客户端多个线程并发请求时，框架内部会调用DefaultFuture对象的get方法进行等待。在发起请求时，框架内部会创建Request对象，
这个时候会被分配一个唯一id，DefaultFuture可以从Request对象中获取id，并将关联关系存储到静态HashMap中。

当客户端收到响应时，会根据Response对象中的id，从Future集合中查找对应DefaultFuture对象，最终会唤醒对应的线程并通知结果。

客户端也会启动一个定时扫描线程去探测超时有没有返回请求。

### 6.3 编解码原理
#### 6.3.1 Dubbo协议编码器
构造报文头部，然后对消息体进行序列化处理。
#### 6.3.2 Dubbo协议解码器
解码工作分成两部分，第一部分解码报文头部（16字节），第二部分解码报文体内容，以及如何把报文体转换成RpcInvocation。

实现解码过程中要解决粘包和半包问题。

心跳报文时没有消息体的；事件有消息体，在使用hession2协议的情况下默认会传递字符R，当优雅停机会通过发送readonly事件来通知客户端服务端不可用。

在解码请求时，是严格按照客户端写数据顺序来处理的。

### 6.4 Telnet调用原理

### 6.5 ChannelHandler
因为Netty每次创建Handler都会应该ChannelPipeline，大量事件经过很多Pipeline会有较多的开销，因此Dubbo会将多个Handler聚合为一个Handler。
#### 6.5.1 核心Handler和线程模型
Handler（ChannelHandler），connected、disconnected、sent、received、caught。

- DubboProtocol中通过内部类继承自ExchangeHandlerAdapter，完成服务提供方Invoker实例的查找并进行服务的真实调用。
- 实例Invoker存储到HashMap中，客户端调用过来必须携带相同的信息构造的key，找到对应Exporter
- serviceKey服务的唯一标识由4部分组成的：端口、接口名、接口版本和接口分组。

线程模型
- IO线程池，负责读写报文，用于接收请求，如果IO线程饱和，则不会接收行的请求
- 业务线程池，Dubbo中负责业务方法调用，
- Dispatcher线程池派发器，属于Dubbo中的扩展点

#### 6.5.2 Dubbo请求相应Handler
HeaderExchangeHandler将方法调用抽象成Request/Response，每次调用都会创建一个请求Request
#### 6.5.3 Dubbo心跳Handler
Dubbo默认客户端和服务端都会发送心跳报文，用来保持TCP长连接状态。
在客户端和服务端，Dubbo内部开启一个线程循环扫描并检测连接是否超时，在服务端如果发现超时则会主动关闭客户端，
在客户端发现超时则会主动重新创建连接。默认心跳检测时间是60秒。
HeartBeatTask

## 第7章 Dubbo集群容错
集群容错、Directory、Router、LoadBalance、Merger、Mock
### 7.1 Cluster层概述
Cluster层是抽象概念，表示的是对外的整个机器容错层；Cluster是容错接口，提供Failover、Failfast等容错策略。

Cluster层
1. 生成Invoker对象
2. 获得可调用的服务列表
3. 负载均衡
4. RPC调用

### 7.2 容错机制的实现
#### 7.2.1 容错机制概述
#### 7.2.2 Cluster接口关系
!> 当上层调用Invoker时，无论实际存在多个少个Invoker，只需要通过Cluster层，即可完成整个调用的**容错**逻辑，包括服务列表、路由、负载均衡等。
Cluster接口只是串联起整个逻辑，其中ClusterInvoker只实现了容错策略部分，其他逻辑则是调用了Directory、Router、LoadBalance等接口实现。

#### 7.2.3 Failover策略
默认实现。for循环实现重试，for循环的次数就是重试的次数。
#### 7.2.4 Failfast策略
校检、负载均衡、远程调用，捕获异常抛出。
#### 7.2.5 Failsafe策略
校检、负载均衡、远程调用，catch异常处理，返回空RcpResult。
#### 7.2.6 Failback策略
校检、负载均衡、远程调用，失败的invocation保存到ConcurrentHashMap中，定时器重试。
#### 7.2.7 Availabe策略
遍历，找到第一个可用的服务直接调用，找不到抛异常。
#### 7.2.8 Broadcast策略
广播给所有可用的节点，如果任何一个节点报错，则返回异常。任何一个节点出错，不会中断整个广播。多个节点异常，只有最后一个节点异常会抛出，前面的会被覆盖。
#### 7.2.9 Forking策略
并行请求多个服务，有任何一个服务返回，则直接返回。内部持有CachedThreadPool线程池。

### 7.3 Directory的实现
#### 7.3.1 总体实现
#### 7.3.2 RegistryDirectory的实现

### 7.4 路由的实现
Directory获取所有的Invoker列表的时候，就会调用到路由接口。路由接口会根据用户配置的不同路由策略对Invoker列表进行过滤，只返回符合规则的Invoker。
#### 7.4.1 路由的总体结构
#### 7.4.2 条件路由的参数规则
#### 7.4.3 条件路由的实现
#### 7.4.4 文件路由的实现
文件路由是把规则写在文件中，文件中写的是自定义的脚本规则，可以是JavaScript、Groovy等，URL中对应的key值填写的是文件的路径。
文件路由主要做的就是把文件中的路由脚本读取出来，然后调用路由的工厂去匹配对应的脚本路由做解析。

#### 7.4.5 脚本路由的实现
脚本路由使用JDK自带的脚本解析器解析脚本并运行，默认使用JavaScript解析器，其逻辑分为构造方法和route方法两大部分。

### 7.5 负载均衡的实现
在整个机器容错流程中，首先经过Directory获取所有Invoker列表，然后经过Router根据路由规则过滤Invoker，
最后幸存下来的Invoker还要经过复制均衡这一关，选出最终要调用的Invoker。

#### 7.5.1 包装后的负载均衡
#### 7.5.2 负载均衡的总体结构
#### 7.5.3 Random负载均衡
#### 7.5.4 RoundRobin负载均衡
#### 7.5.5 LeastActive负载均衡
#### 7.5.6 一致性Hash负载均衡

### 7.6 Merger的实现
### 7.7 Mock

## 第8章 Dubbo扩展点
### 8.1 Dubbo扩展点概述
#### 8.1.1 扩展点的背景
#### 8.1.2 扩展点的整体架构

### 8.2 RPC层扩展点
#### 8.2.1 Proxy层扩展点
#### 8.2.2 Registry层扩展点
#### 8.2.3 Cluster层扩展点

### 8.3 Remote层扩展点
#### 8.3.1 Protocol层扩展点
#### 8.3.2 Exchange层扩展点
#### 8.3.3 Transport层扩展点
#### 8.3.4 Serialize层扩展点
### 8.4 其他扩展点
1. TelnetHandler扩展点
2. StatuChecker扩展点
3. Container扩展点
4. CacheFactory扩展点 请求结果缓存
5. Validation扩展点 参数校验
6. LoggerAdapter扩展点
7. Compiler扩展点 @Adaptive注解会生成Java代码，然后使用编译器动态编译出新的Class。Compiler接口是可扩展的编译器。


## 第9章 Dubbo高级特性
### 9.1 Dubbo高级特性概述
Dubbo解决了分布式场景RPC通信调用的问题，但是要满足各种业务场景还是不够的。其他特性，如并发控制和连接控制等。
### 9.2 服务分组和版本
Dubbo中提供的服务分组和版本是强隔离的，如果服务指定了服务分组和版本，则消费方调用也必须传递相同的服务分组和版本名称。
### 9.3 参数回调
### 9.4 隐式参数
### 9.5 异步调用
### 9.6 泛化调用
### 9.7 上下文信息
### 9.8 Telnet操作
### 9.9 Mock调用
```xml
<dubbo:reference mock="true" />
<dubbo:reference mock="com.xxx.TestServiceMock" />
<dubbo:reference mock="return null" />
<dubbo:reference mock="throw com.xxx.XXXException" />
<dubbo:reference mock="force:return fake" />
<dubbo:reference mock="force:throw com.xxx.XXXException" />
```
### 9.10 结果缓存
```xml
<dubbo:reference cache="lru" />
```

## 第10章 Dubbo过滤器
### 10.1 Dubbo过滤器概述
由于每次调用时都会执行，因此在使用的时候需要注意它对性能的影响。
#### 10.1.1 过滤器的使用
```xml
<dubbo:reference filter="xxx,yyy" />
<dubbo:consumer filter="xxx,yyy" />

<dubbo:service filter="xxx,yyy" />
<dubbo:provider filter="xxx,yyy" />
```
#### 10.1.2 过滤器的总体结构

### 10.2 过滤器链初始化的实现原理

### 10.3 服务提供者过滤器的实现原理
#### 10.3.1 AccessLogFilter的实现原理
日志过滤器。
#### 10.3.2 ExecuteLimitFilter的实现原理
用于限制每个服务中每个方法的最大并发数，有接口级别和方法级别的配置方式。
#### 10.3.3 ClassLoaderFilter的实现原理
如果要实现违反双亲委派模型来查找Class，那么通常会使用上下文类加载器。
#### 10.3.4 ContextFilter的实现原理
ContextFilter主要记录每个请求调用的上下文。
#### 10.3.5 ExceptionFilter的实现原理
#### 10.3.6 TimeoutFilter的实现原理
记录每个Invoker的调用时间，如果超过了接口设置的timeout值，则会打印一条警告日志。
#### 10.3.7 TokenFilter的实现原理
在Dubbo中，如果某些服务提供者不想让消费者绕过注册中心直连自己，则可以使用令牌验证。
#### 10.3.8 TpsLimitFilter的实现原理
TpsLimitFilter主要用于**服务提供端**的**限流**。
TpsLimitFilter的限流是基于令牌的，即一个时间段内只分配N个令牌，每个请求过来都会消耗一个令牌，消耗完毕，后面再来的请求都会被拒绝。  
```xml
<!-- 每次发放1000个令牌 -->
<dubbo:parameter key="tps" value="1000" />
 <!-- 令牌刷新时间间隔是1秒，如果不配置，则默认是60秒 -->
<dubbo:parameter key="tps.interval" value="1000" />
```
### 10.4 消费者过滤器的实现原理
#### 10.4.1 ActiveLimitFilter的实现原理
消费者端的过滤器，限制的是客户端的并发数。
#### 10.4.2 ConsumerContextFilter的实现原理
- 收到请求时，当前节点可以被看作一个服务提供者，由ContextFilter设置上下文。
- 当发起请求到下一个服务时，当前服务变成一个消费者，由ConsumeContextFilter设置上下文。

#### 10.4.3 DeprecatedFilter的实现原理
#### 10.4.4 FutureFilter的实现原理
FutureFilter主要实现框架再调用前后出现异常时，触发调用用户配置的回调方法。

```xml
<bean id="callBack" class="com.test.CallBack">
<dubbo:reference id="testService" interface="com.testService">
    <dubbo:method name="testMethod" onreturn="callBack.onreturn" onthrow="callBack.onthrow" oninvoke="callBack.oninvoke">
</dubbo:reference>
```

## 第11章 Dubbo注册中心扩展实战
### 11.1 etcd背景介绍
etcd是分布式键值存储系统，它提供了可靠的集群存储数据的途径。
etcd使用了`Raft`算法保证集群中数据的一致性，当leader节点下线时会自动触发新的leader选举，以此容忍机器的故障。
应用可以在etcd中读写数据，例如：把一些参数特性的信息通过key-value形式写入etcd，这些数据可以被监听，当数据发生变化的时候，可以通知监听者。

1. 增量快照、增量更新
2. 基于go语言，堆外存储（无垃圾收集暂停）
3. Kubernetes领域中大量生产实践

### 11.2 etcd数据结构设计
### 11.3 构建可运行的注册中心
### 11.4 搭建etcd集群并在Dubbo中运行

## 第12章 Dubbo服务治理平台
### 12.1 服务治理平台总体结构
### 12.2 服务治理平台实现原理

## 第13章 Dubbo未来展望
### 13.1 Dubbo未来生态
### 13.2 云原生
SeviceMesh是一个用于处理服务间通信的基础设施层，它负责为构建复杂的云原生应用传递可靠的网络请求。
在实践中，服务网格通常实现为一组和应用程序部署在一起的轻量级的网络代理，但对应用程序来说是透明的。

把服务治理的能力下沉为平台的基础能力，只保留通信部分即可，由sidecar成为代理，负责现在框架大部分能力的管理。
例如：服务的发型、集群容错、负载均衡等都从框架中剥离出来，应用不关注请求哪个服务，只需要将请求发送给sidecar，由sidecar来完成后续的全部逻辑。
