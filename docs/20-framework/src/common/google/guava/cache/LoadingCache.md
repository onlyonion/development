com.google.common.cache.LoadingCache

## hierarchy
```
LoadingCache (com.google.common.cache)
    LocalLoadingCache in LocalCache (com.google.common.cache)
    LoadingSerializationProxy in LocalCache (com.google.common.cache)
    ForwardingLoadingCache (com.google.common.cache)
        SimpleForwardingLoadingCache in ForwardingLoadingCache (com.google.common.cache)
    AbstractLoadingCache (com.google.common.cache)
```

## define
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

```java
public interface LoadingCache<K, V> extends Cache<K, V>, Function<K, V> {
    V get(K key) throws ExecutionException;
    V getUnchecked(K key);
    ImmutableMap<K, V> getAll(Iterable<? extends K> keys) throws ExecutionException;
    V apply(K key);
    void refresh(K key);
    ConcurrentMap<K, V> asMap();
}
```