## 1. 定义
* Invocation 封装了方法名称、方法参数类型、方法参数值、附加属性
* method.invoker(proxy, args)对反射方法抽象

## 2. 类图
```yuml
// {type:class}

[Invocation]^-.-[RpcInvocation]
[RpcInvocation]^-[DecodeableRpcInvocation]

// 分布式节点
[Node{bg:wheat}]^-[Invoker{bg:wheat}]
[Invocation]++->[Invoker]
```