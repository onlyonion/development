

## springboot first request onRefresh

```
onRefresh:480, DispatcherServlet (org.springframework.web.servlet)
initWebApplicationContext:560, FrameworkServlet (org.springframework.web.servlet)
initServletBean:494, FrameworkServlet (org.springframework.web.servlet)
init:171, HttpServletBean (org.springframework.web.servlet)
init:158, GenericServlet (javax.servlet)
initServlet:1183, StandardWrapper (org.apache.catalina.core)
allocate:795, StandardWrapper (org.apache.catalina.core)

invoke:133, StandardWrapperValve (org.apache.catalina.core) ////////////////////////////////////////////////////////////
invoke:96, StandardContextValve (org.apache.catalina.core)
invoke:478, AuthenticatorBase (org.apache.catalina.authenticator)
invoke:140, StandardHostValve (org.apache.catalina.core)
invoke:81, ErrorReportValve (org.apache.catalina.valves)
invoke:87, StandardEngineValve (org.apache.catalina.core)
service:342, CoyoteAdapter (org.apache.catalina.connector)
service:803, Http11Processor (org.apache.coyote.http11)
process:66, AbstractProcessorLight (org.apache.coyote)
process:868, AbstractProtocol$ConnectionHandler (org.apache.coyote)
doRun:1459, NioEndpoint$SocketProcessor (org.apache.tomcat.util.net)
run:49, SocketProcessorBase (org.apache.tomcat.util.net)
runWorker:1149, ThreadPoolExecutor (java.util.concurrent)
run:624, ThreadPoolExecutor$Worker (java.util.concurrent)
run:61, TaskThread$WrappingRunnable (org.apache.tomcat.util.threads)
run:748, Thread (java.lang)
```


## request-response

```
invoke0:-1, NativeMethodAccessorImpl (sun.reflect)
invoke:62, NativeMethodAccessorImpl (sun.reflect)
invoke:43, DelegatingMethodAccessorImpl (sun.reflect)
invoke:498, Method (java.lang.reflect)

// handler method
doInvoke:205, InvocableHandlerMethod (org.springframework.web.method.support)
invokeForRequest:133, InvocableHandlerMethod (org.springframework.web.method.support)
invokeAndHandle:97, ServletInvocableHandlerMethod (org.springframework.web.servlet.mvc.method.annotation)

// handler adapter
invokeHandlerMethod:827, RequestMappingHandlerAdapter (org.springframework.web.servlet.mvc.method.annotation)
handleInternal:738, RequestMappingHandlerAdapter (org.springframework.web.servlet.mvc.method.annotation)
handle:85, AbstractHandlerMethodAdapter (org.springframework.web.servlet.mvc.method)

// spring webmvc
doDispatch:967, DispatcherServlet (org.springframework.web.servlet)
doService:901, DispatcherServlet (org.springframework.web.servlet)
processRequest:970, FrameworkServlet (org.springframework.web.servlet)
doGet:861, FrameworkServlet (org.springframework.web.servlet)
service:635, HttpServlet (javax.servlet.http)

// servlet api
service:846, FrameworkServlet (org.springframework.web.servlet)
service:742, HttpServlet (javax.servlet.http)

// 应用过滤器
internalDoFilter:231, ApplicationFilterChain (org.apache.catalina.core)
doFilter:166, ApplicationFilterChain (org.apache.catalina.core)
doFilter:52, WsFilter (org.apache.tomcat.websocket.server)
internalDoFilter:193, ApplicationFilterChain (org.apache.catalina.core)
doFilter:166, ApplicationFilterChain (org.apache.catalina.core)
doFilterInternal:99, RequestContextFilter (org.springframework.web.filter)
doFilter:107, OncePerRequestFilter (org.springframework.web.filter)
internalDoFilter:193, ApplicationFilterChain (org.apache.catalina.core)
doFilter:166, ApplicationFilterChain (org.apache.catalina.core)
doFilterInternal:108, HttpPutFormContentFilter (org.springframework.web.filter)
doFilter:107, OncePerRequestFilter (org.springframework.web.filter)
internalDoFilter:193, ApplicationFilterChain (org.apache.catalina.core)
doFilter:166, ApplicationFilterChain (org.apache.catalina.core)
doFilterInternal:81, HiddenHttpMethodFilter (org.springframework.web.filter)
doFilter:107, OncePerRequestFilter (org.springframework.web.filter)
internalDoFilter:193, ApplicationFilterChain (org.apache.catalina.core)
doFilter:166, ApplicationFilterChain (org.apache.catalina.core)
doFilterInternal:197, CharacterEncodingFilter (org.springframework.web.filter)
doFilter:107, OncePerRequestFilter (org.springframework.web.filter)
internalDoFilter:193, ApplicationFilterChain (org.apache.catalina.core)
doFilter:166, ApplicationFilterChain (org.apache.catalina.core)

// pipeline-value 引擎 错误报告 主机 认证器 上下文 包装器
invoke:199, StandardWrapperValve (org.apache.catalina.core) ////////////////////////////////////////////////////////////
invoke:96, StandardContextValve (org.apache.catalina.core)
invoke:478, AuthenticatorBase (org.apache.catalina.authenticator) // 认证器
invoke:140, StandardHostValve (org.apache.catalina.core)
invoke:81, ErrorReportValve (org.apache.catalina.valves) // 错误报告
invoke:87, StandardEngineValve (org.apache.catalina.core)

// 链接器适配器
service:342, CoyoteAdapter (org.apache.catalina.connector)

// protocol 协议连接处理
service:803, Http11Processor (org.apache.coyote.http11)
process:66, AbstractProcessorLight (org.apache.coyote)
process:868, AbstractProtocol$ConnectionHandler (org.apache.coyote)

// io 套接字、Nio端
doRun:1459, NioEndpoint$SocketProcessor (org.apache.tomcat.util.net)
run:49, SocketProcessorBase (org.apache.tomcat.util.net)

// jdk 任务线程、工作者
runWorker:1149, ThreadPoolExecutor (java.util.concurrent)
run:624, ThreadPoolExecutor$Worker (java.util.concurrent)
run:61, TaskThread$WrappingRunnable (org.apache.tomcat.util.threads)
run:748, Thread (java.lang)
```