org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory


## hierarchy
```
SimpleAliasRegistry (org.springframework.core)
    DefaultSingletonBeanRegistry (org.springframework.beans.factory.support)
        FactoryBeanRegistrySupport (org.springframework.beans.factory.support)
            AbstractBeanFactory (org.springframework.beans.factory.support)
                AbstractAutowireCapableBeanFactory (org.springframework.beans.factory.support)
                    DefaultListableBeanFactory (org.springframework.beans.factory.support)
                        XmlBeanFactory (org.springframework.beans.factory.xml)
```

## class

```plantuml
@startuml

class SimpleAliasRegistry

class DefaultSingletonBeanRegistry
SimpleAliasRegistry ^-- DefaultSingletonBeanRegistry

abstract class FactoryBeanRegistrySupport
DefaultSingletonBeanRegistry ^-- FactoryBeanRegistrySupport

abstract class AbstractBeanFactory {
    # abstract Object createBean(String beanName, RootBeanDefinition mbd, @Nullable Object[] args)
    # Class<?> resolveBeanClass(final RootBeanDefinition mbd, String beanName, final Class<?>... typesToMatch)
}
FactoryBeanRegistrySupport ^-- AbstractBeanFactory 

abstract class AbstractAutowireCapableBeanFactory {
    - InstantiationStrategy instantiationStrategy
    - ParameterNameDiscoverer parameterNameDiscoverer
    - final Set<Class<?>> ignoredDependencyTypes
    - final Set<Class<?>> ignoredDependencyInterfaces
    - final NamedThreadLocal<String> currentlyCreatedBean
    - final ConcurrentMap<String, BeanWrapper> factoryBeanInstanceCache
    - final ConcurrentMap<Class<?>, Method[]> factoryMethodCandidateCache
    .. 核心方法创建bean实例，填充属性，应用后处理器 ..
    # Object createBean(String beanName, RootBeanDefinition mbd, @Nullable Object[] args)
    # Object resolveBeforeInstantiation(String beanName, RootBeanDefinition mbd)
    .. 实际创建bean的过程 ..
    # Object doCreateBean(final String beanName, final RootBeanDefinition mbd, final @Nullable Object[] args)
    # BeanWrapper createBeanInstance(String beanName, RootBeanDefinition mbd, @Nullable Object[] args)
    # BeanWrapper instantiateBean(final String beanName, final RootBeanDefinition mbd)
    # void applyMergedBeanDefinitionPostProcessors(RootBeanDefinition mbd, Class<?> beanType, String beanName)
    # void populateBean(String beanName, RootBeanDefinition mbd, @Nullable BeanWrapper bw)
    .. 实初始化bean，感知方法、初始化之前、初始化（InitializingBean、自定义初始化）、初始化之后 ..
    # Object initializeBean(final String beanName, final Object bean, @Nullable RootBeanDefinition mbd)
    - void invokeAwareMethods(final String beanName, final Object bean)
    + Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName)
    # void invokeInitMethods(String beanName, final Object bean, @Nullable RootBeanDefinition mbd)
    # void invokeCustomInitMethod(String beanName, final Object bean, RootBeanDefinition mbd)
    + Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName)
    .. 注册销毁方法 ..
    # void registerDisposableBeanIfNecessary(String beanName, Object bean, RootBeanDefinition mbd)
}

AbstractBeanFactory <|-- AbstractAutowireCapableBeanFactory

@enduml
```

## createBean()
* 解析类 resolveBeanClass
* 准备方法重写 prepareMethodOverrides
* 实例化之前的解析 resolveBeforeInstantiation
* doCreateBean

```mermaid
sequenceDiagram

    AbstractBeanFactory->>AbstractAutowireCapableBeanFactory:createBean()
    
    %% 解析类
    AbstractAutowireCapableBeanFactory->>AbstractBeanFactory:resolveBeanClass()
    
    %% 准备方法重写
    AbstractAutowireCapableBeanFactory->>RootBeanDefinition:prepareMethodOverrides()
    
    %% 实例化之前的解析
    AbstractAutowireCapableBeanFactory->>AbstractAutowireCapableBeanFactory:resolveBeforeInstantiation()
    opt 实例化之前解析的bean不为null
        AbstractAutowireCapableBeanFactory-->>AbstractBeanFactory:返回bean
    end
    
    %% 创建
    AbstractAutowireCapableBeanFactory->>AbstractAutowireCapableBeanFactory:doCreateBean()
    
    AbstractAutowireCapableBeanFactory-->>AbstractBeanFactory:返回创建的bean
```
## doCreateBean()
* 实例化 createBeanInstance 
    + obtainFromSupplier 
    + instantiateUsingFactoryMethod工厂方法实例化 
    + autowireConstructor带参构造 
    + instantiateBean无参构造
