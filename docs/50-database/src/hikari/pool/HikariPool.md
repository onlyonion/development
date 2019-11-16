com.zaxxer.hikari.pool.HikariPool

## hierarchy
```
PoolBase (com.zaxxer.hikari.pool)
    HikariPool (com.zaxxer.hikari.pool)
HikariPool (com.zaxxer.hikari.pool)
    PoolBase (com.zaxxer.hikari.pool)
    HikariPoolMXBean (com.zaxxer.hikari)
    IBagStateListener in ConcurrentBag (com.zaxxer.hikari.util)
```

## define
```plantuml
@startuml

class PoolBase
class HikariConfig

PoolBase *- HikariConfig
PoolBase ^-- HikariPool

class HikariPool

HikariPool *-- ScheduledExecutorService
HikariPool *-- ProxyLeakTask
HikariPool *-- SuspendResumeLock
HikariPool *-- MetricsTrackerDelegate

@enduml
```