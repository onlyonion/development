com.alibaba.dubbo.monitor.dubbo.DubboMonitor

## hierarchy
## define
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

## fields
* ScheduledExecutorService
* ConcurrentMap<Statistics, AtomicReference<long[]>>

```
// 定时任务执行器 3个线程
private final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3, new NamedThreadFactory("DubboMonitorSendTimer", true));
    
// 原子引用
private final ConcurrentMap<Statistics, AtomicReference<long[]>> statisticsMap = new ConcurrentHashMap<Statistics, AtomicReference<long[]>>();
```