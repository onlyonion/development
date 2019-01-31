```
// netty 异步非阻塞
// 传输层 端到端 绑定地址、监听端口
doOpen:69, NettyServer (com.alibaba.dubbo.remoting.transport.netty)
<init>:62, AbstractServer (com.alibaba.dubbo.remoting.transport)
<init>:63, NettyServer (com.alibaba.dubbo.remoting.transport.netty)
bind:33, NettyTransporter (com.alibaba.dubbo.remoting.transport.netty)
bind:-1, Transporter$Adpative (com.alibaba.dubbo.remoting)                      扩展点自适应
bind:57, Transporters (com.alibaba.dubbo.remoting)

// 交换层
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
doLocalExport:163, RegistryProtocol (com.alibaba.dubbo.registry.integration)
export:116, RegistryProtocol (com.alibaba.dubbo.registry.integration)
export:54, ProtocolListenerWrapper (com.alibaba.dubbo.rpc.protocol)
export:92, ProtocolFilterWrapper (com.alibaba.dubbo.rpc.protocol)
export:-1, Protocol$Adpative (com.alibaba.dubbo.rpc)                            扩展点自适应

doExportUrlsFor1Protocol:550, ServiceConfig (com.alibaba.dubbo.config)
doExportUrls:346, ServiceConfig (com.alibaba.dubbo.config)
doExport:307, ServiceConfig (com.alibaba.dubbo.config)
export:206, ServiceConfig (com.alibaba.dubbo.config)
onApplicationEvent:109, ServiceBean (com.alibaba.dubbo.config.spring)

doInvokeListener:172, SimpleApplicationEventMulticaster (org.springframework.context.event)
invokeListener:165, SimpleApplicationEventMulticaster (org.springframework.context.event)
multicastEvent:139, SimpleApplicationEventMulticaster (org.springframework.context.event)
publishEvent:393, AbstractApplicationContext (org.springframework.context.support)
publishEvent:347, AbstractApplicationContext (org.springframework.context.support)
finishRefresh:883, AbstractApplicationContext (org.springframework.context.support)
finishRefresh:144, EmbeddedWebApplicationContext (org.springframework.boot.context.embedded)
refresh:546, AbstractApplicationContext (org.springframework.context.support)
refresh:122, EmbeddedWebApplicationContext (org.springframework.boot.context.embedded)
refresh:693, SpringApplication (org.springframework.boot)
refreshContext:360, SpringApplication (org.springframework.boot)
run:303, SpringApplication (org.springframework.boot)
run:1118, SpringApplication (org.springframework.boot)
run:1107, SpringApplication (org.springframework.boot)
main:10, Application (com.weidai.ops.dubbo.tester)
```