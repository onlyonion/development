java.util.concurrent.CountDownLatch

一个或多个线程等待其他线程完成操作。join。


## define
```plantuml
@startuml

class CountDownLatch {
    - final Sync sync
    + void countDown()
    + void await()
}

abstract class AbstractQueuedSynchronizer
AbstractQueuedSynchronizer ^-- Sync
class Sync {

}
CountDownLatch +-- Sync
CountDownLatch o-- Sync

@enduml
```