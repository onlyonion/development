## zookeeper
org.apache.zookeeper.ZooKeeper.ZooKeeper

org.apache.zookeeper.ClientCnxn

## content
```plantuml
@startuml

ZooKeeper +-- ClientCnxn

ClientCnxn +-- SendThread

ClientCnxn +-- EventThread

Thread <|-- ZooKeeperThread
ZooKeeperThread <|-- SendThread
ZooKeeperThread <|-- EventThread

@enduml
```

## links
* [《从Paxos到Zookeeper 分布式一致性原理与实践》倪超](/99-book/notes/30-distributed/从Paxos到ZooKeeper.md)
