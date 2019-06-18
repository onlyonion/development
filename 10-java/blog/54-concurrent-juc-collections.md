## 并发容器

## 1. ConcurrentHashMap
* volatile
* ReetrantLok
* hash()算法	
* 数组		散列算法、按位与确定索引
* 单向链表
* 红黑树	O(n) -> O(logN)

### 1.1 类图
![ConcurrentHashMap](../img/java-collections-ConcurrentHashMap-class.jpg) 

### 1.2 结构图
![ConcurrentHashMap](../img/java-collections-ConcurrentHashMap.jpg) 

### 1.3 Hashtable ConcurrentHashMap
Hashtable，synchronized是针对整张Hash表的，即每次锁住整张表让线程独占，
ConcurrentHashMap允许多个修改操作并发进行，使用锁分离技术

多个锁来控制对hash表的不同部分进行的修改。ConcurrentHashMap内部使用段(Segment)来表示这些不同的部分，每个段其实就是一个小的hash table，它们有自己的锁
有些方法需要跨段，比如size()和containsValue()，它们可能需要锁定整个表而而不仅仅是某个段，这需要**按顺序**锁定所有段，操作完毕后，又按顺序释放所有段的锁

ConcurrentHashMap和Hashtable主要区别就是围绕着锁的粒度以及如何锁,可以简单理解成把一个大的HashTable分解成多个，形成了锁分离。
而Hashtable的实现方式是---锁整个hash表

## 2. ThreadLocal

解决多线程中相同变量的访问冲突问题

- 以时间换空间        在同步机制中，通过对象的锁机制保证同一时间只有一个线程访问变量。该变量是多个线程共享的
- 以空间换时间        为每一个线程提供一个独立的变量副本，从而隔离了多个线程对数据的访问冲突

ThreadLocalMap存储的键值对中的键是this对象指向的ThreadLocal对象，而值就是你所设置的对象了


## 3. BlockingQueue
阻塞队列，支持阻塞的插入和移除方法。put(e), take(), offer(e, time, unit), poll(time, unit)

### 有限队列
SynchronousQueue
ArrayBlockingQueue 一个由数组支持的有界阻塞队列

### 无限队列
* LinkedBlockingQueue

基于链表结构。如果指定了LinkedBlockingQueue的容量大小，那么它反映出来的使用特性就和ArrayBlockingQueue类似了。

* LinkedBlockingDeque

基于链表的双端队列。LinkedBlockingQueue的内部结构决定了它只能从队列尾部插入，从队列头部取出元素；但是LinkedBlockingDeque既可以从尾部插入/取出元素，还可以从头部插入元素/取出元素。

* PriorityBlockingQueue

按照优先级进行内部元素排序的无限队列。
存放在PriorityBlockingQueue中的元素必须实现Comparable接口，这样才能通过实现compareTo()方法进行排序。

优先级最高的元素将始终排在队列的头部；
PriorityBlockingQueue不会保证优先级一样的元素的排序，也不保证当前队列中除了优先级最高的元素以外的元素，随时处于正确排序的位置。

它的迭代器并不保证队列保持任何特定的顺序，如果想要顺序遍历，考虑使用Arrays.sort(pq.toArray())

* DelayQueue

其存储延时的元素，只有延时耗尽元素才能被取出。队列头元素就是最先耗尽延时的元素，如果没有元素耗尽延时，poll操作会返回null。同样的，该队列不允许空元素。
Delayed接口又是实现Comparable接口，注意该优先队列就是使用这个方法进行对比的，所以Comparable的实现要借助Delayed接口的方法选出剩余延时小，才能保证使用正确

