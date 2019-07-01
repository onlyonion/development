cn.hutool.cache.GlobalPruneTimer

* 枚举实现单例模式
* AtomicInteger

## define
```plantuml
@startuml

enum GlobalPruneTimer {
    
}

GlobalPruneTimer o-- ScheduledExecutorService

interface ScheduledExecutorService


@enduml
```