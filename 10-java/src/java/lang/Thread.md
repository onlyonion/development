java.lang.Thread
## define
```plantuml
@startuml

interface Runnable
Runnable ^.. Thread

class Thread {
    - volatile String name
    - int priority
    - boolean daemon = false
    - ThreadGroup group
    - long stackSize
    - long tid
    - volatile int threadStatus
}

Thread *-- Runnable
Thread o-- ThreadGroup
Thread o-- ClassLoader

enum State {
    NEW
    RUNNABLE
    BLOCKED
    WAITING
    TIMED_WAITING
    TERMINATED
}
Thread +-- State
    

@enduml
```
