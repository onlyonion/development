
## 1. init
### 1.1 init export stack
```
// netty 异步非阻塞
// 服务层 CS架构
doOpen:69, NettyServer (com.alibaba.dubbo.remoting.transport.netty)
<init>:62, AbstractServer (com.alibaba.dubbo.remoting.transport)
<init>:63, NettyServer (com.alibaba.dubbo.remoting.transport.netty)

// 传输层 端到端 绑定地址、监听端口
bind:33, NettyTransporter (com.alibaba.dubbo.remoting.transport.netty)
bind:-1, Transporter$Adpative (com.alibaba.dubbo.remoting)                      扩展点自适应
bind:57, Transporters (com.alibaba.dubbo.remoting)

// 交换层 报文头交换
bind:41, HeaderExchanger (com.alibaba.dubbo.remoting.exchange.support.header)
bind:71, Exchangers (com.alibaba.dubbo.remoting.exchange)

// dubbo通讯协议
createServer:279, DubboProtocol (com.alibaba.dubbo.rpc.protocol.dubbo)
openServer:258, DubboProtocol (com.alibaba.dubbo.rpc.protocol.dubbo)
export:245, DubboProtocol (com.alibaba.dubbo.rpc.protocol.dubbo)

// 协议过滤器包装
export:56, ProtocolListenerWrapper (com.alibaba.dubbo.rpc.protocol)
export:94, ProtocolFilterWrapper (com.alibaba.dubbo.rpc.protocol)
export:-1, Protocol$Adpative (com.alibaba.dubbo.rpc)                            扩展点自适应

// 本地协议导出、远程协议导出
doLocalExport:163, RegistryProtocol (com.alibaba.dubbo.registry.integration)    
export:116, RegistryProtocol (com.alibaba.dubbo.registry.integration)

// filter-listener
export:54, ProtocolListenerWrapper (com.alibaba.dubbo.rpc.protocol)
export:92, ProtocolFilterWrapper (com.alibaba.dubbo.rpc.protocol)
export:-1, Protocol$Adpative (com.alibaba.dubbo.rpc)                            扩展点自适应

// ServiceConfig
doExportUrlsFor1Protocol:550, ServiceConfig (com.alibaba.dubbo.config)
doExportUrls:346, ServiceConfig (com.alibaba.dubbo.config)
doExport:307, ServiceConfig (com.alibaba.dubbo.config)
export:206, ServiceConfig (com.alibaba.dubbo.config)

// ServiceBean
onApplicationEvent:109, ServiceBean (com.alibaba.dubbo.config.spring)

// 容器发布事件
doInvokeListener:172, SimpleApplicationEventMulticaster (org.springframework.context.event)
invokeListener:165, SimpleApplicationEventMulticaster (org.springframework.context.event)
multicastEvent:139, SimpleApplicationEventMulticaster (org.springframework.context.event)
publishEvent:393, AbstractApplicationContext (org.springframework.context.support)
publishEvent:347, AbstractApplicationContext (org.springframework.context.support)

// finish
finishRefresh:883, AbstractApplicationContext (org.springframework.context.support)
finishRefresh:144, EmbeddedWebApplicationContext (org.springframework.boot.context.embedded)
refresh:546, AbstractApplicationContext (org.springframework.context.support)

// context
refresh:122, EmbeddedWebApplicationContext (org.springframework.boot.context.embedded)
refresh:693, SpringApplication (org.springframework.boot)
refreshContext:360, SpringApplication (org.springframework.boot)
run:303, SpringApplication (org.springframework.boot)
run:1118, SpringApplication (org.springframework.boot)
run:1107, SpringApplication (org.springframework.boot)
main:10, Application (com.onion.ops.dubbo.tester)
```
### 1.2 init refer stack
```
<init>:53, WrappedChannelHandler (com.alibaba.dubbo.remoting.transport.dispatcher)
<init>:32, AllChannelHandler (com.alibaba.dubbo.remoting.transport.dispatcher.all)
dispatch:32, AllDispatcher (com.alibaba.dubbo.remoting.transport.dispatcher.all)
dispatch:-1, Dispatcher$Adpative (com.alibaba.dubbo.remoting)

// nio通道
wrapInternal:49, ChannelHandlers (com.alibaba.dubbo.remoting.transport.dispatcher)
wrap:37, ChannelHandlers (com.alibaba.dubbo.remoting.transport.dispatcher)
wrapChannelHandler:119, AbstractClient (com.alibaba.dubbo.remoting.transport)

// 传输层 CS架构
<init>:61, NettyClient (com.alibaba.dubbo.remoting.transport.netty)
connect:37, NettyTransporter (com.alibaba.dubbo.remoting.transport.netty)
connect:-1, Transporter$Adpative (com.alibaba.dubbo.remoting)
connect:76, Transporters (com.alibaba.dubbo.remoting)

// 交换层 报文头
connect:37, HeaderExchanger (com.alibaba.dubbo.remoting.exchange.support.header)
connect:110, Exchangers (com.alibaba.dubbo.remoting.exchange)

// 协议层  本地协议、远程协议
initClient:370, DubboProtocol (com.alibaba.dubbo.rpc.protocol.dubbo)
getSharedClient:336, DubboProtocol (com.alibaba.dubbo.rpc.protocol.dubbo)
getClients:313, DubboProtocol (com.alibaba.dubbo.rpc.protocol.dubbo)
refer:295, DubboProtocol (com.alibaba.dubbo.rpc.protocol.dubbo)

// 过滤器
refer:101, ProtocolFilterWrapper (com.alibaba.dubbo.rpc.protocol)
refer:65, ProtocolListenerWrapper (com.alibaba.dubbo.rpc.protocol)
refer:-1, Protocol$Adpative (com.alibaba.dubbo.rpc)

// 注册目录
toInvokers:386, RegistryDirectory (com.alibaba.dubbo.registry.integration)
refreshInvoker:250, RegistryDirectory (com.alibaba.dubbo.registry.integration)
notify:220, RegistryDirectory (com.alibaba.dubbo.registry.integration)

// 注册目录
notify:431, AbstractRegistry (com.alibaba.dubbo.registry.support)
doNotify:288, FailbackRegistry (com.alibaba.dubbo.registry.support)
notify:274, FailbackRegistry (com.alibaba.dubbo.registry.support)
doSubscribe:182, ZookeeperRegistry (com.alibaba.dubbo.registry.zookeeper)
subscribe:201, FailbackRegistry (com.alibaba.dubbo.registry.support)
subscribe:158, RegistryDirectory (com.alibaba.dubbo.registry.integration)

// 注册协议 本地注册协议、远程注册协议
doRefer:286, RegistryProtocol (com.alibaba.dubbo.registry.integration)
refer:269, RegistryProtocol (com.alibaba.dubbo.registry.integration)

// 监听器、过滤器包装
refer:99, ProtocolFilterWrapper (com.alibaba.dubbo.rpc.protocol)
refer:63, ProtocolListenerWrapper (com.alibaba.dubbo.rpc.protocol)
refer:-1, Protocol$Adpative (com.alibaba.dubbo.rpc)

// ReferenceConfig 初始化、创建代理
createProxy:379, ReferenceConfig (com.alibaba.dubbo.config)
init:320, ReferenceConfig (com.alibaba.dubbo.config)
get:159, ReferenceConfig (com.alibaba.dubbo.config)

// ReferenceBean
getObject:65, ReferenceBean (com.alibaba.dubbo.config.spring)

doGetObjectFromFactoryBean:168, FactoryBeanRegistrySupport (org.springframework.beans.factory.support)
getObjectFromFactoryBean:103, FactoryBeanRegistrySupport (org.springframework.beans.factory.support)
getObjectForBeanInstance:1634, AbstractBeanFactory (org.springframework.beans.factory.support)
doGetBean:254, AbstractBeanFactory (org.springframework.beans.factory.support)
getBean:197, AbstractBeanFactory (org.springframework.beans.factory.support)
getBean:1080, AbstractApplicationContext (org.springframework.context.support)
init:51, DubboReferenceService (com.onion.ops.dubbo.tester.service)
invoke0:-1, NativeMethodAccessorImpl (sun.reflect)
invoke:62, NativeMethodAccessorImpl (sun.reflect)
invoke:43, DelegatingMethodAccessorImpl (sun.reflect)
invoke:498, Method (java.lang.reflect)
invoke:366, InitDestroyAnnotationBeanPostProcessor$LifecycleElement (org.springframework.beans.factory.annotation)
invokeInitMethods:311, InitDestroyAnnotationBeanPostProcessor$LifecycleMetadata (org.springframework.beans.factory.annotation)
postProcessBeforeInitialization:134, InitDestroyAnnotationBeanPostProcessor (org.springframework.beans.factory.annotation)
applyBeanPostProcessorsBeforeInitialization:409, AbstractAutowireCapableBeanFactory (org.springframework.beans.factory.support)
initializeBean:1620, AbstractAutowireCapableBeanFactory (org.springframework.beans.factory.support)
doCreateBean:555, AbstractAutowireCapableBeanFactory (org.springframework.beans.factory.support)
createBean:483, AbstractAutowireCapableBeanFactory (org.springframework.beans.factory.support)
getObject:306, AbstractBeanFactory$1 (org.springframework.beans.factory.support)
getSingleton:230, DefaultSingletonBeanRegistry (org.springframework.beans.factory.support)
doGetBean:302, AbstractBeanFactory (org.springframework.beans.factory.support)
getBean:202, AbstractBeanFactory (org.springframework.beans.factory.support)
resolveCandidate:208, DependencyDescriptor (org.springframework.beans.factory.config)
doResolveDependency:1138, DefaultListableBeanFactory (org.springframework.beans.factory.support)
resolveDependency:1066, DefaultListableBeanFactory (org.springframework.beans.factory.support)
inject:585, AutowiredAnnotationBeanPostProcessor$AutowiredFieldElement (org.springframework.beans.factory.annotation)
inject:88, InjectionMetadata (org.springframework.beans.factory.annotation)
postProcessPropertyValues:366, AutowiredAnnotationBeanPostProcessor (org.springframework.beans.factory.annotation)
populateBean:1264, AbstractAutowireCapableBeanFactory (org.springframework.beans.factory.support)
doCreateBean:553, AbstractAutowireCapableBeanFactory (org.springframework.beans.factory.support)
createBean:483, AbstractAutowireCapableBeanFactory (org.springframework.beans.factory.support)
getObject:306, AbstractBeanFactory$1 (org.springframework.beans.factory.support)
getSingleton:230, DefaultSingletonBeanRegistry (org.springframework.beans.factory.support)
doGetBean:302, AbstractBeanFactory (org.springframework.beans.factory.support)
getBean:197, AbstractBeanFactory (org.springframework.beans.factory.support)
preInstantiateSingletons:761, DefaultListableBeanFactory (org.springframework.beans.factory.support)
finishBeanFactoryInitialization:867, AbstractApplicationContext (org.springframework.context.support)
refresh:543, AbstractApplicationContext (org.springframework.context.support)
refresh:122, EmbeddedWebApplicationContext (org.springframework.boot.context.embedded)
refresh:693, SpringApplication (org.springframework.boot)
refreshContext:360, SpringApplication (org.springframework.boot)
run:303, SpringApplication (org.springframework.boot)
run:1118, SpringApplication (org.springframework.boot)
run:1107, SpringApplication (org.springframework.boot)
main:10, Application (com.onion.ops.dubbo.tester)
```

