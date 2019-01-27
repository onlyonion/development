## 1. 简介
### 背景 去单点引来新问题
为了避免单点故障，现在的应用通常至少会部署在两台服务器上。
对于一些负载比较高的服务，会部署更多的服务器。

这样，在同一环境下的服务提供者数量会大于1。

对于服务消费者来说，同一环境下出现了多个服务提供者。这时会出现一个问题，服务消费者需要决定选择哪个服务提供者进行调用。另外服务调用失败时的处理措施也是需要考虑的，是重试呢，还是抛出异常，亦或是只打印异常等。

### dubbo解决之道
为了处理这些问题，Dubbo 定义了集群接口 Cluster 以及 Cluster Invoker。集群 Cluster 用途是将多个服务提供者合并为一个 Cluster Invoker，并将这个 Invoker 暴露给服务消费者。

这样一来，服务消费者只需通过这个 Invoker 进行远程调用即可，至于具体调用哪个服务提供者，以及调用失败后如何处理等问题，现在都交给集群模块去处理。

### 架构定位
集群模块是服务提供者和服务消费者的中间层，为服务消费者屏蔽了服务提供者的情况，这样服务消费者就可以专心处理远程调用相关事宜。比如发请求，接受服务提供者返回的数据等。这就是集群的作用。

## 2. 集群容错

![dubbo-cluster](../../../img/dubbo-docs-cluster.jpg)

集群工作过程可分为两个阶段，第一个阶段是在服务消费者初始化期间，集群 Cluster 实现类为服务消费者创建 Cluster Invoker 实例，即上图中的 merge 操作。第二个阶段是在服务消费者进行远程调用时。

| cluster          | invoker                 | desc         | misc                                 | middleware       |
|:-----------------|:------------------------|:-------------|:-------------------------------------|:-----------------|
| FailoverCluster  | FailoverClusterInvoker  | 失败自动切换 | 切换一个重试                         | middleware       |
| FailfastCluster  | FailfastClusterInvoker  | 快速失败     | 抛异常                               | 添加幂等         |
| FailbackCluster  | FailbackClusterInvoker  | 失败自动恢复 | 返回 定时任务重试                    | 消息通知         |
| FailsafeCluster  | FailsafeClusterInvoker  | 失败安全     | 仅会打印异常 不抛异常                | 审计日志         |
| ForkingCluster   | ForkingClusterInvoker   | 并行调用     | 并发调用 耗资源 有一个成功返回就返回 | 实时性高的读操作 |
| BroadcastCluster | BroadcastClusterInvoker | 广播调用     | 循环调用                             | 更新缓存或者日志 |


## 3.源码分析

### 3.1 Cluster 实现类分析

### 3.2 Cluster Invoker 分析
#### 3.2.1 FailoverClusterInvoker
FailoverClusterInvoker 在调用失败时，会自动切换 Invoker 进行重试。默认确配置下，Dubbo 会使用这个类作为缺省 Cluster Invoker。

#### 3.2.2 FailbackClusterInvoker
FailbackClusterInvoker 会在调用失败后，返回一个空结果给服务提供者。并通过定时任务对失败的调用进行重传，适合执行消息通知等操作。

#### 3.2.3 FailfastClusterInvoker
FailfastClusterInvoker 只会进行一次调用，失败后立即抛出异常。适用于幂等操作，比如新增记录。

#### 3.2.4 FailsafeClusterInvoker
FailsafeClusterInvoker 是一种失败安全的 Cluster Invoker。所谓的失败安全是指，当调用过程中出现异常时，FailsafeClusterInvoker 仅会打印异常，而不会抛出异常。适用于写入审计日志等操作。

#### 3.2.5 ForkingClusterInvoker
ForkingClusterInvoker 会在运行时通过线程池创建多个线程，并发调用多个服务提供者。只要有一个服务提供者成功返回了结果，doInvoke 方法就会立即结束运行。ForkingClusterInvoker 的应用场景是在一些对实时性要求比较高读操作（注意是读操作，并行写操作可能不安全）下使用，但这将会耗费更多的资源。

#### 3.2.6 BroadcastClusterInvoker
BroadcastClusterInvoker 会逐个调用每个服务提供者，如果其中一台报错，在循环调用结束后，BroadcastClusterInvoker 会抛出异常。该类通常用于通知所有提供者更新缓存或日志等本地资源信息