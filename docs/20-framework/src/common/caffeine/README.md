`com.github.ben-manes.caffeine:caffeine:2.8.4`

- cache
  - [AsyncCache](/docs/20-framework/src/common/caffeine/cache/AsyncCache.md)
  - [AsyncCacheLoader](/docs/20-framework/src/common/caffeine/cache/AsyncCacheLoader.md)
  - [BoundedLocalCache](/docs/20-framework/src/common/caffeine/cache/BoundedLocalCache.md)
  - [LocalLoadingCache](/docs/20-framework/src/common/caffeine/cache/LocalLoadingCache.md)

## package
com.github.benmanes.caffeine
```
base
    UnsafeAccess
cache
    status
        CacheStats
        ConcurrentStatsCounter
        DisabledStatsCounter
        GuardedStatsCounter
        StatsCounter
    AsyncCache
    AsyncCacheLoader
    AsyncLoadingCache
    Buffer
    BoundedLocalCache
    Cache
    CacheLoader
    CacheWriter
    LoadingCache
    LocalAsyncCache
    LocalCache
    LocalCacheFactory
    LocalLoadingCache
    LocalManualCache
SingleConsumerQueue    
```

## stack
```
lambda$new$0:69, CacheController (com.onion.spring.demo.web.controller.demo)
load:-1, 7064297 (com.onion.spring.demo.web.controller.demo.CacheController$$Lambda$526)

lambda$new$0:3670, BoundedLocalCache$BoundedLocalLoadingCache (com.github.benmanes.caffeine.cache)
apply:-1, 529026006 (com.github.benmanes.caffeine.cache.BoundedLocalCache$BoundedLocalLoadingCache$$Lambda$528)
lambda$doComputeIfAbsent$14:2337, BoundedLocalCache (com.github.benmanes.caffeine.cache)
apply:-1, 985654957 (com.github.benmanes.caffeine.cache.BoundedLocalCache$$Lambda$739)

compute:1853, ConcurrentHashMap (java.util.concurrent)
doComputeIfAbsent:2335, BoundedLocalCache (com.github.benmanes.caffeine.cache)
computeIfAbsent:2318, BoundedLocalCache (com.github.benmanes.caffeine.cache)
computeIfAbsent:111, LocalCache (com.github.benmanes.caffeine.cache)
get:66, LocalLoadingCache (com.github.benmanes.caffeine.cache)
caffeineCache:82, CacheController (com.onion.spring.demo.web.controller.demo)
```