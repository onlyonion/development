org.apache.ibatis.session.SqlSession

SqlSession -> [Executor](/20-framework/src/mybatis/mybatis/executor/Executor.md) -> StatementHandler -> ParameterHanlder -> 
原生JDBC -> 原生Statement -> 原生ResultSet
TypeHandler -> ResultSetHandler -> StatementHandler

## hierarchy
```
SqlSession (org.apache.ibatis.session)
    SqlSessionManager (org.apache.ibatis.session)
    SqlSessionTemplate (org.mybatis.spring)
    DefaultSqlSession (org.apache.ibatis.session.defaults)
```

## define

```plantuml
@startuml

SqlSession <|.. SqlSessionTemplate
SqlSession <|.. SqlSessionManager
SqlSession <|.. DefaultSqlSession

interface SqlSession {
    + Configuration getConfiguration();
    + <T> T getMapper(Class<T> type);
    + Connection getConnection();
}

class SqlSessionTemplate #orange
SqlSessionTemplate *-- SqlSessionFactory

class DefaultSqlSession #orange {
    - Configuration configuration
    - Executor executor
    - boolean autoCommit
    - boolean dirty
    - List<Cursor<?>> cursorList
}

DefaultSqlSession *-- Executor

interface InvocationHandler
InvocationHandler <|.. SqlSessionInterceptor
SqlSessionTemplate +-- SqlSessionInterceptor

@enduml
```

## methods
* selectOne
* selectList
* selectMap
* selectCursor
* select
* insert
* update
* delete
* commit
* rollback
* flushStatements
* getConfiguration
* getMapper
* getConnection