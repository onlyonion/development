com.alibaba.dubbo.rpc.Protocol

## 1. 定义
* 基于netty的多协议开发
* 通信协议定义了通信中的语法学, 语义学和同步规则以及可能存在的错误检测与纠正

## 2. 类图
```yuml
// {type:class}

// 协议封装了默认端口、导出、引入、销毁
[Protocol|+defaultPort|+export(Invoker);refer(Class<T>, URL);destroy()]

// 1. 网络协议
[Protocol]^-.-[AbstractProtocol]

// 2. redis、injvm、dubbo、thrift传输协议
[AbstractProtocol]^-[RedisProtocol]
[AbstractProtocol]^-[InjvmProtocol]
[AbstractProtocol]^-[DubboProtocol{bg:tomato}]
[AbstractProtocol]^-[ThriftProtocol]

// 3. 内存缓存
[AbstractProtocol]^-[MemcachedProtocol]
[AbstractProtocol]^-[MockProtocol]

// 4. 代理协议
[AbstractProtocol]^-[AbstractProxyProtocol]
[AbstractProxyProtocol]^-[HttpProtocol]
[AbstractProxyProtocol]^-[RmiProtocol]
[AbstractProxyProtocol]^-[WebServiceProtocol]
[AbstractProxyProtocol]^-[HessianProtocol]

// 5. 协议过滤器包装
[Protocol]^-.-[ProtocolFilterWrapper]
[Protocol]^-.-[ProtocolListenerWrapper]
[Protocol]^-.-[InjvmProtocol]
[Protocol]^-.-[RegistryProtocol{bg:steelblue}]
```