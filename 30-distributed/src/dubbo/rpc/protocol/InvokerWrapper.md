com.alibaba.dubbo.rpc.protocol.InvokerWrapper

## hierarchy
```
InvokerWrapper (com.alibaba.dubbo.rpc.protocol)
    InvokerDelegete in RegistryDirectory (com.alibaba.dubbo.registry.integration)
    InvokerDelegete in RegistryProtocol (com.alibaba.dubbo.registry.integration)
```

## define
```plantuml
@startuml

interface Node {
    + URL getUrl()
    + boolean isAvailable()
    + void destroy()
}
interface Invoker<T> {
    + Class<T> getInterface()
    + Result invoke(Invocation invocation) throws RpcException
}

Node ^-- Invoker
Invoker ^.. InvokerWrapper

class InvokerWrapper<T> {
    - final Invoker<T> invoker
    - final URL url
}

@enduml
```