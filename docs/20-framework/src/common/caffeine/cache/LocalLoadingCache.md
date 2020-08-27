com.github.benmanes.caffeine.cache.LocalLoadingCache

## Hierarchy
```
LocalLoadingCache (com.github.benmanes.caffeine.cache)
    LocalManualCache (com.github.benmanes.caffeine.cache)
        Cache (com.github.benmanes.caffeine.cache)
    LoadingCache (com.github.benmanes.caffeine.cache)
        Cache (com.github.benmanes.caffeine.cache)
```

## Define
```plantuml
@startuml

'''''''''''''''''缓存定义'''''''''''''''''
interface Cache<K, V>
interface LoadingCache<K, V> #orange
interface LocalManualCache<K, V> 
interface LocalLoadingCache<K, V>  #orange

Cache ^-- LoadingCache
Cache ^-- LocalManualCache
LocalManualCache ^-- LocalLoadingCache
LoadingCache ^-- LocalLoadingCache

'''''''''''''''''缓存实现'''''''''''''''''

class BoundedLocalManualCache<K, V>
class BoundedLocalLoadingCache<K, V> #pink

LocalManualCache ^.. BoundedLocalManualCache
BoundedLocalManualCache ^-- BoundedLocalLoadingCache
LocalLoadingCache ^.. BoundedLocalLoadingCache

@enduml
```

```java
interface LocalLoadingCache<K, V> extends LocalManualCache<K, V>, LoadingCache<K, V> {
    
}
```