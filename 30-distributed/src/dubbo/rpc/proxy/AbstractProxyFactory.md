com.alibaba.dubbo.rpc.proxy.AbstractProxyFactory
## hierarchy
```
ProxyFactory (com.alibaba.dubbo.rpc)
    AbstractProxyFactory (com.alibaba.dubbo.rpc.proxy)
        JavassistProxyFactory (com.alibaba.dubbo.rpc.proxy.javassist)
        JdkProxyFactory (com.alibaba.dubbo.rpc.proxy.jdk)
```
## define
```plantuml
@startuml

'''''''''''''''''''''''ProxyFactory'''''''''''''''''''''''''''
interface ProxyFactory {
    + <T> T getProxy(Invoker<T> invoker)
    + <T> Invoker<T> getInvoker(T proxy, Class<T> type, URL url)
}
ProxyFactory ^.. AbstractProxyFactory

'''''''''''''''''''''''AbstractProxyFactory'''''''''''''''''''''''''''
abstract class AbstractProxyFactory {
    + abstract <T> T getProxy(Invoker<T> invoker, Class<?>[] types)
}

AbstractProxyFactory ^-- JavassistProxyFactory
AbstractProxyFactory ^-- JdkProxyFactory

class JavassistProxyFactory
class JdkProxyFactory

@enduml
```