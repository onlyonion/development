org.apache.tomcat.util.threads.TaskThread

## define

## hierachical

```yuml
// {type:class}
[Runnable]^-.-[Thread]
[Thread]++-[Runnable]
[Thread]++-[ThreadGroup]


[Thread]^-[TaskThread]

[TaskThread]+->[WrappingRunnable]
[Runnable]^-.-[WrappingRunnable]

```

## thread.run() -> SocketProcessorBase.run()

```mermaid
sequenceDiagram
    Thread->>TaskThread$WrappingRunnable:run()
    TaskThread$WrappingRunnable->>ThreadPoolExecutor$Worker:run()
    ThreadPoolExecutor$Worker->>ThreadPoolExecutor:runWorker()
    ThreadPoolExecutor->>SocketProcessorBase:run()
```

[SocketProcessorBase](../../SocketProcessorBase.md)