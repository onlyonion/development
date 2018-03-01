# 1. Java多线程关键字

volatitle, final, synchronized

## 缓存不一致的问题
在增加了增加了L1、L2 Cache之后，CPU何时将变量从独享缓存刷新会共享内存，独享缓存是否从共享内存加载变量，时间上都是不可确定的，这就造成了缓存不一致的问题。

## volatitle
保证共享变量对所有线程可见，但不保证原子性
volatile语义是同步，通过共享变量的方式，完成线程间的通信
volatile保证指令赋值完后的变量立即同步回主内存中，声明并通知其他线程当前赋值的变量已经失效，其他线程在下次使用时会放弃工作内存中变量，使用主内存中的变量。这样就完成了线程间对于volatile修饰的变量的通信。

轻量级的synchronized
不会引起线程上下文的切换和调度

禁止指令重排序优化

### 原子性
原子操作是一个或多个不可中断的操作，要么一次性完全执行完毕，要么就不执行，最终状态不存在有些操作执行完，有些操作没有执行，在外部看来是不可分割的整体（比如化学中的原子，当然原子也是可以再分割的，不过站在分子层面，原子是最小的不可分割的），原子操作关注的是不被线程调度器中断的操作。

volatile关注可见性，而与原子性没有关系。volatile关注点在于从工作内存刷新回主内存，而原子操作关注的是否不被打断。原子和同步目的都是让不同线程可以安全地访问共享变量的两种处理方式，避免造成内存一致性错误。

### 可见性
当一个线程修改一个共享变量时，另外一个线程能读到这个修改的值

## final

*	修饰变量		只读
*	修饰方法		不能被重写
*	修饰类		不能被继承

优点
final关键字提高了性能。JVM和Java应用都会缓存final变量。
final变量可以安全的在多线程环境下进行共享，而不需要额外的同步开销。
使用final关键字，JVM会对方法、变量及类进行优化。

final和abstract这两个关键字是反相关的，final类就不可能是abstract的
final方法在编译阶段绑定，称为静态绑定(static binding)

### 多线程语义
编译器和处理器要遵守两个重排序规则：
在构造函数内对一个final域的写入，与随后把这个被构造对象的引用赋值给一个引用变量，这两个操作之间不能重排序。
初次读一个包含final域的对象的引用，与随后初次读这个final域，这两个操作之间不能重排序。

写重排序
JMM禁止编译器把final域的写重排序到构造函数之外。
编译器会在final域的写之后，构造函数return之前，插入一个StoreStore屏障。这个屏障禁止处理器把final域的写重排序到构造函数之外。

读重排序
在一个线程中，初次读对象引用与初次读该对象包含的final域，JMM禁止处理器重排序这两个操作（注意，这个规则仅仅针对处理器）。编译器会在读final域操作的前面插入一个LoadLoad屏障。

## synchronized

1.	修饰普通方法
2.	修饰静态方法
3.	修饰代码块


# 2. Java Object多线程方法 wait(), notify(), notifyAll()

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

## ThreadLocal
解决多线程中相同变量的访问冲突问题

*	以时间换空间		在同步机制中，通过对象的锁机制保证同一时间只有一个线程访问变量。该变量是多个线程共享的
*	以空间换时间		为每一个线程提供一个独立的变量副本，从而隔离了多个线程对数据的访问冲突

ThreadLocalMap存储的键值对中的键是this对象指向的ThreadLocal对象，而值就是你所设置的对象了

# 3. java.util.concurrent

