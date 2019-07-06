io.netty.channel.ChannelFuture

## hierarchy
```
ChannelFuture (io.netty.channel)
    ChannelProgressiveFuture (io.netty.channel)
    ChannelPromise (io.netty.channel)
    CompleteChannelFuture (io.netty.channel)
```

## define
```plantuml
@startuml

interface ChannelFuture
interface GenericFutureListener<F extends Future<?>> 

ChannelFuture ..> GenericFutureListener

@enduml
```
