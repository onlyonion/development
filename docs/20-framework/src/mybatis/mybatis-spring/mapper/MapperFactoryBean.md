org.mybatis.spring.mapper.MapperFactoryBean

## package
```
DaoSupport (org.springframework.dao.support)
    SqlSessionDaoSupport (org.mybatis.spring.support)
        MapperFactoryBean (org.mybatis.spring.mapper)
```

## define
```plantuml
@startuml

interface InitializingBean
InitializingBean ^.. DaoSupport
abstract class DaoSupport

DaoSupport ^-- SqlSessionDaoSupport
abstract class SqlSessionDaoSupport

interface FactoryBean<T>
SqlSessionDaoSupport ^-- MapperFactoryBean
FactoryBean ^-- MapperFactoryBean
class MapperFactoryBean<T>

@enduml
```