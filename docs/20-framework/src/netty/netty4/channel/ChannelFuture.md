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

interface java.util.concurrent.Future
interface Future
java.util.concurrent.Future ^-- Future
Future ^-- ChannelFuture

interface ChannelFuture

ChannelFuture *-- Channel

ChannelFuture ..> GenericFutureListener
interface EventListener
interface GenericFutureListener<F extends Future<?>> 
EventListener ^-- GenericFutureListener

@enduml
```

```java
public interface ChannelFuture extends Future<Void> {

    /**
     * Returns a channel where the I/O operation associated with this
     * future takes place.
     */
    Channel channel();

    @Override
    ChannelFuture addListener(GenericFutureListener<? extends Future<? super Void>> listener);

    @Override
    ChannelFuture addListeners(GenericFutureListener<? extends Future<? super Void>>... listeners);

    @Override
    ChannelFuture removeListener(GenericFutureListener<? extends Future<? super Void>> listener);

    @Override
    ChannelFuture removeListeners(GenericFutureListener<? extends Future<? super Void>>... listeners);

    @Override
    ChannelFuture sync() throws InterruptedException;

    @Override
    ChannelFuture syncUninterruptibly();

    @Override
    ChannelFuture await() throws InterruptedException;

    @Override
    ChannelFuture awaitUninterruptibly();

    boolean isVoid();
}
```
