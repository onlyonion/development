com.alibaba.dubbo.rpc.cluster.Router

## hierarchy
```
Router (com.alibaba.dubbo.rpc.cluster)
    MockInvokersSelector (com.alibaba.dubbo.rpc.cluster.router)
    ConditionRouter (com.alibaba.dubbo.rpc.cluster.router.condition)
    ScriptRouter (com.alibaba.dubbo.rpc.cluster.router.script)
```

## define
```plantuml
@startuml

interface Router {
    URL getUrl()
    <T> List<Invoker<T>> route(List<Invoker<T>> invokers, URL url, Invocation invocation)
}

@enduml
```