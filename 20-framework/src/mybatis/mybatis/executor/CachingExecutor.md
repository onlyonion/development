org.apache.ibatis.executor.CachingExecutor

## define
```plantuml
@startuml

interface Executor
Executor ^.. CachingExecutor
class CachingExecutor {
    - final Executor delegate
    - final TransactionalCacheManager
}

CachingExecutor o-- Executor
CachingExecutor o-- TransactionalCacheManager
CachingExecutor ..> CacheKey

@enduml
```
