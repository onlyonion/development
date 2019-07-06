org.apache.tomcat.util.net.SocketProcessorBase

## hierarchy

```
SocketProcessorBase (org.apache.tomcat.util.net)
    SocketProcessor in AprEndpoint (org.apache.tomcat.util.net)
    SocketProcessor in Nio2Endpoint (org.apache.tomcat.util.net)
    SocketProcessor in NioEndpoint (org.apache.tomcat.util.net)
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