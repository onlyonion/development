com.alibaba.dubbo.remoting.Channel

## hierarchy
```
Channel (com.alibaba.dubbo.remoting)
    ChannelDelegate (com.alibaba.dubbo.remoting.transport)
    ExchangeChannel (com.alibaba.dubbo.remoting.exchange)
        HeaderExchangeChannel (com.alibaba.dubbo.remoting.exchange.support.header)
        ExchangeClient (com.alibaba.dubbo.remoting.exchange)
            HeaderExchangeClient (com.alibaba.dubbo.remoting.exchange.support.header)
            LazyConnectExchangeClient (com.alibaba.dubbo.rpc.protocol.dubbo)
            ReferenceCountExchangeClient (com.alibaba.dubbo.rpc.protocol.dubbo)
    Client (com.alibaba.dubbo.remoting)
        ClientDelegate (com.alibaba.dubbo.remoting.transport)
            ChannelWrapper in ChannelWrappedInvoker (com.alibaba.dubbo.rpc.protocol.dubbo)
        AbstractClient (com.alibaba.dubbo.remoting.transport)
            GrizzlyClient (com.alibaba.dubbo.remoting.transport.grizzly)
            NettyClient (com.alibaba.dubbo.remoting.transport.netty)
            MinaClient (com.alibaba.dubbo.remoting.transport.mina)
        ExchangeClient (com.alibaba.dubbo.remoting.exchange)
            HeaderExchangeClient (com.alibaba.dubbo.remoting.exchange.support.header)
            LazyConnectExchangeClient (com.alibaba.dubbo.rpc.protocol.dubbo)
            ReferenceCountExchangeClient (com.alibaba.dubbo.rpc.protocol.dubbo)
    AbstractChannel (com.alibaba.dubbo.remoting.transport)
        MinaChannel (com.alibaba.dubbo.remoting.transport.mina)
        NettyChannel (com.alibaba.dubbo.remoting.transport.netty)
        GrizzlyChannel (com.alibaba.dubbo.remoting.transport.grizzly)
```