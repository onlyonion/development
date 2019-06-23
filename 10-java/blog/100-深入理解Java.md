
[zejian的博客](https://blog.csdn.net/javazejian/article/details/72828483 )

## 深入理解Java类型信息(Class对象)与反射机制
## 深入理解Java枚举类型(enum)
## 深入理解Java注解类型(@Annotation)
## 深入理解Java类加载器(ClassLoader)
## [深入理解Java并发之synchronized实现原理](https://blog.csdn.net/javazejian/article/details/72828483)
* 三种应用方式：实例方法、静态方法、同步代码块
* 底层语义原理： Java虚拟机中的同步(Synchronization)基于进入和退出管程(Monitor)对象实现。
  - Java对象头与Monitor
  - 修饰方法底层原理 运行时常量池中方法的 ACC_SYNCHRONIZED
  - 修饰代码块底层原理 monitorenter 和 monitorexit 指令
  - Java对synchronized的优化（偏向锁、轻量级锁、自旋锁、锁消除）
* 关于synchronized 可能需要了解的关键点
  * 可重入性 当一个线程再次请求自己持有对象锁的临界资源时，这种情况属于重入锁，请求将会成功
  * 线程中断
  * 中断与synchronized
  * 等待唤醒机制与synchronized

## Java并发编程-无锁CAS与Unsafe类及其并发包Atomic
## [深入理解Java内存模型(JMM)及volatile关键字理解Java内存区域与Java内存模型](https://blog.csdn.net/javazejian/article/details/72772461 )
* Java内存区域：Java内存模型概述
* 硬件内存架构与Java内存模型
  * 硬件内存架构
  * Java线程与硬件处理器
  * Java内存模型与硬件内存架构的关系
* JMM存在的必要性
* Java内存模型的承诺：
  * 原子性
  * 理解指令重排（编译器重排、处理器指令重排）
  * 可见性、有序性
  * JMM提供的解决方案
  * 理解JMM中的happens-before 原则
* volatile内存语义
  * volatile的可见性
  * volatile禁止重排优化


## 剖析基于并发AQS的重入锁(ReetrantLock)及其Condition实现原理
## 剖析基于并发AQS的共享锁的实现(基于信号量Semaphore)
## 并发之阻塞队列LinkedBlockingQueue与ArrayBlockingQueue