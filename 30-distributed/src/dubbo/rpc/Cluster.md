
```java

public interface Cluster {
    <T> Invoker<T> join(Directory<T> directory) throws RpcException;
}


```


## 集群管理
* Cluster.join(Directory<T>)  集群容错 
* Directory.list(Invocation) 目录服务
* Router.route(List<Invoker<T>>, URL, Invocation) 路由
* LoadBalance.select(List<Invoker<T>>, URL, Invocation) 负载均衡

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

## Merger

## Configurator，ConfiguratorFactory

