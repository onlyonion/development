java.util.concurrent.ThreadPoolExecutor

## hierarchy
```
Executor
    ExecutorService
        AbstractExecutorService (java.util.concurrent)
            ThreadPoolExecutor (java.util.concurrent)
                ScheduledThreadPoolExecutor (java.util.concurrent)
                QuantumRenderer (com.sun.javafx.tk.quantum)
                ComInvoker in Win32ShellFolderManager2 (sun.awt.shell)
                MemoryAwareThreadPoolExecutor (org.jboss.netty.handler.execution)
                BrokerFixedThreadPoolExecutor (org.apache.rocketmq.broker.latency)
                ThreadPoolExecutor (org.apache.tomcat.util.threads)
                WorkerPoolExecutor (org.apache.http.impl.bootstrap)
```

## define
* 内部类
  * Worker
* 域
  * 工作队列 BlockingQueue<Runnable> workQueue
  * 全局锁 ReentrantLock mainLock
  * 工作线程集合 HashSet<Worker> workers
  * 全局锁条件 
  * 线程工厂 ThreadFactory
  * 饱和策略 RejectedExecutionHandler
  * 存活时间 keepAliveTime
  * 核心线程池数量 corePoolSize
  * 最大线程池数量 maximumPoolSize

```plantuml
@startuml

'''''''''''''''''''' Executor ''''''''''''''''''''
interface Executor
interface ExecutorService

Executor <|-- ExecutorService
ExecutorService <|-.- AbstractExecutorService

abstract class AbstractExecutorService {
    - <T> T doInvokeAny(Collection<? extends Callable<T>> tasks,
        boolean timed, long nanos)
}

'''''''''''''''''''' ThreadPoolExecutor ''''''''''''''''''''
class ThreadPoolExecutor {
    - final AtomicInteger ctl
    - final BlockingQueue<Runnable> workQueue
    - final ReentrantLock mainLock
    - final HashSet<Worker> workers
    - final Condition termination
    - volatile ThreadFactory threadFactory
    - volatile RejectedExecutionHandler handler
    - volatile long keepAliveTime
    - volatile int corePoolSize
    - volatile int maximumPoolSize
    
    - boolean addWorker(Runnable firstTask, boolean core)
    - Runnable getTask()
    .. 核心函数 ..
    final void runWorker(Worker w) 
    .. 模板方法 执行之前、之后、退出 ..
    # void beforeExecute(Thread t, Runnable r)
    # void afterExecute(Runnable r, Throwable t)
    - void processWorkerExit(Worker w, boolean completedAbruptly)
}
AbstractExecutorService <|-- ThreadPoolExecutor

ThreadPoolExecutor o-- BlockingQueue
ThreadPoolExecutor o-- ReentrantLock
ThreadPoolExecutor o--- ThreadFactory
ThreadPoolExecutor o--- RejectedExecutionHandler

interface ThreadFactory

'''''''''''''''''''' Worker ''''''''''''''''''''
abstract class AbstractQueuedSynchronizer {
    + final void acquire(int arg)
    + final boolean release(int arg)
}
interface Runnable
Runnable ^.. Worker
class Worker {
    final Thread thread
    Runnable firstTask
    volatile long completedTasks
    + void run()
    + void lock()
}

AbstractQueuedSynchronizer <|-- Worker
ThreadPoolExecutor +- Worker

'''''''''''''''''''' BlockingQueue ''''''''''''''''''''
interface BlockingQueue<E>
class LinkedBlockingQueue<E>
class SynchronousQueue<E> 
class DelayQueue<E extends Delayed> 

BlockingQueue ^.. LinkedBlockingQueue
BlockingQueue ^.. SynchronousQueue
BlockingQueue ^.. DelayQueue

DelayQueue o-- PriorityQueue

'''''''''''''''''''' RejectedExecutionHandler ''''''''''''''''''''
interface RejectedExecutionHandler

RejectedExecutionHandler <|-- CallerRunsPolicy
RejectedExecutionHandler <|-- AbortPolicy
RejectedExecutionHandler <|-- DiscardPolicy
RejectedExecutionHandler <|-- DiscardOldestPolicy

'ThreadPoolExecutor +-- CallerRunsPolicy
'ThreadPoolExecutor +-- AbortPolicy
'ThreadPoolExecutor +-- DiscardPolicy
'ThreadPoolExecutor +-- DiscardOldestPolicy

@enduml
```

## 状态图

* RUNNING(-1):  Accept new tasks and process queued tasks
* SHUTDOWN(0): Don't accept new tasks, but process queued tasks
* STOP(1):     Don't accept new tasks, don't process queued tasks,
            and interrupt in-progress tasks
* TIDYING(2):  All tasks have terminated, workerCount is zero,
            the thread transitioning to state TIDYING
            will run the terminated() hook method
* TERMINATED(3): terminated() has completed
     
```mermaid
graph LR
    RUNNING -- shutdown --> SHUTDOWN
    RUNNING -- shutdownNow --> STOP
    SHUTDOWN -- shutdownNow --> STOP
    SHUTDOWN -- queueAndPoolEmpty --> TIDYING
    STOP -- poolEmpty --> TIDYING
    TIDYING -- terminated --> TERMINATED
```

## execute()

```mermaid
sequenceDiagram
    Actor->>ThreadPoolExecutor:execute(command)
    
    %% 1. 原子变量获得状态和数量
    ThreadPoolExecutor->>AtomicInteger:获得线程池状态和线程数量get()
    
    %% 2. 小于核心线程池数量
    opt workerCount < corePoolSize
        opt
            ThreadPoolExecutor->>ThreadPoolExecutor:addWorker(command, true)添加核心线程的Worker
        end
    end
    
    %% 3. 添加任务失败
    alt isRunning(c)并且可以取出任务
        
        ThreadPoolExecutor->>AtomicInteger:重新获得线程池状态和线程数量get()
        
        alt 不是运行状态并且可以删除任务
            ThreadPoolExecutor->>ThreadPoolExecutor:reject(command)
            ThreadPoolExecutor->>RejectedExecutionHandler:rejectedExecution(command,this)
            
        else 工作线程的数量等于0
            ThreadPoolExecutor->>ThreadPoolExecutor:addWorker(null, false)添加非核心线程的Worker
        end
        
    else 不能添加非核心线程Worker
        ThreadPoolExecutor->>ThreadPoolExecutor:reject(command)
        ThreadPoolExecutor->>RejectedExecutionHandler:rejectedExecution(command,this)
    end
```