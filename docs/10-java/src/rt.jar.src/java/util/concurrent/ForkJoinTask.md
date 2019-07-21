java.util.concurrent.ForkJoinTask

* WeakReference

## hierarchy
```
ForkJoinTask (java.util.concurrent)
    AdaptedCallable in ForkJoinTask (java.util.concurrent)
    AdaptedRunnableAction in ForkJoinTask (java.util.concurrent)
    RunnableExecuteAction in ForkJoinTask (java.util.concurrent)
    AdaptedRunnable in ForkJoinTask (java.util.concurrent)
    EmptyTask in ForkJoinPool (java.util.concurrent)
    RecursiveAction (java.util.concurrent)
    RecursiveTask (java.util.concurrent)
    CountedCompleter (java.util.concurrent)
    AsyncSupply in CompletableFuture (java.util.concurrent)
    AsyncRun in CompletableFuture (java.util.concurrent)
    Completion in CompletableFuture (java.util.concurrent)
```


## methods

### fork
异步执行这个任务，然后立即返回

```java
public final ForkJoinTask<V> fork() {
    Thread t;
    if ((t = Thread.currentThread()) instanceof ForkJoinWorkerThread)
        ((ForkJoinWorkerThread)t).workQueue.push(this);
    else
        ForkJoinPool.common.externalPush(this);
    return this;
}
```


### join
阻塞当前线程并等待获取结果

```java
public final V join() {
    int s;
    if ((s = doJoin() & DONE_MASK) != NORMAL)
        reportException(s);
    return getRawResult();
}

private int doJoin() {
    int s; Thread t; ForkJoinWorkerThread wt; ForkJoinPool.WorkQueue w;
    return (s = status) < 0 ? s :
        ((t = Thread.currentThread()) instanceof ForkJoinWorkerThread) ?
        (w = (wt = (ForkJoinWorkerThread)t).workQueue).
        tryUnpush(this) && (s = doExec()) < 0 ? s :
        wt.pool.awaitJoin(w, this, 0L) :
        externalAwaitDone();
}
```