org.springframework.aop.framework.ProxyFactoryBean

```
ProxyFactoryBean
    ProxyCreatorSupport
        AdvisedSupport
            ProxyConfig
```

#### 继承

```yuml
// {type:class}
[ProxyConfig]^-[AdvisedSupport]
[AdvisedSupport]^-[ProxyCreatorSupport]
[ProxyCreatorSupport]^-[ProxyFactoryBean]
```

#### 生成AOP代理
```mermaid
sequenceDiagram
    %% 实现FactoryBean，实例化通知链
    ProxyFactoryBean ->> ProxyFactoryBean: getObject()
    ProxyFactoryBean ->> ProxyFactoryBean: initializeAdvisorChain()
    ProxyFactoryBean ->> ProxyFactoryBean: getSingletonInstance()

    %% 创建代理对象
    ProxyFactoryBean ->> ProxyCreatorSupport: getProxy()
    ProxyCreatorSupport ->> DefaultAopProxyFactory: createAopProxy()

    %% Jdk或者Cglib创建代理对象
    alt is isInterface
　　　　BDefaultAopProxyFactory ->> JdkDynamicAopProxy: isInterface()
        JdkDynamicAopProxy -->> DefaultAopProxyFactory: AopProxy
　　else is Class
　　　　DefaultAopProxyFactory ->> CglibAopProxy: createCglibAopProxy()
        CglibAopProxy -->> DefaultAopProxyFactory: AopProxy
　　end
```