# importnew博文
[importnew博文](http://www.importnew.com/15731.html "title") 

## CountDownLatch
使一个线程等待其他线程完成各自的工作后再执行。
例如，应用程序的主线程希望在负责启动框架服务的线程已经启动所有的框架服务之后再执行。

CountDownLatch是通过一个计数器来实现的，计数器的初始值为线程的数量。每当一个线程完成了自己的任务后，计数器的值就会减1。当计数器值到达0时，它表示所有的线程已经完成了任务，然后在闭锁上等待的线程就可以恢复执行任务。

![altText](./img/java-concurrent-countdownlanch.png "title") 


### CountDownLatch 如何工作
构造器中的计数值（count）实际上就是闭锁需要等待的线程数量。这个值只能被设置一次，而且CountDownLatch没有提供任何机制去重新设置这个计数值。
与CountDownLatch的第一次交互是主线程等待其他线程。
主线程必须在启动其他线程后立即调用CountDownLatch.await()方法。这样主线程的操作就会在这个方法上阻塞，直到其他线程完成各自的任务。
其他N 个线程必须引用闭锁对象，因为他们需要通知CountDownLatch对象，他们已经完成了各自的任务。这种通知机制是通过 CountDownLatch.countDown()方法来完成的；

每调用一次这个方法，在构造函数中初始化的count值就减1。所以当N个线程都调 用了这个方法，count的值等于0，然后主线程就能通过await()方法，恢复执行自己的任务。

CountDownLatch是一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待。 

## CyclicBarrier
CyclicBarrier是一个同步辅助类，允许一组线程互相等待，直到到达某个公共屏障点 (common barrier point)。因为该 barrier 在释放等待线程后可以重用，所以称它为循环 的 barrier。
CyclicBarrier是包含了"ReentrantLock对象lock"和"Condition对象trip"，它是通过独占锁实现的。
CyclicBarrier和CountDownLatch的区别是：

### CyclicBarrier和CountDownLatch的区别
1.	CountDownLatch的作用是允许1或N个线程等待其他线程完成执行；而CyclicBarrier则是允许N个线程相互等待。
2.	CountDownLatch的计数器无法被重置；CyclicBarrier的计数器可以被重置后使用，因此它被称为是循环的barrier。

## Semaphore
Semaphore是一个计数信号量，它的本质是一个"共享锁"。
信号量维护了一个信号量许可集。线程可以通过调用acquire()来获取信号量的许可；当信号量中有可用的许可时，线程能获取该许可；否则线程必须等待，直到有可用的许可为止。 线程可以通过release()来释放它所持有的信号量许可。

## ConcurrentHashMap

## BlockingQueue

## ConcurrentHashMap
Hashtable，synchronized是针对整张Hash表的，即每次锁住整张表让线程独占，
ConcurrentHashMap允许多个修改操作并发进行，使用锁分离技术

多个锁来控制对hash表的不同部分进行的修改。ConcurrentHashMap内部使用段(Segment)来表示这些不同的部分，每个段其实就是一个小的hash table，它们有自己的锁
有些方法需要跨段，比如size()和containsValue()，它们可能需要锁定整个表而而不仅仅是某个段，这需要**按顺序**锁定所有段，操作完毕后，又按顺序释放所有段的锁

 oncurrentHashMap和Hashtable主要区别就是围绕着锁的粒度以及如何锁,可以简单理解成把一个大的HashTable分解成多个，形成了锁分离。
而Hashtable的实现方式是---锁整个hash表



# Locks

## Lock Condition
Condition 将 Object 监视器方法（wait、notify 和 notifyAll）分解成截然不同的对象，以便通过将这些对象与任意 Lock 实现组合使用，为每个对象提供多个等待 set （wait-set）。
其中，Lock 替代了 synchronized 方法和语句的使用，Condition 替代了 Object 监视器方法的使用。

在Condition中，用await()替换wait()，用signal()替换notify()，用signalAll()替换notifyAll()，传统线程的通信方式，Condition都可以实现，这里注意，Condition是被绑定到Lock上的，要创建一个Lock的Condition必须用newCondition()方法。Condition的强大之处在于它可以为多个线程间建立不同的Condition。


## AbstractQueuedSynchronizer
就是被称之为AQS的类，它是一个非常有用的超类，可用来定义锁以及依赖于排队阻塞线程的其他同步器

## 锁基本概念
1. AQS -- 指AbstractQueuedSynchronizer类。
    AQS是java中管理“锁”的抽象类，锁的许多公共方法都是在这个类中实现。AQS是独占锁(例如，ReentrantLock)和共享锁(例如，Semaphore)的公共父类。

2. AQS锁的类别 -- 分为“独占锁”和“共享锁”两种。
    (01) 独占锁 -- 锁在一个时间点只能被一个线程锁占有。根据锁的获取机制，它又划分为“公平锁”和“非公平锁”。公平锁，是按照通过CLH等待线程按照先来先得的规则，公平的获取锁；而非公平锁，则当线程要获取锁时，它会无视CLH等待队列而直接获取锁。独占锁的典型实例子是ReentrantLock，此外，ReentrantReadWriteLock.WriteLock也是独占锁。
    (02) 共享锁 -- 能被多个线程同时拥有，能被共享的锁。JUC包中的ReentrantReadWriteLock.ReadLock，CyclicBarrier， CountDownLatch和Semaphore都是共享锁。这些锁的用途和原理，在以后的章节再详细介绍。

3. CLH队列 -- Craig, Landin, and Hagersten lock queue
    CLH队列是AQS中“等待锁”的线程队列。在多线程中，为了保护竞争资源不被多个线程同时操作而起来错误，我们常常需要通过锁来保护这些资源。在独占锁中，竞争资源在一个时间点只能被一个线程锁访问；而其它线程则需要等待。CLH就是管理这些“等待锁”的线程的队列。
    CLH是一个非阻塞的 FIFO 队列。也就是说往里面插入或移除一个节点的时候，在并发条件下不会阻塞，而是通过自旋锁和 CAS 保证节点插入和移除的原子性。

4. CAS函数 -- Compare And Swap 
    CAS函数，是比较并交换函数，它是原子操作函数；即，通过CAS操作的数据都是以原子方式进行的。
	例如，compareAndSetHead(), compareAndSetTail(), compareAndSetNext()等函数。它们共同的特点是，这些函数所执行的动作是以原子的方式进行的。


## 有限队列

>	SynchronousQueue

>	ArrayBlockingQueue

	一个由数组支持的有界阻塞队列

## 无限队列

>	LinkedBlockingQueue

	基于链表结构。如果指定了LinkedBlockingQueue的容量大小，那么它反映出来的使用特性就和ArrayBlockingQueue类似了。

>	LinkedBlockingDeque

	基于链表的双端队列。LinkedBlockingQueue的内部结构决定了它只能从队列尾部插入，从队列头部取出元素；但是LinkedBlockingDeque既可以从尾部插入/取出元素，还可以从头部插入元素/取出元素。

>	PriorityBlockingQueue

	按照优先级进行内部元素排序的无限队列。存放在PriorityBlockingQueue中的元素必须实现Comparable接口，这样才能通过实现compareTo()方法进行排序。优先级最高的元素将始终排在队列的头部；PriorityBlockingQueue不会保证优先级一样的元素的排序，也不保证当前队列中除了优先级最高的元素以外的元素，随时处于正确排序的位置。

>	LinkedTransferQueue

