com.google.common.cache.Cache

## Hierarchy
```
Cache (com.google.common.cache)
    ForwardingCache (com.google.common.cache)
    LoadingCache (com.google.common.cache)
        LocalLoadingCache in LocalCache (com.google.common.cache)
        LoadingSerializationProxy in LocalCache (com.google.common.cache)
        ForwardingLoadingCache (com.google.common.cache)
        AbstractLoadingCache (com.google.common.cache)
    LocalManualCache in LocalCache (com.google.common.cache)
        LocalLoadingCache in LocalCache (com.google.common.cache)
    AbstractCache (com.google.common.cache)
        AbstractLoadingCache (com.google.common.cache)
```

## Define
```plantuml
@startuml

''''''''''''''''''''缓存定义''''''''''''''''''''
interface Cache<K, V>
interface Function<F, T>
interface LoadingCache<K, V> #orange

Cache ^-- LoadingCache
Function ^-- LoadingCache


''''''''''''''''''''缓存实现''''''''''''''''''''
abstract class AbstractCache<K, V> 
abstract class AbstractLoadingCache<K, V>

Cache ^.. AbstractCache
AbstractCache ^-- AbstractLoadingCache
LoadingCache ^.. AbstractLoadingCache

class LocalManualCache<K, V>
class LocalLoadingCache<K, V> #orange

Cache ^.. LocalManualCache

LocalManualCache ^-- LocalLoadingCache
LoadingCache ^.. LocalLoadingCache
LocalManualCache *-- LocalCache

''''''''''''''''''''本地缓存实现''''''''''''''''''''

class LocalCache<K, V> #pink
abstract class AbstractMap<K, V> #pink
interface ConcurrentMap<K, V> #pink

AbstractMap ^-- LocalCache
ConcurrentMap ^.. LocalCache

class Segment<K, V>
LocalCache *-- Segment
ReentrantLock ^-- Segment

Segment *-- LocalCache

@enduml
```