org.redisson.api.RLock
## hierarchy
```
RLock (org.redisson.api)
    RedissonWriteLock (org.redisson)
    RedissonLock (org.redisson)
        RedissonWriteLock (org.redisson)
        RedissonTransactionalLock (org.redisson.transaction)
        RedissonReadLock (org.redisson)
        RedissonFairLock (org.redisson)
    RedissonReadLock (org.redisson)
    RedissonFairLock (org.redisson)
    RedissonMultiLock (org.redisson)
        RedissonRedLock (org.redisson)
RLock (org.redisson.api)
    Lock (java.util.concurrent.locks)
    RLockAsync (org.redisson.api)
```

## define
```java
public interface RLock extends Lock, RLockAsync {
}
```

## fields


## methods

```java

// Lock
boolean tryLock(long time, TimeUnit unit) throws InterruptedException;

// RLock
boolean tryLock(long waitTime, long leaseTime, TimeUnit unit) throws InterruptedException;

// RLock
void lock(long leaseTime, TimeUnit unit);

```