

## 事务隔离级别
隔离级别是指若干个并发的事务之间的隔离程度

1   TransactionDefinition.ISOLATION_DEFAULT
2   TransactionDefinition.ISOLATION_READ_UNCOMMITTED
3   TransactionDefinition.ISOLATION_READ_COMMITTED
4   TransactionDefinition.ISOLATION_REPEATABLE_READ
5   TransactionDefinition.ISOLATION_SERIALIZABLE

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

## 基于 TransactionTemplate 的编程式事务管理
Spring 在数据访问层非常常见的模板回调模式

## 基于 TransactionInterceptor 的声明式事务管理  @Deprecated

## 基于 TransactionProxyFactoryBean 的声明式事务管理  @Deprecated

## 基于 <tx> 和 <aop>  命名空间的声明式事务管理

## 基于 @Transactional 的声明式事务管理