org.apache.zookeeper.ClientCnxn

* CopyOnWriteArraySet
* LinkedBlockingQueue

## Define

```plantuml
@startuml

''''''''''''''''''''''''ZooKeeper'''''''''''''''''''''''''''
class ZooKeeper
ZooKeeper *- ClientCnxn
ZooKeeper *-- ZKWatchManager


''''''''''''''''''''''''ClientCnxn'''''''''''''''''''''''''''
class ClientCnxn

ClientCnxn *-- ZooKeeper
ClientCnxn *-- HostProvider
ClientCnxn *-- ClientWatchManager
ClientCnxn *-- SendThread
ClientCnxn *-- EventThread


''''''''''''''''''''''''ClientWatchManager'''''''''''''''''''''''''''
interface ClientWatchManager #orange
ClientWatchManager ^.. ZKWatchManager


''''''''''''''''''''''''HostProvider'''''''''''''''''''''''''''
interface HostProvider #orange
class StaticHostProvider
HostProvider ^.. StaticHostProvider


class ZooKeeperThread
class SendThread #yellow
class EventThread #yellow {
    - void processEvent(Object event)
}

ZooKeeperThread ^-- SendThread
ZooKeeperThread ^-- EventThread

''''''''''''''''''''''''SendThread'''''''''''''''''''''''''''
abstract class ClientCnxnSocket
class ClientCnxnSocketNIO #yellow

SendThread *-- ClientCnxnSocket
ClientCnxnSocket ^-- ClientCnxnSocketNIO

@enduml
```

## Methods

### ClientCnxn
- ZooKeeperThread
  - SendThread
  - EventThread

```java
    public ClientCnxn(String chrootPath, HostProvider hostProvider, int sessionTimeout, ZooKeeper zooKeeper,
            ClientWatchManager watcher, ClientCnxnSocket clientCnxnSocket,
            long sessionId, byte[] sessionPasswd, boolean canBeReadOnly) {
        this.zooKeeper = zooKeeper;
        this.watcher = watcher;
        this.sessionId = sessionId;
        this.sessionPasswd = sessionPasswd;
        this.sessionTimeout = sessionTimeout;
        this.hostProvider = hostProvider;
        this.chrootPath = chrootPath;

        connectTimeout = sessionTimeout / hostProvider.size();
        readTimeout = sessionTimeout * 2 / 3;
        readOnly = canBeReadOnly;

        sendThread = new SendThread(clientCnxnSocket);
        eventThread = new EventThread();

    }
```

