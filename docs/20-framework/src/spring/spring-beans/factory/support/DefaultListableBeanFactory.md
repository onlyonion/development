org.springframework.beans.factory.support.DefaultListableBeanFactory

## hierarchy
```
SimpleAliasRegistry
    DefaultSingletonBeanRegistry
        FactoryBeanRegistrySupport
            AbstractBeanFactory
                AbstractAutowireCapableBeanFactory
                    DefaultListableBeanFactory
```

## define

```plantuml
@startuml

class SimpleAliasRegistry

class DefaultSingletonBeanRegistry
SimpleAliasRegistry ^-- DefaultSingletonBeanRegistry

abstract class FactoryBeanRegistrySupport
DefaultSingletonBeanRegistry ^-- FactoryBeanRegistrySupport

abstract class AbstractBeanFactory
FactoryBeanRegistrySupport ^-- AbstractBeanFactory 

abstract class AbstractAutowireCapableBeanFactory
AbstractBeanFactory ^-- AbstractAutowireCapableBeanFactory 

class DefaultListableBeanFactory {
    + void registerBeanDefinition(String beanName, BeanDefinition beanDefinition)
    + void preInstantiateSingletons()
}
AbstractAutowireCapableBeanFactory ^-- DefaultListableBeanFactory 

@enduml
```

## 依赖注入DI

### getBean()

```mermaid
sequenceDiagram
    
    Actor ->> AbstractBeanFactory: getBean()
    AbstractBeanFactory ->> AbstractBeanFactory: doGetBean()
    AbstractBeanFactory ->> DefaultSingletonBeanRegistry: getSingleton()
    AbstractBeanFactory ->> AbstractAutowireCapableBeanFactory: createBean()
    AbstractAutowireCapableBeanFactory ->> AbstractAutowireCapableBeanFactory: doCreateBean()
    AbstractAutowireCapableBeanFactory ->> AbstractAutowireCapableBeanFactory: createBeanInstance()
    AbstractAutowireCapableBeanFactory ->> AbstractAutowireCapableBeanFactory: instantiateUsingFactoryMethod()
```

### 依赖注入
```mermaid
sequenceDiagram
    participant Actor
    participant AbstractBeanFactory
    participant AbstractAutowireCapableBeanFactory

    Actor ->> AbstractBeanFactory: getBean()
    AbstractBeanFactory ->> AbstractBeanFactory: doGetBean()
    AbstractBeanFactory ->> AbstractAutowireCapableBeanFactory: createBean()
    AbstractAutowireCapableBeanFactory ->> AbstractAutowireCapableBeanFactory: doCreateBean()
    AbstractAutowireCapableBeanFactory ->> InstantiationStrategy: instantiate()
    InstantiationStrategy -->> AbstractAutowireCapableBeanFactory: populateBean()
    AbstractAutowireCapableBeanFactory ->> AbstractAutowireCapableBeanFactory: applyPropertyValues()
```

### 初始化bean
```mermaid
graph LR
    %% AbstractAutowireCapableBeanFactory 初始化bean
    initializeBean --> invokeAwareMethods

    %% 感知方法
    invokeAwareMethods --> BeanNameAware
    invokeAwareMethods --> BeanClassLoaderAware
    invokeAwareMethods --> BeanFactoryAware

    %% 初始化之前
    initializeBean --> applyBeanPostProcessorsBeforeInitialization

    %% 初始化方法
    initializeBean --> invokeInitMethods
    invokeInitMethods --> InitializingBean.afterPropertiesSet
    invokeInitMethods --> invokeCustomInitMethod

    %% 初始化之后
    initializeBean --> applyBeanPostProcessorsAfterInitialization
```