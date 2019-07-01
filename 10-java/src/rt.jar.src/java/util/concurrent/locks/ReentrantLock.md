java.util.concurrent.locks.ReentrantLock
## hierarchy
```
ReentrantLock (java.util.concurrent.locks)
    Segment in ConcurrentHashMap (java.util.concurrent)
    Segment in ConcurrentReferenceHashMap (org.springframework.util)
    Segment in ConcurrentHashMap (org.jboss.netty.util.internal)
    Segment in ConcurrentIdentityHashMap (org.jboss.netty.util.internal)
    Segment in LocalCache (com.google.common.cache)
    ZkLock (org.I0Itec.zkclient)
```
## define
```plantuml
@startuml

'''''''''''''''''''''''''''''''' AQS ''''''''''''''''''''''''''''''''
abstract class AbstractOwnableSynchronizer {
    - transient Thread exclusiveOwnerThread
}

abstract class AbstractQueuedSynchronizer {
    - transient volatile Node head
    - transient volatile Node tail
    - volatile int state
}

class Node {
    ~ volatile int waitStatus
    ~ volatile Node prev
    ~ volatile Node next
    ~ volatile Thread thread
    ~ Node nextWaiter
}

AbstractOwnableSynchronizer <|-- AbstractQueuedSynchronizer
AbstractQueuedSynchronizer +-- Node

'''''''''''''''''''''''''''''''' Sync FairSync NonfairSync''''''''''''''''''''''''''''''''
abstract class Sync
class FairSync
class NonfairSync

AbstractQueuedSynchronizer <|-- Sync
Sync <|-- NonfairSync
Sync <|-- FairSync

'''''''''''''''''''''''''''''''' ReentrantLock ''''''''''''''''''''''''''''''''
interface Lock #orange
class ReentrantLock #orange {
    - final Sync sync
}

Lock <|.. ReentrantLock
ReentrantLock *-- Sync

@enduml
```