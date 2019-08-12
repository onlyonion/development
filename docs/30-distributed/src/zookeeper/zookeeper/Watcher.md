org.apache.zookeeper.Watcher

## hierarchy
```
Watcher (org.apache.zookeeper)
    ServerCnxn (org.apache.zookeeper.server)
    MyWatcher in ZooKeeperMain (org.apache.zookeeper)
    
    ZkClient (org.I0Itec.zkclient)
    
    ConnectionState (org.apache.curator)
    NamespaceWatcher (org.apache.curator.framework.imps)
    TreeNode in TreeCache (org.apache.curator.framework.recipes.cache)
```

## define
```plantuml
@startuml

interface Watcher {
    + void process(WatchedEvent event);
}

interface Event

enum KeeperState {
     Disconnected (0),
     SyncConnected (3),
     AuthFailed (4),
     ConnectedReadOnly (5),
     SaslAuthenticated(6),
     Expired (-112);
}
enum EventType {
    None (-1),
    NodeCreated (1),
    NodeDeleted (2),
    NodeDataChanged (3),
    NodeChildrenChanged (4);
}

Watcher +-- Event
Event +-- KeeperState
Event +-- EventType

@enduml
```