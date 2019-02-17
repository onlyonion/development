org.mybatis.spring.SqlSessionTemplate

## define
```plantuml
@startuml

interface SqlSession
interface DisposableBean

class SqlSessionTemplate {
    - final SqlSessionFactory sqlSessionFactory
    - final ExecutorType executorType
    .. 使用会话代理 ..
    - final SqlSession sqlSessionProxy
    - final PersistenceExceptionTranslator exceptionTranslator
}

SqlSession <|.. SqlSessionTemplate
DisposableBean <|.. SqlSessionTemplate


class SqlSessionInterceptor {

}

InvocationHandler <|.. SqlSessionInterceptor
SqlSessionTemplate +- SqlSessionInterceptor

enum ExecutorType {
  SIMPLE, REUSE, BATCH
}

@enduml
```

## invoke

```
// spring-mybatis
invoke:433, SqlSessionTemplate$SqlSessionInterceptor (org.mybatis.spring)
selectList:-1, $Proxy91 (com.sun.proxy)
selectList:230, SqlSessionTemplate (org.mybatis.spring)

// MapperProxy
executeForMany:137, MapperMethod (org.apache.ibatis.binding)
execute:75, MapperMethod (org.apache.ibatis.binding)
invoke:59, MapperProxy (org.apache.ibatis.binding)
selectList:-1, $Proxy92 (com.sun.proxy)
```