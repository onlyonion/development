org.apache.catalina.core.StandardHostValve

## define
```plantuml
@startuml
interface Valve
abstract class ValveBase

class StandardHostValve {
}

Valve <|.. ValveBase
ValveBase <|-- StandardHostValve
@enduml
```