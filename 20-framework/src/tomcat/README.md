
## socket request-response 
* [SocketProcessorBase](/20-framework/src/tomcat/tomcat/SocketProcessorBase.md)
* [CoyoteAdapter](/20-framework/src/tomcat/catalina/connector/CoyoteAdapter.md)

## package
```
javax.servlet servlet规范

org.apache.
    catalina 容器实现
    coyote 链接器
    naming
    tomcat
```

## tomcat package

tomcat-embed-core-8.5.23

```
    catalina
        connector
            Connector
            Request
            Response
        filters
        loader
            WebappClassLoader
            WebappClassLoaderBase
            WebappLoader
        mapper
            Mapper
        realm
        servlets
            DefaultServlet
        session
        ssi
        startup
        users
        valves
        webresources
        
        Cluster
        Container
        ContainerEvent
        ContainerListener
        ContainerServlet    
        Context
        Engine
        Executor
        Host
        Lifecycle
        LifecycleEvent
        LifecycleListener
        LifecycleState
        
        Server
        Service
        Session
        SessionListener
        Wrapper
        
        Pipeline
        Valve
    coyote
        ajp
        http2
        http11
        
        Adapter
        Proccessor
        ProtocolHandler
        UpgradeProtocol
    juli
    naming
    tomcat
        jni
        util
            digester
            file
            http
            net
                jsse
                openssl
                
                AbstractEndpoint
                AbstractEndpoint.Acceptor
                NioChannel
                NioEndpoint
                NioSelectorPool
```
