org.apache.tomcat.util.net.SocketProcessorBase

## hierarchy
```
SocketProcessorBase (org.apache.tomcat.util.net)
    SocketProcessor in AprEndpoint (org.apache.tomcat.util.net)
    SocketProcessor in Nio2Endpoint (org.apache.tomcat.util.net)
    SocketProcessor in NioEndpoint (org.apache.tomcat.util.net)
```

## define
```java
public abstract class SocketProcessorBase<S> implements Runnable {

    protected SocketWrapperBase<S> socketWrapper;
    protected SocketEvent event;

    public SocketProcessorBase(SocketWrapperBase<S> socketWrapper, SocketEvent event) {
        reset(socketWrapper, event);
    }


    public void reset(SocketWrapperBase<S> socketWrapper, SocketEvent event) {
        Objects.requireNonNull(event);
        this.socketWrapper = socketWrapper;
        this.event = event;
    }


    @Override
    public final void run() {
        synchronized (socketWrapper) {
            // It is possible that processing may be triggered for read and
            // write at the same time. The sync above makes sure that processing
            // does not occur in parallel. The test below ensures that if the
            // first event to be processed results in the socket being closed,
            // the subsequent events are not processed.
            if (socketWrapper.isClosed()) {
                return;
            }
            doRun();
        }
    }


    protected abstract void doRun();
}
```

## SocketProcessorBase.run()

```mermaid
sequenceDiagram
    
    %% socket -> 通信端点
    SocketProcessorBase->>NioEndpoint.SocketProcessor: doRun()
    
    %% 通信端点 -> 连接处理
    NioEndpoint.SocketProcessor->>AbstractProtocol.ConnectionHandler: process()
    
    %% 连接处理 -> 所有协议处理器
    AbstractProtocol.ConnectionHandler->>AbstractProcessorLight: process()
    
    %% http协议处理器
    AbstractProcessorLight->>Http11Processor: service()
    
    %% 链接适配器 
    Http11Processor->>CoyoteAdapter: service()
    CoyoteAdapter->>StandardEngineValve: invoke()
```

## seq
```plantuml
@startuml
' socket -> 通信端点
SocketProcessorBase -> NioEndpoint.SocketProcessor: doRun()

' 通信端点 -> 连接处理
NioEndpoint.SocketProcessor -> AbstractProtocol.ConnectionHandler: process()

' 连接处理 -> 所有协议处理器
AbstractProtocol.ConnectionHandler -> AbstractProcessorLight: process()

' http协议处理器
AbstractProcessorLight -> Http11Processor: service()

' 链接适配器 
Http11Processor -> CoyoteAdapter: service()

' 容器处理
CoyoteAdapter -> StandardEngineValve: invoke()
@enduml
```