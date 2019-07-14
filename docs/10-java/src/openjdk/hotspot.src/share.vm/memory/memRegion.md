

```plantuml
@startuml

class MemRegion

StackObj ^-- MemRegionClosure
class MemRegionClosure

MemRegionClosure ^-- MemRegionClosureRO
class MemRegionClosureRO


@enduml
```
