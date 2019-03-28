com.alibaba.dubbo.rpc.cluster.directory.AbstractDirectory

## hierachy
```
Directory (com.alibaba.dubbo.rpc.cluster)
    AbstractDirectory (com.alibaba.dubbo.rpc.cluster.directory)
        RegistryDirectory (com.alibaba.dubbo.registry.integration)
        StaticDirectory (com.alibaba.dubbo.rpc.cluster.directory)
```

## define

```plantuml
@startuml
interface Directory<T>
Directory ^.. AbstractDirectory

abstract class AbstractDirectory<T> {
    + List<Invoker<T>> list(Invocation invocation)
    # abstract List<Invoker<T>> doList(Invocation invocation)
}
AbstractDirectory ^-- RegistryDirectory

class RegistryDirectory<T> {
    + List<Invoker<T>> doList(Invocation invocation)
}

@enduml
```