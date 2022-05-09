com.alibaba.dubbo.registry.RegistryService
## hierachy
```
RegistryService (com.alibaba.dubbo.registry)
    Registry (com.alibaba.dubbo.registry)
        AbstractRegistry (com.alibaba.dubbo.registry.support)
            FailbackRegistry (com.alibaba.dubbo.registry.support)
                DubboRegistry (com.alibaba.dubbo.registry.dubbo)
                RedisRegistry (com.alibaba.dubbo.registry.redis)
                MulticastRegistry (com.alibaba.dubbo.registry.multicast)
                ZookeeperRegistry (com.alibaba.dubbo.registry.zookeeper)
```

## define
```plantuml
@startuml

interface RegistryService {
    void register(URL url);
    void unregister(URL url);
    void subscribe(URL url, NotifyListener listener);
    void unsubscribe(URL url, NotifyListener listener);
    List<URL> lookup(URL url);
}

interface Node
interface Registry
abstract class AbstractRegistry
abstract class FailbackRegistry

FailbackRegistry *- ScheduledExecutorService

class DubboRegistry
class RedisRegistry
class MulticastRegistry
class ZookeeperRegistry

RegistryService ^-- Registry
Node ^-- Registry
Registry ^.. AbstractRegistry
AbstractRegistry ^-- FailbackRegistry

FailbackRegistry ^-- DubboRegistry
FailbackRegistry ^-- RedisRegistry
FailbackRegistry ^-- MulticastRegistry
FailbackRegistry ^-- ZookeeperRegistry

'''''''''''' 工厂模式 ''''''''''''
interface RegistryFactory
RegistryFactory *- Registry

@enduml
```

## duty(function)
* 注册url
* 取消注册url
* 订阅url, listener
* 取消订阅
* 查找