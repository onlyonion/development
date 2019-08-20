com.alibaba.dubbo.common.Node

## hierarchy
```
Node (com.alibaba.dubbo.common)
    Directory (com.alibaba.dubbo.rpc.cluster)
        AbstractDirectory (com.alibaba.dubbo.rpc.cluster.directory)
            RegistryDirectory (com.alibaba.dubbo.registry.integration)
            StaticDirectory (com.alibaba.dubbo.rpc.cluster.directory)
    Invoker (com.alibaba.dubbo.rpc)
        AbstractClusterInvoker (com.alibaba.dubbo.rpc.cluster.support)
        AbstractInvoker (com.alibaba.dubbo.rpc.protocol)
        AbstractProxyInvoker (com.alibaba.dubbo.rpc.proxy)
        DelegateInvoker (com.alibaba.dubbo.rpc.support)
        InvokerWrapper (com.alibaba.dubbo.rpc.protocol)
        ListenerInvokerWrapper (com.alibaba.dubbo.rpc.listener)
        MergeableClusterInvoker (com.alibaba.dubbo.rpc.cluster.support)
        MockClusterInvoker (com.alibaba.dubbo.rpc.cluster.support.wrapper)
        MockInvoker (com.alibaba.dubbo.rpc.support)
    Monitor (com.alibaba.dubbo.monitor)
        DubboMonitor (com.alibaba.dubbo.monitor.dubbo)
    Registry (com.alibaba.dubbo.registry)
        AbstractRegistry (com.alibaba.dubbo.registry.support)
            FailbackRegistry (com.alibaba.dubbo.registry.support)
                DubboRegistry (com.alibaba.dubbo.registry.dubbo)
                MulticastRegistry (com.alibaba.dubbo.registry.multicast)
                RedisRegistry (com.alibaba.dubbo.registry.redis)
                ZookeeperRegistry (com.alibaba.dubbo.registry.zookeeper)
```

## define
```java
public interface Node {
    URL getUrl();
    boolean isAvailable();
    void destroy();
}
```