### 1.3 init refer stack ReferenceBean.getObject()

ReferenceBean.getObject()
ReferenceConfig.get()
ReferenceConfig.init() 配置检查

```
toInvokers:344, RegistryDirectory (com.alibaba.dubbo.registry.integration) 注册目录-toInvokers
refreshInvoker:250, RegistryDirectory (com.alibaba.dubbo.registry.integration) 注册目录-refreshInvoker

notify:220, RegistryDirectory (com.alibaba.dubbo.registry.integration) 注册目录-notify
notify:431, AbstractRegistry (com.alibaba.dubbo.registry.support) 抽象注册-notify

doNotify:288, FailbackRegistry (com.alibaba.dubbo.registry.support) 失败返回注册-doNotify
notify:274, FailbackRegistry (com.alibaba.dubbo.registry.support) 失败返回注册-notify
doSubscribe:182, ZookeeperRegistry (com.alibaba.dubbo.registry.zookeeper) Zookeeper注册-doSubscribe
subscribe:201, FailbackRegistry (com.alibaba.dubbo.registry.support) 失败返回注册-subscribe
subscribe:158, RegistryDirectory (com.alibaba.dubbo.registry.integration) 注册目录-subscribe

doRefer:286, RegistryProtocol (com.alibaba.dubbo.registry.integration) 注册协议-doRefer
refer:269, RegistryProtocol (com.alibaba.dubbo.registry.integration) 注册协议-refer

refer:99, ProtocolFilterWrapper (com.alibaba.dubbo.rpc.protocol) 协议过滤器包装-refer
refer:63, ProtocolListenerWrapper (com.alibaba.dubbo.rpc.protocol) 协议监听器包装-refer
refer:-1, Protocol$Adpative (com.alibaba.dubbo.rpc) 接口自适应-refer

createProxy:379, ReferenceConfig (com.alibaba.dubbo.config) 创建代理对象的，该方法还会调用其他方法构建以及合并 Invoker 实例
init:320, ReferenceConfig (com.alibaba.dubbo.config) 配置检查，默认配置设置
get:159, ReferenceConfig (com.alibaba.dubbo.config) 
getObject:65, ReferenceBean (com.alibaba.dubbo.config.spring) factoryBean获得对象

// getBean触发依赖注入
doGetObjectFromFactoryBean:168, FactoryBeanRegistrySupport (org.springframework.beans.factory.support) 工厂bean注册表支持-doGetObjectFromFactoryBean
getObjectFromFactoryBean:103, FactoryBeanRegistrySupport (org.springframework.beans.factory.support) 工厂bean注册表支持-getObjectFromFactoryBean
getObjectForBeanInstance:1634, AbstractBeanFactory (org.springframework.beans.factory.support) 抽象工厂-getObjectForBeanInstance
doGetBean:254, AbstractBeanFactory (org.springframework.beans.factory.support) 抽象工厂-doGetBean
getBean:197, AbstractBeanFactory (org.springframework.beans.factory.support) 抽象工厂-getBean
getBean:1080, AbstractApplicationContext (org.springframework.context.support)

// dubbo
init:51, DubboReferenceService (com.onion.ops.dubbo.tester.service)
// 反射方法调用
invoke0:-1, NativeMethodAccessorImpl (sun.reflect)
invoke:62, NativeMethodAccessorImpl (sun.reflect)
invoke:43, DelegatingMethodAccessorImpl (sun.reflect)
invoke:498, Method (java.lang.reflect)

// 自动装配 实例化、复制属性、初始化（初始化之前、初始化中、自定义初始化、初始化之后）
invoke:366, InitDestroyAnnotationBeanPostProcessor$LifecycleElement (org.springframework.beans.factory.annotation) 后处理-invoke
invokeInitMethods:311, InitDestroyAnnotationBeanPostProcessor$LifecycleMetadata (org.springframework.beans.factory.annotation) 注解配置后处理-invokeInitMethods
postProcessBeforeInitialization:134, InitDestroyAnnotationBeanPostProcessor (org.springframework.beans.factory.annotation) 后处理beforeInit
applyBeanPostProcessorsBeforeInitialization:409, AbstractAutowireCapableBeanFactory (org.springframework.beans.factory.support) 自动装配-应用后处理beforeInit
initializeBean:1620, AbstractAutowireCapableBeanFactory (org.springframework.beans.factory.support) 自动装配-initializeBean
doCreateBean:555, AbstractAutowireCapableBeanFactory (org.springframework.beans.factory.support) 自动装配-doCreateBean
createBean:483, AbstractAutowireCapableBeanFactory (org.springframework.beans.factory.support) 自动装配-createBean

// 依赖注入
getObject:306, AbstractBeanFactory$1 (org.springframework.beans.factory.support) 抽象工厂-getObject()
getSingleton:230, DefaultSingletonBeanRegistry (org.springframework.beans.factory.support) 单例bean注册表-getSingleton
doGetBean:302, AbstractBeanFactory (org.springframework.beans.factory.support) 抽象工厂-doGetBean
getBean:202, AbstractBeanFactory (org.springframework.beans.factory.support) 抽象工厂-getBean

// 解析依赖触发了另一个依赖注入
resolveCandidate:208, DependencyDescriptor (org.springframework.beans.factory.config) 
doResolveDependency:1138, DefaultListableBeanFactory (org.springframework.beans.factory.support)
resolveDependency:1066, DefaultListableBeanFactory (org.springframework.beans.factory.support)

inject:585, AutowiredAnnotationBeanPostProcessor$AutowiredFieldElement (org.springframework.beans.factory.annotation)
inject:88, InjectionMetadata (org.springframework.beans.factory.annotation)
postProcessPropertyValues:366, AutowiredAnnotationBeanPostProcessor (org.springframework.beans.factory.annotation)

// 自动装配 实例化、复制属性、初始化
populateBean:1264, AbstractAutowireCapableBeanFactory (org.springframework.beans.factory.support)
doCreateBean:553, AbstractAutowireCapableBeanFactory (org.springframework.beans.factory.support)
createBean:483, AbstractAutowireCapableBeanFactory (org.springframework.beans.factory.support)
getObject:306, AbstractBeanFactory$1 (org.springframework.beans.factory.support)
getSingleton:230, DefaultSingletonBeanRegistry (org.springframework.beans.factory.support)

// 依赖注入
doGetBean:302, AbstractBeanFactory (org.springframework.beans.factory.support) 抽象工厂-doGetBean
getBean:197, AbstractBeanFactory (org.springframework.beans.factory.support) 抽象工厂-getBean
preInstantiateSingletons:761, DefaultListableBeanFactory (org.springframework.beans.factory.support) 默认列表bean工厂-preInstantiateSingletons

// spring-context
finishBeanFactoryInitialization:867, AbstractApplicationContext (org.springframework.context.support) 抽象应用上下文-finishBeanFactoryInitialization
refresh:543, AbstractApplicationContext (org.springframework.context.support) 抽象应用上下文-refresh
refresh:122, EmbeddedWebApplicationContext (org.springframework.boot.context.embedded) 嵌入式web应用上下文-refresh

// spring-boot
refresh:693, SpringApplication (org.springframework.boot) spring应用上下文-refresh
refreshContext:360, SpringApplication (org.springframework.boot) spring应用上下文-refreshContext
run:303, SpringApplication (org.springframework.boot) spring应用上下文-run
run:1118, SpringApplication (org.springframework.boot)
run:1107, SpringApplication (org.springframework.boot)
main:10, Application (com.onion.ops.dubbo.tester)
```

