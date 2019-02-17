org.mybatis.spring.support.SqlSessionDaoSupport

## hierarchy
```
DaoSupport (org.springframework.dao.support)
    SqlSessionDaoSupport (org.mybatis.spring.support)
        MapperFactoryBean (org.mybatis.spring.mapper)
```

## define
```plantuml
@startuml

abstract class SqlSessionDaoSupport {
    - SqlSession sqlSession
    - boolean externalSqlSession
}

class SqlSessionTemplate

SqlSessionDaoSupport ..> SqlSessionTemplate

@enduml
```