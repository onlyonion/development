org.springframework.context.support.AbstractApplicationContext

## hierarchy
```
DefaultResourceLoader (org.springframework.core.io)
    AbstractApplicationContext (org.springframework.context.support)
        AbstractRefreshableApplicationContext (org.springframework.context.support)
            AbstractRefreshableConfigApplicationContext (org.springframework.context.support)
                AnnotationConfigReactiveWebApplicationContext (org.springframework.boot.web.reactive.context)
                AbstractXmlApplicationContext (org.springframework.context.support)
                    FileSystemXmlApplicationContext (org.springframework.context.support)
                    ClassPathXmlApplicationContext (org.springframework.context.support)
                AbstractRefreshableWebApplicationContext (org.springframework.web.context.support)
                    XmlWebApplicationContext (org.springframework.web.context.support)
                    GroovyWebApplicationContext (org.springframework.web.context.support)
                    AnnotationConfigWebApplicationContext (org.springframework.web.context.support)
        GenericApplicationContext (org.springframework.context.support)
            GenericXmlApplicationContext (org.springframework.context.support)
            StaticApplicationContext (org.springframework.context.support)
            GenericWebApplicationContext (org.springframework.web.context.support)
                ServletWebServerApplicationContext (org.springframework.boot.web.servlet.context)
                    AnnotationConfigServletWebServerApplicationContext (org.springframework.boot.web.servlet.context)
                    XmlServletWebServerApplicationContext (org.springframework.boot.web.servlet.context)
            ResourceAdapterApplicationContext (org.springframework.jca.context)
            GenericGroovyApplicationContext (org.springframework.context.support)
            AnnotationConfigApplicationContext (org.springframework.context.annotation)
            GenericReactiveWebApplicationContext (org.springframework.boot.web.reactive.context)
                ReactiveWebServerApplicationContext (org.springframework.boot.web.reactive.context)
                    AnnotationConfigReactiveWebServerApplicationContext (org.springframework.boot.web.reactive.context)
```

## class

```plantuml
@startuml

abstract class AbstractApplicationContext {
    - String id
    - String displayName
    - ApplicationContext parent
    - ConfigurableEnvironment environment
    - final List<BeanFactoryPostProcessor> beanFactoryPostProcessors
    - final AtomicBoolean active
    - final AtomicBoolean closed
    - Thread shutdownHook
    - ResourcePatternResolver resourcePatternResolver
    - LifecycleProcessor lifecycleProcessor
    - MessageSource messageSource
    - ApplicationEventMulticaster applicationEventMulticaster
    - final Set<ApplicationListener<?>> applicationListeners
    - Set<ApplicationEvent> earlyApplicationEvents
    .. 启动方法 ..
    + void refresh()
    .. 1. 准备 ..
    # void prepareRefresh()
    # ConfigurableListableBeanFactory obtainFreshBeanFactory()
    # void prepareBeanFactory(ConfigurableListableBeanFactory beanFactory)
    .. 2. 后处理 ..
    # void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
    # void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory)
    # void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory)
    .. 3. 初始化 ..
    # void initMessageSource()
    # void initApplicationEventMulticaster()
    # void onRefresh()
    # void registerListeners()
    .. 4. 完成 ..
    # void finishBeanFactoryInitialization(ConfigurableListableBeanFactory beanFactory)
    # void finishRefresh()
    .. 获取bean工厂 ..
    abstract ConfigurableListableBeanFactory getBeanFactory()
}

class DefaultResourceLoader {
    - ClassLoader classLoader
    - final Set<ProtocolResolver> protocolResolvers
    - final Map<Class<?>, Map<Resource, ?>> resourceCaches
}
DefaultResourceLoader <|-- AbstractApplicationContext

interface ConfigurableApplicationContext {
    + void setId(String id)
    + void setParent(@Nullable ApplicationContext parent)
    + void setEnvironment(ConfigurableEnvironment environment)
    + ConfigurableEnvironment getEnvironment()
    + void addBeanFactoryPostProcessor(BeanFactoryPostProcessor postProcessor)
    + void addApplicationListener(ApplicationListener<?> listener)
    + void addProtocolResolver(ProtocolResolver resolver)
    + void refresh()
    + void registerShutdownHook()
    + void close()
    + boolean isActive()
    + ConfigurableListableBeanFactory getBeanFactory()
}
ConfigurableApplicationContext <|.. AbstractApplicationContext

@enduml
```

## refresh()
tomcat环境下spring启动，通过ContextLoader加载

四大阶段、十二子过程

