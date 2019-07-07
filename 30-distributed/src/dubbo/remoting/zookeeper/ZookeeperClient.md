com.alibaba.dubbo.remoting.zookeeper.ZookeeperClient

## hierarchy
```
ZookeeperClient (com.alibaba.dubbo.remoting.zookeeper)
    AbstractZookeeperClient (com.alibaba.dubbo.remoting.zookeeper.support)
        ZkclientZookeeperClient (com.alibaba.dubbo.remoting.zookeeper.zkclient)
        CuratorZookeeperClient (com.alibaba.dubbo.remoting.zookeeper.curator)
```

## define
```plantuml
@startuml

interface ZookeeperClient
abstract class AbstractZookeeperClient<TargetChildListener>
class ZkclientZookeeperClient #orange
class CuratorZookeeperClient #orange

ZkclientZookeeperClient o-- ZkClient
CuratorZookeeperClient o-- CuratorFramework 

ZookeeperClient ^.. AbstractZookeeperClient
AbstractZookeeperClient ^-- CuratorZookeeperClient
AbstractZookeeperClient ^-- ZkclientZookeeperClient

@enduml
```