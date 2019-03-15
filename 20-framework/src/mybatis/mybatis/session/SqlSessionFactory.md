org.apache.ibatis.session.SqlSessionFactory

## hierarchy
```
SqlSessionFactory (org.apache.ibatis.session)
    DefaultSqlSessionFactory (org.apache.ibatis.session.defaults)
    SqlSessionManager (org.apache.ibatis.session)
```

## define

```plantuml
@startuml

interface SqlSessionFactory {
    + SqlSession openSession(ExecutorType execType, TransactionIsolationLevel level)
    + Configuration getConfiguration()
}

class DefaultSqlSessionFactory {
    - SqlSession openSessionFromDataSource(ExecutorType execType, TransactionIsolationLevel level, boolean autoCommit)
}
SqlSessionFactory ^.. DefaultSqlSessionFactory

enum ExecutorType {
  SIMPLE, REUSE, BATCH
}
SqlSessionFactory ..> ExecutorType

@enduml
```

## openSessionFromDataSource
```plantuml
@startuml
actor Actor

Actor -> DefaultSqlSessionFactory: openSession()
DefaultSqlSessionFactory --> DefaultSqlSessionFactory: openSessionFromDataSource()
DefaultSqlSessionFactory --> Configuration: getEnvironment()
DefaultSqlSessionFactory --> DefaultSqlSessionFactory: getTransactionFactoryFromEnvironment()

DefaultSqlSessionFactory --> TransactionFactory: newTransaction()
DefaultSqlSessionFactory --> Configuration: newExecutor()
DefaultSqlSessionFactory --> Actor: new DefaultSqlSession()

@enduml
```