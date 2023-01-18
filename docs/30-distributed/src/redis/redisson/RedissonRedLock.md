org.redisson.RedissonRedLock

## hierarchy
```
RedissonMultiLock (org.redisson)
    RedissonRedLock (org.redisson)
```

## methods
```java
    @Override
    protected int failedLocksLimit() {
        return locks.size() - minLocksAmount(locks);
    }
    
    protected int minLocksAmount(final List<RLock> locks) {
        return locks.size()/2 + 1;
    }
```