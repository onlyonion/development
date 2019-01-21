# AopProxy

## 定义
```java
public interface AopProxy {
	Object getProxy();
	Object getProxy(ClassLoader classLoader);
}
```

## 类图
```yuml
// {type:class}

// 1. jdk实现代理
[AopProxy]^-.-[JdkDynamicAopProxy]
[InvocationHandler]^-.-[JdkDynamicAopProxy]
[JdkDynamicAopProxy]++-.-[AdvisedSupport]

// 2. cglib实现代理
[AopProxy]^-.-[CglibAopProxy]
[CglibAopProxy]++-.-[AdvisedSupport]


// 代理配置
[ProxyConfig]^-[AdvisedSupport]

// 已通知的支持类
[Advised]^-.-[AdvisedSupport]

// 目标对象感知
[TargetClassAware]^-[Advised]
```

### 1. JdkDynamicAopProxy
org.springframework.aop.framework.JdkDynamicAopProxy

Proxy.newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h)

```yuml
// {type:class}

// 代理类对应目标类方法的实现
// super.h.invoke(this, m3, (Object[])null)

// 代理类继承Proxy
[Proxy]^-[$ProxyXXX]

// Proxy持有调用处理器
[Proxy]++-[InvocationHandler]

// 代理类的方法
[$ProxyXXX]++1-*[Method]

```

### 2. CglibAopProxy
org.springframework.aop.framework.CglibAopProxy

### CglibMethodInvocation
org.springframework.aop.framework.CglibAopProxy.CglibMethodInvocation

DynamicAdvisedInterceptor

### ObjenesisCglibAopProxy