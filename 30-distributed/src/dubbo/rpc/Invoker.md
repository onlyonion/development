com.alibaba.dubbo.rpc.Invoker

## 1. 定义

## 2. 类图

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