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
            DecodeHandler (com.alibaba.dubbo.remoting.transport) 解码处理器
            HeartbeatHandler (com.alibaba.dubbo.remoting.exchange.support.header)
            MultiMessageHandler (com.alibaba.dubbo.remoting.transport)
        HeaderExchangeHandler (com.alibaba.dubbo.remoting.exchange.support.header) 报文头处理器
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
* connected 连接
* disconnected 关闭
* sent 发送 
* received 接收
* caught 异常

```plantuml
@startuml

interface ChannelHandler {
    + void connected(Channel channel) throws RemotingException
    + void disconnected(Channel channel) throws RemotingException
    + void sent(Channel channel, Object message) throws RemotingException
    + void received(Channel channel, Object message) throws RemotingException
    + void caught(Channel channel, Throwable exception) throws RemotingException
}

ChannelHandler ^-- ChannelHandlerDelegate
interface ChannelHandlerDelegate
ChannelHandlerDelegate o-- ChannelHandler

ChannelHandlerDelegate ^.. HeaderExchangeHandler
ChannelHandlerDelegate ^.. AbstractChannelHandlerDelegate

class HeaderExchangeHandler
abstract class AbstractChannelHandlerDelegate

AbstractChannelHandlerDelegate ^-- DecodeHandler
AbstractChannelHandlerDelegate ^-- HeartbeatHandler
AbstractChannelHandlerDelegate ^-- MultiMessageHandler

@enduml
```


```java
@SPI
public interface ChannelHandler {

    void connected(Channel channel) throws RemotingException;

    void disconnected(Channel channel) throws RemotingException;

    void sent(Channel channel, Object message) throws RemotingException;

    void received(Channel channel, Object message) throws RemotingException;

    /**
     * on exception caught.
     *
     * @param channel   channel.
     * @param exception exception.
     */
    void caught(Channel channel, Throwable exception) throws RemotingException;

}
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