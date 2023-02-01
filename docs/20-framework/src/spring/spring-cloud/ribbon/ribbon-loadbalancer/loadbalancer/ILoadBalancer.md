com.netflix.loadbalancer.ILoadBalancer
## hierarchy
```
ILoadBalancer (com.netflix.loadbalancer)
    AbstractLoadBalancer (com.netflix.loadbalancer)
        NoOpLoadBalancer (com.netflix.loadbalancer)
        BaseLoadBalancer (com.netflix.loadbalancer)
            DynamicServerListLoadBalancer (com.netflix.loadbalancer)
                ZoneAwareLoadBalancer (com.netflix.loadbalancer)
```

```plantuml
@startuml

''''''''''''''''''''''''''''''''''ILoadBalancer''''''''''''''''''''''''''''''''''
interface ILoadBalancer
abstract class AbstractLoadBalancer

ILoadBalancer ^.. AbstractLoadBalancer
AbstractLoadBalancer ^-- BaseLoadBalancer
BaseLoadBalancer ^-- DynamicServerListLoadBalancer
DynamicServerListLoadBalancer ^-- ZoneAwareLoadBalancer

''''''''''''''''''''''''''''''''''IRule''''''''''''''''''''''''''''''''''
interface IRule
IRule ^.. AbstractLoadBalancerRule
AbstractLoadBalancerRule ^-- ClientConfigEnabledRoundRobinRule
AbstractLoadBalancerRule ^-- RoundRobinRule
AbstractLoadBalancerRule ^-- RandomRule
AbstractLoadBalancerRule ^-- RetryRule

BaseLoadBalancer *- IRule

@enduml
```