com.alibaba.dubbo.rpc.protocol.dubbo.DubboCodec

## hierarchy
```
AbstractCodec (com.alibaba.dubbo.remoting.transport)
    TransportCodec (com.alibaba.dubbo.remoting.transport.codec)
        TelnetCodec (com.alibaba.dubbo.remoting.telnet.codec)
            ExchangeCodec (com.alibaba.dubbo.remoting.exchange.codec)
                DubboCodec (com.alibaba.dubbo.rpc.protocol.dubbo)
DubboCodec (com.alibaba.dubbo.rpc.protocol.dubbo)
    ExchangeCodec (com.alibaba.dubbo.remoting.exchange.codec)
    Codec2 (com.alibaba.dubbo.remoting)
```

## define

```plantuml
@startuml

interface Codec2 
abstract class AbstractCodec 
class TransportCodec 
class TelnetCodec 
class ExchangeCodec 
class DubboCodec 

Codec2 ^.. AbstractCodec
AbstractCodec ^-- TransportCodec
TransportCodec ^-- TelnetCodec
TelnetCodec ^-- ExchangeCodec
ExchangeCodec ^-- DubboCodec

Codec2 ^.. DubboCodec

@enduml
```
