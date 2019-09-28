
## catalina start
spring-context
```
refresh:507, AbstractApplicationContext (org.springframework.context.support)
configureAndRefreshWebApplicationContext:444, ContextLoader (org.springframework.web.context)
initWebApplicationContext:326, ContextLoader (org.springframework.web.context)
contextInitialized:107, ContextLoaderListener (org.springframework.web.context)

// 
listenerStart:5099, StandardContext (org.apache.catalina.core)/////////////////////////////////////////////listenerStart
startInternal:5615, StandardContext (org.apache.catalina.core)
start:147, LifecycleBase (org.apache.catalina.util)

// 
addChildInternal:899, ContainerBase (org.apache.catalina.core)
addChild:875, ContainerBase (org.apache.catalina.core)
addChild:652, StandardHost (org.apache.catalina.core)
manageApp:1863, HostConfig (org.apache.catalina.startup)

invoke0:-1, NativeMethodAccessorImpl (sun.reflect)
invoke:57, NativeMethodAccessorImpl (sun.reflect)
invoke:43, DelegatingMethodAccessorImpl (sun.reflect)
invoke:606, Method (java.lang.reflect)

// mbeans modeler
invoke:301, BaseModelMBean (org.apache.tomcat.util.modeler)
invoke:819, DefaultMBeanServerInterceptor (com.sun.jmx.interceptor)
invoke:801, JmxMBeanServer (com.sun.jmx.mbeanserver)
createStandardContext:618, MBeanFactory (org.apache.catalina.mbeans)
createStandardContext:565, MBeanFactory (org.apache.catalina.mbeans)

// jmx
invoke0:-1, NativeMethodAccessorImpl (sun.reflect)
invoke:57, NativeMethodAccessorImpl (sun.reflect)
invoke:43, DelegatingMethodAccessorImpl (sun.reflect)
invoke:606, Method (java.lang.reflect)
invoke:301, BaseModelMBean (org.apache.tomcat.util.modeler)
invoke:819, DefaultMBeanServerInterceptor (com.sun.jmx.interceptor)
invoke:801, JmxMBeanServer (com.sun.jmx.mbeanserver)

// rmi
doOperation:1487, RMIConnectionImpl (javax.management.remote.rmi)
access$300:97, RMIConnectionImpl (javax.management.remote.rmi)
run:1328, RMIConnectionImpl$PrivilegedOperation (javax.management.remote.rmi)
doPrivilegedOperation:1420, RMIConnectionImpl (javax.management.remote.rmi)
invoke:848, RMIConnectionImpl (javax.management.remote.rmi)

// server
invoke0:-1, NativeMethodAccessorImpl (sun.reflect)
invoke:57, NativeMethodAccessorImpl (sun.reflect)
invoke:43, DelegatingMethodAccessorImpl (sun.reflect)
invoke:606, Method (java.lang.reflect)
dispatch:322, UnicastServerRef (sun.rmi.server)
run:202, Transport$2 (sun.rmi.transport)
run:199, Transport$2 (sun.rmi.transport)
doPrivileged:-1, AccessController (java.security)

// transport
serviceCall:198, Transport (sun.rmi.transport)

// tcp
handleMessages:567, TCPTransport (sun.rmi.transport.tcp)
run0:828, TCPTransport$ConnectionHandler (sun.rmi.transport.tcp)
access$400:619, TCPTransport$ConnectionHandler (sun.rmi.transport.tcp)
run:684, TCPTransport$ConnectionHandler$1 (sun.rmi.transport.tcp)
run:681, TCPTransport$ConnectionHandler$1 (sun.rmi.transport.tcp)
doPrivileged:-1, AccessController (java.security)
run:681, TCPTransport$ConnectionHandler (sun.rmi.transport.tcp)

// thread
runWorker:1145, ThreadPoolExecutor (java.util.concurrent)
run:615, ThreadPoolExecutor$Worker (java.util.concurrent)
run:745, Thread (java.lang)
```

