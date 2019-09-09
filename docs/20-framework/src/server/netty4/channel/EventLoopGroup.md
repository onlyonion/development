io.netty.channel.EventLoopGroup

## hierarchy
```
EventLoopGroup (io.netty.channel)
    AbstractEventLoopGroup (io.netty.channel)
    MultithreadEventLoopGroup (io.netty.channel)
        DefaultEventLoopGroup (io.netty.channel)
            LocalEventLoopGroup
        EpollEventLoopGroup (io.netty.channel.epoll)
        NioEventLoopGroup (io.netty.channel.nio)
    ThreadPerChannelEventLoopGroup (io.netty.channel)
        OioEventLoopGroup (io.netty.channel.oio)
    EventLoop (io.netty.channel)
        AbstractEventLoop (io.netty.channel)
        SingleThreadEventLoop (io.netty.channel)
            EpollEventLoop (io.netty.channel.epoll)
            ThreadPerChannelEventLoop (io.netty.channel)
            DefaultEventLoop (io.netty.channel)
            NioEventLoop (io.netty.channel.nio)
        EmbeddedEventLoop (io.netty.channel.embedded)

EventLoopGroup (io.netty.channel)
    EventExecutorGroup (io.netty.util.concurrent)
        ScheduledExecutorService (java.util.concurrent)
            ExecutorService (java.util.concurrent)
                Executor (java.util.concurrent)
        Iterable (java.lang)
```