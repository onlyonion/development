org.apache.rocketmq.namesrv

## package
```
kvconfig
    KVConfigManager
    KVConfigSerializeWrapper
processor
    ClusterTestRequestProcessor
    DefaultRequestProcessor
routeinfo
    BrokerHousekeepingService
    BrokerLiveInfo
    RouteInfoManager
NamesrvController
NamesrvStartup
```

## overview
```plantuml
@startuml

class NamesrvStartup
NamesrvStartup ..> NamesrvController

class NamesrvController

class KVConfigManager
class RouteInfoManager

NamesrvController *-- KVConfigManager
NamesrvController *-- RouteInfoManager
NamesrvController *-- NamesrvConfig
NamesrvController *-- BrokerHousekeepingService

interface ChannelEventListener
class BrokerHousekeepingService
ChannelEventListener ^.. BrokerHousekeepingService

@enduml
```