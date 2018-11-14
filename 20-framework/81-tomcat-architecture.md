#### Server

* Connector
* Container, Engine, Host, Wrapper, Context
* Lifecycle
* pipeline, valve
* Executor
* Boostrap, Catalina

#### ClassLoader

* bootstrap, extension, system
* tomcat classloader
* webapp classloader

#### tomcat package

```
catalina
    connector
        Connector
        Request
        Response
    mapper
        Mapper
        
    Lifecycle
    Server
    Service
    Container
    Engine
    Host
    Context
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
```

#### javax.servlet

```
annotation
descriptor
http
    Cookie
    HttpServletRequest
    HttpServertResponse
    HttpSession
resources

Filter
FilterChain
FilterConfig
Registration

Servlet
ServletConfig
ServletContext

ServletRequest
ServletResponse
```