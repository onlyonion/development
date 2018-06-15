## ACID
*	原子性（Atomicity）	 事务的原子性是指事务必须是一个原子的操作序列单元。事务中包含的各项操作在一次执行过程中，要么全部执行，要么全部不执行。
*	一致性（Consistency）事务的一致性是指事务的执行不能破坏数据库数据的完整性和一致性，一个事务在执行前后，数据库都必须处于一致性状态
*	隔离性（Isolation）	 事务的**隔离性**是指在并发环境中，并发的事务是相互隔离的，事务之间互不干扰
*	持久性（Durability）	 事务的持久性又称为永久性，是指一个事务一旦提交，对数据库中对应数据的状态变更就应该是永久性的

## CAP

## 事务隔离级别
隔离级别是指若干个并发的事务之间的隔离程度
事务隔离级别越高，就越能保证数据的完整性和一致性，但同时对并发性能的影响也越大

1   TransactionDefinition.ISOLATION_DEFAULT
2   TransactionDefinition.ISOLATION_READ_UNCOMMITTED	未授权读取
3   TransactionDefinition.ISOLATION_READ_COMMITTED		授权读取
4   TransactionDefinition.ISOLATION_REPEATABLE_READ		可重复读取
5   TransactionDefinition.ISOLATION_SERIALIZABLE		串行化

## 事务传播行为
如果在开始当前事务之前，一个事务上下文已经存在，此时有若干选项可以指定一个事务性方法的执行行为

*   TransactionDefinition.PROPAGATION_REQUIRED
*   TransactionDefinition.PROPAGATION_REQUIRES_NEW
*   TransactionDefinition.PROPAGATION_SUPPORTS
*   TransactionDefinition.PROPAGATION_NOT_SUPPORTED
*   TransactionDefinition.PROPAGATION_NEVER
*   TransactionDefinition.PROPAGATION_MANDATORY
*   TransactionDefinition.PROPAGATION_NESTED

# Spring 事务管理 API
PlatformTransactionManager
TransactionDefinition
TransactionStatus

*	基于 TransactionTemplate 的编程式事务管理 Spring 在数据访问层非常常见的模板回调模式
*	基于 TransactionInterceptor 的声明式事务管理  @Deprecated
*	基于 TransactionProxyFactoryBean 的声明式事务管理  @Deprecated
*	基于 <tx> 和 <aop>  命名空间的声明式事务管理
*	基于 @Transactional 的声明式事务管理

