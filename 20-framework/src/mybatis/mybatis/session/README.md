
## package
```
defaults
    DefaultSqlSession
    DefaultSqlSessionFactory
AutoMappingBehavior
AutoMappingUnknownColumnBehavior
Configuration
ExecutorType
LocalCacheScope
ResultContext
ResultHandler
RowBounds
SqlSession
SqlSessionException
SqlSessionFactory
SqlSessionFactoryBuilder
SqlSessionManager
TransactionIsolationLevel
```

## overview

```plantuml
@startuml

''''''''''''''''' 工厂接口 '''''''''''''''''''
interface SqlSessionFactory {
    + SqlSession openSession()
    + Configuration getConfiguration()
}
SqlSessionFactory -.-> ExecutorType
SqlSessionFactory -.-> TransactionIsolationLevel

''''''''''''''''' 工厂默认实现 '''''''''''''''''''
class DefaultSqlSessionFactory
SqlSessionFactory <|-.- DefaultSqlSessionFactory

''''''''''''''''' 工厂产品接口 '''''''''''''''''''
interface SqlSession
SqlSessionFactory -.-> SqlSession

''''''''''''''''' 工厂产品默认实现 '''''''''''''''''''
class DefaultSqlSession {
    - final Configuration configuration
    - final Executor executor
}
SqlSession <|-.- DefaultSqlSession

enum ExecutorType {
  SIMPLE, REUSE, BATCH
}

enum TransactionIsolationLevel {
  NONE(Connection.TRANSACTION_NONE),
  READ_UNCOMMITTED(Connection.TRANSACTION_READ_UNCOMMITTED),
  READ_COMMITTED(Connection.TRANSACTION_READ_COMMITTED),
  REPEATABLE_READ(Connection.TRANSACTION_REPEATABLE_READ),
  SERIALIZABLE(Connection.TRANSACTION_SERIALIZABLE);
}

@enduml
```