| 阶段       | 关键词      | 子过程                                                                            |
| :--------- | :---------- | :-------------------------------------------------------------------------------- |
| 准备阶段   | prepare     | prepareRefresh obtainFreshBeanFactory prepareBeanFactory                          |
| 后处理阶段 | postProcess | postProcessBeanFactory invokeBeanFactoryPostProcessors registerBeanPostProcessors |
| 初始化阶段 | init        | initMessageSource initApplicationEventMulticaster onRefresh registerListeners     |
| 完成阶段   | finish      | finishBeanFactoryInitialization finishRefresh                                     |

### 过程一：准备阶段

#### 1. 准备刷新 prepareRefresh();
设置启动时间、活动状态标志、验证属性配置

```mermaid
sequenceDiagram
    ContextLoader->>ContextLoader:initWebApplicationContext()
    ContextLoader->>AbstractApplicationContext:refresh()
    %% 1. 准备刷新
    AbstractApplicationContext->>AbstractApplicationContext:prepareRefresh()

    %% 设置启动时间、活动状态标志
    AbstractApplicationContext->>AbstractApplicationContext:initPropertySources()

    AbstractApplicationContext->>AbstractApplicationContext:getEnvironment()
    opt environment为null
        AbstractApplicationContext->>AbstractApplicationContext:createEnvironment() 实例化StandardEnvironment
    end

    %% 验证属性配置
    AbstractApplicationContext->>ConfigurablePropertyResolver:validateRequiredProperties()
    ConfigurablePropertyResolver->>AbstractEnvironment:validateRequiredProperties()
    ConfigurablePropertyResolver->>ConfigurablePropertyResolver:validateRequiredProperties()

```
#### 2. 获得刷新工厂 obtainFreshBeanFactory();
实例化bean工厂，资源定位、bean定义验证解析、bean定义注册

```mermaid
sequenceDiagram
    %% 2. 获得刷新工厂
    AbstractApplicationContext->>AbstractApplicationContext:obtainFreshBeanFactory()
    %% 2.1 刷新bean工厂
    AbstractApplicationContext->>AbstractRefreshableApplicationContext:refreshBeanFactory()
    %% 2.2 创建--判断是否存在bean工厂，存在先销毁，然后再去创建bean工厂
    AbstractRefreshableApplicationContext->>AbstractRefreshableApplicationContext:createBeanFactory()
    %% 2.3 加载--子类去加载bean定义，xml的工厂、xml的读取器去读取、加载
    AbstractRefreshableApplicationContext->>XmlWebApplicationContext:loadBeanDefinitions(DefaultListableBeanFactory beanFactory)

    %%%%%%% 资源定位--加载、解析--bean注册
    loop 加载bean定义
        %% 资源定位
        XmlWebApplicationContext->>AbstractRefreshableConfigApplicationContext:getConfigLocations()
        alt abstractRefreshableConfigApplicationContext.configLocations != null
            AbstractRefreshableConfigApplicationContext-->>XmlWebApplicationContext:this.configLocations
        else
            AbstractRefreshableConfigApplicationContext-->>XmlWebApplicationContext:getDefaultConfigLocations()
        end
        %% 加载
        XmlWebApplicationContext->>XmlBeanDefinitionReader:loadBeanDefinitions(XmlBeanDefinitionReader reader)
        XmlBeanDefinitionReader->>AbstractBeanDefinitionReader:loadBeanDefinitions()
        %% 2.3.1 资源加载器
        AbstractBeanDefinitionReader->>AbstractBeanDefinitionReader:getResourceLoader()
        XmlBeanDefinitionReader->>XmlBeanDefinitionReader:doLoadBeanDefinitions()
        %% 2.3.2 注册
        XmlBeanDefinitionReader->>XmlBeanDefinitionReader:registerBeanDefinitions()
        XmlBeanDefinitionReader->>DefaultBeanDefinitionDocumentReader:registerBeanDefinitions(Document doc, XmlReaderContext readerContext)
        XmlBeanDefinitionReader->>DefaultBeanDefinitionDocumentReader:doRegisterBeanDefinitions()
        %% 2.3.3 委托给代理读取解析
        DefaultBeanDefinitionDocumentReader->>DefaultBeanDefinitionDocumentReader:createDelegate()
        DefaultBeanDefinitionDocumentReader->>DefaultBeanDefinitionDocumentReader:parseBeanDefinitions()
        loop parse
            DefaultBeanDefinitionDocumentReader->>BeanDefinitionParserDelegate:isDefaultNamespace()
            alt is isDefaultNamespace
                DefaultBeanDefinitionDocumentReader->>DefaultBeanDefinitionDocumentReader:parseDefaultElement()
                %% 不同元素不同的解析方式
                alt 节点为import
                    DefaultBeanDefinitionDocumentReader->>DefaultBeanDefinitionDocumentReader:importBeanDefinitionResource()
                else 节点为alias
                    DefaultBeanDefinitionDocumentReader->>DefaultBeanDefinitionDocumentReader:processAliasRegistration()
                else 节点为bean
                    DefaultBeanDefinitionDocumentReader->>DefaultBeanDefinitionDocumentReader:processBeanDefinition()
                    %% 核心方法-解析元素
                    DefaultBeanDefinitionDocumentReader->>BeanDefinitionParserDelegate:parseBeanDefinitionElement()
                else 节点为beans
                    DefaultBeanDefinitionDocumentReader->>DefaultBeanDefinitionDocumentReader:doRegisterBeanDefinitions()
                end
            else
                DefaultBeanDefinitionDocumentReader->>BeanDefinitionParserDelegate:parseCustomElement()
            end
        end
    end
```
#### 3. 准备工厂 prepareBeanFactory(beanFactory);
设置类加载器，忽略内置接口，忽略依赖的接口、注册可解析的依赖、准备环境bean