## 2. invoke
### 2.1 invoke client stack
```
// dubbo 同步等待
get:125, DefaultFuture (com.alibaba.dubbo.remoting.exchange.support)
get:113, DefaultFuture (com.alibaba.dubbo.remoting.exchange.support)

// dubbo ExchangeClient 交换层、传输层
// 异步无返回、异步有返回、同步
doInvoke:97, DubboInvoker (com.alibaba.dubbo.rpc.protocol.dubbo)
invoke:144, AbstractInvoker (com.alibaba.dubbo.rpc.protocol)

// dubbo 过滤器
// ConsumerContextFilter -> FutureFilter -> MonitorFilter
invoke:65, MonitorFilter (com.alibaba.dubbo.monitor.support)
invoke:69, ProtocolFilterWrapper$1 (com.alibaba.dubbo.rpc.protocol)
invoke:54, FutureFilter (com.alibaba.dubbo.rpc.protocol.dubbo.filter)
invoke:69, ProtocolFilterWrapper$1 (com.alibaba.dubbo.rpc.protocol)
invoke:48, ConsumerContextFilter (com.alibaba.dubbo.rpc.filter)
invoke:69, ProtocolFilterWrapper$1 (com.alibaba.dubbo.rpc.protocol)

// dubbo listener
invoke:74, ListenerInvokerWrapper (com.alibaba.dubbo.rpc.listener)
invoke:53, InvokerWrapper (com.alibaba.dubbo.rpc.protocol)

// dubbo 动态代理实现、模拟数据、集群策略
doInvoke:47, FailfastClusterInvoker (com.alibaba.dubbo.rpc.cluster.support)
invoke:229, AbstractClusterInvoker (com.alibaba.dubbo.rpc.cluster.support)
invoke:72, MockClusterInvoker (com.alibaba.dubbo.rpc.cluster.support.wrapper)
invoke:52, InvokerInvocationHandler (com.alibaba.dubbo.rpc.proxy)

// dubbo proxy
findAvailable:-1, proxy8 (com.alibaba.dubbo.common.bytecode)

invoke:-1, GeneratedMethodAccessor138 (sun.reflect)
invoke:43, DelegatingMethodAccessorImpl (sun.reflect)
invoke:498, Method (java.lang.reflect)

// 三层架构 controller-service-dao
invoke:139, DubboReferenceService (com.onion.ops.dubbo.tester.service)
invoke:58, IndexController (com.onion.ops.dubbo.tester.controller)

// 反射调用方法
invoke:-1, GeneratedMethodAccessor137 (sun.reflect)
invoke:43, DelegatingMethodAccessorImpl (sun.reflect)
invoke:498, Method (java.lang.reflect)

// springmvc 处理方法 handler method
doInvoke:205, InvocableHandlerMethod (org.springframework.web.method.support)
invokeForRequest:133, InvocableHandlerMethod (org.springframework.web.method.support)
invokeAndHandle:97, ServletInvocableHandlerMethod (org.springframework.web.servlet.mvc.method.annotation)

// springmvc 请求映射处理器适配器 adapter
invokeHandlerMethod:827, RequestMappingHandlerAdapter (org.springframework.web.servlet.mvc.method.annotation)
handleInternal:738, RequestMappingHandlerAdapter (org.springframework.web.servlet.mvc.method.annotation)
handle:85, AbstractHandlerMethodAdapter (org.springframework.web.servlet.mvc.method)

// springmvc 分发请求
doDispatch:967, DispatcherServlet (org.springframework.web.servlet)
doService:901, DispatcherServlet (org.springframework.web.servlet)

// springmvc 处理所有的http方法
processRequest:970, FrameworkServlet (org.springframework.web.servlet)

// tomcat + springmvc
doPost:872, FrameworkServlet (org.springframework.web.servlet)
service:661, HttpServlet (javax.servlet.http)
service:846, FrameworkServlet (org.springframework.web.servlet)
service:742, HttpServlet (javax.servlet.http)

// tomcat + springmvc filter
// abstract class OncePerRequestFilter 
// CharacterEncodingFilter -> HiddenHttpMethodFilter -> HttpPutFormContentFilter -> RequestContextFilter -> WsFilter
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

// tomcat 管道阀 pipeline-valve
invoke:199, StandardWrapperValve (org.apache.catalina.core)
invoke:96, StandardContextValve (org.apache.catalina.core)
invoke:478, AuthenticatorBase (org.apache.catalina.authenticator)  认证
invoke:140, StandardHostValve (org.apache.catalina.core)
invoke:81, ErrorReportValve (org.apache.catalina.valves) 错误报告阀门
invoke:87, StandardEngineValve (org.apache.catalina.core)

// tomcat 适配器 adapter
service:342, CoyoteAdapter (org.apache.catalina.connector)

// tomcat 输入输出 nio protocol
service:803, Http11Processor (org.apache.coyote.http11)
process:66, AbstractProcessorLight (org.apache.coyote)

// tomcat 连接处理
process:868, AbstractProtocol$ConnectionHandler (org.apache.coyote)

// tomcat 非阻塞IO端 
doRun:1459, NioEndpoint$SocketProcessor (org.apache.tomcat.util.net)

// tomcat 套接字处理器
run:49, SocketProcessorBase (org.apache.tomcat.util.net)

// jdk 线程池
runWorker:1149, ThreadPoolExecutor (java.util.concurrent)
run:624, ThreadPoolExecutor$Worker (java.util.concurrent)
run:61, TaskThread$WrappingRunnable (org.apache.tomcat.util.threads)
run:748, Thread (java.lang)

```

