## 1. 定义
* 代理工厂，生成代理，生成调用器
* jdk动态代理、javassit代理

## 2. 类图

### 字节码框架
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