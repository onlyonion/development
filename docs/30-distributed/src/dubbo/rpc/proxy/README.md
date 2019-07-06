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

AbstractProxyFactory ^-- JavassistProxyFactory
AbstractProxyFactory ^-- JdkProxyFactory
class JavassistProxyFactory
class JdkProxyFactory

JavassistProxyFactory +-- JavassistProxyFactory$1
JdkProxyFactory +-- JdkProxyFactory$1

''''''''''''''''''''调用者处理器''''''''''''''''''''
class InvokerInvocationHandler
InvokerInvocationHandler o-- Invoker

@enduml
```