
## 
```
@startuml

interface ServletContext {
    + String getContextPath()
    + ServletContext getContext(String uripath)
    + URL getResource(String path)
    + InputStream getResourceAsStream(String path)
    + RequestDispatcher getRequestDispatcher(String path)
    .. 小服务程序管理 ..
    + ServletRegistration.Dynamic addServlet(String servletName, String className)
    + <T extends Servlet> T createServlet(Class<T> clazz)
    .. 过滤器管理 ..
    + FilterRegistration.Dynamic addFilter(String filterName, String className)
    + <T extends Filter> T createFilter(Class<T> clazz)
    + FilterRegistration getFilterRegistration(String filterName)
    .. 监听器管理 ..
    + <T extends EventListener> void addListener(T t)
    + <T extends EventListener> T createListener(Class<T> c)
    .. 类加载 ..
    + ClassLoader getClassLoader()
}

interface RequestDispatcher {
    + void forward(ServletRequest request, ServletResponse response)
}

class ApplicationContext {
    - final StandardContext context
    - final Service service
}

ServletContext <|.. ApplicationContext
ServletContext o-- ServletContext
ServletContext o-- RequestDispatcher

class StandardContext {
    - InstanceManager instanceManager
    - Loader loader
    - Map<String, ApplicationFilterConfig> filterConfigs 
    - Map<String, FilterDef> filterDefs 
    - final ContextFilterMaps filterMaps
    - List<Object> applicationEventListenersList
    + Wrapper createWrapper()
    + void addChild(Container child) 
    + void addFilterDef(FilterDef filterDef)
    + void addApplicationEventListener(Object listener)
}
ApplicationContext o-- StandardContext

@enduml
```