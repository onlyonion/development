org.apache.tomcat.util.threads.ThreadPoolExecutor

* AtomicInteger AtomicLong

## hierarchy
```
AbstractExecutorService (java.util.concurrent)
    ThreadPoolExecutor (java.util.concurrent)
        ThreadPoolExecutor (org.apache.tomcat.util.threads)
```

## methods

```java
@Override
protected void afterExecute(Runnable r, Throwable t) {
    submittedCount.decrementAndGet();

    if (t == null) {
        stopCurrentThreadIfNeeded();
    }
}
```