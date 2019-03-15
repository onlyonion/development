org.springframework.web.servlet.FrameworkServlet

## hierachy
```
GenericServlet (javax.servlet)
    HttpServlet (javax.servlet.http)
        HttpServletBean (org.springframework.web.servlet)
            FrameworkServlet (org.springframework.web.servlet)
                DispatcherServlet (org.springframework.web.servlet)
```

## define
* 内部类
* 静态域
* 实例域
* 实例方法
  * processRequest()
  * doService()

```plantuml
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
    .. 抽象服务方法 ..
    # abstract void doService(HttpServletRequest request, HttpServletResponse response)
}
HttpServletBean <|-- FrameworkServlet
ApplicationContextAware <|.. FrameworkServlet


@enduml
```