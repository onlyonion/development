## spring-tx
* dao
* jca
* transaction
  * [PlatformTransactionManager](/20-framework/src/spring/spring-tx/transaction/PlatformTransactionManager.md)
  * [TransactionDefinition](/20-framework/src/spring/spring-tx/transaction/TransactionDefinition.md)
  * [TransactionInterceptor](/20-framework/src/spring/spring-tx/transaction/interceptor/TransactionInterceptor.md)
  * [TransactionProxyFactoryBean](/20-framework/src/spring/spring-tx/transaction/interceptor/TransactionProxyFactoryBean.md)

## package
org.springframework
```
dao
    support
        DaoSupport
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
        Transactional
        TransactionAnnotationParser
    config
    event
    interceptor
        TransactionInterceptor
        TransactionProxyFactoryBean
    jta
        JtaAfterCompletionSynchronization
        JtaTransactionManager
        JtaTransactionObject
        ManagedTransactionAdapter
        SimpleTransactionFactory
        SpringJtaSynchronizationAdapter
        TransactionFactory
        UserTransactionAdapter
        WebLogicJtaTransactionManager
        WebSphereUowTransactionManager
    support
        AbstractPlatformTransactionManager
        AbstractTransactionStatus
        ResourceHolder
        ResourceTransactionManager
        TransactionCallback
        TransactionSynchronization
        TransactionOperations
        TransactionTemplate
    PlatformTransactionManager
    SavepointManager
    TransactionDefinition
    TransactionStatus
```

## overview
* TransactionProxyFactoryBean
  * Pointcut
  * TransactionInterceptor extends TransactionAspectSupport
    * TransactionAttributeSource
    * PlatformTransactionManager
      * HibernateTransactionManager
      * JpaTransactionManager
      * DataSourceTransactionManager
* TransactionTemplate
  * PlatformTransactionManager
* TransactionAttributeSourceAdvisor
  * TransactionInterceptor
  
```plantuml
@startuml

'''''''''''''''''''''''''Advice'''''''''''''''''''''''''
TransactionProxyFactoryBean o-- TransactionInterceptor
TransactionProxyFactoryBean o-- Pointcut

'''''''''''''''''''''''''事务切面'''''''''''''''''''''''''
BeanFactoryAware ^.. TransactionAspectSupport
InitializingBean ^.. TransactionAspectSupport
abstract class TransactionAspectSupport #orange

TransactionAspectSupport o-- PlatformTransactionManager
TransactionAspectSupport o-- TransactionAttributeSource

class TransactionAttributeSourceAdvisor
TransactionAttributeSourceAdvisor o-- TransactionInterceptor

'''''''''''''''''''''''''事务管理器'''''''''''''''''''''''''
interface PlatformTransactionManager #orange

interface SavepointManager
interface Flushable
SavepointManager ^-- TransactionStatus
Flushable ^-- TransactionStatus
interface TransactionStatus
PlatformTransactionManager o-- TransactionStatus
PlatformTransactionManager ..>TransactionDefinition

PlatformTransactionManager ^.. AbstractPlatformTransactionManager
abstract class AbstractPlatformTransactionManager

AbstractPlatformTransactionManager ^-- HibernateTransactionManager
AbstractPlatformTransactionManager ^-- JpaTransactionManager
AbstractPlatformTransactionManager ^-- DataSourceTransactionManager
AbstractPlatformTransactionManager ^-- JdoTransactionManager

class DataSourceTransactionManager
DataSourceTransactionManager o-- DataSource

'''''''''''''''''''''''''数据源'''''''''''''''''''''''''
interface DataSource
DataSource ^.. DruidAbstractDataSource
abstract class DruidAbstractDataSource
DruidAbstractDataSource ^-- DruidDataSource

'''''''''''''''''''''''''事务定义'''''''''''''''''''''''''
interface TransactionDefinition
note left: 事务隔离级别、传播范围
TransactionDefinition ^.. DefaultTransactionDefinition

DefaultTransactionDefinition ^-- TransactionTemplate
TransactionOperations ^.. TransactionTemplate
InitializingBean ^.. TransactionTemplate
TransactionTemplate o-- PlatformTransactionManager

'''''''''''''''''''''''''事务拦截器'''''''''''''''''''''''''
TransactionAspectSupport ^-- TransactionInterceptor
MethodInterceptor ^.. TransactionInterceptor
class TransactionInterceptor #orange

'''''''''''''''''''''''''Proxy'''''''''''''''''''''''''
package spring-aop {
    class ProxyConfig
    
    ProxyConfig ^-- AbstractSingletonProxyFactoryBean
    FactoryBean ^.. AbstractSingletonProxyFactoryBean
    BeanClassLoaderAware ^.. AbstractSingletonProxyFactoryBean
    InitializingBean ^.. AbstractSingletonProxyFactoryBean
    abstract class AbstractSingletonProxyFactoryBean
    AbstractSingletonProxyFactoryBean o-- AdvisorAdapterRegistry
    
    ProxyConfig ^-- AdvisedSupport
    Advised ^.. AdvisedSupport
    AdvisedSupport ^-- ProxyCreatorSupport
    ProxyCreatorSupport ^-- ProxyFactory  
    AbstractSingletonProxyFactoryBean ..> ProxyFactory
    
    
    interface AdvisorAdapterRegistry
    AdvisorAdapterRegistry ^.. DefaultAdvisorAdapterRegistry
    DefaultAdvisorAdapterRegistry "1" o-- "*" AdvisorAdapter
    
    interface AdvisorAdapter
    AdvisorAdapter ^.. MethodBeforeAdviceAdapter
    AdvisorAdapter ^.. ThrowsAdviceAdapter
    AdvisorAdapter ^.. AfterReturningAdviceAdapter
}

AbstractSingletonProxyFactoryBean ^-- TransactionProxyFactoryBean
BeanFactoryAware ^.. TransactionProxyFactoryBean


@enduml
```