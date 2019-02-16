org.apache.catalina.valves.ValveBase

## define

```plantuml
@startuml

interface Valve
interface Contained

abstract class ValveBase {
    # Container container
    # Valve next
}

LifecycleMBeanBase <|-- ValveBase

Valve <|.. ValveBase
Contained <|.. ValveBase

@enduml
```