org.apache.catalina.core.StandardEngineValve

## define
```plantuml
@startuml
interface Valve
abstract class ValveBase

class StandardEngineValve {
}

Valve <|.. ValveBase
ValveBase <|-- StandardEngineValve
@enduml
```