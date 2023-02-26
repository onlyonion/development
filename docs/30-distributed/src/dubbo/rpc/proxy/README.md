com.alibaba.dubbo.rpc.proxy

## package
```
javassist
    JavassistProxyFactory
jdk
    JdkProxyFactory
wrapper
    StubProxyFactoryWrapper
AbstractProxyFactory
AbstractProxyInvoker
InvokerInvocationHandler
```

## overview
```plantuml
@startuml

''''''''''''''''''''调用者''''''''''''''''''''''''
interface Invoker
Invoker ^.. AbstractProxyInvoker
abstract class AbstractProxyInvoker

AbstractProxyInvoker ^-- JavassistProxyFactory$1
AbstractProxyInvoker ^-- JdkProxyFactory$1

''''''''''''''''''''代理''''''''''''''''''''''''''
interface ProxyFactory 
ProxyFactory ..> Invoker

ProxyFactory ^.. AbstractProxyFactory

abstract class AbstractProxyFactory
class JavassistProxyFactory #orange
class JdkProxyFactory #orange

AbstractProxyFactory ^-- JavassistProxyFactory
AbstractProxyFactory ^-- JdkProxyFactory
JavassistProxyFactory +-- JavassistProxyFactory$1
JdkProxyFactory +-- JdkProxyFactory$1

''''''''''''''''''''调用者处理器''''''''''''''''''''
class InvokerInvocationHandler
InvokerInvocationHandler o-- Invoker

@enduml
```