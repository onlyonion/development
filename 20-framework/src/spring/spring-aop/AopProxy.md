# AopProxy

```java
public interface AopProxy {
	Object getProxy();
	Object getProxy(ClassLoader classLoader);
}
```

```yuml
// {type:class}

// jdk实现代理
[AopProxy]^-.-[JdkDynamicAopProxy]
[InvocationHandler]^-.-[JdkDynamicAopProxy]
[JdkDynamicAopProxy]++-.-[AdvisedSupport]

// cglib实现代理
[AopProxy]^-.-[CglibAopProxy]
[CglibAopProxy]++-.-[AdvisedSupport]


// 代理的配置信息与已通知的信息
[ProxyConfig]^-[AdvisedSupport]
[Advised]^-.-[AdvisedSupport]
[TargetClassAware]^-[Advised]
```

## 1. JdkDynamicAopProxy
org.springframework.aop.framework.JdkDynamicAopProxy

```yuml
// {type:class}

[Proxy]^-[$ProxyXXX]
[Proxy]++-.-[InvocationHandler]

[$ProxyXXX]<>1->*[Method]

```

## 2. CglibAopProxy
org.springframework.aop.framework.CglibAopProxy

### CglibMethodInvocation
org.springframework.aop.framework.CglibAopProxy.CglibMethodInvocation
DynamicAdvisedInterceptor

### ObjenesisCglibAopProxy