### 2.2 invoke client stack
```
doInvoke:72, DubboInvoker (com.alibaba.dubbo.rpc.protocol.dubbo)
invoke:144, AbstractInvoker (com.alibaba.dubbo.rpc.protocol)

// filter ConsumerContextFilter -> MonitorFilter -> CatDubboFilter -> FutureFilter -> IterationConsumerFilter
invoke:25,  (com.onion.middleware.rpcext)
invoke:91, ProtocolFilterWrapper$1 (com.alibaba.dubbo.rpc.protocol)
invoke:53, FutureFilter (com.alibaba.dubbo.rpc.protocol.dubbo.filter)
invoke:91, ProtocolFilterWrapper$1 (com.alibaba.dubbo.rpc.protocol)
invoke:76, CatDubboFilter (com.onion.cat.plugin.dubbo)
invoke:91, ProtocolFilterWrapper$1 (com.alibaba.dubbo.rpc.protocol)
invoke:75, MonitorFilter (com.alibaba.dubbo.monitor.support)
invoke:91, ProtocolFilterWrapper$1 (com.alibaba.dubbo.rpc.protocol)
invoke:48, ConsumerContextFilter (com.alibaba.dubbo.rpc.filter)
invoke:91, ProtocolFilterWrapper$1 (com.alibaba.dubbo.rpc.protocol)

// listener
invoke:74, ListenerInvokerWrapper (com.alibaba.dubbo.rpc.listener)
invoke:53, InvokerWrapper (com.alibaba.dubbo.rpc.protocol)

// cluster 集群、目录、路由、负载均衡
doInvoke:47, FailfastClusterInvoker (com.alibaba.dubbo.rpc.cluster.support)
invoke:227, AbstractClusterInvoker (com.alibaba.dubbo.rpc.cluster.support)
invoke:27, IterationClusterInvoker (com.onion.middleware.rpcext)
invoke:72, MockClusterInvoker (com.alibaba.dubbo.rpc.cluster.support.wrapper)

// proxy
invoke:52, InvokerInvocationHandler (com.alibaba.dubbo.rpc.proxy)
getProfile:-1, proxy9 (com.alibaba.dubbo.common.bytecode)

// 线程池
updateProfile:171, ApplicationContext (com.onion.sso.client.core)
access$000:45, ApplicationContext (com.onion.sso.client.core)
run:240, ApplicationContext$1 (com.onion.sso.client.core)
run:47, TtlRunnable (com.alibaba.ttl)

// 异步
call:471, Executors$RunnableAdapter (java.util.concurrent)
runAndReset:304, FutureTask (java.util.concurrent)

// 调度线程池
access$301:178, ScheduledThreadPoolExecutor$ScheduledFutureTask (java.util.concurrent)
run:293, ScheduledThreadPoolExecutor$ScheduledFutureTask (java.util.concurrent)
runWorker:1145, ThreadPoolExecutor (java.util.concurrent)

// 工作者
run:615, ThreadPoolExecutor$Worker (java.util.concurrent)
run:745, Thread (java.lang)
```

