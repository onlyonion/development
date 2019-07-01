org.apache.ibatis.cache
## package
```
decorators
    BlockingCache           ReentrantLock
    FifoCache               先进先出
    LoggingCache            记录日志的缓存
    LruCache                近期最少使用
    ScheduledCache          
    SerializedCache
    SoftCache               软引用
    SynchronizedCache       同步的
    TransactionalCache      
    WeakCache               弱引用
impl
    PerpetualCache          KV键值
Cache
CacheException
CacheKey
NullCacheKey
TransactionalCacheManager
```