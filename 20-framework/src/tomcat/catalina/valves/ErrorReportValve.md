## define

```
@startuml
interface Valve
abstract class ValveBase

class ErrorReportValve {
}

Valve <|.. ValveBase
ValveBase <|-- ErrorReportValve
@enduml
```