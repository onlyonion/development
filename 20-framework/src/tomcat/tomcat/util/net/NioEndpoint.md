org.apache.tomcat.util.net.NioEndpoint
org.apache.tomcat.util.net.NioEndpoint

## hierarchy
```
AbstractEndpoint (org.apache.tomcat.util.net)
    AbstractJsseEndpoint (org.apache.tomcat.util.net)
        NioEndpoint (org.apache.tomcat.util.net)
```

## define
```
@startuml

class NioEndpoint #orange {
    - NioSelectorPool selectorPool
    - ServerSocketChannel serverSock
    - volatile CountDownLatch stopLatch
    -  Poller[] pollers
}

'''''''''''AbstractEndpoint AbstractJsseEndpoint NioEndpoint'''''''''''

abstract class AbstractJsseEndpoint
abstract class AbstractEndpoint {
    # Acceptor[] acceptors
    - int maxConnections = 10000
    - Executor executor = null
    - int port
    - InetAddress address
    - int maxThreads = 200
    - Handler handler
}
AbstractJsseEndpoint <|-- NioEndpoint
AbstractEndpoint <|-- AbstractJsseEndpoint
interface Handler {
    + SocketState process(SocketWrapperBase socket, SocketEvent status)
}
AbstractEndpoint +-- Handler

'''''''''''内部类 Poller'''''''''''''''''''''''''''''''''''''''''''''''
class Poller #orange {
    - Selector selector
    - final SynchronizedQueue<PollerEvent> events = new SynchronizedQueue<>()
}
NioEndpoint +-- Poller
Runnable <|.. Poller

'''''''''''内部类 SocketProcessor''''''''''''''''''''''''''''''''''''''''
class SocketProcessor #orange {
    - Selector selector
    - final SynchronizedQueue<PollerEvent> events = new SynchronizedQueue<>()
    # void doRun()
    
}
abstract class SocketProcessorBase {
    + final void run()
    # abstract void doRun()
}

NioEndpoint +-- SocketProcessor
SocketProcessorBase <|-- SocketProcessor
Runnable <|.. SocketProcessorBase

@enduml
```
## fileds
private int pollerThreadCount = Math.min(2,Runtime.getRuntime().availableProcessors())