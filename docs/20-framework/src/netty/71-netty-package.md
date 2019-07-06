
#### netty package

* bootstrap
* buffer
* channel
* container
* handler

#### io.netty 4.x

4.1.5.Final
jdk.1.7.0_85

```
io.netty

bootstrap
buffer
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

#### org.jboss.netty 3.x

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