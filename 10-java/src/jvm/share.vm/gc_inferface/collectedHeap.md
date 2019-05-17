```plantuml
@startuml

class CollectedHeap

CollectedHeap ^-- SharedHeap
CollectedHeap ^-- ParallelScavengeHeap

SharedHeap ^-- GenCollectedHeap
SharedHeap ^-- G1CollectedHeap

@enduml
```
