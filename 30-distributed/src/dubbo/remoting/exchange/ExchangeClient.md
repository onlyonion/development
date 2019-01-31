
```
ExchangeClient (com.alibaba.dubbo.remoting.exchange)
    HeaderExchangeClient (com.alibaba.dubbo.remoting.exchange.support.header)
    LazyConnectExchangeClient (com.alibaba.dubbo.rpc.protocol.dubbo)
    ReferenceCountExchangeClient (com.alibaba.dubbo.rpc.protocol.dubbo)
```

## 交换层-传输层
```mermaid
sequenceDiagram
    %% 1. 交换层 引用计数、报文头交换、报文头通道
    DubboInvoker->>ReferenceCountExchangeClient:request(request, timeout)
    ReferenceCountExchangeClient->>HeaderExchangeClient:request(request, timeout)
    HeaderExchangeClient->>HeaderExchangeChannel:request(request, timeout)
    
    %% 2. 传输层 tcp协议，端到端的、面向连接与字节流的、可靠的、全双工的传输协议
    %% 2.1 对等传输，点对点
    HeaderExchangeChannel->>AbstractPeer:send()
    AbstractPeer->>AbstractClient:send()
    %% 2.2 netty网络库
    AbstractClient->>NettyChannel:send()
    %% 2.3 NIO套接字通道
    NettyChannel->>NioClientSocketChannel:write()
```