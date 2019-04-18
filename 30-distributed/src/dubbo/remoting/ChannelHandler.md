com.alibaba.dubbo.remoting.ChannelHandler

## hierarchy
```
ChannelHandler (com.alibaba.dubbo.remoting)
    AbstractPeer (com.alibaba.dubbo.remoting.transport)
        AbstractChannel (com.alibaba.dubbo.remoting.transport)
            GrizzlyChannel (com.alibaba.dubbo.remoting.transport.grizzly)
            MinaChannel (com.alibaba.dubbo.remoting.transport.mina)
            NettyChannel (com.alibaba.dubbo.remoting.transport.netty)
    AbstractEndpoint (com.alibaba.dubbo.remoting.transport)
        AbstractClient (com.alibaba.dubbo.remoting.transport)
            GrizzlyClient (com.alibaba.dubbo.remoting.transport.grizzly)
            MinaClient (com.alibaba.dubbo.remoting.transport.mina)
            NettyClient (com.alibaba.dubbo.remoting.transport.netty)
        AbstractServer (com.alibaba.dubbo.remoting.transport)
            GrizzlyServer (com.alibaba.dubbo.remoting.transport.grizzly)
            MinaServer (com.alibaba.dubbo.remoting.transport.mina)
            NettyServer (com.alibaba.dubbo.remoting.transport.netty)
    ChannelHandlerAdapter (com.alibaba.dubbo.remoting.transport)
        TelnetHandlerAdapter (com.alibaba.dubbo.remoting.telnet.support)
    ChannelHandlerDelegate (com.alibaba.dubbo.remoting.transport)
        AbstractChannelHandlerDelegate (com.alibaba.dubbo.remoting.transport)
            DecodeHandler (com.alibaba.dubbo.remoting.transport)
            HeartbeatHandler (com.alibaba.dubbo.remoting.exchange.support.header)
            MultiMessageHandler (com.alibaba.dubbo.remoting.transport)
        HeaderExchangeHandler (com.alibaba.dubbo.remoting.exchange.support.header)
            WrappedChannelHandler (com.alibaba.dubbo.remoting.transport.dispatcher)
            AllChannelHandler (com.alibaba.dubbo.remoting.transport.dispatcher.all)
            ConnectionOrderedChannelHandler (com.alibaba.dubbo.remoting.transport.dispatcher.connection)
            ExecutionChannelHandler (com.alibaba.dubbo.remoting.transport.dispatcher.execution)
            MessageOnlyChannelHandler (com.alibaba.dubbo.remoting.transport.dispatcher.message)
    ChannelHandlerDispatcher (com.alibaba.dubbo.remoting.transport)
    ExchangeHandler (com.alibaba.dubbo.remoting.exchange)
        ExchangeHandlerAdapter (com.alibaba.dubbo.remoting.exchange.support)
            1 in DubboProtocol (com.alibaba.dubbo.rpc.protocol.dubbo)
            1 in ThriftProtocol (com.alibaba.dubbo.rpc.protocol.thrift)
        ExchangeHandlerDispatcher (com.alibaba.dubbo.remoting.exchange.support)
```

## define
* connected
* disconnected
* sent
* received
* caught

```plantuml
@startuml

interface ChannelHandler {
    + void connected(Channel channel) throws RemotingException
    + void disconnected(Channel channel) throws RemotingException
    + void sent(Channel channel, Object message) throws RemotingException
    + void received(Channel channel, Object message) throws RemotingException
    + void caught(Channel channel, Throwable exception) throws RemotingException
}

@enduml
```

## sequence
```mermaid
sequenceDiagram
    %% 传输层处理
    ChannelHandler->>DecodeHandler:received(channel, message)
    DecodeHandler->>HeaderExchangeHandler:received(channel, message)
    HeaderExchangeHandler->>HeaderExchangeHandler:handleRequest(ExchangeChannel, Request)
    
    %% dubbo传输协议处理
    HeaderExchangeHandler->>DubboProtocol$1:reply(ExchangeChannel, Object) 内部类ExchangeHandlerAdapter
    
    %% 过滤器链处理
    %% 服务实现

    DubboProtocol$1-->>HeaderExchangeHandler:返回result，封装成response对象
    %% 对等连接方式，发送
    HeaderExchangeHandler->>AbstractPeer:send(Object)
    AbstractPeer->>NettyChannel:send(Object,boolean)

    %% netty通道，nio读写
    NettyChannel->>NioAcceptedSocketChannel:AbstractChannel.write(Object)

```