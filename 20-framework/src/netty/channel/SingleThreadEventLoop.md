io.netty.channel.SingleThreadEventLoop

## hierarchy
```
AbstractExecutorService (java.util.concurrent)
    AbstractEventExecutor (io.netty.util.concurrent)
        AbstractScheduledEventExecutor (io.netty.util.concurrent)
            SingleThreadEventExecutor (io.netty.util.concurrent)
                SingleThreadEventLoop (io.netty.channel)
                    EpollEventLoop (io.netty.channel.epoll)
                    ThreadPerChannelEventLoop (io.netty.channel)
                    DefaultEventLoop (io.netty.channel)
                    NioEventLoop (io.netty.channel.nio)
                    KQueueEventLoop (io.netty.channel.kqueue)
```