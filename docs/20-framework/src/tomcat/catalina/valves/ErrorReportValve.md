org.apache.catalina.valves.ErrorReportValve

## define

```plantuml
@startuml
interface Valve
abstract class ValveBase

class ErrorReportValve {
}

Valve <|.. ValveBase
ValveBase <|-- ErrorReportValve
@enduml
```