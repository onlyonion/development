com.alibaba.dubbo.remoting.exchange.support.header.HeartbeatHandler

## hierarchy

## define

## stack
```
write:251, AbstractChannel (org.jboss.netty.channel)
send:98, NettyChannel (com.alibaba.dubbo.remoting.transport.netty)
send:54, AbstractPeer (com.alibaba.dubbo.remoting.transport)

received:68, HeartbeatHandler (com.alibaba.dubbo.remoting.exchange.support.header)
received:27, MultiMessageHandler (com.alibaba.dubbo.remoting.transport)
received:138, AbstractPeer (com.alibaba.dubbo.remoting.transport)
messageReceived:91, NettyHandler (com.alibaba.dubbo.remoting.transport.netty)

handleUpstream:100, SimpleChannelHandler (org.jboss.netty.channel)
sendUpstream:564, DefaultChannelPipeline (org.jboss.netty.channel)
sendUpstream:783, DefaultChannelPipeline$DefaultChannelHandlerContext (org.jboss.netty.channel)
fireMessageReceived:302, Channels (org.jboss.netty.channel)
messageReceived:148, NettyCodecAdapter$InternalDecoder (com.alibaba.dubbo.remoting.transport.netty)

handleUpstream:80, SimpleChannelUpstreamHandler (org.jboss.netty.channel)
sendUpstream:564, DefaultChannelPipeline (org.jboss.netty.channel)
sendUpstream:559, DefaultChannelPipeline (org.jboss.netty.channel)
fireMessageReceived:274, Channels (org.jboss.netty.channel)
fireMessageReceived:261, Channels (org.jboss.netty.channel)

read:349, NioWorker (org.jboss.netty.channel.socket.nio)
processSelectedKeys:280, NioWorker (org.jboss.netty.channel.socket.nio)
run:200, NioWorker (org.jboss.netty.channel.socket.nio)

run:108, ThreadRenamingRunnable (org.jboss.netty.util)
run:44, DeadLockProofWorker$1 (org.jboss.netty.util.internal)

runWorker:1149, ThreadPoolExecutor (java.util.concurrent)
run:624, ThreadPoolExecutor$Worker (java.util.concurrent)
run:748, Thread (java.lang)
```