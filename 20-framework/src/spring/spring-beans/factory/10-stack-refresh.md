```
// spring-registerBeanDefinition
registerBeanDefinition:807, DefaultListableBeanFactory (org.springframework.beans.factory.support)
registerPostProcessor:218, AnnotationConfigUtils (org.springframework.context.annotation)
registerAnnotationConfigProcessors:163, AnnotationConfigUtils (org.springframework.context.annotation)

// spring-parse
parse:47, AnnotationConfigBeanDefinitionParser (org.springframework.context.annotation)
parse:74, NamespaceHandlerSupport (org.springframework.beans.factory.xml)
parseCustomElement:1411, BeanDefinitionParserDelegate (org.springframework.beans.factory.xml)
parseCustomElement:1401, BeanDefinitionParserDelegate (org.springframework.beans.factory.xml)
parseBeanDefinitions:168, DefaultBeanDefinitionDocumentReader (org.springframework.beans.factory.xml)
doRegisterBeanDefinitions:138, DefaultBeanDefinitionDocumentReader (org.springframework.beans.factory.xml)
registerBeanDefinitions:94, DefaultBeanDefinitionDocumentReader (org.springframework.beans.factory.xml)
registerBeanDefinitions:508, XmlBeanDefinitionReader (org.springframework.beans.factory.xml)

// spring-loadBeanDefinitions
doLoadBeanDefinitions:392, XmlBeanDefinitionReader (org.springframework.beans.factory.xml)
loadBeanDefinitions:336, XmlBeanDefinitionReader (org.springframework.beans.factory.xml)
loadBeanDefinitions:304, XmlBeanDefinitionReader (org.springframework.beans.factory.xml)
loadBeanDefinitions:181, AbstractBeanDefinitionReader (org.springframework.beans.factory.support)
loadBeanDefinitions:217, AbstractBeanDefinitionReader (org.springframework.beans.factory.support)
loadBeanDefinitions:188, AbstractBeanDefinitionReader (org.springframework.beans.factory.support)
loadBeanDefinitions:125, XmlWebApplicationContext (org.springframework.web.context.support)
loadBeanDefinitions:94, XmlWebApplicationContext (org.springframework.web.context.support)

// spring-refreshBeanFactory
refreshBeanFactory:129, AbstractRefreshableApplicationContext (org.springframework.context.support)
obtainFreshBeanFactory:609, AbstractApplicationContext (org.springframework.context.support)
refresh:510, AbstractApplicationContext (org.springframework.context.support)
configureAndRefreshWebApplicationContext:444, ContextLoader (org.springframework.web.context)

// tomcat-spring 
initWebApplicationContext:326, ContextLoader (org.springframework.web.context)
contextInitialized:107, ContextLoaderListener (org.springframework.web.context)

// tomcat
listenerStart:5099, StandardContext (org.apache.catalina.core)
startInternal:5615, StandardContext (org.apache.catalina.core)
start:147, LifecycleBase (org.apache.catalina.util)
addChildInternal:899, ContainerBase (org.apache.catalina.core)
addChild:875, ContainerBase (org.apache.catalina.core)
addChild:652, StandardHost (org.apache.catalina.core)
manageApp:1863, HostConfig (org.apache.catalina.startup)

// tomcat createStandardContext
invoke0:-1, NativeMethodAccessorImpl (sun.reflect)
invoke:62, NativeMethodAccessorImpl (sun.reflect)
invoke:43, DelegatingMethodAccessorImpl (sun.reflect)
invoke:498, Method (java.lang.reflect)
invoke:301, BaseModelMBean (org.apache.tomcat.util.modeler)
invoke:819, DefaultMBeanServerInterceptor (com.sun.jmx.interceptor)
invoke:801, JmxMBeanServer (com.sun.jmx.mbeanserver)
createStandardContext:618, MBeanFactory (org.apache.catalina.mbeans)
createStandardContext:565, MBeanFactory (org.apache.catalina.mbeans)

invoke0:-1, NativeMethodAccessorImpl (sun.reflect)
invoke:62, NativeMethodAccessorImpl (sun.reflect)
invoke:43, DelegatingMethodAccessorImpl (sun.reflect)
invoke:498, Method (java.lang.reflect)
invoke:301, BaseModelMBean (org.apache.tomcat.util.modeler)
invoke:819, DefaultMBeanServerInterceptor (com.sun.jmx.interceptor)
invoke:801, JmxMBeanServer (com.sun.jmx.mbeanserver)
doOperation:1468, RMIConnectionImpl (javax.management.remote.rmi)
access$300:76, RMIConnectionImpl (javax.management.remote.rmi)
run:1309, RMIConnectionImpl$PrivilegedOperation (javax.management.remote.rmi)
doPrivilegedOperation:1401, RMIConnectionImpl (javax.management.remote.rmi)
invoke:829, RMIConnectionImpl (javax.management.remote.rmi)
invoke0:-1, NativeMethodAccessorImpl (sun.reflect)
invoke:62, NativeMethodAccessorImpl (sun.reflect)
invoke:43, DelegatingMethodAccessorImpl (sun.reflect)
invoke:498, Method (java.lang.reflect)

dispatch:357, UnicastServerRef (sun.rmi.server)
run:200, Transport$1 (sun.rmi.transport)
run:197, Transport$1 (sun.rmi.transport)
doPrivileged:-1, AccessController (java.security)
serviceCall:196, Transport (sun.rmi.transport)
handleMessages:573, TCPTransport (sun.rmi.transport.tcp)
run0:835, TCPTransport$ConnectionHandler (sun.rmi.transport.tcp)
lambda$run$0:688, TCPTransport$ConnectionHandler (sun.rmi.transport.tcp)
run:-1, 203583094 (sun.rmi.transport.tcp.TCPTransport$ConnectionHandler$$Lambda$5)
doPrivileged:-1, AccessController (java.security)
run:687, TCPTransport$ConnectionHandler (sun.rmi.transport.tcp)
runWorker:1149, ThreadPoolExecutor (java.util.concurrent)
run:624, ThreadPoolExecutor$Worker (java.util.concurrent)
run:748, Thread (java.lang)
```