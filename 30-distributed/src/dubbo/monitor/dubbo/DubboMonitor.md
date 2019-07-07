com.alibaba.dubbo.monitor.dubbo.DubboMonitor

## hierarchy
## define
* ScheduledExecutorService
* ConcurrentMap<Statistics, AtomicReference<long[]>>

```plantuml
@startuml

class DubboMonitor

DubboMonitor o-- ScheduledExecutorService
DubboMonitor o-- ScheduledFuture
DubboMonitor o-- MonitorService

class ScheduledExecutorService
class ScheduledFuture
class MonitorService

ScheduledExecutorService o-- DelayedWorkQueue

@enduml
```