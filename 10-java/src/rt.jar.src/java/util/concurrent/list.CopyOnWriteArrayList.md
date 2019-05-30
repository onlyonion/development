java.util.concurrent.CopyOnWriteArrayList


## define
```plantuml
@startuml

class CopyOnWriteArrayList<E> {
    final transient ReentrantLock lock
    - transient volatile Object[] array
}

@enduml
```