### 2.3 invoke client stack
```
Channels.write(Channel, Object, SocketAddress) line: 613	
Channels.write(Channel, Object) line: 578	
NioClientSocketChannel(AbstractChannel).write(Object) line: 251	
NettyChannel.send(Object, boolean) line: 98	

NettyClient(AbstractClient).send(Object, boolean) line: 258	
NettyClient(AbstractPeer).send(Object) line: 54	

HeaderExchangeChannel.request(Object, int) line: 112	
HeaderExchangeClient.request(Object, int) line: 86	
ReferenceCountExchangeClient.request(Object, int) line: 78	

DubboInvoker<T>.doInvoke(Invocation) line: 97	
DubboInvoker<T>(AbstractInvoker<T>).invoke(Invocation) line: 144	

MonitorFilter.invoke(Invoker<?>, Invocation) line: 75	
ProtocolFilterWrapper$1.invoke(Invocation) line: 69	
FutureFilter.invoke(Invoker<?>, Invocation) line: 54	
ProtocolFilterWrapper$1.invoke(Invocation) line: 69	
ConsumerContextFilter.invoke(Invoker<?>, Invocation) line: 48	
ProtocolFilterWrapper$1.invoke(Invocation) line: 69	

ListenerInvokerWrapper<T>.invoke(Invocation) line: 74	
RegistryDirectory$InvokerDelegete<T>(InvokerWrapper<T>).invoke(Invocation) line: 53	
FailfastClusterInvoker<T>.doInvoke(Invocation, List<Invoker<T>>, LoadBalance) line: 47	
FailfastClusterInvoker<T>(AbstractClusterInvoker<T>).invoke(Invocation) line: 229	
MockClusterInvoker<T>.invoke(Invocation) line: 72	
InvokerInvocationHandler.invoke(Object, Method, Object[]) line: 52
proxy26.collect(URL) line: not available	
DubboMonitor.send() line: 113	
DubboMonitor$1.run() line: 70	
Executors$RunnableAdapter<T>.call() line: 511	
ScheduledThreadPoolExecutor$ScheduledFutureTask<V>(FutureTask<V>).runAndReset() line: 308	
ScheduledThreadPoolExecutor$ScheduledFutureTask<V>.access$301(ScheduledThreadPoolExecutor$ScheduledFutureTask) line: 180	
ScheduledThreadPoolExecutor$ScheduledFutureTask<V>.run() line: 294	
ScheduledThreadPoolExecutor(ThreadPoolExecutor).runWorker(ThreadPoolExecutor$Worker) line: 1149	
ThreadPoolExecutor$Worker.run() line: 624	
Thread.run() line: 748	
```

