org.apache.zookeeper.Watcher

## hierarchy

## define
```plantuml
@startuml

interface Watcher
interface Event
enum KeeperState
enum EventType

Watcher +-- Event
Event +-- KeeperState
Event +-- EventType

@enduml
```