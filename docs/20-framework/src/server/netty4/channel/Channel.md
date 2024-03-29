io.netty.channel.Channel

## hierarchy
```
Channel (io.netty.channel)
    UnixChannel (io.netty.channel.unix)
    DatagramChannel (io.netty.channel.socket)
        NioDatagramChannel (io.netty.channel.socket.nio)
        OioDatagramChannel (io.netty.channel.socket.oio)
        EpollDatagramChannel (io.netty.channel.epoll)
    DuplexChannel (io.netty.channel.socket)
        DomainSocketChannel (io.netty.channel.unix)
        AbstractEpollStreamChannel (io.netty.channel.epoll)
        SocketChannel (io.netty.channel.socket)
    AbstractChannel (io.netty.channel)
        AbstractEpollChannel (io.netty.channel.epoll)
        LocalChannel (io.netty.channel.local)
        AbstractHttp2StreamChannel (io.netty.handler.codec.http2)
        EmbeddedChannel (io.netty.channel.embedded)
        AbstractOioChannel (io.netty.channel.oio)
        AbstractServerChannel (io.netty.channel)
    AbstractNioChannel (io.netty.channel.nio)
        UdtChannel (io.netty.channel.udt)
        NioUdtByteConnectorChannel (io.netty.channel.udt.nio)
        NioUdtMessageConnectorChannel (io.netty.channel.udt.nio)
        UdtServerChannel (io.netty.channel.udt)
    ServerChannel (io.netty.channel)
        ServerSocketChannel (io.netty.channel.socket)
        ServerDomainSocketChannel (io.netty.channel.unix)
        AbstractEpollServerChannel (io.netty.channel.epoll)
        SctpServerChannel (io.netty.channel.sctp)
        AbstractServerChannel (io.netty.channel)
        UdtServerChannel (io.netty.channel.udt)
    SctpChannel (io.netty.channel.sctp)
        NioSctpChannel (io.netty.channel.sctp.nio)
        OioSctpChannel (io.netty.channel.sctp.oio)
```

## define
```plantuml
@startuml

interface Channel

Channel *-- EventLoop
Channel *-- ChannelPipeline
Channel *-- ByteBufAllocator

interface EventLoop
interface ChannelPipeline
interface ByteBufAllocator

Channel +-- Unsafe
interface Unsafe

@enduml
```