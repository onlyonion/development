org.springframework.cache.interceptor.CacheResolver

## hierarchy
```
CacheResolver (org.springframework.cache.interceptor)
    AbstractCacheResolver (org.springframework.cache.interceptor)
        SimpleCacheResolver (org.springframework.cache.interceptor)
        NamedCacheResolver (org.springframework.cache.interceptor)
```

## define
```plantuml
@startuml

interface CacheResolver {
    + Collection<? extends Cache> resolveCaches(CacheOperationInvocationContext<?> context)
}
CacheResolver ^.. AbstractCacheResolver

abstract class AbstractCacheResolver {
    - CacheManager cacheManager
    # abstract Collection<String> getCacheNames(CacheOperationInvocationContext<?> context)
}

interface CacheManager
AbstractCacheResolver o-- CacheManager

''''''''''''''''''''''''' 两种缓存解析器 '''''''''''''''''''''''''
AbstractCacheResolver ^-- SimpleCacheResolver
AbstractCacheResolver ^-- NamedCacheResolver

class SimpleCacheResolver {

}

class NamedCacheResolver {

}

@enduml
```