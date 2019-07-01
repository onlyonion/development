org.apache.tomcat.util.threads.LimitLatch

* AQS并发框架
* volatile
* AtomicLong

## define
```plantuml
@startuml

class LimitLatch {
    private final Sync sync
    private final AtomicLong count
    private volatile long limit
    private volatile boolean released
}

LimitLatch o-- Sync
LimitLatch o-- AtomicLong

@enduml
```