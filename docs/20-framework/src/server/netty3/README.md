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

![components](../../../img/netty-components.png)

## org.jboss.netty 3.x
3.7.0.Final
jdk.1.7.0_25

```
org.jboss.netty
    bootstrap
        ClientBootstrap
        ServerBootstrap
    buffer
        ChannelBuffer
        ChannelBufferFactory
        CompositeChannelBuffer
        DirectChannelBufferFactory
        HeapChannelBuffer
        HeapChannelBufferFactory
    channel
        group
            ChannelGroup
            ChannelGroupFuture
            ChannelGroupFutureListener
        local
            LocalChannel
            LocalServerChannel
        socket
            http
            nio
            oio
            DatagramChannel
            DatagramChannelCofnig
            DatagramChannelFactory
            ServerSocketChannel
            ServerSocketChannelConfig
            ServerSocketChannelFactory
            SocketChannel
            SocketChannelConfig
        Channel
        ChannelConfig
        ChannelEvent
        ChannelFactory
        ChannelHandler
        ChannelPipeline
        FileRegion
        MessageEvent
        ServerChannel
        ServerChannelFactory
    container
        microcontainer
        osgi
        spring
    handler
        codec
            base64
            compression
            embedder
            frame
                DelimitersBasedFrameDecoder
                FixedLengthFrameDecoder
            http
                websocket
                Cookie
                CookieDecoder
                CookieEncoder
                HttpHeader
                HttpMessage
                HttpMethod
                HttpRequest
                HttpResponse
            marshalling
            oneone
            protobuf
                ProtobufDecoder
                ProtobufEncoder
            replay
            rtsp
            serialization
                ObjectDecoder
                ObjectEncoder
            socks
            spdy
            string
        execution
        ipfilter
        logging
        queue
        ssl
        stream
        timeout
        traffic
    logging
    util
```