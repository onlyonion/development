java.util.concurrent.ScheduledThreadPoolExecutor

## hierarchy
```
AbstractExecutorService (java.util.concurrent)
    ThreadPoolExecutor (java.util.concurrent)
        ScheduledThreadPoolExecutor (java.util.concurrent)
            UnorderedThreadPoolEventExecutor (io.netty.util.concurrent)
ScheduledThreadPoolExecutor (java.util.concurrent)
    ThreadPoolExecutor (java.util.concurrent)
        AbstractExecutorService (java.util.concurrent)
            Object (java.lang)
            ExecutorService (java.util.concurrent)
                Executor (java.util.concurrent)
    ScheduledExecutorService (java.util.concurrent)
        ExecutorService (java.util.concurrent)
            Executor (java.util.concurrent)
```

## define
```plantuml
@startuml

'''''''''''''''''''' 父类 ''''''''''''''''''''
abstract class AbstractExecutorService
AbstractExecutorService ^-- ThreadPoolExecutor
ExecutorService ^.. AbstractExecutorService
class ThreadPoolExecutor #yellow


'''''''''''''''''''' 接口 ''''''''''''''''''''
Executor ^-- ExecutorService
ExecutorService ^-- ScheduledExecutorService

interface Executor
interface ExecutorService
interface ScheduledExecutorService #orange {
    + ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit)
    + <V> ScheduledFuture<V> schedule(Callable<V> callable, long delay, TimeUnit unit)                                       
    + ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit)   
    + ScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit)                                                                                      
}

'''''''''''''''''''' ScheduledThreadPoolExecutor ''''''''''''''''''''
ThreadPoolExecutor ^-- ScheduledThreadPoolExecutor
ScheduledExecutorService ^.. ScheduledThreadPoolExecutor
class ScheduledThreadPoolExecutor #orange {
    - static final AtomicLong sequencer
}


ScheduledThreadPoolExecutor +-- ScheduledFutureTask
ScheduledThreadPoolExecutor +-- DelayedWorkQueue

'''''''''''''''''''' ScheduledFutureTask ''''''''''''''''''''
interface RunnableFuture<V> 
RunnableFuture ^-- RunnableScheduledFuture
interface RunnableScheduledFuture<V>
class FutureTask<V>
RunnableFuture ^.. FutureTask
RunnableScheduledFuture ^.. ScheduledFutureTask
FutureTask ^-- ScheduledFutureTask
class ScheduledFutureTask<V> {
    - final long sequenceNumber
    - long time
    - final long period
    RunnableScheduledFuture<V> outerTask 
    int heapIndex
}

'''''''''''''''''''' DelayedWorkQueue ''''''''''''''''''''
interface BlockingQueue<Runnable>
BlockingQueue ^.. DelayedWorkQueue
class DelayedWorkQueue {
    private static final int INITIAL_CAPACITY = 16
    private RunnableScheduledFuture<?>[] queue
    private final ReentrantLock lock
    private int size = 0
    private Thread leader
    private final Condition available 
}


@enduml
```