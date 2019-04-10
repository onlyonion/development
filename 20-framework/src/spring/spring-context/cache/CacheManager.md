org.springframework.cache.CacheManager

## hierarchy
```
CacheManager (org.springframework.cache)
    GuavaCacheManager (org.springframework.cache.guava)
    AbstractCacheManager (org.springframework.cache.support)
        SimpleCacheManager (org.springframework.cache.support)
        AbstractTransactionSupportingCacheManager (org.springframework.cache.transaction)
            JCacheCacheManager (org.springframework.cache.jcache)
            EhCacheCacheManager (org.springframework.cache.ehcache)
            RedisCacheManager (org.springframework.data.redis.cache)
    CompositeCacheManager (org.springframework.cache.support)
    CaffeineCacheManager (org.springframework.cache.caffeine)
    NoOpCacheManager (org.springframework.cache.support)
    TransactionAwareCacheManagerProxy (org.springframework.cache.transaction)
    ConcurrentMapCacheManager (org.springframework.cache.concurrent)
```