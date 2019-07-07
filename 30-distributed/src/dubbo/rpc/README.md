
## rpc
* 应用层协议
* 集群 配置、容错、目录、路由、负载均衡
* 动态代理 方法参数、返回值、方法调用
* 过滤器
* 事件监听

## content
* Exporter 导出者
* ExporterListener
* Filter 过滤器
* Invocation 调用器，封装方法名、参数、类型
* Invoker 调用者
* InvokerListener
* Protocol 网络传输协议
* ProxyFactory 动态代理工厂
* Result 封装了结果、异常、附加属性等

## package

```
cluster
    configurator
    directory
    loadbalance  负载均衡
        AbstractLoadBalance
        ConsistentHashLoadBalance   一致性哈希
        LeastActiveLoadBalance      最小活跃数
        RandomLoadBalance           加权随机
        RoundRobinLoadBalance       轮询
    merger
    router  路由选择 根据脚本或者条件
        condition                   
        file
        script
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
filter
    tps
    AccessLogFilter
    ActiveLimitFilter
    ClassLoaderFilter
    CompatibleFilter
    ConsumerContextFilter
    ContextFilter
    DeprecatedFilter
    EchoFilter
    ExceptionFilter
    ExecuteLimitFilter
    GenericFilter
    GenericImplFilter
    TimeoutFilter
    TokenFilter
    TpsLimitFilter
listener
    DeprecatedInvokerListener
    ExporterListenerAdapter
    InvokerListenerAdapter
    ListenerExporterWrapper
    ListenerInvokerWrapper
protocol
    dubbo
    hessian
    http
    injvm
    memcached
    redis
    rmi
    thrift
    webservice
    AbstractExporter
    AbstractInvoker
    AbstractProtocol
    AbstractProxyProtocol
    InvokerWrapper
    ProtocolFilterWrapper
    ProtocolListenerWrapper
proxy
    javassist
    jdk
    wrapper
    AbstractProxyFactory
    AbstractProxyInvoker
    InvokerInvocationHandler
service
    EchoService
    GenericException
    GenericService
support
    DelegateExporter
    DelegateInvoker
    MockInvoker
    MockProtocol
    ProtocolUtils
    RpcUtils
Exporter
ExporterListener
Filter
Invocation
Invoker
InvokerListener
Protocol
ProxyFactory
Result
RpcConstants
RpcContext
RpcException
RpcInvocation
RpcResult
RpcStatus
StaticContext
```


