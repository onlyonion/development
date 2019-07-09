org.apache.tomcat.util.net.AbstractEndpoint
## tcp
TCP协议：端对端的、面向字节流、面向连接的、全双工可靠的传输层协议

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

'''''''''''''''''''''''AbstractEndpoint'''''''''''''''''''''''''''
abstract class AbstractEndpoint<S> {
    # Acceptor[] acceptors
    - int maxConnections = 10000
    - int acceptCount = 100
    - int minSpareThreads = 10
    - int maxThreads = 200
    # int acceptorThreadCount = 1
    - Handler handler
}

AbstractEndpoint o- LimitLatch
AbstractEndpoint +-- Handler
AbstractEndpoint +- BindState

interface Handler<S>
Handler +-- SocketState
Handler <|.. ConnectionHandler

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

enum BindState {
    UNBOUND, 
    BOUND_ON_INIT, 
    BOUND_ON_START
}

'''''''''''''''''''''''''''Acceptor'''''''''''''''''''''''''''
abstract class Acceptor {
enum AcceptorState {
    NEW
    RUNNING
    PAUSED
    ENDED
}
AbstractEndpoint +-- Acceptor
Acceptor +-- AcceptorState
enum AcceptorState {
    NEW, 
    RUNNING, 
    PAUSED, 
    ENDED
}

'''''''''''''''''''''''''AbstractJsseEndpoint'''''''''''''''''''''''''
abstract class AbstractJsseEndpoint<S> #orange
class NioEndpoint #yellow
class Nio2Endpoint #yellow {
    setMaxConnections(-1)
}

'''''''''''''''''''''''''AprEndpoint'''''''''''''''''''''''''
class AprEndpoint #orange {
    setMaxConnections(8 * 1024)
}

AbstractEndpoint ^-- AbstractJsseEndpoint
AbstractJsseEndpoint ^-- NioEndpoint
AbstractJsseEndpoint ^-- Nio2Endpoint

NioEndpoint +-- NioEndpoint.Acceptor
Acceptor ^.. NioEndpoint.Acceptor

AbstractEndpoint ^-- AprEndpoint

@enduml
```

## methods

### createExecutor()
```
public void createExecutor() {
    internalExecutor = true;
    TaskQueue taskqueue = new TaskQueue();
    TaskThreadFactory tf = new TaskThreadFactory(getName() + "-exec-", daemon, getThreadPriority());
    executor = new ThreadPoolExecutor(getMinSpareThreads(), getMaxThreads(), 60, TimeUnit.SECONDS,taskqueue, tf);
    taskqueue.setParent( (ThreadPoolExecutor) executor);
}
```