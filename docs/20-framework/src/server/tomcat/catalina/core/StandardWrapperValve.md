org.apache.catalina.core.StandardWrapperValve

## hierachy
```
LifecycleBase (org.apache.catalina.util)
    LifecycleMBeanBase (org.apache.catalina.util)
        ValveBase (org.apache.catalina.valves)
            StandardWrapperValve (org.apache.catalina.core)
```

## define

```plantuml
@startuml
interface Valve
abstract class ValveBase

class StandardWrapperValve {
    - volatile long processingTime
    - final AtomicInteger requestCount
    - final AtomicInteger errorCount
    + final void invoke(Request request, Response response)
}

Valve <|.. ValveBase
ValveBase <|-- StandardWrapperValve
@enduml
```