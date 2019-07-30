com.alibaba.dubbo.cache

## package
```
filter
    CacheFilter
support
    jcache
        JCache                          javax.cache.Cache
        JCacheFactory
    lru                                 LinkedHashMap实现
        LruCache
        LruCacheFactory
    threadlocal                         ThreadLocal缓存
        ThreadLocalCache
        ThreadLocalCacheFactory
        AbstractCacheFactory
Cache
CacheFactory
```

## overview

```java

public interface Cache {
    void put(Object key, Object value);
    Object get(Object key);

}

@SPI("lru")
public interface CacheFactory {
    @Adaptive("cache")
    Cache getCache(URL url);
}
```



