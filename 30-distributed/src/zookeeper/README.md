org.apache.zookeeper.ZooKeeper.ZooKeeper
org.apache.zookeeper.ClientCnxn


```yuml
// {type:class}


[ZooKeeper]++-[ClientCnxn]

[ClientCnxn]++-[SendThread]

[ClientCnxn]++-[EventThread]

[Thread]^-[ZooKeeperThread]
[ZooKeeperThread]^-[SendThread]
[ZooKeeperThread]^-[EventThread]

```