### 2.4 invoke server stack
```
// javassit proxy
invokeMethod:-1, Wrapper26 (com.alibaba.dubbo.common.bytecode)

// 调用者代理
doInvoke:46, JavassistProxyFactory$1 (com.alibaba.dubbo.rpc.proxy.javassist)
invoke:72, AbstractProxyInvoker (com.alibaba.dubbo.rpc.proxy)

// 调用者包装
invoke:53, InvokerWrapper (com.alibaba.dubbo.rpc.protocol)

// 过滤器链 ProtocolFilterWrapper$1
// EchoFilter -> ClassLoaderFilter -> GenericFilter -> ContextFilter -> TraceFilter -> MonitorFilter -> TimeoutFilter -> ExceptionFilter
invoke:64, ExceptionFilter (com.alibaba.dubbo.rpc.filter)
invoke:91, ProtocolFilterWrapper$1 (com.alibaba.dubbo.rpc.protocol)
invoke:42, TimeoutFilter (com.alibaba.dubbo.rpc.filter)
invoke:91, ProtocolFilterWrapper$1 (com.alibaba.dubbo.rpc.protocol)
invoke:75, MonitorFilter (com.alibaba.dubbo.monitor.support)
invoke:91, ProtocolFilterWrapper$1 (com.alibaba.dubbo.rpc.protocol)
invoke:78, TraceFilter (com.alibaba.dubbo.rpc.protocol.dubbo.filter)
invoke:91, ProtocolFilterWrapper$1 (com.alibaba.dubbo.rpc.protocol)
invoke:60, ContextFilter (com.alibaba.dubbo.rpc.filter)
invoke:91, ProtocolFilterWrapper$1 (com.alibaba.dubbo.rpc.protocol)
invoke:112, GenericFilter (com.alibaba.dubbo.rpc.filter)
invoke:91, ProtocolFilterWrapper$1 (com.alibaba.dubbo.rpc.protocol)
invoke:38, ClassLoaderFilter (com.alibaba.dubbo.rpc.filter)
invoke:91, ProtocolFilterWrapper$1 (com.alibaba.dubbo.rpc.protocol)
invoke:38, EchoFilter (com.alibaba.dubbo.rpc.filter)
invoke:91, ProtocolFilterWrapper$1 (com.alibaba.dubbo.rpc.protocol)

// 协议
reply:108, DubboProtocol$1 (com.alibaba.dubbo.rpc.protocol.dubbo)

// 报文头交换处理
handleRequest:84, HeaderExchangeHandler (com.alibaba.dubbo.remoting.exchange.support.header)
received:170, HeaderExchangeHandler (com.alibaba.dubbo.remoting.exchange.support.header)

// 传输层 解码处理
received:52, DecodeHandler (com.alibaba.dubbo.remoting.transport)

// 传输层 通道事件，分发
run:82, ChannelEventRunnable (com.alibaba.dubbo.remoting.transport.dispatcher)

// 线程模型
runWorker:1145, ThreadPoolExecutor (java.util.concurrent)
run:615, ThreadPoolExecutor$Worker (java.util.concurrent)
run:745, Thread (java.lang)
```

