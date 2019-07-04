com.alibaba.dubbo.rpc.protocol.dubbo.DubboInvoker

* AtomicPositiveInteger 对AtomicInteger的包装
* ReentrantLock

## hierarchy
```
AbstractInvoker (com.alibaba.dubbo.rpc.protocol)
    ThriftInvoker (com.alibaba.dubbo.rpc.protocol.thrift)
    1 in RedisProtocol (com.alibaba.dubbo.rpc.protocol.redis)
    1 in MemcachedProtocol (com.alibaba.dubbo.rpc.protocol.memcached)
    2 in AbstractProxyProtocol (com.alibaba.dubbo.rpc.protocol)
    ChannelWrappedInvoker (com.alibaba.dubbo.rpc.protocol.dubbo)
    InjvmInvoker (com.alibaba.dubbo.rpc.protocol.injvm)
    DubboInvoker (com.alibaba.dubbo.rpc.protocol.dubbo)
```

## define
* doInvoke
  * isOneway 异步不带回调
  * isAsync 异步带回调
  * sync 同步

```plantuml
@startuml

'''''''''''''''''''''调用者''''''''''''''''''''''''
interface Invoker<T>

abstract class AbstractInvoker<T> {
    + Result invoke(Invocation inv)
    # abstract Result doInvoke(Invocation invocation)
}
Invoker ^.. AbstractInvoker

class DubboInvoker<T> {
    - final ExchangeClient[]      clients
    - final AtomicPositiveInteger index
    - final String                version
    - final ReentrantLock         destroyLock
    - final Set<Invoker<?>>       invokers
    # Result doInvoke(final Invocation invocation)
}
AbstractInvoker ^-- DubboInvoker

'''''''''''''''''''''调用器''''''''''''''''''''''''
interface Invocation
class RpcInvocation
Invocation ^.. RpcInvocation
DubboInvoker ..> Invocation

'''''''''''''''''''''交换层''''''''''''''''''''''''
interface Client
interface ExchangeClient
interface ExchangeChannel {
    + ResponseFuture request(Object request, int timeout)
}

Client ^-- ExchangeClient
ExchangeChannel ^-- ExchangeClient

ExchangeClient ^.. HeaderExchangeClient
ExchangeClient ^.. LazyConnectExchangeClient
ExchangeClient ^.. ReferenceCountExchangeClient

DubboInvoker o-- ExchangeClient

@enduml
```

## invoke
* ReferenceCountExchangeClient
* HeaderExchangeClient
* HeaderExchangeChannel