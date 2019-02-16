## spring-tx
* dao
* jca
* transaction
  * [PlatformTransactionManager](/20-framework/src/spring/spring-tx/transaction/PlatformTransactionManager.md)
  * [TransactionDefinition](/20-framework/src/spring/spring-tx/transaction/TransactionDefinition.md)
  * [TransactionInterceptor](/20-framework/src/spring/spring-tx/transaction/TransactionInterceptor.md)
  * [TransactionProxyFactoryBean](/20-framework/src/spring/spring-tx/transaction/TransactionProxyFactoryBean.md)

## package
org.springframework
```
dao
jca
    cci
    context
    endpoint
    support
    work
transaction
    annotation
        Isolation 4种隔离级别
        Propagation 7种传播范围
    config
    event
    interceptor
        TransactionInterceptor
        TransactionProxyFactoryBean
    jta
    support
        TransactionCallback
        TransactionTemplate
    PlatformTransactionManager
    SavepointManager
    TransactionDefinition
    TransactionStatus
```
