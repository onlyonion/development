
## Exporter

```yuml

// {type:class}

[Exporter]^-.-[AbstractExporter]
[AbstractExporter]^-[InjvmExporter]
[AbstractExporter]^-[DubboExporter]

[Exporter]^-.-[ListenerExporterWrapper]
[Exporter]^-.-[DelegateExporter]

```

## ExporterListener

## Filter

## Invocation

## Invoker

## InvokerListener

## Protocol

```
Protocol (com.alibaba.dubbo.rpc)
    AbstractProtocol (com.alibaba.dubbo.rpc.protocol)
        RedisProtocol (com.alibaba.dubbo.rpc.protocol.redis)
        InjvmProtocol (com.alibaba.dubbo.rpc.protocol.injvm)
        DubboProtocol (com.alibaba.dubbo.rpc.protocol.dubbo)
        ThriftProtocol (com.alibaba.dubbo.rpc.protocol.thrift)
        AbstractProxyProtocol (com.alibaba.dubbo.rpc.protocol)
            HttpProtocol (com.alibaba.dubbo.rpc.protocol.http)
            RmiProtocol (com.alibaba.dubbo.rpc.protocol.rmi)
            WebServiceProtocol (com.alibaba.dubbo.rpc.protocol.webservice)
            HessianProtocol (com.alibaba.dubbo.rpc.protocol.hessian)
        MemcachedProtocol (com.alibaba.dubbo.rpc.protocol.memcached)
        MockProtocol (com.alibaba.dubbo.rpc.support)
    ProtocolFilterWrapper (com.alibaba.dubbo.rpc.protocol)
    ProtocolListenerWrapper (com.alibaba.dubbo.rpc.protocol)
    InjvmProtocol (com.alibaba.dubbo.rpc.protocol.injvm)
    RegistryProtocol (com.alibaba.dubbo.registry.integration)
```

## ProxyFactory

## Result