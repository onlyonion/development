io.netty.util.concurrent.EventExecutor

## hierarchy
```
EventExecutor (io.netty.util.concurrent)
    EventExecutorGroup (io.netty.util.concurrent)
        ScheduledExecutorService (java.util.concurrent)
            ExecutorService (java.util.concurrent)
                Executor (java.util.concurrent)
    Iterable (java.lang)
```

## define
```plantuml
@startuml

package java.util.concurrent {
    interface Executor
    interface ExecutorService 
    interface ScheduledExecutorService
    
    Executor ^-- ExecutorService
    ExecutorService ^-- ScheduledExecutorService
    
    abstract class AbstractExecutorService
    ExecutorService ^.. AbstractExecutorService
}

package io.netty.util.concurrent {
    '''''''''''''''''事件执行器组、事件执行器、有序事件执行器'''''''''''''''''''''''''
    interface EventExecutorGroup #orange
    interface EventExecutor #orange
    interface OrderedEventExecutor #orange
    
    ScheduledExecutorService ^-- EventExecutorGroup
    EventExecutorGroup ^-- EventExecutor
    EventExecutor ^-- OrderedEventExecutor
    
    abstract class AbstractEventExecutor
    abstract class AbstractScheduledEventExecutor
    abstract class SingleThreadEventExecutor
    
    AbstractExecutorService ^-- AbstractEventExecutor 
    EventExecutor ^.. AbstractEventExecutor
    AbstractEventExecutor ^-- AbstractScheduledEventExecutor
    AbstractScheduledEventExecutor ^-- SingleThreadEventExecutor
    OrderedEventExecutor ^.. SingleThreadEventExecutor
}

package  io.netty.channel {
    '''''''''''''''''事件循环组、事件循环'''''''''''''''''''''''''
    interface EventLoopGroup #orange
    interface EventLoop #orange
    
    EventExecutorGroup ^-- EventLoopGroup
    EventLoopGroup ^-- EventLoop
    OrderedEventExecutor ^-- EventLoop
    
    '''''''''''''''''单线程事件循环'''''''''''''''''''''''''
    abstract class AbstractEventLoop 
    abstract class SingleThreadEventLoop 
    class NioEventLoop #lime
    
    AbstractEventExecutor ^-- AbstractEventLoop
    EventLoop ^.. AbstractEventLoop
    SingleThreadEventExecutor ^-- SingleThreadEventLoop
    EventLoop ^.. SingleThreadEventLoop
    SingleThreadEventLoop ^-- NioEventLoop
    
    '''''''''''''''''多线程组事件循环'''''''''''''''''''''''''
    abstract class AbstractEventExecutorGroup
    abstract class MultithreadEventExecutorGroup
    abstract class MultithreadEventLoopGroup
    class NioEventLoopGroup #lime
    
    EventExecutorGroup ^-- AbstractEventExecutorGroup
    AbstractEventExecutorGroup ^-- MultithreadEventExecutorGroup
    MultithreadEventExecutorGroup ^-- MultithreadEventLoopGroup
    EventLoopGroup ^.. MultithreadEventLoopGroup
    MultithreadEventLoopGroup ^-- NioEventLoopGroup
    
}

@enduml
```