[importnew博文](http://www.importnew.com/15731.html) 

### 方法
| method  | desc                     | return                                             |
| :------ | :----------------------- | :------------------------------------------------- |
| add     | 增加一个元索             | 如果队列已满，则抛出一个IIIegaISlabEepeplian异常   |
| remove  | 移除并返回队列头部的元素 | 如果队列为空，则抛出一个NoSuchElementException异常 |
| element | 返回队列头部的元素       | 如果队列为空，则抛出一个NoSuchElementException异常 |
| offer   | 添加一个元素并返回true   | 如果队列已满，则返回false                          |
| poll    | 移除并返问队列头部的元素 | 如果队列为空，则返回null                           |
| peek    | 返回队列头部的元素       | 如果队列为空，则返回null                           |
| put     | 添加一个元素             | 如果队列满，则阻塞                                 |
| take    | 移除并返回队列头部的元素 | 如果队列为空，则阻塞                               |

### 1. 没有实现的阻塞接口的LinkedList： 实现了java.util.Queue接口和java.util.AbstractQueue接口
内置的不阻塞队列： PriorityQueue 和 ConcurrentLinkedQueue
*	PriorityQueue 和 ConcurrentLinkedQueue 类在 Collection Framework 中加入两个具体集合实现。 
	PriorityQueue 类实质上维护了一个有序列表。加入到 Queue 中的元素根据它们的天然排序（通过其 java.util.Comparable 实现）或者根据传递给构造函数的 java.util.Comparator 实现来定位。
*	ConcurrentLinkedQueue 是基于链接节点的、线程安全的队列。并发访问不需要同步。因为它在队列的尾部添加元素并从头部删除它们，所以只要不需要知道队列的大 小，ConcurrentLinkedQueue 对公共集合的共享访问就可以工作得很好。收集关于队列大小的信息会很慢，需要遍历队列。


### 2. 实现阻塞接口的：
java.util.concurrent 中加入了 BlockingQueue 接口和五个阻塞队列类。它实质上就是一种带有一点扭曲的 FIFO 数据结构。不是立即从队列中添加或者删除元素，线程执行操作阻塞，直到有空间或者元素可用。
五个队列所提供的各有不同：
*	ArrayBlockingQueue ：一个由数组支持的有界队列。
*	LinkedBlockingQueue ：一个由链接节点支持的可选有界队列。
*	PriorityBlockingQueue ：一个由优先级堆支持的无界优先级队列。
*	DelayQueue ：一个由优先级堆支持的、基于时间的调度队列。
*	SynchronousQueue ：一个利用 BlockingQueue 接口的简单聚集（rendezvous）机制。

### SynchronousQueue
特别之处在于它内部没有容器，一个生产线程，当它生产产品（即put的时候），如果当前没有人想要消费产品(即当前没有线程执行take)，此生产线程必须阻塞，等待一个消费线程调用take操作，take操作将会唤醒该生产线程，同时消费线程会获取生产线程的产品（即数据传递），这样的一个过程称为一次配对过程(当然也可以先take后put,原理是一样的)


### [Java并发容器](https://www.cnblogs.com/ygj0930/p/6543901.html)

ConcurrentSkipListMap的底层是通过跳表来实现的。跳表是一个链表，但是通过使用“跳跃式”查找的方式使得插入、读取数据时复杂度变成了O（logn）。
跳表（SkipList）：使用“空间换时间”的算法，令链表的每个结点不仅记录next结点位置，还可以按照level层级分别记录后继第level个结点。

ConcurrentSkipListMap线程安全的原理与非阻塞队列ConcurrentBlockingQueue的原理一样：
利用底层的插入、删除的CAS原子性操作，通过死循环不断获取最新的结点指针来保证不会出现竞态条件。

此法使用的就是“先大步查找确定范围，再逐渐缩小迫近”的思想进行的查找。

ConcurrentHashMap采取了“锁分段”技术来细化锁的粒度：
把整个map划分为一系列被成为segment的组成单元，一个segment相当于一个小的hashtable。
这样，加锁的对象就从整个map变成了一个更小的范围——一个segment。

ConcurrentHashMap线程安全并且提高性能原因就在于：对map中的读是并发的，无需加锁；
只有在put、remove操作时才加锁，而加锁仅是对需要操作的segment加锁，不会影响其他segment的读写，
由此，不同的segment之间可以并发使用，极大地提高了性能。