```mermaid
sequenceDiagram    
    AbstractApplicationContext->>AbstractApplicationContext:prepareBeanFactory(beanFactory)
    %% 设置类加载器
    AbstractApplicationContext->>DefaultResourceLoader:getClassLoader()
    AbstractApplicationContext->>AbstractBeanFactory:setBeanClassLoader()

    %% 设置spring-el表达式解析器
    AbstractApplicationContext->>AbstractBeanFactory:setBeanExpressionResolver()
    AbstractApplicationContext->>AbstractBeanFactory:addPropertyEditorRegistrar()

    %% Configure the bean factory with context callbacks.
    %% ResourceLoaderAware.class
    AbstractApplicationContext->>ConfigurableListableBeanFactory:ignoreDependencyInterface()

    %% Configure the bean factory with context callbacks.
    %% BeanFactory.class ResourceLoader.class ApplicationEventPublisher.class ApplicationContext.class
    AbstractApplicationContext->>ConfigurableListableBeanFactory:registerResolvableDependency()

    %% Detect a LoadTimeWeaver and prepare for weaving, if found.
    opt bean工厂包含loadTimeWeaver
        AbstractApplicationContext->>ConfigurableBeanFactory:addBeanPostProcessor()
        AbstractApplicationContext->>ConfigurableBeanFactory:setTempClassLoader()
    end

    %% 注册默认的环境bean
    opt bean工厂不包含environment
        AbstractApplicationContext->>SingletonBeanRegistry:registerSingleton()
    end
    opt bean工厂不包含systemProperties
        AbstractApplicationContext->>SingletonBeanRegistry:registerSingleton()
    end
    opt bean工厂不包含systemEnvironment
        AbstractApplicationContext->>SingletonBeanRegistry:registerSingleton()
    end
```

### 过程二：后置处理阶段

#### 4. 后处理工厂postProcessBeanFactory(beanFactory);
模板方法，此时所有的bean definitions已经被加载，但是都没有实例化。
这样可以允许注册特殊的后置处理器（BeanPostProcessors）

#### 5. 调用工厂后处理器 invokeBeanFactoryPostProcessors(beanFactory);
Invoke factory processors registered as beans in the context.

```mermaid
sequenceDiagram
    AbstractApplicationContext->>AbstractApplicationContext:invokeBeanFactoryPostProcessors(beanFactory)

    loop
        AbstractApplicationContext->>BeanFactoryPostProcessor:postProcessBeanFactory(ConfigurableListableBeanFactory)
    end
```

#### 6. registerBeanPostProcessors(beanFactory);
Register bean processors that intercept bean creation.

```mermaid
sequenceDiagram
    AbstractApplicationContext->>AbstractApplicationContext:registerBeanPostProcessors(beanFactory)

    loop
        AbstractApplicationContext->>ConfigurableBeanFactory:addBeanPostProcessor(BeanPostProcessor)
    end
```

### 过程三：初始化阶段
Initialize message source for this context.
初始化国际化资源、事件广播器、注册监听器

#### 7. initMessageSource();
国际化

```mermaid
sequenceDiagram
    AbstractApplicationContext->>AbstractApplicationContext:initMessageSource()
    %% 获取beanFactory，内部属性
    AbstractApplicationContext->>AbstractApplicationContext:getBeanFactory()

    alt bean工厂已包含messageSource
        AbstractApplicationContext->>BeanFactory:getBean()触发依赖注入
    else 未包含
        AbstractApplicationContext->>DelegatingMessageSource:实例化
        AbstractApplicationContext->>SingletonBeanRegistry:registerSingleton()
    end
```

#### 8. initApplicationEventMulticaster();
初始化应用事件多路广播器

