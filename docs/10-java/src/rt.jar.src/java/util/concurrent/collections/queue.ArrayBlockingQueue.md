java.util.concurrent.ArrayBlockingQueue

## hierarchy
```
AbstractCollection (java.util)
    AbstractQueue (java.util)
        ArrayBlockingQueue (java.util.concurrent)
ArrayBlockingQueue (java.util.concurrent)
    AbstractQueue (java.util)
        AbstractCollection (java.util)
            Object (java.lang)
            Collection (java.util)
                Iterable (java.lang)
        Queue (java.util)
            Collection (java.util)
                Iterable (java.lang)
    BlockingQueue (java.util.concurrent)
        Queue (java.util)
            Collection (java.util)
                Iterable (java.lang)
```
## define
```plantuml
@startuml

class ArrayBlockingQueue<E> {
    final Object[] items
    int takeIndex
    int putIndex
    int count
    final ReentrantLock lock
    private final Condition notEmpty
    private final Condition notFull
}
AbstractQueue ^-- ArrayBlockingQueue
BlockingQueue ^.. ArrayBlockingQueue
ArrayBlockingQueue o-- ReentrantLock
ArrayBlockingQueue o-- Condition

@enduml
```