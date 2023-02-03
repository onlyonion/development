java.util.concurrent.SubmissionPublisher


## Field
```java
    private static final Executor ASYNC_POOL =
        (ForkJoinPool.getCommonPoolParallelism() > 1) ?
        ForkJoinPool.commonPool() : new ThreadPerTaskExecutor();

    private static final class ThreadPerTaskExecutor implements Executor {
        ThreadPerTaskExecutor() {}      // prevent access constructor creation
        public void execute(Runnable r) { new Thread(r).start(); }
    }

    final ReentrantLock lock;
```