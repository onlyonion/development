
[importnew博文](http://www.importnew.com/15731.html "title") 

## CountDownLatch
使一个线程等待其他线程完成各自的工作后再执行。
例如，应用程序的主线程希望在负责启动框架服务的线程已经启动所有的框架服务之后再执行。

CountDownLatch是通过一个计数器来实现的，计数器的初始值为线程的数量。每当一个线程完成了自己的任务后，计数器的值就会减1。当计数器值到达0时，它表示所有的线程已经完成了任务，然后在闭锁上等待的线程就可以恢复执行任务。

![altText](./img/java-concurrent-countdownlanch.png "title") 


### CountDownLatch 如何工作
构造器中的计数值（count）实际上就是闭锁需要等待的线程数量。这个值只能被设置一次，而且CountDownLatch没有提供任何机制去重新设置这个计数值。

与CountDownLatch的第一次交互是主线程等待其他线程。主线程必须在启动其他线程后立即调用CountDownLatch.await()方法。这样主线程的操作就会在这个方法上阻塞，直到其他线程完成各自的任务。

其他N 个线程必须引用闭锁对象，因为他们需要通知CountDownLatch对象，他们已经完成了各自的任务。这种通知机制是通过 CountDownLatch.countDown()方法来完成的；每调用一次这个方法，在构造函数中初始化的count值就减1。所以当N个线程都调 用了这个方法，count的值等于0，然后主线程就能通过await()方法，恢复执行自己的任务。

## CyclicBarrier

## Semaphore

## ConcurrentHashMap

## BlockingQueue


## 等待/通知模式的经典范式
http://www.cnblogs.com/wxd0108/p/5479442.html

### 等待方: 
1.	获取对象的锁 
2.	如果条件不满足 那么调用对象的wait方法 被通知后仍要检查条件、 
3.	条件满足则执行对应的逻辑

伪码:

```java
synchronized(对象){
	while(条件不满足){
		对象.wait()
	}
	对应的处理逻辑
}
```

### 通知方:

1.	获得对象的锁 
2.	改变条件 
3.	通知所有等待在对象上的线程

伪码:

```java
synchronized(对象){
	改变条件
	对象.notifyAll();
}
```
