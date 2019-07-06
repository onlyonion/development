
## package
```
AbstractOwnableSynchronizer
AbstractQueuedLongSynchronizer
AbstractQueuedSynchronizer
Condition
Lock
LockSupport
ReadWriteLock
ReentrantLock
ReentrantReadWriteLock
StampedLock
```

## overview

```plantuml
@startuml

interface Lock
interface Condition
interface ReadWriteLock

'''''''''''''''''''''队列同步器'''''''''''''''''''''
abstract class AbstractOwnableSynchronizer
abstract class AbstractQueuedLongSynchronizer
abstract class AbstractQueuedSynchronizer #orange

Lock o-- Condition
Lock ^.. ReentrantLock
ReadWriteLock o-- Lock
ReadWriteLock ^.. ReentrantReadWriteLock

AbstractOwnableSynchronizer ^-- AbstractQueuedLongSynchronizer
AbstractOwnableSynchronizer ^-- AbstractQueuedSynchronizer

'''''''''''''''''''''可重入锁'''''''''''''''''''''
class ReentrantLock 
class ReentrantReadWriteLock

abstract class ReentrantLock.Sync
AbstractQueuedSynchronizer ^-- ReentrantLock.Sync
ReentrantLock +-- ReentrantLock.Sync

abstract class ReentrantReadWriteLock.Sync
AbstractQueuedSynchronizer ^-- ReentrantReadWriteLock.Sync
ReentrantReadWriteLock +-- ReentrantReadWriteLock.Sync

class StampedLock
LockSupport <.. StampedLock
LockSupport <.. AbstractQueuedSynchronizer

@enduml
```