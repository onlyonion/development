org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory

## createBean()
* resolveBeanClass
* prepareMethodOverrides
* resolveBeforeInstantiation
* doCreateBean

```mermaid
sequenceDiagram

    AbstractBeanFactory->>AbstractAutowireCapableBeanFactory:createBean()
    
    %% 解析
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
* 填充属性 populateBean
* 初始化 initializeBean
    - 调用感知方法 invokeAwareMethods
    - 应用后处理器初始化之前方法 applyBeanPostProcessorsBeforeInitialization 循环遍历 postProcessBeforeInitialization
    - 初始化中的bean InitializingBean.afterPropertiesSet()
    - 调用自定义初始化方法 invokeCustomInitMethod
    - 应用后处理器初始化之前方法 applyBeanPostProcessorsAfterInitialization 循环遍历 postProcessAfterInitialization
* 注册销毁接口 registerDisposableBeanIfNecessary

```mermaid
sequenceDiagram
    AbstractAutowireCapableBeanFactory->>AbstractAutowireCapableBeanFactory:doCreateBean()
    
    %% 实例化
    AbstractAutowireCapableBeanFactory->>AbstractAutowireCapableBeanFactory:createBeanInstance()    
    
    %% 后处理
    AbstractAutowireCapableBeanFactory->>AbstractAutowireCapableBeanFactory:applyMergedBeanDefinitionPostProcessors()    
    
    opt 懒汉式单例bean
        AbstractAutowireCapableBeanFactory->>DefaultSingletonBeanRegistry:addSingletonFactory()    
    end
    
    %% 填充属性
    AbstractAutowireCapableBeanFactory->>AbstractAutowireCapableBeanFactory:populateBean()    
    
    %% 初始化
    AbstractAutowireCapableBeanFactory->>AbstractAutowireCapableBeanFactory:initializeBean()    
    
    %% 注册销毁接口
    AbstractAutowireCapableBeanFactory->>AbstractBeanFactory:registerDisposableBeanIfNecessary()
```

## initializeBean()
* invokeAwareMethods
* applyBeanPostProcessorsBeforeInitialization
* afterPropertiesSet
* invokeCustomInitMethod
* applyBeanPostProcessorsAfterInitialization

```mermaid
sequenceDiagram
    AbstractAutowireCapableBeanFactory->>AbstractAutowireCapableBeanFactory:initializeBean()  
    
    %% 感知方法 BeanNameAware BeanClassLoaderAware BeanFactoryAware
    AbstractAutowireCapableBeanFactory->>AbstractAutowireCapableBeanFactory:invokeAwareMethods()  
    
    %% 初始化之前
    AbstractAutowireCapableBeanFactory->>AbstractAutowireCapableBeanFactory:applyBeanPostProcessorsBeforeInitialization()  
    loop bean后处理器
        AbstractAutowireCapableBeanFactory->>AbstractBeanFactory:getBeanPostProcessors()  
        AbstractAutowireCapableBeanFactory->>BeanPostProcessor:postProcessBeforeInitialization() 
    end
    
    %% 初始化
    AbstractAutowireCapableBeanFactory->>AbstractAutowireCapableBeanFactory:invokeInitMethods()  
    opt
        AbstractAutowireCapableBeanFactory->>InitializingBean:afterPropertiesSet()  
    end
    opt
        AbstractAutowireCapableBeanFactory->>AbstractAutowireCapableBeanFactory:invokeCustomInitMethod()  
    end
    
    %% 初始化之后
    AbstractAutowireCapableBeanFactory->>AbstractAutowireCapableBeanFactory:applyBeanPostProcessorsAfterInitialization()  
    loop bean后处理器
        AbstractAutowireCapableBeanFactory->>AbstractBeanFactory:getBeanPostProcessors()  
        AbstractAutowireCapableBeanFactory->>BeanPostProcessor:postProcessAfterInitialization() 
    end
```
