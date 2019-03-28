com.alibaba.dubbo.rpc.cluster.support.AbstractClusterInvoker

## hierarchy
```
AbstractClusterInvoker (com.alibaba.dubbo.rpc.cluster.support)
    1 in AvailableCluster (com.alibaba.dubbo.rpc.cluster.support)
    AvailableClusterInvoker (com.alibaba.dubbo.rpc.cluster.support)
    BroadcastClusterInvoker (com.alibaba.dubbo.rpc.cluster.support)
    FailbackClusterInvoker (com.alibaba.dubbo.rpc.cluster.support)
    FailfastClusterInvoker (com.alibaba.dubbo.rpc.cluster.support)
    FailoverClusterInvoker (com.alibaba.dubbo.rpc.cluster.support)
    FailsafeClusterInvoker (com.alibaba.dubbo.rpc.cluster.support)
    ForkingClusterInvoker (com.alibaba.dubbo.rpc.cluster.support)
```

## define

```plantuml
@startuml
interface Invoker<T>
Invoker ^.. AbstractClusterInvoker

abstract class AbstractClusterInvoker<T>{
    # final Directory<T> directory
    # final boolean availablecheck
    - AtomicBoolean destroyed
    - volatile Invoker<T> stickyInvoker
    .. 选择-负载均衡 ..
    # Invoker<T> select(LoadBalance loadbalance, Invocation invocation, 
        List<Invoker<T>> invokers, List<Invoker<T>> selected)
    - Invoker<T> doselect(LoadBalance loadbalance, Invocation invocation, 
        List<Invoker<T>> invokers, List<Invoker<T>> selected)
    .. 再选择-重新负载均衡 ..
    - Invoker<T> reselect(LoadBalance loadbalance, Invocation invocation,
        List<Invoker<T>> invokers, List<Invoker<T>> selected, boolean availablecheck)  
    + Result invoke(final Invocation invocation)   
    .. 抽象调用方法 ..
    # abstract Result doInvoke(Invocation invocation, List<Invoker<T>> invokers,
         LoadBalance loadbalance)   
    .. 列表 ..
    # List<Invoker<T>> list(Invocation invocation)      
}


@enduml
```