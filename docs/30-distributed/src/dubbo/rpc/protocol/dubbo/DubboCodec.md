com.alibaba.dubbo.rpc.protocol.dubbo.DubboCodec
- 编解码

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
interface ScopeModelAware
abstract class AbstractCodec 
class TransportCodec 
class TelnetCodec 
class ExchangeCodec #orange {
  // header length
  # static final int HEADER_LENGTH = 16
  // magic header
  # static final short MAGIC = short 0xdabb
  // message flag

}
class DubboCodec 

Codec2 ^.. AbstractCodec
ScopeModelAware ^.. AbstractCodec

AbstractCodec ^-- TransportCodec
TransportCodec ^-- TelnetCodec
TelnetCodec ^-- ExchangeCodec
ExchangeCodec ^-- DubboCodec

Codec2 ^.. DubboCodec #gray : delete from dubbo3

@enduml
```
