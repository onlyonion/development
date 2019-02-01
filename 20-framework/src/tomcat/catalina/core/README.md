StandardContext
StandardContextValve

StandardEngine
StandardEngineValve

StandardHost
StandardHostValve

StandardPipeline
StandardServer
StandardService

StandardThreadExecutor

StandardWrapper
StandardWrapperFacade
StandardWrapperValve

### tomcat 加载 app servlet
```mermaid
sequenceDiagram
    HostConfig->>HostConfig:manageApp
    HostConfig->>StandardHost:addChild
    StandardHost->>ContainerBase:addChild
    ContainerBase->>ContainerBase:addChildInternal
    ContainerBase->>LifecycleBase:start
    LifecycleBase->>StandardContext:startInternal
    StandardContext->>StandardContext:loadOnStartup
    StandardContext->>StandardWrapper:load
    StandardContext->>StandardWrapper:loadServlet    
    StandardContext->>StandardWrapper:initServlet    
    StandardContext->>GenericServlet:init
```

### springmvc dispatcherServlet init
```mermaid
sequenceDiagram
    GenericServlet->>GenericServlet:init
    GenericServlet->>HttpServletBean:init
    HttpServletBean->>FrameworkServlet:initServletBean
    FrameworkServlet->>FrameworkServlet:initWebApplicationContext
    FrameworkServlet->>FrameworkServlet:createWebApplicationContext
    FrameworkServlet->>FrameworkServlet:createWebApplicationContext
    FrameworkServlet->>FrameworkServlet:configureAndRefreshWebApplicationContext
    FrameworkServlet->>AbstractApplicationContext:refresh
    FrameworkServlet->>FrameworkServlet:onRefresh
    FrameworkServlet->>DispatcherServlet:initStrategies
```