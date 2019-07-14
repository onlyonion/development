org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor

## hierarchy
```
CustomizableThreadCreator (org.springframework.util)
    CustomizableThreadFactory (org.springframework.scheduling.concurrent)
        ExecutorConfigurationSupport (org.springframework.scheduling.concurrent)
            ThreadPoolTaskExecutor (org.springframework.scheduling.concurrent)
```

## define
```plantuml
@startuml

'''''''''''''''''''' 自定义线程生成器 ''''''''''''''''''''
class CustomizableThreadCreator {
    - String threadNamePrefix
	- int threadPriority = Thread.NORM_PRIORITY
	- boolean daemon = false
	- ThreadGroup threadGroup
	- final AtomicInteger threadCount
}
CustomizableThreadCreator ^-- CustomizableThreadFactory

'''''''''''''''''''' 自定义线程工厂 ''''''''''''''''''''
class CustomizableThreadFactory
ThreadFactory ^.. CustomizableThreadFactory

'''''''''''''''''''' 执行器配置支持 ''''''''''''''''''''
CustomizableThreadFactory ^-- ExecutorConfigurationSupport

abstract class ExecutorConfigurationSupport {
    - ThreadFactory threadFactory = this
    - boolean threadNamePrefixSet = false
    - RejectedExecutionHandler rejectedExecutionHandler
    - boolean waitForTasksToCompleteOnShutdown = false
    - int awaitTerminationSeconds = 0
    - String beanName
    - ExecutorService executor
    	
    # abstract ExecutorService initializeExecutor(
			ThreadFactory threadFactory, RejectedExecutionHandler rejectedExecutionHandler)
}

'''''''''''''''''''' 线程池任务执行器 ''''''''''''''''''''
ExecutorConfigurationSupport ^-- ThreadPoolTaskExecutor
class ThreadPoolTaskExecutor

@enduml
```
