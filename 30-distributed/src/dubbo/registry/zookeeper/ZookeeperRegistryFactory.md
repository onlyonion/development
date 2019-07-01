com.alibaba.dubbo.registry.zookeeper.ZookeeperRegistryFactory
## hierarchy
```
AbstractRegistryFactory (com.alibaba.dubbo.registry.support)
    DubboRegistryFactory (com.alibaba.dubbo.registry.dubbo)
    MulticastRegistryFactory (com.alibaba.dubbo.registry.multicast)
    ZookeeperRegistryFactory (com.alibaba.dubbo.registry.zookeeper)
```

## define
```plantuml
@startuml

'''''''''''''''''''''''''RegistryFactory'''''''''''''''''''''''''''''
interface Registry
interface RegistryFactory {
    + Registry getRegistry(URL url)
}

'''''''''''''''''''''''''AbstractRegistryFactory'''''''''''''''''''''''''''''
abstract class AbstractRegistryFactory
class ZookeeperRegistryFactory

RegistryFactory ..> Registry

AbstractRegistryFactory o-- ReentrantLock
AbstractRegistryFactory "1" o-- "*" Registry

RegistryFactory ^.. AbstractRegistryFactory
AbstractRegistryFactory ^-- ZookeeperRegistryFactory

@enduml
```