## catalina start
springmvc-context
```
initStrategies:483, DispatcherServlet (org.springframework.web.servlet)
onRefresh:474, DispatcherServlet (org.springframework.web.servlet)
onApplicationEvent:804, FrameworkServlet (org.springframework.web.servlet)
onApplicationEvent:1118, FrameworkServlet$ContextRefreshListener (org.springframework.web.servlet)
onApplicationEvent:1114, FrameworkServlet$ContextRefreshListener (org.springframework.web.servlet)
onApplicationEvent:56, GenericApplicationListenerAdapter (org.springframework.context.event)
onApplicationEventInternal:107, SourceFilteringListener (org.springframework.context.event)
onApplicationEvent:71, SourceFilteringListener (org.springframework.context.event)
invokeListener:166, SimpleApplicationEventMulticaster (org.springframework.context.event)
multicastEvent:138, SimpleApplicationEventMulticaster (org.springframework.context.event)
publishEvent:381, AbstractApplicationContext (org.springframework.context.support)
publishEvent:335, AbstractApplicationContext (org.springframework.context.support)
finishRefresh:855, AbstractApplicationContext (org.springframework.context.support)

refresh:541, AbstractApplicationContext (org.springframework.context.support)
configureAndRefreshWebApplicationContext:666, FrameworkServlet (org.springframework.web.servlet)
createWebApplicationContext:632, FrameworkServlet (org.springframework.web.servlet)
createWebApplicationContext:680, FrameworkServlet (org.springframework.web.servlet)
initWebApplicationContext:551, FrameworkServlet (org.springframework.web.servlet)
initServletBean:492, FrameworkServlet (org.springframework.web.servlet)
init:136, HttpServletBean (org.springframework.web.servlet)
init:158, GenericServlet (javax.servlet)
initServlet:1282, StandardWrapper (org.apache.catalina.core)
loadServlet:1195, StandardWrapper (org.apache.catalina.core)
load:1085, StandardWrapper (org.apache.catalina.core)

loadOnStartup:5349, StandardContext (org.apache.catalina.core)/////////////////////////////////////////////loadOnStartup
```


## request-response
spring boot first
```
initStrategies:488, DispatcherServlet (org.springframework.web.servlet)
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

// tomcat-应用过滤器 OncePerRequestFilter CharacterEncodingFilter HiddenHttpMethodFilter WsFilter
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

// tomcat-pipeline-valve 引擎 错误报告 主机 认证器 上下文 包装器
invoke:199, StandardWrapperValve (org.apache.catalina.core) ////////////////////////////////////////////////////////////
invoke:96, StandardContextValve (org.apache.catalina.core)
invoke:478, AuthenticatorBase (org.apache.catalina.authenticator) // 认证器
invoke:140, StandardHostValve (org.apache.catalina.core)
invoke:81, ErrorReportValve (org.apache.catalina.valves) // 错误报告
invoke:87, StandardEngineValve (org.apache.catalina.core)

// tomcat-链接器适配器
service:342, CoyoteAdapter (org.apache.catalina.connector)

// tomcat-connnector protocol 协议连接处理
service:803, Http11Processor (org.apache.coyote.http11)
process:66, AbstractProcessorLight (org.apache.coyote)
process:868, AbstractProtocol$ConnectionHandler (org.apache.coyote)

// tomcat-connnector io 套接字、Nio端
doRun:1459, NioEndpoint$SocketProcessor (org.apache.tomcat.util.net)
run:49, SocketProcessorBase (org.apache.tomcat.util.net)

// jdk 任务线程、工作者
runWorker:1149, ThreadPoolExecutor (java.util.concurrent)
run:624, ThreadPoolExecutor$Worker (java.util.concurrent)

// tomcat task thread
run:61, TaskThread$WrappingRunnable (org.apache.tomcat.util.threads)
run:748, Thread (java.lang)
```