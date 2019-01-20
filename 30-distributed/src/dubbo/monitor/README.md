

```yuml
// {type:class}

[MonitorService||+collect(url);+lookup(url)]

// 网络节点
[Node]^-[Monitor]
[Node]^-[Invoker]
[MonitorService]^-[Monitor]

// 监控
[Monitor]^-.-[DubboMonitor]

[DubboMonitor]++-[ScheduledExecutorService]
[DubboMonitor]++-[ScheduledFuture]
[DubboMonitor]++-[Invoker]
[DubboMonitor]++-[MonitorService]

// 监控工厂
[MonitorFactory]^-.-[AbstractMonitorFactory||#createMonitor(url)]
[AbstractMonitorFactory]^-[DubboMonitorFactroy]

[MonitorFactory]++-[Monitor]
[DubboMonitorFactroy]++-[Protocol]
[DubboMonitorFactroy]++-[ProxyFactory]

// 过滤器
[Filter||+(invoke(Invoker<?>,Invocation))]^-.-[MonitorFilter]
```