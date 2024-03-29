org.apache.ibatis.binding.MapperProxy

## define

```plantuml
@startuml

'''''''''''''''''''''MapperRegistry'''''''''''''''''''''
class MapperRegistry
MapperRegistry *-- Configuration
MapperRegistry "1" *-- "*" MapperProxyFactory

'''''''''''''''''''''MapperProxyFactory'''''''''''''''''''''
class MapperProxyFactory<T> {
    - final Class<T> mapperInterface
    - final Map<Method, MapperMethod> methodCache
}
MapperProxyFactory "1" o-- "*" MapperMethod
MapperProxyFactory ..> MapperProxy

'''''''''''''''''''''MapperProxy'''''''''''''''''''''
class MapperProxy<T> #orange {
    - final SqlSession sqlSession
    - final Class<T> mapperInterface
    - final Map<Method, MapperMethod> methodCache
}
MapperProxy "1" o-- "*" MapperMethod
interface InvocationHandler
InvocationHandler ^.. MapperProxy

@enduml
```

## invoke

```mermaid
sequenceDiagram
    Proxy ->> MapperProxy: invoke()
    %% 特殊方法处理 Object方法，jdk8接口默认方法
    
    MapperProxy ->> MapperProxy: cachedMapperMethod(method)
    
    opt 缓存未命中
        MapperProxy ->> MapperMethod: 创建MapperMethod
    end
    
    %% 执行映射方法
    MapperProxy ->> MapperMethod: execute(sqlSession, args)
```