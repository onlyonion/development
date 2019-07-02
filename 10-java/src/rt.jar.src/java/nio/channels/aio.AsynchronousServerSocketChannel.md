java.nio.channels.AsynchronousServerSocketChannel

## package
```
AsynchronousServerSocketChannel (java.nio.channels)
    AsynchronousServerSocketChannelImpl (sun.nio.ch)
        WindowsAsynchronousServerSocketChannelImpl (sun.nio.ch)
AsynchronousChannel (java.nio.channels)
    Channel (java.nio.channels)
        Closeable (java.io)
            AutoCloseable (java.lang)
    NetworkChannel (java.nio.channels)
        Channel (java.nio.channels)
            Closeable (java.io)
                AutoCloseable (java.lang)
```

## define
```plantuml
@startuml

'''''''''''''''''''''''''''自动关闭、通道'''''''''''''''''''''''''''
interface AutoCloseable
interface Closeable
interface Channel

AutoCloseable ^-- Closeable
Closeable ^-- Channel

'''''''''''''''''''''''''''异步通道、网络通道'''''''''''''''''''''''''''
interface AsynchronousChannel
interface NetworkChannel

Channel ^-- AsynchronousChannel
Channel ^-- NetworkChannel

AsynchronousChannel ^.. AsynchronousServerSocketChannel
NetworkChannel ^.. AsynchronousServerSocketChannel

'''''''''''''''''''''''''''异步通道组'''''''''''''''''''''''''''
abstract class AsynchronousServerSocketChannel #orange

'''''''''''''''''''''''''''异步回调'''''''''''''''''''''''''''
interface Future #yellow
interface CompletionHandler<V,A> #yellow

AsynchronousServerSocketChannel ..> Future
AsynchronousServerSocketChannel ..> SocketAddress
AsynchronousServerSocketChannel ..> CompletionHandler


@enduml
```


## methods
* provider
* public static AsynchronousServerSocketChannel open(AsynchronousChannelGroup group)
* public static AsynchronousServerSocketChannel open()
* bind
* bind
* setOption
* accept
* accept
* getLocalAddress