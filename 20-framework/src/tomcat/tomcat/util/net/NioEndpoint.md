
## define
```
@startuml

class NioEndpoint {
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
AbstractEndpoint +- Handler

'''''''''''内部类 Poller'''''''''''''''''''''''''''''''''''''''''''''''
class Poller {
    - Selector selector
    - final SynchronizedQueue<PollerEvent> events = new SynchronizedQueue<>()
}
NioEndpoint +- Poller
Runnable <|.. Poller

'''''''''''内部类 SocketProcessor''''''''''''''''''''''''''''''''''''''''
class SocketProcessor {
    - Selector selector
    - final SynchronizedQueue<PollerEvent> events = new SynchronizedQueue<>()
    # void doRun()
    
}
abstract class SocketProcessorBase {
    + final void run()
    # abstract void doRun()
}

NioEndpoint +- SocketProcessor
SocketProcessorBase <|-- SocketProcessor
Runnable <|.. SocketProcessorBase

@enduml
```
