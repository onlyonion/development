seata-all-0.7.1.jar

- Coordinator Core 事务协调器
- Store file, mysql 存储，数据持久化
- Discover macos, zk, redis, eruka 服务注册/发现模块，用于将Server地址暴露给Client
- Config file, macos, zk, zppollo 存储和查找服务端的配置
- Lock 给seata提供全局锁
- RPC netty
- HA-Cluster

## package
io.seata
```
codec
    protobuf
    seata
common
    exception
    executor
    loader
    thread
    util
    Constants
    XID
config
    apollo
    consul
    etcd3
    nacos
    zk
    AbstractConfiguration
    ConfigChangeListener
    ConfigFuture
    ConfigType
    Configuration
    ConfigurationFactory
    ConfigurationKeys
    ConfigurationProvider
    FileConfiguration
core
    codec
    constants
    context
    event
    exception
    lock
    model
    protocol
    rpc
    store
discovery
    loadbalance
    registry
        consul
        etcd3
        eureka
        nacos
        redis
        sofa
        zk
        FileRegistryServiceImpl
        RegistryFactory
        RegistryProvider
        RegistryService
        RegistryType
integration
    dubbo
        alibaba
        TransactionPropagationFilter
    motan
        MotanTransactionFilter
    rpc
        TransactionContextConsumerFilter
        TransactionContextProviderFilter
rm
    datasource
        exec
        sql
        undo
    tcc
        api
        interceptor
        remoting
        RMHandlerTCC
        TCCResource
        TCCResourceManager
        TwoPhaseResult
    AbstractResourceManager
    AbstractRMHandler
    DefaultResourceManager
    DefaultRMHandler
    GlobalLockTemplate
    RMClient
    RMHandlerAT
spring
    annotation
        GlobalLock
        GlobalTransactional
        GlobalTransactionalInterceptor
        GlobalTransactionScanner
        MethodDesc
    tcc
        TccActionInterceptor
    util
        SpringProxyUtils
        TCCBeanParserUtils
tm
    api
        transaction
            NoRollbackRule
            RollbackRule
            TransactionHook
            TransactionHookAdapter
            TransactionHookManager
            TransactionInfo
        DefaultFailureHandlerImpl
        DefaultGlobalTransaction
        FailureHandler
        GlobalTransaction
        GlobalTransactionContext
        GlobalTransactionRole
        TransactionalExecutor
        TransactionalTemplate
    DefaultTransactionManager
    TMClient
    TransactionManagerHolder
```