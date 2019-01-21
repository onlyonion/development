org.springframework.aop.framework.ProxyFactoryBean

## 类图
```yuml
// {type:class}

[AdvisedSupport{bg:thistle}]
[ProxyCreatorSupport{bg:thistle}]

// 1. 代理配置基类
[ProxyConfig]^-[AdvisedSupport]

// 2. 已被通知的支持
[Advised]^-.-[AdvisedSupport]
[TargetClassAware]^-[Advised]

// Advised包括拦截器、通知器的集合、已经代理的接口的数组
[Advised]<>-[Advisor]

// 3. 代理创建支持
[AdvisedSupport]^-[ProxyCreatorSupport]

// 代理创建支持类，持有aop代理工厂，构造时有DefaultAopProxyFactory
[ProxyCreatorSupport]++-[AopProxyFactory]
[ProxyCreatorSupport]++-[AdvisedSupportListener]

// 4.1 代理工厂
[ProxyCreatorSupport]^-[ProxyFactory]
[ProxyCreatorSupport]^-[AspectJProxyFactory]

// 4.2 代理工程bean
[ProxyCreatorSupport]^-[ProxyFactoryBean]

[FactoryBean]^-.-[ProxyFactoryBean]
[BeanClassLoaderAware]^-.-[ProxyFactoryBean]
[BeanFactoryAware]^-.-[ProxyFactoryBean]

```

## 生成AOP代理

```mermaid
sequenceDiagram
   Actor->>ProxyFactoryBean:getObject()
   
   %% 1. 第一次使用时初始化
   ProxyFactoryBean->>ProxyFactoryBean:initializeAdvisorChain()
   
   %% 2. 是不是单例
   opt isSingleton()
        ProxyFactoryBean->>ProxyFactoryBean:getSingletonInstance()
        
        %% 2.1 两种实现，jdk或者cglib
        ProxyFactoryBean->>DefaultAopProxyFactory:createAopProxy()
        alt 是接口
            DefaultAopProxyFactory->>JdkDynamicAopProxy:实例化jdk动态代理
        else
            DefaultAopProxyFactory->>ObjenesisCglibAopProxy:实例化cglib动态代理
        end
        DefaultAopProxyFactory->>ProxyFactoryBean:返回代理动态代理
        
        %% 2.2 真正生成代理
        ProxyFactoryBean->>AopProxy:getProxy(AopProxy)生成代理
   end
   
   %% 3. 原型实例
   ProxyFactoryBean->>ProxyFactoryBean:newPrototypeInstance()
   
   %% 3.1 实例化新的ProxyCreatorSupport
   ProxyFactoryBean->>ProxyCreatorSupport:实例化新的ProxyCreatorSupport()
   
   %% 3.2 步骤与2.1、2.2相同
   ProxyFactoryBean->>ProxyCreatorSupport:createAopProxy()同单例创建
   ProxyFactoryBean->>AopProxy:getProxy(AopProxy)同单例创建

```
