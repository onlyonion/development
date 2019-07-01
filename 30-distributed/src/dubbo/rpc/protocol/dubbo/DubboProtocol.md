com.alibaba.dubbo.rpc.protocol.dubbo.DubboProtocol

* ReentrantLock
* ConcurrentHashMap
* ConcurrentHashSet

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

interface Protocol {
    + int getDefaultPort()
    + <T> Exporter<T> export(Invoker<T> invoker) throws RpcException
    + <T> Invoker<T> refer(Class<T> type, URL url) throws RpcException
    + void destroy()
}
Protocol ^.. AbstractProtocol
Protocol ..> Exporter
Protocol ..> Invoker

abstract class AbstractProtocol {
    .. 已导出的服务 ..
    # final Map<String, Exporter<?>> exporterMap
    # final Set<Invoker<?>> invokers
    # static String serviceKey(URL url)
}

AbstractProtocol ^-- DubboProtocol
class DubboProtocol #orange {
    - final Map<String, ExchangeServer> serverMap
    - final Map<String, ReferenceCountExchangeClient> referenceClientMap
    - final ConcurrentMap<String, LazyConnectExchangeClient> ghostClientMap
    - final ConcurrentMap<String, String> stubServiceMethodsMap
    - ExchangeHandler requestHandler
    - static DubboProtocol
    .. 导出服务 ..
    + <T> Exporter<T> export(Invoker<T> invoker)
    .. 打开服务 ..
    - void openServer(URL url)
    .. 创建服务 ..
    - ExchangeServer createServer(URL url)
    .. 引入服务 ..
    + <T> Invoker<T> refer(Class<T> serviceType, URL url) 
    - ExchangeClient[] getClients(URL url)
    - ExchangeClient getSharedClient(URL url)
    - ExchangeClient initClient(URL url)
}

DubboProtocol o-- ReentrantLock
DubboProtocol o-- DubboInvoker
DubboProtocol "1" o-- "*" ExchangeServer
DubboProtocol o-- Exporter
DubboProtocol o-- ExchangeClient
DubboProtocol o-- ExchangeHandler

interface Invoker<T>
Invoker ^.. AbstractInvoker
abstract class AbstractInvoker<T>
AbstractInvoker ^-- DubboInvoker
class DubboInvoker

@enduml

```

## methods
openServer(URL url)

ExchangeServer createServer(URL url)

