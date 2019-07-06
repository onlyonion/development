com.alibaba.dubbo.rpc.Exporter

## 1. 定义

## 2. 类图

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