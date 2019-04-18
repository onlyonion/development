com.alibaba.dubbo.rpc.protocol.ProtocolFilterWrapper

## hierarchy
```
Protocol (com.alibaba.dubbo.rpc)
    AbstractProtocol (com.alibaba.dubbo.rpc.protocol)
    ProtocolFilterWrapper (com.alibaba.dubbo.rpc.protocol)
    ProtocolListenerWrapper (com.alibaba.dubbo.rpc.protocol)
    InjvmProtocol (com.alibaba.dubbo.rpc.protocol.injvm)
    RegistryProtocol (com.alibaba.dubbo.registry.integration)
```
## define
* 导出、引入时构建调用者链

```plantuml
@startuml

interface Protocol

Protocol ^.. ProtocolFilterWrapper

class ProtocolFilterWrapper {
    - final Protocol protocol
    + <T> Exporter<T> export(Invoker<T> invoker)
    + <T> Invoker<T> refer(Class<T> type, URL url)
    - static <T> Invoker<T> buildInvokerChain(final Invoker<T> invoker, String key, String group)
}

ProtocolFilterWrapper +-- ProtocolFilterWrapper$1 

interface Invoker
Invoker ^.. ProtocolFilterWrapper$1 

class ProtocolFilterWrapper$1  

@enduml
```