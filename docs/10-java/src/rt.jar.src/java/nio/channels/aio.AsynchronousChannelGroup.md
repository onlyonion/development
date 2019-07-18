java.nio.channels.AsynchronousChannelGroup
## hierarchy
```
AsynchronousChannelGroup (java.nio.channels)
    AsynchronousChannelGroupImpl (sun.nio.ch)
        Iocp (sun.nio.ch)
```

## methods
* withFixedThreadPool
* withCachedThreadPool
* withThreadPool
* isShutdown
* isTerminated
* shutdown
* shutdownNow
* awaitTermination

### withFixedThreadPool
内核将事件分发到这些线程
```java
    public static AsynchronousChannelGroup withFixedThreadPool(int nThreads,
                                                               ThreadFactory threadFactory)
        throws IOException
    {
        return AsynchronousChannelProvider.provider()
            .openAsynchronousChannelGroup(nThreads, threadFactory);
    }
```

### withCachedThreadPool
```java
    public static AsynchronousChannelGroup withCachedThreadPool(ExecutorService executor,
                                                                int initialSize)
        throws IOException
    {
        return AsynchronousChannelProvider.provider()
            .openAsynchronousChannelGroup(executor, initialSize);
    }
```

## withThreadPool
采用ExecutorService
```java
    public static AsynchronousChannelGroup withThreadPool(ExecutorService executor)
        throws IOException
    {
        return AsynchronousChannelProvider.provider()
            .openAsynchronousChannelGroup(executor, 0);
    }
```
