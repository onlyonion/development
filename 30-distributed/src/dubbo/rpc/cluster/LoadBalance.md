com.alibaba.dubbo.rpc.cluster.LoadBalance

## hierarchy
```
LoadBalance (com.alibaba.dubbo.rpc.cluster)
    AbstractLoadBalance (com.alibaba.dubbo.rpc.cluster.loadbalance)
        RandomLoadBalance (com.alibaba.dubbo.rpc.cluster.loadbalance)
        LeastActiveLoadBalance (com.alibaba.dubbo.rpc.cluster.loadbalance)
        RoundRobinLoadBalance (com.alibaba.dubbo.rpc.cluster.loadbalance)
        ConsistentHashLoadBalance (com.alibaba.dubbo.rpc.cluster.loadbalance)
```
## define
@startuml

interface LoadBalance
abstract class AbstractLoadBalance
class RandomLoadBalance
class LeastActiveLoadBalance
class RoundRobinLoadBalance
class ConsistentHashLoadBalance

LoadBalance <|-.- AbstractLoadBalance
AbstractLoadBalance <|-- RandomLoadBalance
AbstractLoadBalance <|-- LeastActiveLoadBalance
AbstractLoadBalance <|-- RoundRobinLoadBalance
AbstractLoadBalance <|-- ConsistentHashLoadBalance

interface Cluster

@enduml

@startuml
abstract class AbstractList
abstract AbstractCollection
interface List
interface Collection

List <|-- AbstractList
Collection <|-- AbstractCollection

Collection <|- List
AbstractCollection <|- AbstractList
AbstractList <|-- ArrayList
class ArrayList {
Object[] elementData
size()
}

enum TimeUnit {
DAYS
HOURS
MINUTES
}

@enduml



@startuml
Alice -> Bob: Authentication Request
Bob --> Alice: Authentication Response

Alice -> Bob: Another authentication Request
Alice <-- Bob: another authentication Response
@enduml



@startuml
package "Some Group" {
HTTP - [First Component]
[Another Component]
}

package "Other Groups" {
FTP - [Second Component]

[First Component] --> FTP
}
@enduml