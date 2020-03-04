org.redisson

## package
```
api
cache
client
cluster
codec
command
config
connection
eviction
executor
jcache
liveobject
mapreduce
misc
pubsub
reactive
remote
rx
spring
transaction
```

```java
@Bean
public Redisson redisson() {
    Config config = new Config();
    config.useSingleServer().setAddress("reids://localhost:6379").setDatabase(0);
    return (Redisson) Redisson.create(config);
}


public void demo() {
    String key = "lock";
    Rlock lock = redisson.getLock(key);
    try {
        lock.lock();
        // 业务逻辑
    } finally {
        lock.unlock();
    }
}

```