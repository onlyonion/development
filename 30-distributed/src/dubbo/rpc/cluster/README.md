
集群、目录、路由、负载均衡

## package
```
cluster
    configurator
    directory
    loadbalance
        AbstractLoadBalance
        ConsistentHashLoadBalance
        LeastActiveLoadBalance
        RandomLoadBalance
        RoundRobinLoadBalance
    merger
    router
        condition
            ConditionRouter
            ConditionRouterFactory
        file
            FileRouterFactory
        script
            ScriptRouter
            ScriptRouterFactory
        MockInvokersSelector
    support
    Cluster
    Configurator
    ConfiguratorFactory
    Directory
    LoadBalance
    Merger
    Router
    RouterFactory
    RuleConverter
```