### 2.5 invoke server stack

```
Daemon Thread [DubboServerHandler-192.168.2.252:18177-thread-5] (Suspended (breakpoint at line 198 in BaseRpcDataProviderFacadeImpl))	

    // 服务实现
	ActivityRecordLogFacadeImpl(BaseRpcDataProviderFacadeImpl<U,Q,T>).findById(Integer) line: 198	
	Wrapper14.invokeMethod(Object, String, Class[], Object[]) line: not available	
	
	// 动态代理
	JavassistProxyFactory$1.doInvoke(T, String, Class<?>[], Object[]) line: 46	
	JavassistProxyFactory$1(AbstractProxyInvoker<T>).invoke(Invocation) line: 72	
	
	// 注册协议
	RegistryProtocol$InvokerDelegete<T>(InvokerWrapper<T>).invoke(Invocation) line: 53	
	
	// 过滤器链
	// 异常过滤
	ExceptionFilter.invoke(Invoker<?>, Invocation) line: 64	
	ProtocolFilterWrapper$1.invoke(Invocation) line: 91	
	// 超时过滤
	TimeoutFilter.invoke(Invoker<?>, Invocation) line: 42	
	ProtocolFilterWrapper$1.invoke(Invocation) line: 91	
	// cat过滤
	CatDubboFilter.invoke(Invoker<?>, Invocation) line: 76	
	ProtocolFilterWrapper$1.invoke(Invocation) line: 91	
	// 监控过滤
	MonitorFilter.invoke(Invoker<?>, Invocation) line: 75	
	ProtocolFilterWrapper$1.invoke(Invocation) line: 91	
	// 链路过滤 链路跟踪、链路压测
	TraceFilter.invoke(Invoker<?>, Invocation) line: 78	
	ProtocolFilterWrapper$1.invoke(Invocation) line: 91	
	// 上下文过滤
	ContextFilter.invoke(Invoker<?>, Invocation) line: 60	
	ProtocolFilterWrapper$1.invoke(Invocation) line: 91	
	// 通用过滤
	GenericFilter.invoke(Invoker<?>, Invocation) line: 112	
	ProtocolFilterWrapper$1.invoke(Invocation) line: 91	
	// 类加载器过滤
	ClassLoaderFilter.invoke(Invoker<?>, Invocation) line: 38	
	ProtocolFilterWrapper$1.invoke(Invocation) line: 91	
	// 回声过滤
	EchoFilter.invoke(Invoker<?>, Invocation) line: 38	
	ProtocolFilterWrapper$1.invoke(Invocation) line: 91	
	
	// dubbo传输协议处理
	DubboProtocol$1.reply(ExchangeChannel, Object) line: 108	
	
	// 传输层处理
	HeaderExchangeHandler.handleRequest(ExchangeChannel, Request) line: 84	
	HeaderExchangeHandler.received(Channel, Object) line: 170	
	DecodeHandler.received(Channel, Object) line: 52	
	ChannelEventRunnable.run() line: 82	
	
	// 线程池
	ThreadPoolExecutor.runWorker(ThreadPoolExecutor$Worker) line: 1145	
	ThreadPoolExecutor$Worker.run() line: 615 [local variables unavailable]	
	Thread.run() line: 745 [local variables unavailable]	
```



