org.springframework.beans.factory.support.AbstractBeanFactory

## 1. 定义
```
SimpleAliasRegistry AliasRegistry
    DefaultSingletonBeanRegistry SingletonBeanRegistry
        FactoryBeanRegistrySupport
            AbstractBeanFactory ConfigurableBeanFactory
```

## 2. 依赖注入

### 2.1 getBean()
```mermaid
sequenceDiagram
    %% 1. 获取bean
    Actor ->> AbstractBeanFactory: getBean()
    AbstractBeanFactory ->> AbstractBeanFactory: doGetBean()
    
    %% 2. 名称处理
    AbstractBeanFactory ->> AbstractBeanFactory: transformedBeanName()
    AbstractBeanFactory ->> SimpleAliasRegistry: canonicalName()
    
    %% 3. 单例
    AbstractBeanFactory ->> DefaultSingletonBeanRegistry: getSingleton()
    
    %% 4. 创建
    alt 共享实例存在且参数为null
        AbstractBeanFactory ->> AbstractBeanFactory: 2.2 getObjectForBeanInstance()
    else
        AbstractBeanFactory ->> AbstractBeanFactory: 2.3 根据beanDefinition生成
    end
    
    %% 5. 后处理，返回
    opt 需要进行类型转换
        AbstractBeanFactory ->> TypeConverter: convertIfNecessary()
        AbstractBeanFactory ->> Actor: 生成完毕，返回bean
    end
    AbstractBeanFactory ->> Actor: 生成完毕，返回bean
```

### 2.2 getObjectForBeanInstance()
```mermaid
sequenceDiagram
    AbstractBeanFactory ->> AbstractBeanFactory: getObjectForBeanInstance()
    
    opt 判断是beanFactory
        AbstractBeanFactory ->> AbstractBeanFactory: 返回
    end
```

### 2.3 根据beanDefinition生成
* 父工厂加载
* 依赖注入 循环递归处理依赖
* 单例创建
* 原型创建

```mermaid
sequenceDiagram
    %% 1. 父工厂
    AbstractBeanFactory ->> AbstractBeanFactory: getParentBeanFactory()
    opt 父工厂不为null并且本工厂不包含bean
        %% 由父工厂去加载
        AbstractBeanFactory ->> AbstractBeanFactory: getBean()
    end
    
    opt 类型检查
        AbstractBeanFactory ->> AbstractBeanFactory: markBeanAsCreated()
    end
    
    %% 2. 获得bean定义
    AbstractBeanFactory ->> AbstractBeanFactory: getMergedLocalBeanDefinition()
    AbstractBeanFactory ->> AbstractBeanDefinition: getDependsOn()
    
    %% 3. 依赖注入的解析，循环、递归
    opt 存在依赖
        AbstractBeanFactory ->> DefaultSingletonBeanRegistry: registerDependentBean()
        AbstractBeanFactory ->> AbstractBeanFactory: getBean()
    end
    
    %% 4. 单例、原型、其他范围情景
    alt 单例
        AbstractBeanFactory ->> AbstractAutowireCapableBeanFactory: createBean()
        
        %% 4.1 单例处理
        AbstractBeanFactory ->> DefaultSingletonBeanRegistry: getSingleton()
        AbstractBeanFactory ->> AbstractBeanFactory: getObjectForBeanInstance()
        
    else 原型
        %% 4.2 创建之前、创建、创建之后
        AbstractBeanFactory ->> AbstractAutowireCapableBeanFactory: beforePrototypeCreation()
        AbstractBeanFactory ->> AbstractAutowireCapableBeanFactory: createBean()
        AbstractBeanFactory ->> AbstractAutowireCapableBeanFactory: afterPrototypeCreation()
        
        AbstractBeanFactory ->> AbstractBeanFactory: getObjectForBeanInstance()
        
    else 其他范围情景 request session globalSession application
    
        AbstractBeanFactory ->> AbstractAutowireCapableBeanFactory: beforePrototypeCreation()
        AbstractBeanFactory ->> AbstractAutowireCapableBeanFactory: createBean()
        AbstractBeanFactory ->> AbstractAutowireCapableBeanFactory: afterPrototypeCreation()
        
        %% 4.3 范围处理
        AbstractBeanFactory ->> Scope: get()
        AbstractBeanFactory ->> AbstractBeanFactory: getObjectForBeanInstance()
        
    end
```

### 2.4 createBean()由子类自动装配工厂（AbstractAutowireCapableBeanFactory）实现
