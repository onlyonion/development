com.netflix.hystrix.HystrixCommandProperties

```java
private static final Integer default_executionTimeoutInMilliseconds = 1000; // default => executionTimeoutInMilliseconds: 1000 = 1 second
private static final ExecutionIsolationStrategy default_executionIsolationStrategy = ExecutionIsolationStrategy.THREAD;
```


```java
    public static enum ExecutionIsolationStrategy {
        THREAD, SEMAPHORE
    }
```