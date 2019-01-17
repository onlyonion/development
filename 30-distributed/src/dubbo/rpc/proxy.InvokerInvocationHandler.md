com.alibaba.dubbo.rpc.proxy.InvokerInvocationHandler

## refere调用过程

### 整体
```mermaid
sequenceDiagram
    InvokerInvocationHandler->>InvokerInvocationHandler:invoke(proxy, method, args)
    InvokerInvocationHandler->>RpcInvocation:new
    InvokerInvocationHandler->>MockClusterInvoker:invoke(invocation)
    
    %% 注册中心url
    MockClusterInvoker->>RegistryDirectory:getUrl()
    RegistryDirectory->>URL:getMethodParameter()
    
    alt no mock
        MockClusterInvoker->>AbstractClusterInvoker:invoke(invocation)
        MockClusterInvoker-->>InvokerInvocationHandler:返回Result，然后recreae()
    else force:direct mock
        MockClusterInvoker->>MockClusterInvoker:doMockInvoke()
    else fail-mock
        MockClusterInvoker->>FailfastClusterInvoker:invoke()
    end 
```

### cluster-router-loadbalance
```mermaid
sequenceDiagram
    %% 集群策略
    MockClusterInvoker->>AbstractClusterInvoker:invoke(invocation)
    %% 路由选择
    AbstractClusterInvoker->>AbstractClusterInvoker:list(invocation)
    AbstractClusterInvoker->>AbstractDirectory:list(invocation)
    %% 负载均衡
    AbstractClusterInvoker->>ExtensionLoader:getExtensionLoader(LoadBalance.class).getExtension()
    AbstractClusterInvoker->>FailfastClusterInvoker:doInvoke(invocation,invokers,loadbalance)
    %% 负载均衡
    FailfastClusterInvoker->>AbstractClusterInvoker:select()使用loadbalance选择invoker
    AbstractClusterInvoker->>AbstractClusterInvoker:doSelect()
    AbstractClusterInvoker-->>FailfastClusterInvoker:返回负载均衡后的invoke的包装InvokerWrapper
    %% 返回
    FailfastClusterInvoker-->>AbstractClusterInvoker:doInvoke()返回Result
    AbstractClusterInvoker-->>MockClusterInvoker:invoke()返回Result
```

### protocol
```mermaid
sequenceDiagram
    %% 集群策略 快速失败
    FailfastClusterInvoker->>InvokerWrapper:invoke(invocation)
    InvokerWrapper->>ProtocolFilterWrapper动态代理:invoke(next, invocation)
    
    ProtocolFilterWrapper动态代理->>ConsumerContextFilter:invoke(next, invocation)
    ConsumerContextFilter->>RpcContext:设置invoker、invocation、本地地址、远程地址等
    ConsumerContextFilter-->>ProtocolFilterWrapper动态代理:返回
    
    %% future
    ProtocolFilterWrapper动态代理->>FutureFilter:invoke(next, invocation)
    FutureFilter->>FutureFilter:fireInvokeCallback()

    FutureFilter-->>ProtocolFilterWrapper动态代理:invoke(invocation)
    ProtocolFilterWrapper动态代理->>MonitorFilter:(next, invocation)
    
    %% 监控器
    MonitorFilter->>MonitorFilterListener:(invocation)
    MonitorFilterListener->>AbstractInvoker:(invocation)

    %% dubboinvoker
    AbstractInvoker->>DubboInvoker:(invocation)
    
    %% future同步异步
    alt isAsync
        FutureFilter->>FutureFilter:asyncCallback(invoker, invocation)
    else
        FutureFilter->>FutureFilter:syncCallback(invoker, invocation, result)
    end

    %% 返回
    FutureFilter-->>ProtocolFilterWrapper动态代理:invoke返回Result
    ProtocolFilterWrapper动态代理-->>ConsumerContextFilter:invoke返回Result
    ConsumerContextFilter-->>InvokerWrapper:invoke返回Result
    InvokerWrapper->>FailfastClusterInvoker:invoke返回Result
```

### dubbo-invoker 交换层、传输层
```mermaid
sequenceDiagram
    %% dubboinvoker
    AbstractInvoker->>DubboInvoker:(invocation)
    
    alt isOneway
        DubboInvoker->>ExchangeClient:setFuture(null)
    else isAsync
        DubboInvoker->>ExchangeClient:setFuture(创建FutureAdapter)
    else other
        %% 交换层
        DubboInvoker->>ExchangeClient:request()
        ExchangeClient->>ReferenceCountExchangeClient:request(request, timeout)
        ReferenceCountExchangeClient->>HeaderExchangeClient:request(request, timeout)
        HeaderExchangeClient->>HeaderExchangeChannel:request(request, timeout)
        
        %% 传输层
        HeaderExchangeChannel->>AbstractPeer:send()
        AbstractPeer->>AbstractClient:send()
        AbstractClient->>NettyChannel:send()
        NettyChannel->>AbstractChannel:write()
    end
```
###


### Invocation
* Invocation 封装了方法名称、方法参数类型、方法参数值、附加属性

```yuml
// {type:class}

[Invocation]^-.-[RpcInvocation]
[RpcInvocation]^-[DecodeableRpcInvocation]

[Node]^-[Invoker]
[Invocation]++->[Invoker]


```