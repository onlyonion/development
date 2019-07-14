java.util.concurrent.BlockingQueue

## hierarchy
```
BlockingQueue (java.util.concurrent)
    ArrayBlockingQueue (java.util.concurrent)
    LinkedBlockingQueue (java.util.concurrent)
    PriorityBlockingQueue (java.util.concurrent)
    SynchronousQueue (java.util.concurrent)
    DelayQueue (java.util.concurrent)
    BlockingDeque (java.util.concurrent)
        ForwardingBlockingDeque (com.google.common.collect)
        LinkedBlockingDeque (java.util.concurrent)
        RedisList (org.springframework.data.redis.support.collections)
            DefaultRedisList (org.springframework.data.redis.support.collections)
    TransferQueue (java.util.concurrent)
        LinkedTransferQueue (java.util.concurrent)
    DelayedWorkQueue in ScheduledThreadPoolExecutor (java.util.concurrent)
        ForwardingBlockingQueue (com.google.common.util.concurrent)
    BlockingArrayQueue (org.eclipse.jetty.util)
```
## define

|         | Throws exception | Special value | Blocks         | Times out            |
| :------ | :--------------- | :------------ | :------------- | :------------------- |
| Insert  | add(e)           | offer(e)      | put(e)         | offer(e, time, unit) |
| Remove  | remove()         | poll()        | take()         | poll(time, unit)     |
| Examine | element()        | peek()        | not applicable | not applicable       |
