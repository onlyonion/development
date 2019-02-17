org.apache.ibatis.session.SqlSession

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

interface SqlSession {
    + Configuration getConfiguration();
    + <T> T getMapper(Class<T> type);
    + Connection getConnection();
}

class SqlSessionTemplate

SqlSession <|.. SqlSessionTemplate
SqlSession <|.. SqlSessionManager
SqlSession <|.. DefaultSqlSession

interface InvocationHandler
InvocationHandler <|.. SqlSessionInterceptor
SqlSessionTemplate +-- SqlSessionInterceptor

SqlSession o-  Configuration
SqlSession o-  Connection

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