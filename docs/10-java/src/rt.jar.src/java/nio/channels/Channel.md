java.nio.channels.Channel

## hierarch
```
ReadableByteChannel (java.nio.channels)
    ReadableByteChannelImpl in Channels (java.nio.channels)
    ScatteringByteChannel (java.nio.channels)
    ChunksChannel (oracle.jrockit.jfr)
    ByteChannel (java.nio.channels)
    NamedPipe (sun.plugin2.ipc)
    SourceChannel in Pipe (java.nio.channels)
InterruptibleChannel (java.nio.channels)
    AbstractInterruptibleChannel (java.nio.channels.spi)
        ReadableByteChannelImpl in Channels (java.nio.channels)
        SelectableChannel (java.nio.channels)
            AbstractSelectableChannel (java.nio.channels.spi)
                SctpMultiChannel (com.sun.nio.sctp)
                SocketChannel (java.nio.channels)
                SctpChannel (com.sun.nio.sctp)
                SctpServerChannel (com.sun.nio.sctp)
                SinkChannel in Pipe (java.nio.channels)
                DatagramChannel (java.nio.channels)
                SourceChannel in Pipe (java.nio.channels)
                ServerSocketChannel (java.nio.channels)
        WritableByteChannelImpl in Channels (java.nio.channels)
        FileChannel (java.nio.channels)
SelectableChannel (java.nio.channels)
    AbstractSelectableChannel (java.nio.channels.spi)
        SctpMultiChannel (com.sun.nio.sctp)
        SocketChannel (java.nio.channels)
        SctpChannel (com.sun.nio.sctp)
        SctpServerChannel (com.sun.nio.sctp)
        SinkChannel in Pipe (java.nio.channels)
        DatagramChannel (java.nio.channels)
        SourceChannel in Pipe (java.nio.channels)
        ServerSocketChannel (java.nio.channels)
NetworkChannel (java.nio.channels)
    AsynchronousServerSocketChannel (java.nio.channels)
    SocketChannel (java.nio.channels)
    MulticastChannel (java.nio.channels)
    ServerSocketChannel (java.nio.channels)
    AsynchronousSocketChannel (java.nio.channels)
WritableByteChannel (java.nio.channels)
    1 in FileRegionEncoder (org.apache.rocketmq.remoting.netty)
    SinkChannel in Pipe (java.nio.channels)
    WritableByteChannelImpl in Channels (java.nio.channels)
    ByteChannel (java.nio.channels)
    NamedPipe (sun.plugin2.ipc)
    GatheringByteChannel (java.nio.channels)
AbstractInterruptibleChannel (java.nio.channels.spi)
SelChImpl (sun.nio.ch)
    ServerSocketChannelImpl (sun.nio.ch)
    DatagramChannelImpl (sun.nio.ch)
    SourceChannelImpl (sun.nio.ch)
    SinkChannelImpl (sun.nio.ch)
    SocketChannelImpl (sun.nio.ch)
AsynchronousChannel (java.nio.channels)
    AsynchronousServerSocketChannel (java.nio.channels)
    AsynchronousFileChannel (java.nio.channels)
    AsynchronousByteChannel (java.nio.channels)
        AsynchronousSocketChannel (java.nio.channels)
```

## define
```plantuml
@startuml

interface Channel {
    + boolean isOpen()
    + void close()
}


interface ReadableByteChannel 
interface WritableByteChannel
interface InterruptibleChannel
interface NetworkChannel
abstract class SelectableChannel
abstract class AbstractInterruptibleChannel

''''''''''''''''''''''''''异步通道''''''''''''''''''''''''''''
interface AsynchronousChannel

Channel ^-- ReadableByteChannel
Channel ^-- WritableByteChannel
Channel ^--- InterruptibleChannel
Channel ^-- NetworkChannel
Channel ^--- AsynchronousChannel
Channel ^.. AbstractInterruptibleChannel

''''''''''''''''''''''''''SelectableChannel''''''''''''''''''''''''''''
Channel ^--- SelectableChannel
SelectableChannel ^.. AbstractSelectableChannel
abstract class AbstractSelectableChannel

abstract class ServerSocketChannel
abstract class DatagramChannel
abstract class SocketChannel

AbstractSelectableChannel ^-- ServerSocketChannel
AbstractSelectableChannel ^-- DatagramChannel
AbstractSelectableChannel ^-- SocketChannel

@enduml
```