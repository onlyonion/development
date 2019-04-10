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

interface CacheResolver 
CacheResolver ^.. AbstractCacheResolver

abstract class AbstractCacheResolver {
    - CacheManager cacheManager
}

interface CacheManager
AbstractCacheResolver o-- CacheManager

AbstractCacheResolver ^-- SimpleCacheResolver
AbstractCacheResolver ^-- NamedCacheResolver

class SimpleCacheResolver
class NamedCacheResolver

@enduml
```