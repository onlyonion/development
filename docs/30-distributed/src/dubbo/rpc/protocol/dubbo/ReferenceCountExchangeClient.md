com.alibaba.dubbo.rpc.protocol.dubbo.ReferenceCountExchangeClient

* AtomicInteger
* ConcurrentMap

## hierarchy
```
ReferenceCountExchangeClient (com.alibaba.dubbo.rpc.protocol.dubbo)
    ExchangeClient (com.alibaba.dubbo.remoting.exchange)
        Client (com.alibaba.dubbo.remoting)
        ExchangeChannel (com.alibaba.dubbo.remoting.exchange)
```

## define
```plantuml
@startuml

class ReferenceCountExchangeClient

@enduml
```

```java
final class ReferenceCountExchangeClient implements ExchangeClient {
    private final URL url;
    private final AtomicInteger refenceCount = new AtomicInteger(0);

    //    private final ExchangeHandler handler;
    private final ConcurrentMap<String, LazyConnectExchangeClient> ghostClientMap;
    private ExchangeClient client;
}    
```