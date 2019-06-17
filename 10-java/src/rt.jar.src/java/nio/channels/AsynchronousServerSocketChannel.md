java.nio.channels.AsynchronousServerSocketChannel

## package
```
AsynchronousServerSocketChannel (java.nio.channels)
    AsynchronousServerSocketChannelImpl (sun.nio.ch)
        WindowsAsynchronousServerSocketChannelImpl (sun.nio.ch)
```

## define
```plantuml
@startuml


interface AutoCloseable
interface Closeable
interface Channel

AutoCloseable ^-- Closeable
Closeable ^-- Channel

interface AsynchronousChannel
interface NetworkChannel

Channel ^-- AsynchronousChannel
Channel ^-- NetworkChannel

AsynchronousChannel ^.. AsynchronousServerSocketChannel
NetworkChannel ^.. AsynchronousServerSocketChannel

abstract class AsynchronousServerSocketChannel

@enduml
```


## methods
* provider
* open
* open
* bind
* bind
* setOption
* accept
* accept
* getLocalAddress