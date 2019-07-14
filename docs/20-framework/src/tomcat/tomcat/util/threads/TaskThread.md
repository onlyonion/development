org.apache.tomcat.util.threads.TaskThread

## hierachical
```
Thread (java.lang)
    TaskThread (org.apache.tomcat.util.threads)
```
## define
```plantuml
@startuml

class TaskThread
TaskThread *- creationTime

TaskThread +-- WrappingRunnable
WrappingRunnable *- Runnable

@enduml
```

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

[SocketProcessorBase](../net/SocketProcessorBase.md)