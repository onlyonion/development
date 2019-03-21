com.alibaba.dubbo.rpc.Invoker

## 1. hierarchy
* 服务端代理调用者
* mock数据调用者
* 调用者包装器
* 集群调用者
* 消费端调用者

```
Invoker (com.alibaba.dubbo.rpc)
    1 in ProtocolFilterWrapper (com.alibaba.dubbo.rpc.protocol)
    AbstractProxyInvoker (com.alibaba.dubbo.rpc.proxy)
        1 in JdkProxyFactory (com.alibaba.dubbo.rpc.proxy.jdk)
        1 in JavassistProxyFactory (com.alibaba.dubbo.rpc.proxy.javassist)
    MockClusterInvoker (com.alibaba.dubbo.rpc.cluster.support.wrapper)
    DelegateInvoker (com.alibaba.dubbo.rpc.support)
    ListenerInvokerWrapper (com.alibaba.dubbo.rpc.listener)
    InvokerWrapper (com.alibaba.dubbo.rpc.protocol)
        InvokerDelegete in RegistryDirectory (com.alibaba.dubbo.registry.integration)
        InvokerDelegete in RegistryProtocol (com.alibaba.dubbo.registry.integration)
    AbstractClusterInvoker (com.alibaba.dubbo.rpc.cluster.support)
        1 in AvailableCluster (com.alibaba.dubbo.rpc.cluster.support)
        AvailableClusterInvoker (com.alibaba.dubbo.rpc.cluster.support)
        ForkingClusterInvoker (com.alibaba.dubbo.rpc.cluster.support)
        FailsafeClusterInvoker (com.alibaba.dubbo.rpc.cluster.support)
        FailoverClusterInvoker (com.alibaba.dubbo.rpc.cluster.support)
        FailfastClusterInvoker (com.alibaba.dubbo.rpc.cluster.support)
        BroadcastClusterInvoker (com.alibaba.dubbo.rpc.cluster.support)
        FailbackClusterInvoker (com.alibaba.dubbo.rpc.cluster.support)
    MergeableClusterInvoker (com.alibaba.dubbo.rpc.cluster.support)
    MockInvoker (com.alibaba.dubbo.rpc.support)
    AbstractInvoker (com.alibaba.dubbo.rpc.protocol)
        ThriftInvoker (com.alibaba.dubbo.rpc.protocol.thrift)
        1 in RedisProtocol (com.alibaba.dubbo.rpc.protocol.redis)
        1 in MemcachedProtocol (com.alibaba.dubbo.rpc.protocol.memcached)
        2 in AbstractProxyProtocol (com.alibaba.dubbo.rpc.protocol)
        ChannelWrappedInvoker (com.alibaba.dubbo.rpc.protocol.dubbo)
        InjvmInvoker (com.alibaba.dubbo.rpc.protocol.injvm)
        DubboInvoker (com.alibaba.dubbo.rpc.protocol.dubbo)
```

## 2. 类图

```yuml
// {type:class}

[Node]^-[Invoker||+getInterface();+invoke(invocation)]
[Node]++-[URL]


// 1. 模拟数据集群调用器
[Invoker]^-[MockClusterInvoker{bg:wheat}]
[MockClusterInvoker]++->[Directory]
[MockClusterInvoker]++->[Invoker]

// 2. 抽象集群调用器
[Invoker]^-.-[AbstractClusterInvoker{bg:thistle}]
// 2.1 快速失败集群调用器
[AbstractClusterInvoker]^-[FailfastClusterInvoker]
// 2.2 失效转移
[AbstractClusterInvoker]^-[FailoverClusterInvoker]
// 2.3 失败安全
[AbstractClusterInvoker]^-[FailsafeClusterInvoker]
// 2.4 失败恢复
[AbstractClusterInvoker]^-[FailbackClusterInvoker]
// 2.5 广播
[AbstractClusterInvoker]^-[BroadcastClusterInvoker]
// 2.6 并行
[AbstractClusterInvoker]^-[ForkingClusterInvoker]

// 3. 调用器包装
[Invoker]^-.-[InvokerWrapper]

// 4. 虚拟机内、远程间
[Invoker]^-.-[AbstractInvoker{bg:snow}]
[AbstractInvoker]^-[InjvmInvoker]
[AbstractInvoker]^-[DubboInvoker]

// 5. 代理调用器 jdk动态代理、javassist代理
[Invoker]^-.-[AbstractProxyInvoker{bg:snow}]
[AbstractProxyInvoker]^-[JdkProxyFactory]
[AbstractProxyInvoker]^-[JavassistProxyFactory]

```