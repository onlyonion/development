com.alibaba.dubbo.remoting.exchange.support.header.HeaderExchangeChannel

## define

```plantuml
@startuml

interface ExchangeChannel

class HeaderExchangeChannel {
    - final Channel       channel
    + ResponseFuture request(Object request, int timeout)
}

ExchangeChannel ^.. HeaderExchangeChannel

@enduml
```