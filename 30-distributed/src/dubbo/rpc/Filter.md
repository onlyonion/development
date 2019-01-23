## 1. 定义

## 2. 类图

```yuml
// {type:class}
[Filter]^-.-[ConsumerContextFilter]
[Filter]^-.-[FutureFilter]
[Filter]^-.-[ExceptionFilter]
[Filter]^-.-[MonitorFilter]
[Filter]^-.-[ContextFilter]
```