org.mybatis.spring.support.SqlSessionDaoSupport

## hierarchy
```
DaoSupport (org.springframework.dao.support)
    SqlSessionDaoSupport (org.mybatis.spring.support)
        MapperFactoryBean (org.mybatis.spring.mapper)
    CciDaoSupport (org.springframework.jca.cci.core.support)
    JdbcDaoSupport (org.springframework.jdbc.core.support)
```

## define
```plantuml
@startuml

'''''''''''''''spring.dao'''''''''''''''''''''
interface InitializingBean
InitializingBean ^.. DaoSupport

abstract class DaoSupport {
    + final void afterPropertiesSet() 
    # abstract void checkDaoConfig()
    # void initDao()
}

DaoSupport ^-- SqlSessionDaoSupport

'''''''''''''''mybatis'''''''''''''''''''''
abstract class SqlSessionDaoSupport {
    - SqlSession sqlSession
    - boolean externalSqlSession
}

SqlSessionDaoSupport o--> SqlSessionTemplate
SqlSessionDaoSupport ..> SqlSessionFactory

'''''''''''''''MapperFactoryBean'''''''''''''''''''''
SqlSessionDaoSupport ^-- MapperFactoryBean
class MapperFactoryBean<T> {

}

@enduml
```