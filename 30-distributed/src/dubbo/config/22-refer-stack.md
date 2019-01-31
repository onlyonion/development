
### ReferenceBean.getObject()

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

doGetObjectFromFactoryBean:168, FactoryBeanRegistrySupport (org.springframework.beans.factory.support) 工厂bean注册表支持-doGetObjectFromFactoryBean
getObjectFromFactoryBean:103, FactoryBeanRegistrySupport (org.springframework.beans.factory.support) 工厂bean注册表支持-getObjectFromFactoryBean
getObjectForBeanInstance:1634, AbstractBeanFactory (org.springframework.beans.factory.support) 抽象工厂-getObjectForBeanInstance
doGetBean:254, AbstractBeanFactory (org.springframework.beans.factory.support) 抽象工厂-doGetBean
getBean:197, AbstractBeanFactory (org.springframework.beans.factory.support) 抽象工厂-getBean
getBean:1080, AbstractApplicationContext (org.springframework.context.support)
// dubbo

init:51, DubboReferenceService (com.weidai.ops.dubbo.tester.service)
// 反射方法调用
invoke0:-1, NativeMethodAccessorImpl (sun.reflect)
invoke:62, NativeMethodAccessorImpl (sun.reflect)
invoke:43, DelegatingMethodAccessorImpl (sun.reflect)
invoke:498, Method (java.lang.reflect)

invoke:366, InitDestroyAnnotationBeanPostProcessor$LifecycleElement (org.springframework.beans.factory.annotation) 后处理-invoke
invokeInitMethods:311, InitDestroyAnnotationBeanPostProcessor$LifecycleMetadata (org.springframework.beans.factory.annotation) 注解配置后处理-invokeInitMethods
postProcessBeforeInitialization:134, InitDestroyAnnotationBeanPostProcessor (org.springframework.beans.factory.annotation) 后处理beforeInit
applyBeanPostProcessorsBeforeInitialization:409, AbstractAutowireCapableBeanFactory (org.springframework.beans.factory.support) 自动装配-应用后处理beforeInit
initializeBean:1620, AbstractAutowireCapableBeanFactory (org.springframework.beans.factory.support) 自动装配-initializeBean
doCreateBean:555, AbstractAutowireCapableBeanFactory (org.springframework.beans.factory.support) 自动装配-doCreateBean
createBean:483, AbstractAutowireCapableBeanFactory (org.springframework.beans.factory.support) 自动装配-createBean

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
populateBean:1264, AbstractAutowireCapableBeanFactory (org.springframework.beans.factory.support)
doCreateBean:553, AbstractAutowireCapableBeanFactory (org.springframework.beans.factory.support)
createBean:483, AbstractAutowireCapableBeanFactory (org.springframework.beans.factory.support)
getObject:306, AbstractBeanFactory$1 (org.springframework.beans.factory.support)
getSingleton:230, DefaultSingletonBeanRegistry (org.springframework.beans.factory.support)
doGetBean:302, AbstractBeanFactory (org.springframework.beans.factory.support) 抽象工厂-doGetBean
getBean:197, AbstractBeanFactory (org.springframework.beans.factory.support) 抽象工厂-getBean
preInstantiateSingletons:761, DefaultListableBeanFactory (org.springframework.beans.factory.support) 默认列表bean工厂-preInstantiateSingletons

// spring-refresh
finishBeanFactoryInitialization:867, AbstractApplicationContext (org.springframework.context.support) 抽象应用上下文-finishBeanFactoryInitialization
refresh:543, AbstractApplicationContext (org.springframework.context.support) 抽象应用上下文-refresh
refresh:122, EmbeddedWebApplicationContext (org.springframework.boot.context.embedded) 嵌入式web应用上下文-refresh
refresh:693, SpringApplication (org.springframework.boot) spring应用上下文-refresh
refreshContext:360, SpringApplication (org.springframework.boot) spring应用上下文-refreshContext
run:303, SpringApplication (org.springframework.boot) spring应用上下文-run
run:1118, SpringApplication (org.springframework.boot)
run:1107, SpringApplication (org.springframework.boot)
main:10, Application (com.weidai.ops.dubbo.tester)
```