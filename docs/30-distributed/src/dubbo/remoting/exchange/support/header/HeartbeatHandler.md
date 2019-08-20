com.alibaba.dubbo.remoting.exchange.support.header.HeartbeatHandler

## hierarchy
```
AbstractChannelHandlerDelegate (com.alibaba.dubbo.remoting.transport)
    HeartbeatHandler (com.alibaba.dubbo.remoting.exchange.support.header)
```
## define


## methods

### connected
```java
    public void connected(Channel channel) throws RemotingException {
        setReadTimestamp(channel);
        setWriteTimestamp(channel);
        handler.connected(channel);
    }
```

### received
```java
    public void received(Channel channel, Object message) throws RemotingException {
        setReadTimestamp(channel);
        if (isHeartbeatRequest(message)) {
            Request req = (Request) message;
            if (req.isTwoWay()) {
                Response res = new Response(req.getId(), req.getVersion());
                res.setEvent(Response.HEARTBEAT_EVENT);
                channel.send(res);
                if (logger.isInfoEnabled()) {
                    int heartbeat = channel.getUrl().getParameter(Constants.HEARTBEAT_KEY, 0);
                    if (logger.isDebugEnabled()) {
                        logger.debug("Received heartbeat from remote channel " + channel.getRemoteAddress()
                                + ", cause: The channel has no data-transmission exceeds a heartbeat period"
                                + (heartbeat > 0 ? ": " + heartbeat + "ms" : ""));
                    }
                }
            }
            return;
        }
        if (isHeartbeatResponse(message)) {
            if (logger.isDebugEnabled()) {
                logger.debug(
                        new StringBuilder(32)
                                .append("Receive heartbeat response in thread ")
                                .append(Thread.currentThread().getName())
                                .toString());
            }
            return;
        }
        handler.received(channel, message);
    }
```


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