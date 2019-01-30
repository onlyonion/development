
## define
@startuml
interface SqlSession

class SqlSessionTemplate

SqlSession <|.. SqlSessionTemplate
'SqlSession <|.. SqlSessionManager
'SqlSession <|.. DefaultSqlSession

InvocationHandler <|.. SqlSessionInterceptor
SqlSessionTemplate +- SqlSessionInterceptor

SqlSession o--  Configuration
SqlSession o--  Connection



@enduml

## hierarchy
```
SqlSession (org.apache.ibatis.session)
    SqlSessionManager (org.apache.ibatis.session)
    SqlSessionTemplate (org.mybatis.spring)
    DefaultSqlSession (org.apache.ibatis.session.defaults)
```