## netty
Netty is a NIO client server framework which enables quick and easy development of network applications such as protocol servers and clients. 
It greatly simplifies and streamlines network programming such as TCP and UDP socket server.

## 组件 components
* core 零拷贝、缓冲、通用通信API、扩展的事件模型
    - zero-copy-capabie, rich byte buffer
    - universal communication api
    - extensible event model
* transport services 传输层服务
    - socket & datagram
    - http tunnel
    - in-vm pipe
* protocol support 协议支持
    - http & websocket
    - ssl startTLS
    - google protobuf
    - zlib/gzip comppression
    - large file transfer
    - rtsp Real Time Streaming Protocol 实时流传输协议
    - legacy text binary protocols with unit testablility

![components](../../img/netty-components.png)

    
## io.netty 4.x
* bootstrap
  * [AbstractBootstrap](/docs/20-framework/src/netty/netty4/bootstrap/AbstractBootstrap.md)
  * [ServerBootstrap](/docs/20-framework/src/netty/netty4/bootstrap/ServerBootstrap.md)
* buffer
  * [ByteBuf](/docs/20-framework/src/netty/netty4/buffer/ByteBuf.md)
  * [AbstractByteBuf](/docs/20-framework/src/netty/netty4/buffer/AbstractByteBuf.md)
  * [AbstractReferenceCountedByteBuf](/docs/20-framework/src/netty/netty4/buffer/AbstractReferenceCountedByteBuf.md)
  * [CompositeByteBuf](/docs/20-framework/src/netty/netty4/buffer/CompositeByteBuf.md) 合并字节缓存，零拷贝
* channel
  * embedded
  * epoll
  * kequeue
  * nio
    * [NioEventLoop](/docs/20-framework/src/netty/netty4/channel/nio/NioEventLoop.md)
    * [NioEventLoopGroup](/docs/20-framework/src/netty/netty4/channel/nio/NioEventLoopGroup.md)
  * oio
  * pool
  * sctp
  * socket
  * unix
  * [Channel](/docs/20-framework/src/netty/netty4/channel/Channel.md)
  * [ChannelHandler](/docs/20-framework/src/netty/netty4/channel/ChannelHandler.md)
  * [ChannelHandlerAdapter](/docs/20-framework/src/netty/netty4/channel/ChannelHandlerAdapter.md)
  * [ChannelFuture](/docs/20-framework/src/netty/netty4/channel/ChannelFuture.md)
  * [ChannelPipeline](/docs/20-framework/src/netty/netty4/channel/ChannelPipeline.md)
  * [FileRegion](/docs/20-framework/src/netty/netty4/channel/FileRegion.md) transferTo 文件区，零拷贝
  * [DefaultFileRegion](/docs/20-framework/src/netty/netty4/channel/DefaultFileRegion.md)
  * [loop.EventLoop](/docs/20-framework/src/netty/netty4/channel/EventLoop.md) 事件循环
  * [loop.EventLoopGroup](/docs/20-framework/src/netty/netty4/channel/EventLoopGroup.md) 事件循环组
  * [loop.MultithreadEventLoopGroup](/docs/20-framework/src/netty/netty4/channel/MultithreadEventLoopGroup.md)
  * [loop.SingleThreadEventLoop](/docs/20-framework/src/netty/netty4/channel/SingleThreadEventLoop.md)
* [handler](/docs/20-framework/src/netty/netty4/handler/README.md)
  * codec
    * http2
    * [ByteToMessageDecoder](/docs/20-framework/src/netty/netty4/handler/codec/ByteToMessageDecoder.md)
    * [DelimiterBasedFrameDecoder](/docs/20-framework/src/netty/netty4/handler/codec/DelimiterBasedFrameDecoder.md) 分隔符解码器
    * [LineBasedFrameDecoder](/docs/20-framework/src/netty/netty4/handler/codec/LineBasedFrameDecoder.md) 换行符解码器
    * [FixedLengthFrameDecoder](/docs/20-framework/src/netty/netty4/handler/codec/FixedLengthFrameDecoder.md) 固定长度解码器
  * timeout
    * [IdleStateHandler](/docs/20-framework/src/netty/netty4/handler/timeout/IdleStateHandler.md)
    * [ReadTimeoutHandler](/docs/20-framework/src/netty/netty4/handler/timeout/ReadTimeoutHandler.md)
* resovler
  * dns
* util
  * collection
  * concurrent
    * [executor.EventExecutor](/docs/20-framework/src/netty/netty4/util/concurrent/EventExecutor.md)
    * [executor.SingleThreadEventExecutor](/docs/20-framework/src/netty/netty4/util/concurrent/SingleThreadEventExecutor.md)
    * [exexutor.MultithreadEventExecutorGroup](/docs/20-framework/src/netty/netty4/util/concurrent/MultithreadEventExecutorGroup.md)
    * [result.Future](/docs/20-framework/src/netty/netty4/util/concurrent/Future.md)
    * [result.AbstractFuture](/docs/20-framework/src/netty/netty4/util/concurrent/AbstractFuture.md)
    * [result.DefaultPromise](/docs/20-framework/src/netty/netty4/util/concurrent/DefaultPromise.md)
    * [GenericFutureListener](/docs/20-framework/src/netty/netty4/util/concurrent/GenericFutureListener.md)
  * [ReferenceCounted](/docs/20-framework/src/netty/netty4/util/ReferenceCounted.md)
  * [ResourceLeakDetector](/docs/20-framework/src/netty/netty4/util/ResourceLeakDetector.md)
## package
4.1.36.Final
```
io.netty
    bootstrap
        Bootstrap
        ServerBootstrap
    buffer
        ByteBuf
        ByteBufAllocator
    channel
        embedded
            EmbeddedChannel
            EmbeddedEventLoop
        epoll
        group
        local
        nio
            NioEventLoop
            NioEventLoopGroup
            NioTask
        oio
        pool
        rxtx
        sctp
        socket
        udt
        unix
        
        SingleThreadEventLoop
    handler    
        codec
            base64
            bytes
            compression
            dns
            haproxy
            http
            http2 二进制传输；消息头hpack压缩；基于帧和流的多路复用；支持服务端推送
            json
            marshalling
            memcache
            mqtt
            protobuf
            redis
            rtsp
            sctp
            serialization
            socks
            socksx
            spdy google开发用于传输web内容的开发网络协议，http2协议的母体。压缩、多路复用、优先级
            stomp
            string
            xml
        flow
        flush
        ipfilter
        logging
        proxy
        ssl
        stream
        timeout
        traffic
    resovler
    util
        collection
        concurrent
        internal    
```

## links
* [《Netty权威指南（第2版）》李林锋](/99-book/notes/21-server/Netty权威指南.md)
* [《Netty实战》 何品 译 人民邮电出版社](/99-book/notes/21-server/Netty实战.md)