com.alibaba.dubbo.monitor.MonitorService
## hierarchy
```
MonitorService (com.alibaba.dubbo.monitor)
    Monitor (com.alibaba.dubbo.monitor)
        DubboMonitor (com.alibaba.dubbo.monitor.dubbo)
```
## define
```plantuml
@startuml

interface MonitorService {
    void collect(URL statistics)
    List<URL> lookup(URL query)
}

@enduml
```