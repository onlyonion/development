## spring-context


## package
org.springframework
* [cache](/20-framework/src/spring/spring-context/cache/README.md)
* [context](20-framework/src/spring/spring-context/context/README.md)
  * [ApplicationContext](/20-framework/src/spring/spring-context/context/ApplicationContext.md)
  * support
    * [AbstractApplicationContext](/20-framework/src/spring/spring-context/context/support/AbstractApplicationContext.md)
      * [AbstractRefreshableApplicationContext](/20-framework/src/spring/spring-context/context/support/AbstractRefreshableApplicationContext.md) `loadBeanDefinitions`
        * [FileSystemXmlApplicationContext](/20-framework/src/spring/spring-context/context/support/FileSystemXmlApplicationContext.md)
      * [GenericApplicationContext](/20-framework/src/spring/spring-context/context/support/GenericApplicationContext.md)
* ejb
* [format](/docs/20-framework/src/spring/spring-context/format/README.md)
  * [Parser](/docs/20-framework/src/spring/spring-context/format/Parser.md)
* instrument.classloading
* jmx
* jndi
* remoting
* scheduling
* scripting
* stereotype
* ui
* validation

```
cache
context
    access
    annotation
    config
    event
    expression
    i18n
    support
        AbstractApplicationContext
        ClassPathXmlApplicationContext
        FileSystemXmlApplicationContext
        GenericApplicationContext
        AbstractRefreshableApplicationContext
    weaving
        LoadTimeWeaverAware
    HierarchicalMessageSource
    EnvironmentAware
    Lifecycle
    ApplicationListener
    ConfigurableApplicationContext
    ApplicationContextInitializer
    ApplicationContext
    EmbeddedValueResolverAware
    LifecycleProcessor
    MessageSource
    ApplicationEvent
    ApplicationEventPublisherAware
    ApplicationEventPublisher
    MessageSourceResolvable
    ApplicationContextAware
    MessageSourceAware
    PayloadApplicationEvent
    Phased
    ResourceLoaderAware
    SmartLifecycle
ejb
format
instrument.classloading
jmx
jndi
remoting
    rmi
    soap
    support
scheduling
    annotation
    concurrent
    config
    support
    SchedulingTaskExecutor
scripting
    bsh
    config
    groovy
    jruby
    support
    ScriptEvaluator
    ScriptFactory
    ScriptSource
stereotype
    Component
    Controller
    Repository
    Service
ui
    Model
    ModelMap
validation
    annotation
    beanvalidation
    support
```