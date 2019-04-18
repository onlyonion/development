## 栈调用

### invoke client stack
```
// 同步等待
get:125, DefaultFuture (com.alibaba.dubbo.remoting.exchange.support)
get:113, DefaultFuture (com.alibaba.dubbo.remoting.exchange.support)

// ExchangeClient 交换层、传输层
// 异步无返回、异步有返回、同步
doInvoke:97, DubboInvoker (com.alibaba.dubbo.rpc.protocol.dubbo)
invoke:144, AbstractInvoker (com.alibaba.dubbo.rpc.protocol)

// 过滤器
// ConsumerContextFilter -> FutureFilter -> MonitorFilter
invoke:65, MonitorFilter (com.alibaba.dubbo.monitor.support)
invoke:69, ProtocolFilterWrapper$1 (com.alibaba.dubbo.rpc.protocol)
invoke:54, FutureFilter (com.alibaba.dubbo.rpc.protocol.dubbo.filter)
invoke:69, ProtocolFilterWrapper$1 (com.alibaba.dubbo.rpc.protocol)
invoke:48, ConsumerContextFilter (com.alibaba.dubbo.rpc.filter)
invoke:69, ProtocolFilterWrapper$1 (com.alibaba.dubbo.rpc.protocol)

// listener
invoke:74, ListenerInvokerWrapper (com.alibaba.dubbo.rpc.listener)
invoke:53, InvokerWrapper (com.alibaba.dubbo.rpc.protocol)

// 动态代理实现、模拟数据、集群策略
doInvoke:47, FailfastClusterInvoker (com.alibaba.dubbo.rpc.cluster.support)
invoke:229, AbstractClusterInvoker (com.alibaba.dubbo.rpc.cluster.support)
invoke:72, MockClusterInvoker (com.alibaba.dubbo.rpc.cluster.support.wrapper)
invoke:52, InvokerInvocationHandler (com.alibaba.dubbo.rpc.proxy)

// dubbo proxy
findAvailable:-1, proxy8 (com.alibaba.dubbo.common.bytecode)

invoke:-1, GeneratedMethodAccessor138 (sun.reflect)
invoke:43, DelegatingMethodAccessorImpl (sun.reflect)
invoke:498, Method (java.lang.reflect)

// controller-service-dao
invoke:139, DubboReferenceService (com.onion.ops.dubbo.tester.service)
invoke:58, IndexController (com.onion.ops.dubbo.tester.controller)

invoke:-1, GeneratedMethodAccessor137 (sun.reflect)
invoke:43, DelegatingMethodAccessorImpl (sun.reflect)
invoke:498, Method (java.lang.reflect)

// handler method
doInvoke:205, InvocableHandlerMethod (org.springframework.web.method.support)
invokeForRequest:133, InvocableHandlerMethod (org.springframework.web.method.support)
invokeAndHandle:97, ServletInvocableHandlerMethod (org.springframework.web.servlet.mvc.method.annotation)

// adapter
invokeHandlerMethod:827, RequestMappingHandlerAdapter (org.springframework.web.servlet.mvc.method.annotation)
handleInternal:738, RequestMappingHandlerAdapter (org.springframework.web.servlet.mvc.method.annotation)
handle:85, AbstractHandlerMethodAdapter (org.springframework.web.servlet.mvc.method)

// 分发请求
doDispatch:967, DispatcherServlet (org.springframework.web.servlet)
doService:901, DispatcherServlet (org.springframework.web.servlet)

// 处理所有的http方法
processRequest:970, FrameworkServlet (org.springframework.web.servlet)

// servlet-api
doPost:872, FrameworkServlet (org.springframework.web.servlet)
service:661, HttpServlet (javax.servlet.http)
service:846, FrameworkServlet (org.springframework.web.servlet)
service:742, HttpServlet (javax.servlet.http)

// filter
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

// pipeline-valve
invoke:199, StandardWrapperValve (org.apache.catalina.core)
invoke:96, StandardContextValve (org.apache.catalina.core)
invoke:478, AuthenticatorBase (org.apache.catalina.authenticator)
invoke:140, StandardHostValve (org.apache.catalina.core)
invoke:81, ErrorReportValve (org.apache.catalina.valves)
invoke:87, StandardEngineValve (org.apache.catalina.core)

// adapter
service:342, CoyoteAdapter (org.apache.catalina.connector)

// nio protocol
service:803, Http11Processor (org.apache.coyote.http11)
process:66, AbstractProcessorLight (org.apache.coyote)
process:868, AbstractProtocol$ConnectionHandler (org.apache.coyote)
doRun:1459, NioEndpoint$SocketProcessor (org.apache.tomcat.util.net)
run:49, SocketProcessorBase (org.apache.tomcat.util.net)

// jdk
runWorker:1149, ThreadPoolExecutor (java.util.concurrent)
run:624, ThreadPoolExecutor$Worker (java.util.concurrent)
run:61, TaskThread$WrappingRunnable (org.apache.tomcat.util.threads)
run:748, Thread (java.lang)
```

### invoke server stack
```
// javassit proxy
invokeMethod:-1, Wrapper26 (com.alibaba.dubbo.common.bytecode)

// 调用者代理
doInvoke:46, JavassistProxyFactory$1 (com.alibaba.dubbo.rpc.proxy.javassist)
invoke:72, AbstractProxyInvoker (com.alibaba.dubbo.rpc.proxy)

// 调用者包装
invoke:53, InvokerWrapper (com.alibaba.dubbo.rpc.protocol)

// ProtocolFilterWrapper$1
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

// jdk
runWorker:1145, ThreadPoolExecutor (java.util.concurrent)
run:615, ThreadPoolExecutor$Worker (java.util.concurrent)
run:745, Thread (java.lang)
```

### invoke server stack

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

