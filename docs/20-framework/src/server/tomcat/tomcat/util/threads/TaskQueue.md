org.apache.tomcat.util.threads.TaskQueue

## hierachical
```
AbstractCollection (java.util)
    AbstractQueue (java.util)
        LinkedBlockingQueue (java.util.concurrent)
            TaskQueue (org.apache.tomcat.util.threads)
```
## define


```yuml

// 集合
[Collection]^-.-[AbstractCollection]

// 阻塞队列
[AbstractCollection]^-[AbstractQueue]
[Queue]^-.-[AbstractQueue]

// 链表阻塞队列
[AbstractQueue]^-[LinkedBlockingQueue]
[BlockingQueue]^-.-[LinkedBlockingQueue]

// 任务队列
[LinkedBlockingQueue]^-[TaskQueue]
```