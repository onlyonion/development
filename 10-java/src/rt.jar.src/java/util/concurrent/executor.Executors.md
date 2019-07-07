java.util.concurrent.Executors

## define
* 内部类
  * RunnableAdapter
  * PrivilegedCallable
  * PrivilegedCallableUsingCurrentClassLoader
  * DefaultThreadFactory
  * PrivilegedThreadFactory
  * DelegatedExecutorService
  * FinalizableDelegatedExecutorService
  * DelegatedScheduledExecutorService
  
## methods
* newFixedThreadPool 固定线程数，无界队列，适用于任务数量不均匀的场景，对内存压力不敏感，但系统负载比较敏感的场景
* newCachedThreadPool 无限线程数，适用于要低延迟的短期任务场景
* newSingleThreadExecutor 单个线程固定线程池，适用于保证异步执行顺序的场景
* newScheduledThreadPool 定期执行任务的场景，支持固定频率和固定延时，心跳、监控
* newWorkStealingPool 使用ForkJoinPool，多任务队列的固定并行度，适合任务执行长不均匀的场景
* newSingleThreadScheduledExecutor
  