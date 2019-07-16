## 并发编程
* [java concurrent](/docs/10-java/src/rt.jar.src/java/README.md)
* 线程 thread 
  * 线程方法 start join sleep interrupt interrupted isInterrupted isAlive 
  * 线程属性 priority group daemon tid 
  * 线程状态 State NEW RUNNABLE BLOCKED WAITING TIMED_WAITING TERMINATED
* 基础
  * JMM master-worker hanpens-before
  * volatile synchronized final
  * Lock AQS
* 并发容器 ConccurrentHashMap CopyOnWriteArrayList
* 并发工具 CountdownLatch CyclicBarrier Semophore
* 原子操作 Atomic
* 线程池 executor框架 ThreadPoolExecutor

## 并发编程模型
多线程开发可以更好的发挥多核cpu性能，常用的多线程设计模式有：Future、Master-Worker、Guard Susperionsion、不变、生产者-消费者模式。
jdk除了定义了若干并发的数据结构，也内置了多线程框架和各种线程池；锁（分为内部锁、重入锁、读写锁）、ThreadLocal、信号量等在并发控制中发挥着巨大的作用。

### 生产者-消费者模式
也称作有界缓冲区（bounded-buffer）问题，两个进程共享一个公共的固定大小的缓冲区。
生产者-消费模式，通常有两类线程，即若干个生产者线程和若干个消费者线程。生产者线程负责提交用户请求，消费者线程负责具体处理生产者提交的任务。两者之间通过共享内存缓冲去进行通信。
生产-消费模式的核心组件是共享内存缓冲区，是两者的通信桥梁，起到解耦作用，优化系统整体结构。
由于缓冲区的存在，生产者和消费者，无论谁在某一局部时间内速度相对较高，都可以使用缓冲区得到缓解，保证系统正常运行，这在一定程度上缓解了性能瓶颈对系统系能的影响。

### MasterWorker模式
Master-Worker模式是常用的并行模式之一，它的核心思想是，系统有两个进程协作工作：Master进程，负责接收和分配任务；Worker进程，负责处理子任务。
当Worker进程将子任务处理完成后，结果返回给Master进程，由Master进程做归纳汇总，最后得到最终的结果。
一种将串行任务并行化的方案，被分解的子任务在系统中可以被并行处理，同时，如果有需要，Master进程不需要等待所有子任务都完成计算，就可以根据已有的部分结果集计算最终结果集。

### Future模式
将异步请求和代理模式联合的模型产物。
Future模式可以在连续流程中满足数据驱动的并发需求，既获得了并发执行的性能提升，又不失连续流程的简洁优雅。
Futrue模式有个重大缺陷：当消费者工作得不够快的时候，它会阻塞住生产者线程，从而可能导致系统吞吐量的下降。所以不建议在高性能的服务端使用。

### Fork/Join
Oracle的官方给出的定义是：Fork/Join框架是一个实现了ExecutorService接口的多线程处理器。它可以把一个大的任务划分为若干个小的任务并发执行，充分利用可用的资源，进而提高应用的执行效率。
1. ExecutorService接口的一个实现，可以帮助开发人员充分利用多核处理器的优势，编写出并行执行的程序，提高应用程序的性能；设计的目的是为了处理那些可以被递归拆分的任务。
2. 与其它ExecutorService的实现类相似，会给线程池中的线程分发任务，不同之处在于它使用了工作窃取算法，所谓工作窃取，指的是对那些处理完自身任务的线程，会从其它线程窃取任务执行。
3. 核心是ForkJoinPool类，该类继承了AbstractExecutorService类。ForkJoinPool实现了工作窃取算法并且能够执行 ForkJoinTask任务。

[如何更直观理解进程、线程、事务的概念](https://www.cnblogs.com/xinchrome/p/4872428.html)

[常用的线程模型](https://www.cnblogs.com/PerkinsZhu/p/7570775.html)
* future模型 结合Callable接口配合使用。
* fork&join模型 包含递归思想和回溯思想，递归用来拆分任务，回溯用合并结果。把一个大任务逐级拆分为多个子任务，然后分别在子线程中执行，当每个子线程执行结束之后逐级回溯，返回结果进行汇总合并，最终得出想要的结果。
* actor消息模型 一种基于消息传递机制并行任务处理思想，它以消息的形式来进行线程间数据传输，避免了全局变量的使用
* 生产者消费者模型 使用一个缓存来保存任务。任务的生成和处理分隔开。
* master-worker模型 类似于任务分发策略，开启一个master线程接收任务，然后在master中根据任务的具体情况进行分发给其它worker子线程，然后由子线程处理任务。


## 线程状态
[Java线程的6种状态及切换](https://blog.csdn.net/pange1991/article/details/53860651) 
1. 初始(NEW)：新创建了一个线程对象，但还没有调用start()方法。
2. 运行(RUNNABLE)：Java线程中将就绪（ready）和运行中（running）两种状态笼统的成为“运行”。
线程对象创建后，其他线程(比如main线程）调用了该对象的start()方法。该状态的线程位于可运行线程池中，等待被线程调度选中，获取cpu 的使用权，此时处于就绪状态（ready）。就绪状态的线程在获得cpu 时间片后变为运行中状态（running）。
3. 阻塞(BLOCKED)：表线程阻塞于锁。
4. 等待(WAITING)：进入该状态的线程需要等待其他线程做出一些特定动作（通知或中断）。
5. 超时等待(TIME_WAITING)：该状态不同于WAITING，它可以在指定的时间内自行返回。
6. 终止(TERMINATED)：表示该线程已经执行完毕。