```mermaid
sequenceDiagram
    AbstractApplicationContext->>AbstractApplicationContext:initApplicationEventMulticaster()

    %% 获取beanFactory，内部属性
    AbstractApplicationContext->>AbstractApplicationContext:getBeanFactory()
    alt bean工厂已包含应用广播器
        AbstractApplicationContext->>BeanFactory:getBean()触发依赖注入
    else 未包含
        AbstractApplicationContext->>SimpleApplicationEventMulticaster:new(beanFactory)实例化默认的事件广播器
        AbstractApplicationContext->>SingletonBeanRegistry:registerSingleton()
    end
```

#### 9. onRefresh();
模板方法

#### 10. registerListeners();
注册监听器

```mermaid
sequenceDiagram
    AbstractApplicationContext->>AbstractApplicationContext:registerListeners()

    loop 注册监听器
        AbstractApplicationContext->>AbstractApplicationContext:getApplicationListeners()
        AbstractApplicationContext->>ApplicationEventMulticaster:addApplicationListener(listener)
    end

    loop 注册监听器Bean
        %% 抽象应用上下文内部聚合beanFactory，委托的方式实现beanFactory接口
        AbstractApplicationContext->>AbstractApplicationContext:getBeanNamesForType()
        AbstractApplicationContext->>ListableBeanFactory:getBeanNamesForType() 聚合、委托给bean工厂

        AbstractApplicationContext->>ApplicationEventMulticaster:addApplicationListener(listenerBeanName)
    end

```

### 过程四：完成阶段
#### 11. finishBeanFactoryInitialization(beanFactory);

```mermaid
sequenceDiagram
    AbstractApplicationContext->>AbstractApplicationContext:finishBeanFactoryInitialization(beanFactory)
    %% 初始化转换服务
    opt beanFactory包含转换服务bean
        AbstractApplicationContext->>AbstractBeanFactory:beanFactory.setConversionService()
　　end

    %% 加载时织入，触发依赖注入LoadTimeWeaverAware.class
    loop 初始化 LoadTimeWeaverAware beans
        AbstractApplicationContext->>AbstractApplicationContext:getBean()
    end

    %% Stop using the temporary ClassLoader for type matching.
    AbstractApplicationContext->>AbstractBeanFactory:beanFactory.setTempClassLoader(null)

	%% Allow for caching all bean definition metadata, not expecting further changes.
    AbstractApplicationContext->>DefaultListableBeanFactory:beanFactory.freezeConfiguration()

	%% Instantiate all remaining (non-lazy-init) singletons.
    AbstractApplicationContext->>DefaultListableBeanFactory:beanFactory.preInstantiateSingletons()实例化所有非懒加载的单例

    loop
        %% 获取bean定义
        DefaultListableBeanFactory->>AbstractBeanFactory:getMergedLocalBeanDefinition()
        %% AbstractBeanFactory-->>DefaultListableBeanFactory:RootBeanDefinition

        %% 非抽象的、单例的、非懒加载的
        alt !bd.isAbstract() && bd.isSingleton() && !bd.isLazyInit()
            alt isFactoryBean(beanName)
                DefaultListableBeanFactory->>SmartFactoryBean:isEagerInit()
                alt isEagerInit
                    DefaultListableBeanFactory->>AbstractBeanFactory:getBean(String)
                end
            else
                DefaultListableBeanFactory->>AbstractBeanFactory:getBean(String)
            end
        end
    end
```

#### 12. finishRefresh();
生命周期处理器、发布事件

```mermaid
sequenceDiagram
    AbstractApplicationContext->>AbstractApplicationContext:finishRefresh(beanFactory)

    %% 生命周期处理器
    AbstractApplicationContext->>AbstractApplicationContext:initLifecycleProcessor()
    AbstractApplicationContext->>DefaultLifecycleProcessor:onRefresh()
    DefaultLifecycleProcessor->>DefaultLifecycleProcessor:startBeans()
    
    %% 发布事件 ContextRefreshedEvent
    AbstractApplicationContext->>AbstractApplicationContext:publishEvent(new ContextRefreshedEvent(this))
    AbstractApplicationContext->>SimpleApplicationEventMulticaster:multicastEvent(ApplicationEvent)
    loop
        SimpleApplicationEventMulticaster->>AbstractApplicationEventMulticaster:getApplicationListeners()
        alt executor != null
             AbstractApplicationEventMulticaster->>ApplicationListener:executor.execute() onApplicationEvent(event)
        else
            AbstractApplicationEventMulticaster->>ApplicationListener:onApplicationEvent(event)
        end
    end

    %% LiveBeansView
    AbstractApplicationContext->>LiveBeansView:registerApplicationContext(this)

```

#### 异常情况
destroyBeans();
cancelRefresh(ex);


## idea uml
![hierarchry](../../../../../img/spring-idea-AbstractApplicationContext.png)

![hierarchry](../../../../../img/spring-idea-AbstractApplicationContext-field.png)