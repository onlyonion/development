
* SqlSessionFactoryBean.afterPropertiesSet, buildSqlSessionFactory
* XMLConfigBuilder


```
parseConfiguration:106, XMLConfigBuilder (org.apache.ibatis.builder.xml)
parse:99, XMLConfigBuilder (org.apache.ibatis.builder.xml)

buildSqlSessionFactory:494, SqlSessionFactoryBean (org.mybatis.spring)

// mybatis-spring
afterPropertiesSet:380, SqlSessionFactoryBean (org.mybatis.spring)

invokeInitMethods:1641, AbstractAutowireCapableBeanFactory (org.springframework.beans.factory.support)
initializeBean:1578, AbstractAutowireCapableBeanFactory (org.springframework.beans.factory.support)
doCreateBean:545, AbstractAutowireCapableBeanFactory (org.springframework.beans.factory.support)
createBean:482, AbstractAutowireCapableBeanFactory (org.springframework.beans.factory.support)
getObject:306, AbstractBeanFactory$1 (org.springframework.beans.factory.support)

getSingleton:230, DefaultSingletonBeanRegistry (org.springframework.beans.factory.support)
doGetBean:302, AbstractBeanFactory (org.springframework.beans.factory.support)
getBean:197, AbstractBeanFactory (org.springframework.beans.factory.support)

findAutowireCandidates:1199, DefaultListableBeanFactory (org.springframework.beans.factory.support)
doResolveDependency:1123, DefaultListableBeanFactory (org.springframework.beans.factory.support)
resolveDependency:1021, DefaultListableBeanFactory (org.springframework.beans.factory.support)
autowireByType:1296, AbstractAutowireCapableBeanFactory (org.springframework.beans.factory.support)

populateBean:1203, AbstractAutowireCapableBeanFactory (org.springframework.beans.factory.support)
doCreateBean:543, AbstractAutowireCapableBeanFactory (org.springframework.beans.factory.support)
createBean:482, AbstractAutowireCapableBeanFactory (org.springframework.beans.factory.support)
getObject:306, AbstractBeanFactory$1 (org.springframework.beans.factory.support)
getSingleton:230, DefaultSingletonBeanRegistry (org.springframework.beans.factory.support)
doGetBean:302, AbstractBeanFactory (org.springframework.beans.factory.support)
getBean:197, AbstractBeanFactory (org.springframework.beans.factory.support)

findAutowireCandidates:1199, DefaultListableBeanFactory (org.springframework.beans.factory.support)
doResolveDependency:1123, DefaultListableBeanFactory (org.springframework.beans.factory.support)
resolveDependency:1021, DefaultListableBeanFactory (org.springframework.beans.factory.support)
inject:545, AutowiredAnnotationBeanPostProcessor$AutowiredFieldElement (org.springframework.beans.factory.annotation)
inject:88, InjectionMetadata (org.springframework.beans.factory.annotation)
postProcessPropertyValues:331, AutowiredAnnotationBeanPostProcessor (org.springframework.beans.factory.annotation)
populateBean:1218, AbstractAutowireCapableBeanFactory (org.springframework.beans.factory.support)

doCreateBean:543, AbstractAutowireCapableBeanFactory (org.springframework.beans.factory.support)
createBean:482, AbstractAutowireCapableBeanFactory (org.springframework.beans.factory.support)

getObject:306, AbstractBeanFactory$1 (org.springframework.beans.factory.support)
getSingleton:230, DefaultSingletonBeanRegistry (org.springframework.beans.factory.support)
doGetBean:302, AbstractBeanFactory (org.springframework.beans.factory.support)
getBean:197, AbstractBeanFactory (org.springframework.beans.factory.support)
preInstantiateSingletons:778, DefaultListableBeanFactory (org.springframework.beans.factory.support)

// spring
finishBeanFactoryInitialization:839, AbstractApplicationContext (org.springframework.context.support)
refresh:538, AbstractApplicationContext (org.springframework.context.support)
configureAndRefreshWebApplicationContext:444, ContextLoader (org.springframework.web.context)

// spring-mvc
initWebApplicationContext:326, ContextLoader (org.springframework.web.context)
contextInitialized:107, ContextLoaderListener (org.springframework.web.context)

listenerStart:5099, StandardContext (org.apache.catalina.core)
startInternal:5615, StandardContext (org.apache.catalina.core)
start:147, LifecycleBase (org.apache.catalina.util)

addChildInternal:899, ContainerBase (org.apache.catalina.core)
addChild:875, ContainerBase (org.apache.catalina.core)
addChild:652, StandardHost (org.apache.catalina.core)
manageApp:1863, HostConfig (org.apache.catalina.startup)

invoke0:-1, NativeMethodAccessorImpl (sun.reflect)
invoke:57, NativeMethodAccessorImpl (sun.reflect)
invoke:43, DelegatingMethodAccessorImpl (sun.reflect)
invoke:606, Method (java.lang.reflect)

invoke:301, BaseModelMBean (org.apache.tomcat.util.modeler)
invoke:819, DefaultMBeanServerInterceptor (com.sun.jmx.interceptor)
invoke:801, JmxMBeanServer (com.sun.jmx.mbeanserver)
createStandardContext:618, MBeanFactory (org.apache.catalina.mbeans)
createStandardContext:565, MBeanFactory (org.apache.catalina.mbeans)
invoke0:-1, NativeMethodAccessorImpl (sun.reflect)
invoke:57, NativeMethodAccessorImpl (sun.reflect)
invoke:43, DelegatingMethodAccessorImpl (sun.reflect)
invoke:606, Method (java.lang.reflect)
invoke:301, BaseModelMBean (org.apache.tomcat.util.modeler)

invoke:819, DefaultMBeanServerInterceptor (com.sun.jmx.interceptor)
invoke:801, JmxMBeanServer (com.sun.jmx.mbeanserver)
doOperation:1487, RMIConnectionImpl (javax.management.remote.rmi)
access$300:97, RMIConnectionImpl (javax.management.remote.rmi)
run:1328, RMIConnectionImpl$PrivilegedOperation (javax.management.remote.rmi)
doPrivilegedOperation:1420, RMIConnectionImpl (javax.management.remote.rmi)
invoke:848, RMIConnectionImpl (javax.management.remote.rmi)
invoke0:-1, NativeMethodAccessorImpl (sun.reflect)
invoke:57, NativeMethodAccessorImpl (sun.reflect)
invoke:43, DelegatingMethodAccessorImpl (sun.reflect)
invoke:606, Method (java.lang.reflect)
dispatch:322, UnicastServerRef (sun.rmi.server)
run:202, Transport$2 (sun.rmi.transport)
run:199, Transport$2 (sun.rmi.transport)
doPrivileged:-1, AccessController (java.security)
serviceCall:198, Transport (sun.rmi.transport)
handleMessages:567, TCPTransport (sun.rmi.transport.tcp)
run0:828, TCPTransport$ConnectionHandler (sun.rmi.transport.tcp)
access$400:619, TCPTransport$ConnectionHandler (sun.rmi.transport.tcp)
run:684, TCPTransport$ConnectionHandler$1 (sun.rmi.transport.tcp)
run:681, TCPTransport$ConnectionHandler$1 (sun.rmi.transport.tcp)
doPrivileged:-1, AccessController (java.security)
run:681, TCPTransport$ConnectionHandler (sun.rmi.transport.tcp)
runWorker:1145, ThreadPoolExecutor (java.util.concurrent)
run:615, ThreadPoolExecutor$Worker (java.util.concurrent)
run:745, Thread (java.lang)
```