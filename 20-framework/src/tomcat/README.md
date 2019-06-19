## tomcat
* javax.servlet
  * [FilterChain](/20-framework/src/tomcat/javax.servlet/FilterChain.md)
* [catalina](/20-framework/src/tomcat/catalina/README.md)
  * authenticator
    * [AuthenticatorBase](/20-framework/src/tomcat/catalina/authenticator/AuthenticatorBase.md)
  * connector
    * [Connector](/20-framework/src/tomcat/catalina/connector/Connector.md)
    * [CoyoteAdapter](/20-framework/src/tomcat/catalina/connector/CoyoteAdapter.md)
  * core
    * [ApplicationContext](/20-framework/src/tomcat/catalina/core/ApplicationContext.md)
    * [ApplicationFilterChain](/20-framework/src/tomcat/catalina/core/ApplicationFilterChain.md)
    * [StandardContextValve](/20-framework/src/tomcat/catalina/core/StandardContextValve.md)
    * [StandardEngineValve](/20-framework/src/tomcat/catalina/core/StandardEngineValve.md)
    * [StandardHostValve](/20-framework/src/tomcat/catalina/core/StandardHostValve.md)
    * [StandardWrapperValve](/20-framework/src/tomcat/catalina/core/StandardWrapperValve.md)
  * filters
  * loader
  * startup
  * util
    * [LifecycleBase](/20-framework/src/tomcat/catalina/util/LifecycleBase.md)
    * [LifecycleMBeanBase](/20-framework/src/tomcat/catalina/util/LifecycleMBeanBase.md)
  * valves
    * [ErrorReportValve](/20-framework/src/tomcat/catalina/valves/ErrorReportValve.md)
    * [ValveBase](/20-framework/src/tomcat/catalina/valves/ValveBase.md)
  * [Lifecycle](/20-framework/src/tomcat/catalina/Lifecycle.md)
  * [Container](/20-framework/src/tomcat/catalina/Container.md)
  * [Service](/20-framework/src/tomcat/catalina/Service.md)
  * [Pipeline](/20-framework/src/tomcat/catalina/Pipeline.md)
  * [Valve](/20-framework/src/tomcat/catalina/Valve.md)
* [coyote](/20-framework/src/tomcat/coyote/README.md)
  * ajp
  * http2
  * http11
  * [Adapter](/20-framework/src/tomcat/coyote/Adapter.md)
  * [Processor](/20-framework/src/tomcat/coyote/Processor.md)
  * [ProtocolHandler](/20-framework/src/tomcat/coyote/ProtocolHandler.md)
  * [Request](/20-framework/src/tomcat/coyote/Request.md)
  * [Response](/20-framework/src/tomcat/coyote/Response.md)
* juli
* naming
* tomcat
  * uitl
    * [NioChannel](/20-framework/src/tomcat/tomcat/util/net/NioChannel.md)
    * [NioEndpoint](/20-framework/src/tomcat/tomcat/util/net/NioEndpoint.md)
    * [LimitLatch](/20-framework/src/tomcat/tomcat/util/threads/LimitLatch.md)
    * [TaskQueue](/20-framework/src/tomcat/tomcat/util/threads/TaskQueue.md)
    * [TaskThread](/20-framework/src/tomcat/tomcat/util/threads/TaskThread.md)
  * websocket
    * [WsFilter](/20-framework/src/tomcat/tomcat/websocket/WsFilter.md)
  * [AbstractEndpoint](/20-framework/src/tomcat/tomcat/AbstractEndpoint.md)
  * [SocketProcessorBase](/20-framework/src/tomcat/tomcat/SocketProcessorBase.md)
  

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

## links
* [《Tomcat架构解析》刘光瑞](/99-book/notes/21-server/Tomcat架构解析.md)