com.alibaba.dubbo.rpc.cluster.Cluster

## hierarchy
```
Cluster (com.alibaba.dubbo.rpc.cluster)
    FailfastCluster (com.alibaba.dubbo.rpc.cluster.support) 快速失败，只发起一次调用，失败立即报错，通常用于非幂等性的写操作。
    FailoverCluster (com.alibaba.dubbo.rpc.cluster.support) 失败转移，当出现失败，重试其它服务器，通常用于读操作，但重试会带来更长延迟。
    FailbackCluster (com.alibaba.dubbo.rpc.cluster.support) 失败自动恢复，后台记录失败请求，定时重发，通常用于消息通知操作。
    FailsafeCluster (com.alibaba.dubbo.rpc.cluster.support) 失败安全，出现异常时，直接忽略，通常用于写入审计日志等操作。
    ForkingCluster (com.alibaba.dubbo.rpc.cluster.support) 并行调用，只要一个成功即返回，通常用于实时性要求较高的操作，但需要浪费更多服务资源。
    BroadcastCluster (com.alibaba.dubbo.rpc.cluster.support)
    AvailableCluster (com.alibaba.dubbo.rpc.cluster.support)
    MergeableCluster (com.alibaba.dubbo.rpc.cluster.support)
    MockClusterWrapper (com.alibaba.dubbo.rpc.cluster.support.wrapper)
```
* 读 fail over
* 写 fail fast
* 重试 fail back
* 忽略 fail safe
* 并行 forking

## define
```java
@SPI(FailoverCluster.NAME)
public interface Cluster {
    <T> Invoker<T> join(Directory<T> directory) throws RpcException;
}
```

## 集群管理
目、路、集、衡
* 集群容错 Cluster.join(Directory<T>)   
* 目录服务 Directory.list(Invocation) 
* 路由规则 Router.route(List<Invoker<T>>, URL, Invocation) 
* 负载均衡 LoadBalance.select(List<Invoker<T>>, URL, Invocation) 

```yuml
// {type:class}

[Cluster{bg:wheat}]
[FailfastCluster{bg:wheat}]
[FailoverCluster{bg:wheat}]

[AbstractClusterInvoker{bg:tan}]
[FailfastClusterInvoker{bg:tan}]
[FailoverClusterInvoker{bg:tan}]

[Directory{bg:tomato}]
[RegistryDirectory{bg:tomato}]

[Router{bg:thistle}]
[MockInvokersSelector{bg:thistle}]
[ScriptRouter{bg:thistle}]

[LoadBalance{bg:slategray}]
[AbstractLoadBalance{bg:slategray}]
[LeastActiveLoadBalance{bg:slategray}]
[ConsistentHashLoadBalance{bg:slategray}]

// 1. 集群容错
[Cluster]^-.-[FailfastCluster]
[Cluster]^-.-[FailoverCluster]
[Cluster]^-.-[FailsafeCluster]
[Cluster]^-.-[FailbackCluster]
[Cluster]^-.-[BroadcastCluster]
[Cluster]^-.-[ForkingCluster]

// Invoker
[Invoker]^-.-[AbstractClusterInvoker]

[AbstractClusterInvoker]^-[FailfastClusterInvoker]
[AbstractClusterInvoker]^-[FailoverClusterInvoker]
[AbstractClusterInvoker]^-[FailsafeClusterInvoker]
[AbstractClusterInvoker]^-[FailbackClusterInvoker]
[AbstractClusterInvoker]^-[BroadcastClusterInvoker]
[AbstractClusterInvoker]^-[ForkingClusterInvoker]

// 依赖关系
[FailfastCluster]uses->[FailfastClusterInvoker]
[FailoverCluster]uses->[FailoverClusterInvoker]
[FailsafeCluster]uses->[FailsafeClusterInvoker]
[FailbackCluster]uses->[FailbackClusterInvoker]
[BroadcastCluster]uses->[BroadcastClusterInvoker]
[ForkingCluster]uses->[ForkingClusterInvoker]

// 2. 目录
[AbstractClusterInvoker]++-[Directory]
[Node]^-[Directory]
[Directory]^-.-[AbstractDirectory{bg:tomato}]

// 静态目录
[AbstractDirectory]^-[StaticDirectory]

// 注册目录
[AbstractDirectory]^-[RegistryDirectory]
[NotifyListener]^-.-[RegistryDirectory]

// 依赖
[Directory]uses->[Invocation]
[Directory]uses->[Invoker]

// 3. 路由
[Router]^-.-[MockInvokersSelector]
[Router]^-.-[ConditionRouter]
[Router]^-.-[ScriptRouter]

[RouterFactory]^-.-[FileRouterFactory]
[RouterFactory]^-.-[ConditionRouterFactory]
[RouterFactory]^-.-[ScriptRouterFactory]

// 依赖
[Directory]uses->[Router]
[ScriptRouterFactory]uses->[ScriptRouter]
[ConditionRouterFactory]uses->[ConditionRouter]

// 4. 负载均衡
[LoadBalance||+select()]
[LoadBalance]^-.-[AbstractLoadBalance]

// 继承、实现、聚合
[AbstractLoadBalance]^-[RandomLoadBalance]
[AbstractLoadBalance]^-[LeastActiveLoadBalance]
[AbstractLoadBalance]^-[RoundRobinLoadBalance]
[AbstractLoadBalance]^-[ConsistentHashLoadBalance]

// 依赖
[LoadBalance]uses->[Invoker]
[LoadBalance]uses->[URL]
[LoadBalance]uses->[Invocation]
```

## Configurator，ConfiguratorFactory

