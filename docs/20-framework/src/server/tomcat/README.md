# tomcat

## src

### javax.servlet
* [javax.servlet](/docs/20-framework/src/tomcat/javax.servlet/README.md) servlet规范
  * [Servlet](/docs/20-framework/src/tomcat/javax.servlet/Servlet.md)
  * [Filter](/docs/20-framework/src/tomcat/javax.servlet/Filter.md)
  * [FilterChain](/20-framework/src/tomcat/javax.servlet/FilterChain.md)

### catalina
* [catalina](/20-framework/src/tomcat/catalina/README.md) Servlet容器实现
  * authenticator 认证器
    * [AuthenticatorBase](/20-framework/src/tomcat/catalina/authenticator/AuthenticatorBase.md)
  * [connector](/docs/20-framework/src/tomcat/catalina/connector/README.md) 连接器
    * [Connector](/20-framework/src/tomcat/catalina/connector/Connector.md)
    * [CoyoteAdapter](/20-framework/src/tomcat/catalina/connector/CoyoteAdapter.md) 连接器适配器，实现了Connector与Mapper、Container的解耦。
  * core
    * [ApplicationContext](/20-framework/src/tomcat/catalina/core/ApplicationContext.md)
    * [ApplicationFilterChain](/20-framework/src/tomcat/catalina/core/ApplicationFilterChain.md)
    * [StandardThreadExecutor](/docs/20-framework/src/tomcat/catalina/core/StandardThreadExecutor.md)
    * [StandardContextValve](/20-framework/src/tomcat/catalina/core/StandardContextValve.md)
    * [StandardEngineValve](/20-framework/src/tomcat/catalina/core/StandardEngineValve.md)
    * [StandardHostValve](/20-framework/src/tomcat/catalina/core/StandardHostValve.md)
    * [StandardWrapperValve](/20-framework/src/tomcat/catalina/core/StandardWrapperValve.md)
  * filters 过滤器--职责链
  * [loader](/docs/20-framework/src/tomcat/catalina/startup/README.md) 加载器，资源加载
    * [WebappClassLoader](/docs/20-framework/src/tomcat/catalina/loader/WebappClassLoader.md) web应用类加载器，加载WEB-INF/classes和WEB-INF/lib，只对当前web应用可见
  * mapper 请求映射
    * [Mapper](/docs/20-framework/src/tomcat/catalina/mapper/Mapper.md) 请求映射，请求地址映射到具体的容器处理。
    * [MapperListener](/docs/20-framework/src/tomcat/catalina/mapper/MapperListener.md)
  * startup 启动
    * [Bootstrap](/docs/20-framework/src/tomcat/catalina/startup/Bootstrap.md) 应用服务器启动入口。创建Catalina实例，根据参数调用Catalina方法完成启动、停止。
    * [Catalina](/docs/20-framework/src/tomcat/catalina/startup/Catalina.md)
    * [Tomcat](/docs/20-framework/src/tomcat/catalina/startup/Tomcat.md) 编程式启动，嵌入式启动
  * util
    * [LifecycleBase](/20-framework/src/tomcat/catalina/util/LifecycleBase.md)
    * [LifecycleMBeanBase](/20-framework/src/tomcat/catalina/util/LifecycleMBeanBase.md)
  * valves 阀门
    * [ErrorReportValve](/20-framework/src/tomcat/catalina/valves/ErrorReportValve.md)
    * [ValveBase](/20-framework/src/tomcat/catalina/valves/ValveBase.md)
  * [Lifecycle](/20-framework/src/tomcat/catalina/Lifecycle.md) 生命周期组件
  * [Server](/docs/20-framework/src/tomcat/catalina/Server.md) 整个Servlet容器
  * [Service](/20-framework/src/tomcat/catalina/Service.md) 一个或多个Connector的集合，这些Connector共享一个Container来处理请求。一个Tomcat实例可包含多个Service，它们彼此独立。
  * [Executor](/docs/20-framework/src/tomcat/catalina/Executor.md) 同一Service中的组件可以共享一个线程池
  * [Container](/docs/20-framework/src/tomcat/catalina/container.Container.md) 容器
  * [Engine](/docs/20-framework/src/tomcat/catalina/container.Engine.md)
  * [Host](/docs/20-framework/src/tomcat/catalina/container.Host.md)
  * [Context](/docs/20-framework/src/tomcat/catalina/container.Context.md)
  * [Wrapper](/docs/20-framework/src/tomcat/catalina/container.Wrapper.md)
  * [Pipeline](/20-framework/src/tomcat/catalina/Pipeline.md) 管道，职责链
  * [Valve](/20-framework/src/tomcat/catalina/Valve.md) 阀门，职责链上的处理器

### coyote
* [coyote](/20-framework/src/tomcat/coyote/README.md) 连接器实现，网络协议处理
  * ajp
  * http2
    * [Http2Protocol](/docs/20-framework/src/tomcat/coyote/http2/Http2Protocol.md)
  * http11
    * [Http11Processor](/docs/20-framework/src/tomcat/coyote/http11/Http11Processor.md)
    * [Http11NioProtocol](/docs/20-framework/src/tomcat/coyote/http11/Http11NioProtocol.md)
  * [Adapter](/20-framework/src/tomcat/coyote/Adapter.md)
  * [Processor](/20-framework/src/tomcat/coyote/Processor.md)
  * [ProtocolHandler](/20-framework/src/tomcat/coyote/ProtocolHandler.md)
  * [Request](/20-framework/src/tomcat/coyote/Request.md)
  * [Response](/20-framework/src/tomcat/coyote/Response.md)
* juli 服务器日志
* naming 命名服务

### tomcat
* tomcat
  * uitl
    * net 套接字编程
      * [AbstractEndpoint](/docs/20-framework/src/tomcat/tomcat/util/net/AbstractEndpoint.md)
      * [NioEndpoint](/20-framework/src/tomcat/tomcat/util/net/NioEndpoint.md)
      * [NioChannel](/20-framework/src/tomcat/tomcat/util/net/NioChannel.md)
      * [Nio2Endpoint](/docs/20-framework/src/tomcat/tomcat/util/net/Nio2Endpoint.md)
      * [Nio2Channel](/docs/20-framework/src/tomcat/tomcat/util/net/Nio2Channel.md)
      * [SocketProcessorBase](/docs/20-framework/src/tomcat/tomcat/util/net/SocketProcessorBase.md)
      * [SocketWrapperBase](/docs/20-framework/src/tomcat/tomcat/util/net/SocketWrapperBase.md)
    * threads 线程处理
      * [LimitLatch](/20-framework/src/tomcat/tomcat/util/threads/LimitLatch.md)
      * [TaskQueue](/20-framework/src/tomcat/tomcat/util/threads/TaskQueue.md)
      * [TaskThread](/20-framework/src/tomcat/tomcat/util/threads/TaskThread.md)
      * [ThreadPoolExecutor](/docs/20-framework/src/tomcat/tomcat/util/threads/ThreadPoolExecutor.md)
  * websocket
    * [WsFilter](/20-framework/src/tomcat/tomcat/websocket/WsFilter.md)
  * [AbstractEndpoint](/20-framework/src/tomcat/tomcat/AbstractEndpoint.md)
  * [SocketProcessorBase](/20-framework/src/tomcat/tomcat/util/net/SocketProcessorBase.md)
  
## package

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

## book
* [《Tomcat架构解析》刘光瑞](/99-book/notes/21-server/Tomcat架构解析.md)
* [《Tomcat内核设计剖析》汪健](/99-book/notes/21-server/Tomcat内核设计剖析.md)