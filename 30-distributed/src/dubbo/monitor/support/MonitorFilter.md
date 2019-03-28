com.alibaba.dubbo.monitor.support.MonitorFilter

## hierarchy
```
Filter (com.alibaba.dubbo.rpc)
    AccessLogFilter (com.alibaba.dubbo.rpc.filter)
    ActiveLimitFilter (com.alibaba.dubbo.rpc.filter)
    CacheFilter (com.alibaba.dubbo.cache.filter)
    ClassLoaderFilter (com.alibaba.dubbo.rpc.filter)
    CompatibleFilter (com.alibaba.dubbo.rpc.filter)
    ConsumerContextFilter (com.alibaba.dubbo.rpc.filter)
    ContextFilter (com.alibaba.dubbo.rpc.filter)
    DeprecatedFilter (com.alibaba.dubbo.rpc.filter)
    DubboLogTraceFilter (com.weidai.middleware.log.filter)
    EchoFilter (com.alibaba.dubbo.rpc.filter)
    ExceptionFilter (com.alibaba.dubbo.rpc.filter)
    ExecuteLimitFilter (com.alibaba.dubbo.rpc.filter)
    FutureFilter (com.alibaba.dubbo.rpc.protocol.dubbo.filter)
    GenericFilter (com.alibaba.dubbo.rpc.filter)
    GenericImplFilter (com.alibaba.dubbo.rpc.filter)
    MonitorFilter (com.alibaba.dubbo.monitor.support)
    TimeoutFilter (com.alibaba.dubbo.rpc.filter)
    TokenFilter (com.alibaba.dubbo.rpc.filter)
    TpsLimitFilter (com.alibaba.dubbo.rpc.filter)
    TraceFilter (com.alibaba.dubbo.rpc.protocol.dubbo.filter)
    ValidationFilter (com.alibaba.dubbo.validation.filter)
```

## define
```plantuml
@startuml

interface Filter {
    Result invoke(Invoker<?> invoker, Invocation invocation)
}
Filter ^.. MonitorFilter

'''''''''''''''''''监控过滤''''''''''''''''''''''
class MonitorFilter {
    - MonitorFactory monitorFactory
    - void collect(Invoker<?> invoker, Invocation invocation, 
        Result result, String remoteHost, long start, boolean error)
}
MonitorFilter ..> MonitorFactory
MonitorFilter ..> Monitor

'''''''''''''''''''监控工厂''''''''''''''''''''''
interface MonitorFactory {
    @Adaptive("protocol")
    Monitor getMonitor(URL url)
}
MonitorFactory ..> Monitor

'''''''''''''''''''监控服务''''''''''''''''''''''
interface MonitorService {
    + void collect(URL statistics)
    + List<URL> lookup(URL query)
}
MonitorService ^-- Monitor

interface Monitor

@enduml
```