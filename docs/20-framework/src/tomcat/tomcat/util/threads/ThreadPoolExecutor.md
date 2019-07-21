org.apache.tomcat.util.threads.ThreadPoolExecutor

* AtomicInteger AtomicLong

## hierarchy
```
AbstractExecutorService (java.util.concurrent)
    ThreadPoolExecutor (java.util.concurrent)
        ThreadPoolExecutor (org.apache.tomcat.util.threads)
```

## feilds
```java
    private final AtomicInteger submittedCount = new AtomicInteger(0);
    private final AtomicLong lastContextStoppedTime = new AtomicLong(0L);\
    
    private final AtomicLong lastTimeThreadKilledItself = new AtomicLong(0L);
    private long threadRenewalDelay = Constants.DEFAULT_THREAD_RENEWAL_DELAY;
```

## methods

### construct
```java
    public ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
        prestartAllCoreThreads();
    }
```

### afterExecute
```java
@Override
protected void afterExecute(Runnable r, Throwable t) {
    submittedCount.decrementAndGet();

    if (t == null) {
        stopCurrentThreadIfNeeded();
    }
}
```