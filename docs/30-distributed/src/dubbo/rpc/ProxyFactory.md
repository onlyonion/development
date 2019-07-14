com.alibaba.dubbo.rpc.ProxyFactory

## hierarchy
```
ProxyFactory (com.alibaba.dubbo.rpc)
    AbstractProxyFactory (com.alibaba.dubbo.rpc.proxy)
        JavassistProxyFactory (com.alibaba.dubbo.rpc.proxy.javassist)
        JdkProxyFactory (com.alibaba.dubbo.rpc.proxy.jdk)
    StubProxyFactoryWrapper (com.alibaba.dubbo.rpc.proxy.wrapper)
```

## define
* 代理工厂，生成代理，生成调用器
* jdk动态代理、javassit代理

```java
@SPI("javassist")
public interface ProxyFactory {
    @Adaptive({Constants.PROXY_KEY})
    <T> T getProxy(Invoker<T> invoker) throws RpcException;
    @Adaptive({Constants.PROXY_KEY})
    <T> Invoker<T> getInvoker(T proxy, Class<T> type, URL url) throws RpcException;
}
```

## 字节码框架
* asm
* javassit
* jdk
* cglib

```yuml
// {type:class}
// 代理工厂
[ProxyFactory||+getProxy(invoker);+getInvoker(proxy,type,url)]
[ProxyFactory]^-.-[AbstractProxyFactory]

// 两种类型的代理工厂
[AbstractProxyFactory]^-[JavassistProxyFactory{bg:steelblue}]
[AbstractProxyFactory]^-[JdkProxyFactory{bg:turquoise}]

// 客户端桩代理工厂包装
[ProxyFactory]^-.-[StubProxyFactoryWrapper]
```