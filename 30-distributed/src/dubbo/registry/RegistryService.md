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

@enduml
```

## duty(function)
* 注册url
* 取消注册url
* 订阅url, listener
* 取消订阅
* 查找