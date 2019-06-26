org.springframework.cache.CacheManager

## hierarchy
```
CacheManager (org.springframework.cache)
    AbstractCacheManager (org.springframework.cache.support)
        SimpleCacheManager (org.springframework.cache.support)
        AbstractTransactionSupportingCacheManager (org.springframework.cache.transaction)
            JCacheCacheManager (org.springframework.cache.jcache)
            EhCacheCacheManager (org.springframework.cache.ehcache)
            RedisCacheManager (org.springframework.data.redis.cache)
    CaffeineCacheManager (org.springframework.cache.caffeine)
    CompositeCacheManager (org.springframework.cache.support)
    ConcurrentMapCacheManager (org.springframework.cache.concurrent)
    GuavaCacheManager (org.springframework.cache.guava)
    NoOpCacheManager (org.springframework.cache.support)
    TransactionAwareCacheManagerProxy (org.springframework.cache.transaction)
```