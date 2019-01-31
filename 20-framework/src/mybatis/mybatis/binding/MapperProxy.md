
## define
```java
public class MapperProxy<T> implements InvocationHandler, Serializable {
  private final SqlSession sqlSession;
  private final Class<T> mapperInterface;
  private final Map<Method, MapperMethod> methodCache;
}
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