com.alibaba.dubbo.remoting.transport.netty.NettyServer

## hierarchy
```
AbstractPeer (com.alibaba.dubbo.remoting.transport)
    AbstractEndpoint (com.alibaba.dubbo.remoting.transport)
        AbstractServer (com.alibaba.dubbo.remoting.transport)
            NettyServer (com.alibaba.dubbo.remoting.transport.netty)
```
## define
```plantuml
@startuml

abstract class AbstractPeer
AbstractPeer *-- ChannelHandler

abstract class AbstractEndpoint
AbstractEndpoint *--Codec2

abstract class AbstractServer 
AbstractServer *-- ExecutorService

class NettyServer
NettyServer *-- ServerBootstrap

AbstractPeer ^-- AbstractEndpoint
AbstractEndpoint ^-- AbstractServer
AbstractServer ^-- NettyServer

@enduml
```