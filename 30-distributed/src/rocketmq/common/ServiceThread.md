com.alibaba.rocketmq.common.ServiceThread

## hierarchy
```
ServiceThread (com.alibaba.rocketmq.common)
    PullMessageService (com.alibaba.rocketmq.client.impl.consumer)
    RebalanceService (com.alibaba.rocketmq.client.impl.consumer)
```
## define
```plantuml
@startuml

abstract class ServiceThread
class PullMessageService
class RebalanceService

ServiceThread ^-- PullMessageService
ServiceThread ^-- RebalanceService

@enduml
```