io.netty.channel.SingleThreadEventLoop

## hierarchy
```
AbstractExecutorService (java.util.concurrent)
    AbstractEventExecutor (io.netty.util.concurrent)
        AbstractScheduledEventExecutor (io.netty.util.concurrent)
            SingleThreadEventExecutor (io.netty.util.concurrent)        单线程事件执行器
                DefaultEventExecutor (io.netty.util.concurrent)
                SingleThreadEventLoop (io.netty.channel)                    单线程事件循环
                    EpollEventLoop (io.netty.channel.epoll)
                    ThreadPerChannelEventLoop (io.netty.channel)
                    DefaultEventLoop (io.netty.channel)
                    NioEventLoop (io.netty.channel.nio)
                    KQueueEventLoop (io.netty.channel.kqueue)
```

## define
```plantuml
@startuml

abstract class SingleThreadEventLoop

@enduml
```

## fields
```java
    protected static final int DEFAULT_MAX_PENDING_TASKS = Math.max(16,
            SystemPropertyUtil.getInt("io.netty.eventLoop.maxPendingTasks", Integer.MAX_VALUE));

    private final Queue<Runnable> tailTasks;
```


## methods