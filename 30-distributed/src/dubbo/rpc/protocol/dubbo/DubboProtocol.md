com.alibaba.dubbo.rpc.protocol.dubbo.DubboProtocol

## hierarchy
```
AbstractProtocol (com.alibaba.dubbo.rpc.protocol)
    RedisProtocol (com.alibaba.dubbo.rpc.protocol.redis)
    InjvmProtocol (com.alibaba.dubbo.rpc.protocol.injvm)
    DubboProtocol (com.alibaba.dubbo.rpc.protocol.dubbo)
    ThriftProtocol (com.alibaba.dubbo.rpc.protocol.thrift)
    AbstractProxyProtocol (com.alibaba.dubbo.rpc.protocol)
    MemcachedProtocol (com.alibaba.dubbo.rpc.protocol.memcached)
    MockProtocol (com.alibaba.dubbo.rpc.support)
```

## define
```plantuml
@startuml

interface Protocol
abstract class AbstractProtocol {
    # final Map<String, Exporter<?>> exporterMap
    # final Set<Invoker<?>> invokers
}
Protocol ^.. AbstractProtocol

class DubboProtocol {
    + final ReentrantLock lock
    - final Map<String, ExchangeServer> serverMap
    - final Map<String, ReferenceCountExchangeClient> referenceClientMap
    - final ConcurrentMap<String, LazyConnectExchangeClient> ghostClientMap
    - final ConcurrentMap<String, String> stubServiceMethodsMap
    - ExchangeHandler requestHandler
    - static DubboProtocol
    .. 导出服务 ..
    + <T> Exporter<T> export(Invoker<T> invoker)
    - void openServer(URL url)
    - ExchangeServer createServer(URL url)
    .. 引入服务 ..
    + <T> Invoker<T> refer(Class<T> serviceType, URL url) 
    - ExchangeClient[] getClients(URL url)
    - ExchangeClient getSharedClient(URL url)
    - ExchangeClient initClient(URL url)
}
AbstractProtocol ^-- DubboProtocol

@enduml

```