io.netty.channel.nio.NioEventLoopGroup

## hierarchy
```
AbstractEventExecutorGroup (io.netty.util.concurrent)
    MultithreadEventExecutorGroup (io.netty.util.concurrent)
        MultithreadEventLoopGroup (io.netty.channel)
            NioEventLoopGroup (io.netty.channel.nio)
```

## define
```plantuml
@startuml

''''''''''''''''''''''''''executor''''''''''''''''''''''''''
abstract class AbstractEventExecutorGroup
abstract class MultithreadEventExecutorGroup 

''''''''''''''''''''''''''loop''''''''''''''''''''''''''
interface EventLoopGroup 
abstract class MultithreadEventLoopGroup 
class NioEventLoopGroup

AbstractEventExecutorGroup ^-- MultithreadEventExecutorGroup
MultithreadEventExecutorGroup ^-- MultithreadEventLoopGroup
MultithreadEventLoopGroup ^-- NioEventLoopGroup

EventLoopGroup ^.. MultithreadEventLoopGroup

@enduml
```