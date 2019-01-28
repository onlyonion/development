 《Java并发编程的艺术》 放腾飞 魏鹏 程晓明 机械工业出版社

## 第1章 并发编程的挑战
### 1.1 上下文切换
### 1.2 死锁
### 1.3 资源限制的挑战

## 第2章 Java并发机制的底层实现原理
### 2.1 volatile
### 2.2 synchronized的实现原理与应用
### 2.3 原子操作的实现原理
1. 术语定义
2. 处理器如何实现原子操作
   1. 使用总线锁
   2. 通过缓存锁定
3. Java如何实现原子操作
   1. 循环CAS cmpxchg 
      1. ABA问题（AtomicStampedReference）
      2. 循环时间长开销大（pause指令）
      3. 只能保证一个共享变量的原子操作（AtomicReference多个变量放在一个对象里）
   2. 锁机制 偏向锁、轻量级锁、互斥锁

## 第3章 Java内存模型
### 3.1 Java内存模型的基础
### 3.2 重排序
### 3.3 顺序一致性
### 3.4 volatile的内存语义
### 3.5 锁的内存语义
### 3.6 final域的内存语义
### 3.7 happens-before
### 3.8 双重检查锁定与延迟初始化
### 3.9 Java内存模型综述

## 第4章 Java并发编程基础
### 4.1 线程简介
### 4.2 启动和终止线程
### 4.3 线程间通信
### 4.4 线程应用实例

## 第5章 Java中的锁
### 5.1 Lock接口
### 5.2 队列同步器
### 5.3 重入锁
### 5.4 读写锁
### 5.5 LockSupport工具
### 5.6 Condition接口

## 第6章 Java并发容器和框架
### 6.1 ConcurrentHashMap的实现原理与使用
### 6.2 ConcurrentLinkedQueue
### 6.3 Java中的阻塞队列
### 6.4 Fork/Join框架

## 第7章 Java中的13个原子操作类

## 第8章 Java中的并发工具类
### 8.1 等待多线程完成的CountDownLatch
### 8.2 同步屏障CyclicBrrier
### 8.3 控制并发线程数的Semaphore
### 8.4 线程间交换数据的Exchanger

## 第9章 Java中的线程池
### 9.1 线程池的实现原理
### 9.2 线程池的使用

## 第10章 Executor框架
### 10.1 Executor框架简介
### 10.2 ThreadPoolExecutor详解
### 10.3 ScheduledThreadPoolExecutor详解
### 10.4 FutureTask详解

## 第11章 Java并发编程实践
