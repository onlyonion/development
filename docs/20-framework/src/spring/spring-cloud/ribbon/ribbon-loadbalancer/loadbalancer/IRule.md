com.netflix.loadbalancer.IRule

## hierarchy
```
IRule (com.netflix.loadbalancer)
    AbstractLoadBalancerRule (com.netflix.loadbalancer)
        ClientConfigEnabledRoundRobinRule (com.netflix.loadbalancer)
            BestAvailableRule (com.netflix.loadbalancer)
            PredicateBasedRule (com.netflix.loadbalancer)
                ZoneAvoidanceRule (com.netflix.loadbalancer)
                AvailabilityFilteringRule (com.netflix.loadbalancer)
        RoundRobinRule (com.netflix.loadbalancer)
            WeightedResponseTimeRule (com.netflix.loadbalancer)
            ResponseTimeWeightedRule (com.netflix.loadbalancer)
        RandomRule (com.netflix.loadbalancer)
        RetryRule (com.netflix.loadbalancer)
```