## netty

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

## src
* buffer
  * [ByteBuf](20-framework/src/netty/buffer/ByteBuf.md)
  * [AbstractByteBuf](20-framework/src/netty/buffer/AbstractByteBuf.md)
* channel
  * nio
    * [NioEventLoop](/20-framework/src/netty/channel/nio/NioEventLoop.md)
    * [NioEventLoopGroup](/20-framework/src/netty/channel/nio/NioEventLoopGroup.md)
  * [Channel](/20-framework/src/netty/channel/Channel.md)
  * [ChannelHandler](/20-framework/src/netty/channel/ChannelHandler.md)
  * [ChannelFuture](/20-framework/src/netty/channel/ChannelFuture.md)
  * [ChannelPipeline](/20-framework/src/netty/channel/ChannelPipeline.md)
  * [FileRegion](20-framework/src/netty/channel/FileRegion.md)
* handler
* util
  * concurrent
    * [EventExecutor](/20-framework/src/netty/util/concurrent/EventExecutor.md)
    
    
## io.netty 4.x
4.1.5.Final
jdk.1.7.0_85

```
io.netty

bootstrap
buffer
channel
    embedded
    epoll
    group
    local
    nio
    oio
    pool
    rxtx
    sctp
    socket
    udt
    unix
handler    
    codec
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