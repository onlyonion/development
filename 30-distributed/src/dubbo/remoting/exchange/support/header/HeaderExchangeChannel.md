com.alibaba.dubbo.remoting.exchange.support.header.HeaderExchangeChannel

## hierarchy
```
ExchangeChannel (com.alibaba.dubbo.remoting.exchange)
    ExchangeClient (com.alibaba.dubbo.remoting.exchange)
        HeaderExchangeClient (com.alibaba.dubbo.remoting.exchange.support.header)
        LazyConnectExchangeClient (com.alibaba.dubbo.rpc.protocol.dubbo)
        ReferenceCountExchangeClient (com.alibaba.dubbo.rpc.protocol.dubbo)
    HeaderExchangeChannel (com.alibaba.dubbo.remoting.exchange.support.header)
```
## define

```plantuml
@startuml

interface ExchangeChannel
ExchangeChannel ^.. HeaderExchangeChannel

class HeaderExchangeChannel {
    - final Channel       channel
    + ResponseFuture request(Object request, int timeout)
}

HeaderExchangeChannel ..> Request
HeaderExchangeChannel ..> DefaultFuture

class Request {
    - static final AtomicLong INVOKE_ID
    - final long mId
    - String mVersion
    - Object mData
}

class DefaultFuture

@enduml
```

## method

### request
RpcInvocation -> Request