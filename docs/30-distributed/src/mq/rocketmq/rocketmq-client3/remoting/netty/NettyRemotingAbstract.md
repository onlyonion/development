org.apache.rocketmq.remoting.netty.NettyRemotingAbstract

## hierarchy
```
NettyRemotingAbstract (org.apache.rocketmq.remoting.netty)
    NettyRemotingClient (org.apache.rocketmq.remoting.netty)
    NettyRemotingServer (org.apache.rocketmq.remoting.netty)
```

## define
```plantuml
@startuml

abstract class NettyRemotingAbstract {
    # final NettyEventExecutor nettyEventExecutor
    # volatile SslContext sslContext
    + abstract ChannelEventListener getChannelEventListener()
    + void processRequestCommand(final ChannelHandlerContext ctx, final RemotingCommand cmd)
}

NettyRemotingAbstract ^-- NettyRemotingClient
NettyRemotingAbstract ^-- NettyRemotingServer


@enduml
```