## 并发容器

### ConcurrentHashMap

*	volatile
*	ReetrantLok
*	hash()算法	
*	数组		散列算法、按位与确定索引
*	单向链表
*	红黑树	O(n) -> O(logN)

#### 类图
![altText](./img/java-collections-ConcurrentHashMap-class.jpg "title") 

#### 结构图
![altText](./img/java-collections-ConcurrentHashMap.jpg "title") 

### ConcurrentLinkedQueue


### 阻塞队列

支持阻塞的插入和移除方法。put(e), take(), offer(e, time, unit), poll(time, unit)


## CountDownLatch

使一个线程等待其他线程完成各自的工作后再执行。
例如，应用程序的主线程希望在负责启动框架服务的线程已经启动所有的框架服务之后再执行。

CountDownLatch是通过一个计数器来实现的，计数器的初始值为线程的数量。每当一个线程完成了自己的任务后，计数器的值就会减1。当计数器值到达0时，它表示所有的线程已经完成了任务，然后在闭锁上等待的线程就可以恢复执行任务。

![altText](./img/java-concurrent-countdownlanch.png "title") 

### CountDownLatch 如何工作

构造器中的计数值（count）实际上就是闭锁需要等待的线程数量。这个值只能被设置一次，而且CountDownLatch没有提供任何机制去重新设置这个计数值。
与CountDownLatch的第一次交互是主线程等待其他线程。

1.	主线程必须在启动其他线程后立即调用CountDownLatch.await()方法。这样主线程的操作就会在这个方法上阻塞，直到其他线程完成各自的任务。

2.	其他N 个线程必须引用闭锁对象，因为他们需要通知CountDownLatch对象，他们已经完成了各自的任务。这种通知机制是通过 CountDownLatch.countDown()方法来完成的；

3.	每调用一次这个方法，在构造函数中初始化的count值就减1。所以当N个线程都调 用了这个方法，count的值等于0，然后主线程就能通过await()方法，恢复执行自己的任务。

CountDownLatch是一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待。 

## CyclicBarrier

CyclicBarrier是一个同步辅助类，允许一组线程互相等待，直到到达某个公共屏障点 (common barrier point)。因为该 barrier 在释放等待线程后可以重用，所以称它为循环 的 barrier。
CyclicBarrier是包含了"ReentrantLock对象lock"和"Condition对象trip"，它是通过独占锁实现的。
CyclicBarrier和CountDownLatch的区别是：

### CyclicBarrier和CountDownLatch的区别

1. CountDownLatch的作用是允许1或N个线程等待其他线程完成执行；而CyclicBarrier则是允许N个线程相互等待。
2. CountDownLatch的计数器无法被重置；CyclicBarrier的计数器可以被重置后使用，因此它被称为是循环的barrier。

## Semaphore

Semaphore是一个计数信号量，它的本质是一个"共享锁"。
信号量维护了一个信号量许可集。线程可以通过调用acquire()来获取信号量的许可；当信号量中有可用的许可时，线程能获取该许可；否则线程必须等待，直到有可用的许可为止。 线程可以通过release()来释放它所持有的信号量许可。

## BlockingQueue

## ConcurrentHashMap

Hashtable，synchronized是针对整张Hash表的，即每次锁住整张表让线程独占，
ConcurrentHashMap允许多个修改操作并发进行，使用锁分离技术

多个锁来控制对hash表的不同部分进行的修改。ConcurrentHashMap内部使用段(Segment)来表示这些不同的部分，每个段其实就是一个小的hash table，它们有自己的锁
有些方法需要跨段，比如size()和containsValue()，它们可能需要锁定整个表而而不仅仅是某个段，这需要**按顺序**锁定所有段，操作完毕后，又按顺序释放所有段的锁

ConcurrentHashMap和Hashtable主要区别就是围绕着锁的粒度以及如何锁,可以简单理解成把一个大的HashTable分解成多个，形成了锁分离。
而Hashtable的实现方式是---锁整个hash表

## ThreadLocal

解决多线程中相同变量的访问冲突问题

- 以时间换空间        在同步机制中，通过对象的锁机制保证同一时间只有一个线程访问变量。该变量是多个线程共享的
- 以空间换时间        为每一个线程提供一个独立的变量副本，从而隔离了多个线程对数据的访问冲突

ThreadLocalMap存储的键值对中的键是this对象指向的ThreadLocal对象，而值就是你所设置的对象了

## 有限队列

>    SynchronousQueue
> 
>    ArrayBlockingQueue

```
一个由数组支持的有界阻塞队列
```

## 无限队列

>    LinkedBlockingQueue

基于链表结构。如果指定了LinkedBlockingQueue的容量大小，那么它反映出来的使用特性就和ArrayBlockingQueue类似了。

>    LinkedBlockingDeque

基于链表的双端队列。LinkedBlockingQueue的内部结构决定了它只能从队列尾部插入，从队列头部取出元素；但是LinkedBlockingDeque既可以从尾部插入/取出元素，还可以从头部插入元素/取出元素。

>    PriorityBlockingQueue

按照优先级进行内部元素排序的无限队列。
存放在PriorityBlockingQueue中的元素必须实现Comparable接口，这样才能通过实现compareTo()方法进行排序。

优先级最高的元素将始终排在队列的头部；
PriorityBlockingQueue不会保证优先级一样的元素的排序，也不保证当前队列中除了优先级最高的元素以外的元素，随时处于正确排序的位置。

它的迭代器并不保证队列保持任何特定的顺序，如果想要顺序遍历，考虑使用Arrays.sort(pq.toArray())

>    LinkedTransferQueue
> 
>    DelayQueue

其存储延时的元素，只有延时耗尽元素才能被取出。队列头元素就是最先耗尽延时的元素，如果没有元素耗尽延时，poll操作会返回null。同样的，该队列不允许空元素。

Delayed接口又是实现Comparable接口，注意该优先队列就是使用这个方法进行对比的，所以Comparable的实现要借助Delayed接口的方法选出剩余延时小，才能保证使用正确

[importnew博文](http://www.importnew.com/15731.html "title") 