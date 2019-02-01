org.apache.tomcat.util.net.AbstractEndpoint
## tcp
TCP协议：端对端的、面向字节流、面向连接的、全双工可靠的传输层协议

## hierachy
```
AbstractEndpoint (org.apache.tomcat.util.net)
    AprEndpoint (org.apache.tomcat.util.net)
    AbstractJsseEndpoint (org.apache.tomcat.util.net)
        NioEndpoint (org.apache.tomcat.util.net)
        Nio2Endpoint (org.apache.tomcat.util.net)
```

## define
```
@startuml

abstract class AbstractEndpoint<S> {
    # Acceptor[] acceptors
    - int maxConnections = 10000
    - Executor executor = null
    - int port
    - InetAddress address
    - int maxThreads = 200
    - Handler handler
}

'''''''''''''''''''''''''''Handler'''''''''''''''''''''''''''
interface Handler {
    + SocketState process(SocketWrapperBase socket, SocketEvent status)
}
enum SocketState {
    OPEN
    CLOSED
    LONG
    ASYNC_END
    SENDFILE
    UPGRADING
    UPGRADED
    SUSPENDED
}

class ConnectionHandler {

}
AbstractEndpoint +- Handler
Handler +- SocketState
Handler <|.. ConnectionHandler

'''''''''''''''''''''''''''Acceptor'''''''''''''''''''''''''''
abstract class Acceptor {
enum AcceptorState {
    NEW
    RUNNING
    PAUSED
    ENDED
}
AbstractEndpoint +- Acceptor
Acceptor +- AcceptorState


'''''''''''''''''''''''''''Endpoint实现'''''''''''''''''''''''''''
AbstractEndpoint <|-- AprEndpoint
AbstractEndpoint <|-- AbstractJsseEndpoint
AbstractJsseEndpoint <|-- NioEndpoint
AbstractJsseEndpoint <|-- Nio2Endpoint
@enduml

```
