
## spring-webmvc
* [FrameworkServlet](/20-framework/src/spring/spring-webmvc/FrameworkServlet.md)
* [DispatcherServlet](/20-framework/src/spring/spring-webmvc/DispatcherServlet.md)
* [HandlerAdapter](/20-framework/src/spring/spring-webmvc/HandlerAdapter.md)
* [HandlerExecutionChain](/20-framework/src/spring/spring-webmvc/HandlerExecutionChain.md)
* [HandlerInterceptor](/20-framework/src/spring/spring-webmvc/HandlerInterceptor.md)
* [HandlerMapping](/20-framework/src/spring/spring-webmvc/HandlerMapping.md)

## package
org.springframework.web.servlet
```
    config
    handler
    i18n
    mvc
        annotation
        condition
        method
            annotation
                RequestMappingHandlerMapping
                RequestMappingHandlerAdapter
                ServletInvocableHandlerMethod
            AbstractHandlerMethodAdapter
        multiaction
        support
            AbstractControllerUrlHandlerMapping
            ControllerClassNameHandlerMapping
    resource
    support
    tags
    theme
    view
        document
        feed
        freemarker
        groovy
        jasperreports
        json
        script
        tiles2
        tiles3
        velocity
        xml
        xslt
    AsyncHandlerInterceptor
    DispatcherServlet   springmvc 核心分发请求的Servlet
    DispatcherServlet.properties
    FlashMap
    FlashMapManager
    FrameworkServlet    springmv框架基础，提供applicationContext；子类必须实现doService()方法
    HandlerAdapter
    HandlerExceptionResolver
    HandlerExecutionChain
    HandlerInterceptor
    HandlerMapping
    HttpServletBean     抽象类，与springmvc整合的Bean
    LocaleContextResolver
    LocaleResolver
    ModelAndView
    ModelAndViewDefiningException
    NoHandlerFoundException
    RequestToViewNameTranslator
    ResourceServlet
    SmartView
    ThemeResolver
    View
    ViewRendererServlet
    ViewResolver
```

## init

## request-response
```
initStrategies:488, DispatcherServlet (org.springframework.web.servlet)
onRefresh:480, DispatcherServlet (org.springframework.web.servlet)
initWebApplicationContext:560, FrameworkServlet (org.springframework.web.servlet)
initServletBean:494, FrameworkServlet (org.springframework.web.servlet)
init:171, HttpServletBean (org.springframework.web.servlet)
init:158, GenericServlet (javax.servlet)
initServlet:1183, StandardWrapper (org.apache.catalina.core)

allocate:795, StandardWrapper (org.apache.catalina.core)

invoke:133, StandardWrapperValve (org.apache.catalina.core)
invoke:96, StandardContextValve (org.apache.catalina.core)
invoke:478, AuthenticatorBase (org.apache.catalina.authenticator)
invoke:140, StandardHostValve (org.apache.catalina.core)
invoke:81, ErrorReportValve (org.apache.catalina.valves)
invoke:87, StandardEngineValve (org.apache.catalina.core)

// adapter
service:342, CoyoteAdapter (org.apache.catalina.connector)

// protocol
service:803, Http11Processor (org.apache.coyote.http11)
process:66, AbstractProcessorLight (org.apache.coyote)

// handler
process:868, AbstractProtocol$ConnectionHandler (org.apache.coyote)
doRun:1459, NioEndpoint$SocketProcessor (org.apache.tomcat.util.net)
run:49, SocketProcessorBase (org.apache.tomcat.util.net)

// threadpool
runWorker:1149, ThreadPoolExecutor (java.util.concurrent)
run:624, ThreadPoolExecutor$Worker (java.util.concurrent)
run:61, TaskThread$WrappingRunnable (org.apache.tomcat.util.threads)
run:748, Thread (java.lang)
```
```