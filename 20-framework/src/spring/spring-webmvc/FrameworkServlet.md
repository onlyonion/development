


## hierachy
```
GenericServlet (javax.servlet)
    HttpServlet (javax.servlet.http)
        HttpServletBean (org.springframework.web.servlet)
            FrameworkServlet (org.springframework.web.servlet)
                DispatcherServlet (org.springframework.web.servlet)
```

## class
```
@startuml

'''''''''''''''''''''''''' servlet-api ''''''''''''''''''''''''''

interface Servlet {
    + void init(ServletConfig config)
    + void service(ServletRequest req, ServletResponse res)
    + void destroy()
}
interface ServletConfig {
    + ServletContext getServletContext()
}
interface ServletContext {
    + ServletContext getContext(String uripath)
    + ClassLoader getClassLoader()
}

abstract class GenericServlet {
    - transient ServletConfig config
    + void init()
}

Servlet <|.. GenericServlet
ServletConfig <|.. GenericServlet

abstract class HttpServlet {
    + void service(ServletRequest req, ServletResponse res)
    # void service(HttpServletRequest req, HttpServletResponse resp)
}
GenericServlet <|-- HttpServlet


'''''''''''''''''''''''''' spring-webmvc ''''''''''''''''''''''''''
interface EnvironmentCapable {
    + Environment getEnvironment()
}
interface EnvironmentAware {
    + void setEnvironment(Environment environment)
}

abstract class HttpServletBean {
    - ConfigurableEnvironment environment
    # void initServletBean()
}
HttpServlet <|-- HttpServletBean
EnvironmentCapable <|.. HttpServletBean
EnvironmentAware <|.. HttpServletBean

interface ApplicationContextAware

'''''''''''''''''''''''''' 框架小服务程序 ''''''''''''''''''''''''''
abstract class FrameworkServlet {
    - String contextConfigLocation
    - WebApplicationContext webApplicationContext
    # WebApplicationContext initWebApplicationContext()
    # void configureAndRefreshWebApplicationContext(ConfigurableWebApplicationContext wac)
    # WebApplicationContext createWebApplicationContext(ApplicationContext parent)
    # WebApplicationContext findWebApplicationContext()
    # void onRefresh(ApplicationContext context)
    # void processRequest(HttpServletRequest request, HttpServletResponse response)
    # abstract void doService(HttpServletRequest request, HttpServletResponse response)
}
HttpServletBean <|-- FrameworkServlet
ApplicationContextAware <|.. FrameworkServlet

'''''''''''''''''''''''''' 分配器(调度程序)小服务程序 ''''''''''''''''''''''''''
class DispatcherServlet {
    - MultipartResolver multipartResolver
    - LocaleResolver localeResolver
    - ThemeResolver themeResolver
    - List<HandlerMapping> handlerMappings
    - List<HandlerAdapter> handlerAdapters
    - List<HandlerExceptionResolver> handlerExceptionResolvers
    - RequestToViewNameTranslator viewNameTranslator
    - FlashMapManager flashMapManager
    - List<ViewResolver> viewResolvers
    # void doService(HttpServletRequest request, HttpServletResponse response) 
    # void doDispatch(HttpServletRequest request, HttpServletResponse response)
    # HttpServletRequest checkMultipart(HttpServletRequest request)
    # HandlerExecutionChain getHandler(HttpServletRequest request)
    # HandlerAdapter getHandlerAdapter(Object handler)
    - void applyDefaultViewName(HttpServletRequest request, ModelAndView mv)
    - void processDispatchResult(HttpServletRequest request, HttpServletResponse response,
        HandlerExecutionChain mappedHandler, ModelAndView mv, Exception exception) 
    # void render(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) 
}
FrameworkServlet <|-- DispatcherServlet

'''''''''''''''''''''''''' 处理器映射器 ''''''''''''''''''''''''''
interface HandlerMapping { 
    + HandlerExecutionChain getHandler(HttpServletRequest request)
}
DispatcherServlet o-- HandlerMapping

'''''''''''''''''''''''''' 处理器执行链 ''''''''''''''''''''''''''
class HandlerExecutionChain { 
    - final Object handler
    - HandlerInterceptor[] interceptors
    boolean applyPreHandle(HttpServletRequest request, HttpServletResponse response)
    void applyPostHandle(HttpServletRequest request, HttpServletResponse response, ModelAndView mv)
    void triggerAfterCompletion(HttpServletRequest request, HttpServletResponse response, Exception ex)
}

'''''''''''''''''''''''''' 处理器执行链-拦截器 ''''''''''''''''''''''''''
interface HandlerInterceptor {
    + boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
    + void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
    			ModelAndView modelAndView)
    + void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
   			 Exception ex)  			
}
DispatcherServlet ..> HandlerExecutionChain
HandlerExecutionChain o-- HandlerInterceptor

'''''''''''''''''''''''''' 处理器适配器 ''''''''''''''''''''''''''
interface HandlerAdapter {
    + boolean supports(Object handler)
    + ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) 
}

DispatcherServlet o-- HandlerAdapter

'''''''''''''''''''''''''' 视图解析器 ''''''''''''''''''''''''''
interface ViewResolver {
	+ View resolveViewName(String viewName, Locale locale)
}
DispatcherServlet o-- ViewResolver

'''''''''''''''''''''''''' 视图 ''''''''''''''''''''''''''
interface View {
    + void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response)
}
DispatcherServlet ..> View

class ModelAndView {
    - Object view
    - ModelMap model
}
DispatcherServlet ..> ModelAndView

@enduml
```