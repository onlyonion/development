
## servlet
```yuml
// {type:class}

// 1. Servlet
[Servlet{bg:wheat}]
[GenericServlet{bg:wheat}]
[HttpServlet{bg:wheat}]

// 1.1 Servlet的实现
[Servlet]^-.-[GenericServlet]
[ServletConfig]^-.-[GenericServlet]
[GenericServlet]++-[ServletConfig]

// 1.2 servlet接口、配置、上下文
[Servlet]++-[ServletConfig]
[ServletConfig]++-[ServletContext]

// 1.3 请求-响应模型
[ServletRequest]++-[AsyncContext]
[ServletRequest]++-[ServletContext]
[ServletRequest]++-[RequestDispatcher]
[ServletRequest]++-[DispatcherType]

[ServletResponse]

// 1.4 http协议的servlet
[GenericServlet]^-[HttpServlet]

// 依赖
[Servlet]uses->[ServletRequest]
[Servlet]uses->[ServletResponse]

// 2. 事件
[ServletContextListener]uses->[ServletContextEvent]
[ServletContextEvent]uses->[ServletContext]


// 3. Filter
[Filter]
[FilterChain]
[FilterConfig]++-[ServletContext]

// 3.1 注册表
[Registration]^-[FilterRegistration]

// 依赖
[Filter]uses->[FilterConfig]
[Filter]uses->[ServletRequest]
[Filter]uses->[ServletResponse]
[FilterChain]uses->[ServletRequest]
[FilterChain]uses->[ServletResponse]

```