
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