io.netty.channel.EventLoop

## hierarchy
```
EventLoop (io.netty.channel)
    AbstractEventLoop (io.netty.channel)
    SingleThreadEventLoop (io.netty.channel)
        EpollEventLoop (io.netty.channel.epoll)
        ThreadPerChannelEventLoop (io.netty.channel)
        DefaultEventLoop (io.netty.channel)
        NioEventLoop (io.netty.channel.nio)
    EmbeddedEventLoop (io.netty.channel.embedded)

EventLoop (io.netty.channel)
    OrderedEventExecutor (io.netty.util.concurrent)               有序事件执行器
        EventExecutor (io.netty.util.concurrent)                     事件执行器
            EventExecutorGroup (io.netty.util.concurrent)               事件执行器组
                ScheduledExecutorService (java.util.concurrent)             调度执行器服务
                    ExecutorService (java.util.concurrent)                      执行器服务
                        Executor (java.util.concurrent)                             执行器
                Iterable (java.lang)
    EventLoopGroup (io.netty.channel)                               事件循环组
        EventExecutorGroup (io.netty.util.concurrent)                   事件执行器组
            ScheduledExecutorService (java.util.concurrent)                 调度执行器服务
                ExecutorService (java.util.concurrent)                          执行器服务
                    Executor (java.util.concurrent)                                 执行器
            Iterable (java.lang)
```