com.alibaba.dubbo.rpc.proxy.InvokerInvocationHandler实现jdk的调用处理器

## invoke时序
![invoke](../../../img/dubbo-consumer-invoke-provider.png)

## 调用过程

* InvokerInvocationHandler
* RpcInvocation
* MockClusterInvoker
* AbstractClusterInvoker
* AbstractDirectory RegistryDirectory
* AbstractClusterInvoker FailfastClusterInvoker
* FailfastClusterInvoker
* LoadBalance

* InvokerWrapper
* ProtocolFilterWrapper$1 过滤器链
* ConsumerContextFilter

### 整体

```mermaid
sequenceDiagram
    %% 1.1 调用处理器
    Proxy->>InvokerInvocationHandler:invoke(proxy, method, args)
    
    %% 1.2 Object方法处理
    %% opt 方法的声明是object.class
    %%     InvokerInvocationHandler->>Method:method.invoke(invoker, args)
    %% end
    %% opt 判断是toString(),hashCode(),equals(obj)
    %%     InvokerInvocationHandler->>Invoker:调用invoke的相应方法
    %% end
    
    %% 2.1 构造调用器
    %% InvokerInvocationHandler->>RpcInvocation:new方法与参数的封装
    
    %% 2.2 这里的invoke封装了模拟数据、集群策略、负载均衡、异步同步处理、过滤链的invoke
    InvokerInvocationHandler->>MockClusterInvoker:invoke(invocation)
    
    %% 3.1 回声集群 获取注册中心url 获取参数
    MockClusterInvoker->>RegistryDirectory:getUrl()
    RegistryDirectory->>URL:getMethodParameter()
    
    %% 3.2 根据模拟集群调用器选择
    alt no mock
        MockClusterInvoker->>AbstractClusterInvoker:invoke(invocation)
        MockClusterInvoker-->>InvokerInvocationHandler:返回Result，然后recreae()
    else force:direct mock
        MockClusterInvoker->>MockClusterInvoker:doMockInvoke()
    else fail-mock
        MockClusterInvoker->>FailfastClusterInvoker:invoke()
    end 
```

### 集群、路由、负载均衡 cluster-router-loadbalance
```mermaid
sequenceDiagram
    %% 4.1 集群策略
    MockClusterInvoker->>AbstractClusterInvoker:invoke(invocation)
    
    %% 4.2 路由选择 Directory.list()
    AbstractClusterInvoker->>AbstractClusterInvoker:list(invocation)
    AbstractClusterInvoker->>AbstractDirectory:list(invocation)
    
    %% 4.3 负载均衡 select()
    AbstractClusterInvoker->>ExtensionLoader:getExtensionLoader(LoadBalance.class).getExtension()
    AbstractClusterInvoker->>FailfastClusterInvoker:doInvoke(invocation,invokers,loadbalance)
    
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
    %% 协议过滤器包装
    InvokerWrapper->>ProtocolFilterWrapper$1:invoke(next, invocation)
    
    %% 消息上下文过滤器
    ProtocolFilterWrapper$1->>ConsumerContextFilter:invoke(next, invocation)
    ConsumerContextFilter->>RpcContext:设置invoker、invocation、本地地址、远程地址等
    ConsumerContextFilter-->>ProtocolFilterWrapper$1:返回
    
    %% future
    ProtocolFilterWrapper$1->>FutureFilter:invoke(next, invocation)
    FutureFilter->>FutureFilter:fireInvokeCallback()

    FutureFilter-->>ProtocolFilterWrapper$1:invoke(invocation)
    ProtocolFilterWrapper$1->>MonitorFilter:(next, invocation)
    
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
    FutureFilter-->>ProtocolFilterWrapper$1:invoke返回Result
    ProtocolFilterWrapper$1-->>ConsumerContextFilter:invoke返回Result
    ConsumerContextFilter-->>InvokerWrapper:invoke返回Result
    InvokerWrapper->>FailfastClusterInvoker:invoke返回Result
```

### dubbo-invoker 交换层、传输层

```mermaid
sequenceDiagram
    %% dubbo调用者
    AbstractInvoker->>DubboInvoker:(invocation)
    
    %% 调用方式
    alt isOneway
        %% 一次性调用
        DubboInvoker->>ExchangeClient:setFuture(null)
        DubboInvoker->>ExchangeClient:send(inv, isSent)
        
    else isAsync
        %% 异步调用
        DubboInvoker->>ExchangeClient:request(inv, timeout)
        DubboInvoker->>ExchangeClient:setFuture(创建FutureAdapter)
        
    else other
        DubboInvoker->>ExchangeClient:request(request, timeout)
    end
```