* 填充属性 populateBean
* 初始化 initializeBean
    + 调用感知方法
    + 应用后处理器初始化之前方法
    + 调用初始化方法 afterPropertiesSet invokeCustomInitMethod
    + 应用后处理器初始化之前方法
* 注册销毁接口 registerDisposableBeanIfNecessary

```mermaid
sequenceDiagram
    AbstractAutowireCapableBeanFactory->>AbstractAutowireCapableBeanFactory:doCreateBean()
    
    %% 1. 实例化
    AbstractAutowireCapableBeanFactory->>AbstractAutowireCapableBeanFactory:createBeanInstance()    
    
    %% 后处理
    AbstractAutowireCapableBeanFactory->>AbstractAutowireCapableBeanFactory:applyMergedBeanDefinitionPostProcessors()    
    
    opt 懒汉式单例bean
        AbstractAutowireCapableBeanFactory->>DefaultSingletonBeanRegistry:addSingletonFactory()    
    end
    
    %% 2. 填充属性
    AbstractAutowireCapableBeanFactory->>AbstractAutowireCapableBeanFactory:populateBean()    
    
    %% 3. 初始化
    AbstractAutowireCapableBeanFactory->>AbstractAutowireCapableBeanFactory:initializeBean()    
    
    %% 4. 注册销毁接口
    AbstractAutowireCapableBeanFactory->>AbstractBeanFactory:registerDisposableBeanIfNecessary()
```

## initializeBean()
- 调用感知方法 invokeAwareMethods BeanNameAware BeanClassLoaderAware BeanFactoryAware
- 应用后处理器初始化之前方法 applyBeanPostProcessorsBeforeInitialization 循环遍历 postProcessBeforeInitialization
- 调用初始化方法 invokeInitMethods
    + 初始化中的bean InitializingBean.afterPropertiesSet()
    + 调用自定义初始化方法 invokeCustomInitMethod
- 应用后处理器初始化之前方法 applyBeanPostProcessorsAfterInitialization 循环遍历 postProcessAfterInitialization

```mermaid
sequenceDiagram
    AbstractAutowireCapableBeanFactory->>AbstractAutowireCapableBeanFactory:initializeBean()  
    
    %% 1. 感知方法 BeanNameAware BeanClassLoaderAware BeanFactoryAware
    AbstractAutowireCapableBeanFactory->>AbstractAutowireCapableBeanFactory:invokeAwareMethods()  
    
    %% 2. 初始化之前
    AbstractAutowireCapableBeanFactory->>AbstractAutowireCapableBeanFactory:applyBeanPostProcessorsBeforeInitialization()  
    loop bean后处理器
        AbstractAutowireCapableBeanFactory->>AbstractBeanFactory:getBeanPostProcessors()  
        AbstractAutowireCapableBeanFactory->>BeanPostProcessor:postProcessBeforeInitialization() 
    end
    
    %% 3. 初始化
    AbstractAutowireCapableBeanFactory->>AbstractAutowireCapableBeanFactory:invokeInitMethods()  
    
    %% 3.1 初始化中的bean
    opt
        AbstractAutowireCapableBeanFactory->>InitializingBean:afterPropertiesSet()  
    end
    
    %% 3.2 调用自定义初始化方法
    opt
        AbstractAutowireCapableBeanFactory->>AbstractAutowireCapableBeanFactory:invokeCustomInitMethod()  
    end
    
    %% 4. 初始化之后
    AbstractAutowireCapableBeanFactory->>AbstractAutowireCapableBeanFactory:applyBeanPostProcessorsAfterInitialization()  
    loop bean后处理器
        AbstractAutowireCapableBeanFactory->>AbstractBeanFactory:getBeanPostProcessors()  
        AbstractAutowireCapableBeanFactory->>BeanPostProcessor:postProcessAfterInitialization() 
    end
```
