
## dubbo protocol

```
com.alibaba.dubbo.rpc.protocol.dubbo
    filter
    page
    status
    telnet
    CallbackServiceCodec
    ChannelWrappedInvoker
    DecodeableRpcInvocation
    DecodeableRpcResult
    DubboCodec
    DubboCountCodec
    DubboExporter
    DubboInvoker
    DubboProtocol
    FutureAdapter
    LazyConnectExchangeClient
    ReferenceCountExchangeClient
```

## dubbo协议介绍
[dubbo-protocol](https://blog.csdn.net/fuyuwei2015/article/details/72848310/ )
* 缺省协议，使用基于mina1.1.7+hessian3.2.1的tbremoting交互。 
* 连接个数：单连接 
* 连接方式：长连接 
* 传输协议：TCP 
* 传输方式：NIO异步传输 
* 序列化：Hessian二进制序列化 
* 适用范围：传入传出参数数据包较小（建议小于100K），消费者比提供者个数多，单一消费者无法压满提供者，尽量不要用dubbo协议传输大文件或超大字符串。 
* 适用场景：常规远程服务方法调用

1. dubbo默认采用dubbo协议，dubbo协议采用单一长连接和NIO异步通讯，适合于小数据量大并发的服务调用，以及服务消费者机器数远大于服务提供者机器数的情况 
2. 他不适合传送大数据量的服务，比如传文件，传视频等，除非请求量很低。 
3. Dubbo协议缺省每服务每提供者每消费者使用单一长连接，如果数据量较大，可以使用多个连接。
4. 为防止被大量连接撑挂，可在服务提供方限制大接收连接数，以实现服务提供方自我保护