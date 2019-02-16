org.apache.coyote.ProtocolHandler

## hierachy
```
ProtocolHandler (org.apache.coyote)
    AbstractProtocol (org.apache.coyote)
        AbstractAjpProtocol (org.apache.coyote.ajp)
            AjpNioProtocol (org.apache.coyote.ajp)
            AjpAprProtocol (org.apache.coyote.ajp)
            AjpNio2Protocol (org.apache.coyote.ajp)
        AbstractHttp11Protocol (org.apache.coyote.http11)
            AbstractHttp11JsseProtocol (org.apache.coyote.http11)
                Http11NioProtocol (org.apache.coyote.http11)
                Http11Nio2Protocol (org.apache.coyote.http11)
            Http11AprProtocol (org.apache.coyote.http11)
```

## define

```plantuml
@startuml

interface ProtocolHandler {
    + Adapter getAdapter()
    + Executor getExecutor()
    ..生命周期..
    + void init()
    + void start()
    + void pause()
    + void resume()
    + void stop()
    + void destroy()
    ..升级协议..
    + UpgradeProtocol[] findUpgradeProtocols()
}

abstract class AbstractProtocol {
    - final AbstractEndpoint endpoint
    - Handler handler
    # Adapter adapter
    # abstract Processor createProcessor()
}
ProtocolHandler <|.. AbstractProtocol

class ConnectionHandler {
    - final AbstractProtocol proto
    - final Map<S,Processor> connections
    + SocketState process(SocketWrapperBase socket, SocketEvent status)
}
AbstractProtocol +- ConnectionHandler

@enduml
```