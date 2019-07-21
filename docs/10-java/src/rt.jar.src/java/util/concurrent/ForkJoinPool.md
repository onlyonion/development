java.util.concurrent.ForkJoinPool

并行执行任务的框架，把一个大任务**分割**成若干个小任务，最终**汇总**没个小任务结果后得到大任务结果的框架。

## hierarchy
```
AbstractExecutorService (java.util.concurrent)
    ForkJoinPool (java.util.concurrent)
ForkJoinPool (java.util.concurrent)
    AbstractExecutorService (java.util.concurrent)
        Object (java.lang)
        ExecutorService (java.util.concurrent)
            Executor (java.util.concurrent)
```

## define
* ForkJoinPool
* ForkJoinTask
  * RecursiveAction 没有返回结果的任务
  * RecursiveTask 有返回结果的任务
* ForkJoinWorkerThread

```plantuml
@startuml

interface Executor
interface ExecutorService 
abstract class AbstractExecutorService

Executor ^-- ExecutorService
ExecutorService ^.. AbstractExecutorService
AbstractExecutorService ^-- ForkJoinPool

class ForkJoinPool {

}

ForkJoinPool +-- EmptyTask
ForkJoinPool +-- WorkQueue

interface Future<V>
abstract class ForkJoinTask<V> 
Future ^.. ForkJoinTask
ForkJoinTask ^-- EmptyTask
class EmptyTask

class WorkQueue {
    ForkJoinTask<?>[] array
    final ForkJoinPool pool
    final ForkJoinWorkerThread owner
}
WorkQueue o-- ForkJoinTask
WorkQueue o-- ForkJoinPool
WorkQueue o-- ForkJoinWorkerThread

class ForkJoinWorkerThread {
    final ForkJoinPool pool
    final ForkJoinPool.WorkQueue workQueue
}
ForkJoinWorkerThread o-- ForkJoinPool
ForkJoinWorkerThread o-- WorkQueue

@enduml
```