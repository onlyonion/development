org.apache.shiro.cache

## Overview
```plantuml
@startuml

interface Cache<K, V> #orange
interface CacheManager #yellow
CacheManager ..> Cache

class MapCache<K, V> 
Cache ^.. MapCache

abstract class AbstractCacheManager
class MemoryConstrainedCacheManager
CacheManager ^.. AbstractCacheManager
AbstractCacheManager ^-- MemoryConstrainedCacheManager

interface CacheManagerAware
CacheManagerAware ..> CacheManager

@enduml
```