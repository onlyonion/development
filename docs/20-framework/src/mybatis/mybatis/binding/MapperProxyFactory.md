org.apache.ibatis.binding.MapperProxyFactory

## define
```plantuml
@startuml

'''''''''''''''''''''MapperRegistry'''''''''''''''''''''
class MapperRegistry
MapperRegistry *-- Configuration
MapperRegistry "1" *-- "*" MapperProxyFactory

'''''''''''''''''''''MapperProxyFactory'''''''''''''''''''''
class MapperProxyFactory<T> #orange {
    - final Class<T> mapperInterface
    - final Map<Method, MapperMethod> methodCache
}
MapperProxyFactory "1" o-- "*" MapperMethod
MapperProxyFactory ..> MapperProxy

'''''''''''''''''''''MapperProxy'''''''''''''''''''''
class MapperProxy<T> {
    - final SqlSession sqlSession
    - final Class<T> mapperInterface
    - final Map<Method, MapperMethod> methodCache
}
MapperProxy "1" o-- "*" MapperMethod
interface InvocationHandler
InvocationHandler ^.. MapperProxy

@enduml
```
