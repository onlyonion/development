org.apache.tomcat.util.net.AbstractEndpoint

## hierarchy
```
AbstractEndpoint (org.apache.tomcat.util.net)
    AbstractJsseEndpoint (org.apache.tomcat.util.net)
        NioEndpoint (org.apache.tomcat.util.net)
        Nio2Endpoint (org.apache.tomcat.util.net)
    AprEndpoint (org.apache.tomcat.util.net)
```

## define
```plantuml
@startuml

abstract class AbstractEndpoint<S> {
    - int maxConnections = 10000
}


interface Handler<S>
AbstractEndpoint +-- Handler
Handler +-- SocketState
enum SocketState {
    OPEN, 
    CLOSED, 
    LONG, 
    ASYNC_END, 
    SENDFILE, 
    UPGRADING, 
    UPGRADED, 
    SUSPENDED
}

AbstractEndpoint +-- BindState
enum BindState {
    UNBOUND, 
    BOUND_ON_INIT, 
    BOUND_ON_START
}

abstract class AbstractJsseEndpoint<S>
class NioEndpoint
class Nio2Endpoint

class AprEndpoint

AbstractEndpoint ^-- AbstractJsseEndpoint
AbstractJsseEndpoint ^-- NioEndpoint
AbstractJsseEndpoint ^-- Nio2Endpoint

AbstractEndpoint ^-- AprEndpoint


@enduml
```