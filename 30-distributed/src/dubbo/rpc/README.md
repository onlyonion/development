
* Exporter 导出者
* ExporterListener
* Filter 过滤器
* Invocation 调用器，封装方法名、参数、类型
* Invoker 调用者
* InvokerListener
* Protocol 网络传输协议
* ProxyFactory 动态代理工厂
* Result

## Exporter

```yuml

// {type:class}

[Exporter||+getInvoker();+unexport()]

// 导出服务者的实现，虚拟机内部、远程导出不同
[Exporter]^-.-[AbstractExporter]
[AbstractExporter]^-[InjvmExporter{bg:thistle}]
[AbstractExporter]^-[DubboExporter{bg:thistle}]

[Exporter]^-.-[ListenerExporterWrapper]
[Exporter]^-.-[DelegateExporter]

// 分布式节点
[Node{bg:wheat}]^-[Invoker{bg:wheat}]
[Exporter]++-[Invoker]
```

## ExporterListener

```yuml
// {type:class}

[ExporterListener||+exported(exporter);+unexported(exporter)]^-.-[ExporterListenerAdapter]
```

## Filter

```yuml
// {type:class}

[Filter]^-.-[ConsumerContextFilter]
[Filter]^-.-[FutureFilter]
[Filter]^-.-[ExceptionFilter]
[Filter]^-.-[MonitorFilter]
[Filter]^-.-[ContextFilter]
```

## Invocation
* Invocation 封装了方法名称、方法参数类型、方法参数值、附加属性
* method.invoker(proxy, args)对反射方法抽象
```yuml
// {type:class}

[Invocation]^-.-[RpcInvocation]
[RpcInvocation]^-[DecodeableRpcInvocation]

// 分布式节点
[Node{bg:wheat}]^-[Invoker{bg:wheat}]
[Invocation]++->[Invoker]
```

## Invoker
```yuml
// {type:class}

[Node]^-[Invoker||+getInterface();+invoke(invocation)]
[Node]++-[URL]


// 1. 模拟数据集群调用器
[Invoker]^-[MockClusterInvoker{bg:wheat}]
[MockClusterInvoker]++->[Directory]
[MockClusterInvoker]++->[Invoker]

// 2. 抽象集群调用器
[Invoker]^-.-[AbstractClusterInvoker{bg:thistle}]
// 2.1 快速失败集群调用器
[AbstractClusterInvoker]^-[FailfastClusterInvoker]
// 2.2 失效转移
[AbstractClusterInvoker]^-[FailoverClusterInvoker]
// 2.3 失败安全
[AbstractClusterInvoker]^-[FailsafeClusterInvoker]
// 2.4 失败恢复
[AbstractClusterInvoker]^-[FailbackClusterInvoker]
// 2.5 广播
[AbstractClusterInvoker]^-[BroadcastClusterInvoker]
// 2.6 并行
[AbstractClusterInvoker]^-[ForkingClusterInvoker]

// 3. 调用器包装
[Invoker]^-.-[InvokerWrapper]

// 4. 虚拟机内、远程间
[Invoker]^-.-[AbstractInvoker{bg:snow}]
[AbstractInvoker]^-[InjvmInvoker]
[AbstractInvoker]^-[DubboInvoker]

// 5. 代理调用器 jdk动态代理、javassist代理
[Invoker]^-.-[AbstractProxyInvoker{bg:snow}]
[AbstractProxyInvoker]^-[JdkProxyFactory]
[AbstractProxyInvoker]^-[JavassistProxyFactory]

```

## InvokerListener

## Protocol
* 基于netty的多协议开发
* 通信协议定义了通信中的语法学, 语义学和同步规则以及可能存在的错误检测与纠正

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

## ProxyFactory
* 代理工厂，生成代理，生成调用器
* jdk动态代理、javassit代理

### 字节码
* asm
* javassit
* jdk
* cglib

```yuml
// {type:class}
// 代理工厂
[ProxyFactory||+getProxy(invoker);+getInvoker(proxy,type,url)]
[ProxyFactory]^-.-[AbstractProxyFactory]

// 两种类型的代理工厂
[AbstractProxyFactory]^-[JavassistProxyFactory{bg:steelblue}]
[AbstractProxyFactory]^-[JdkProxyFactory{bg:turquoise}]

// 客户端桩代理工厂包装
[ProxyFactory]^-.-[StubProxyFactoryWrapper]
```

## Result
rpc调用结果接口，封装了结果、异常、附加属性等