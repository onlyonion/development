org.springframework.dao.support.DaoSupport

## hierarchy
```
DaoSupport (org.springframework.dao.support)
    CciDaoSupport (org.springframework.jca.cci.core.support)
    SqlSessionDaoSupport (org.mybatis.spring.support)
        MapperFactoryBean (org.mybatis.spring.mapper)
```

## define
```plantuml
@startuml

abstract class DaoSupport {
    # abstract void checkDaoConfig()
    # void initDao()
}

interface InitializingBean

InitializingBean <|.. DaoSupport

@enduml
```
