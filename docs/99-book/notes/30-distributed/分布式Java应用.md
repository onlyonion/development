《分布式Java应用 基础与实践》林昊 电子工业出版社

介绍分布式Java应用设计的知识
* 基于Java实现网络通信、RPC
* 基于SOA实现大型分布式网络应用
* 编写高性能Java应用  寻找瓶颈、性能优化
* 构建高可用、可伸缩的系统

## 第1章 分布式java应用 
* 网络通信
  * 协议（TCP/IP、UDP/IP、Multicast）
  * IO（BIO、NIO-Reactor、AIO-Proactor）
* 消息方式的系统间通信
* 远程调用方式的系统间通信
  * 基于Java包 RMI、Webservice
  * 基于开源框架 SpringRMI、ApacheCXF
  * 性能角度 反射、序列化、反序列化

大型应用通常会**拆分**为多个子系统来实现，对于Java来说，这些子系统可能部署在同一台机器的多个不同的JVM中，也可能部署在不同的机器上，
但这些子系统又不是完全独立的，要相互通信来共同实现业务功能，对于此类的Java应用，我们称之为分布式Java应用。

!> NIO是基于事件驱动思想的，实现上通常采用Reactor模式，从程序角度而言，当发起IO的读或写操作时，是非阻塞的；当Socket有流可读或可写如Socket时，
操作系统会相应地通知应用程序进行处理，应用再将流读取到缓冲区或写入操作系统。
对于网络IO而言，主要有连接建立、流读取及流写入三种事件，Linux2.6以后的版本采用epoll方式来实现NIO。

!> AIO为异步IO方式，同样基于事件驱动思想，实现上通常采用Proactor模式。从程序角度而言，和NIO不同，当进行读写操作时，
只需调用API的read或write方法即可。这两种方式均为异步的。对于读操作而言，当有流可读取时，操作系统会将可读的流传入read方法的缓冲区，
并通知应用程序；对于写操作而言，当操作系统将write方法传递的流写入完毕时，操作系统会主动通知应用程序。

较之NIO而言，AIO一方面简化了程序的编写，流的读取和写入都又操作系统来代替完成；另一方面省去了NIO中程序要遍历事件通知队列（selector）的代价。
Windows基于IOCP实现AIO，Linux目前只有epoll模拟实现AIO。

### 1.1 基于消息方式实现系统间的通信
* 基于Java自身技术实现消息方式的系统间通信
  * TCP/IP + BIO
  * TCP/IP + NIO
  * TCP/IP + AIO
  * UDP/IP + NIO
* 基于开源框架实现消息方式的系统间通信
  * Mina

### 1.2 基于远程调用方式实现系统间的通信 
当系统之间需要通信时，可通过调用本地的一个Java**接口**的方法，透明地调用远程的Java**实现**。具体的细节则是由Java或框架来完成，
这种方式在Java中主要用来实现基于RMI和WebService的应用。

* 基于java自身技术实现远程调用方式的系统间通信
  * RMI
  * WebServie
* 基于开源框架实现远程调用方式的系统间通信 
  * Spring RMI
  * CXF

## 第2章 大型分布式java应用与soa 
### 2.1 基于sca实现soa平台 
### 2.2 基于esb实现soa平台 
### 2.3 基于tuscany实现soa平台 
### 2.4 基于mule实现soa平台 

## 第3章 深入理解jvm
* Java代码的执行 代码编译class、装载class、执行class
* 内存管理 内存空间、内存分配、内存回收、内存状况分析
* 线程资源同步和交互机制 线程资源同步、线程交互机制（Object.wait/notify、并发包semaphore/countdownLatch）、线程状态及分析方法

### 3.1 Java代码的执行机制
#### 3.1.1 Java源码编译机制 
#### 3.1.2 类加载机制 
#### 3.1.3 类执行机制
字节码解释执行

编译执行
为了提升代码的执行性能，提供将字节码编译为机器码的支持，JIT编译器。
Client compiler又称为C1，主要优化手段：方法内联、去虚拟化、冗余消除等。

Server compiler又称为C2，适合于服务端应用，传统的图着色寄存器分配算法；由于C2会收集程序的运行信息，
因此优化的范围更多在于全局的优化，而不仅仅是一个方法块的优化。
逃逸分析是C2进行优化的基础，基于逃逸分析的C2在编译时会做标量替换、栈上分配、同步削除等优化。

### 3.2 JVM内存管理
#### 3.2.1 内存空间
#### 3.2.2 内存分配
堆上分配内存时需要加锁。
每个新创建的线程在新生代的EdenSpace上分配一块独立的空间，称为TLAB，默认新生代1%。在TLAB分配不需要加锁。
#### 3.2.3 内存回收
#### 3.2.4 JVM内存状况查看方法和分析工具
1. 输出GC日志
2. GC Portal
3. jconsole
4. jvisualvm
5. jmap
6. jhat 自带的young与分析jvm堆dump文件的工具
7. jstat 分析gc、编译状况、class加载状况
8. Eclipse Memory Analyzer

### 3.3 JVM线程资源同步及交互机制 
#### 3.3.1 线程资源同步机制
有了synchronized、lock/unlock及volatile后，在Java中就可以很好地控制多线程中资源的竞争。
#### 3.3.2 线程交互机制
#### 3.3.3 线程状态及分析
```shell
# linux
kill -3 pid
jstack

```
* ThreadXMBean
* TDA 开源分析线程堆栈信息的图形化工具
* top命令 + ThreadDump 分析线程消耗CPU的状况，包括消耗CPU总时间及实时CPU小号比率
* JProfiler JProfiler是由ej-technologies GmbH公司开发的一款性能瓶颈分析工具


## 第4章 分布式应用与sun jdk类库
### 4.1 集合包 
* List
  * ArrayList
  * LinkedList
  * Vector
  * Stack
* Set
  * HashSet
  * TreeSet
* Map
  * HashMap
  * TreeMap

### 4.2 并发包（java.util.concurrent） 
* collection
  * ConcurrentHashMap
  * CopyOnWriteArrayList
  * CopyOnWriteArraySet
  * ArrayBlockingQueue
* AtomicInteger
* ThreadPoolExecutor
* Executors
* tool
  * Semaphore
  * CountDownLatch
  * CyclicBarrier
  * ReentrantLock
  * Condition
  * ReentranReadWriteLock

### 4.3 序列化/反序列化 
ObjectOutputStream

ObjectInputStream

## 第5章 性能调优 
* 衡量系统现状 先粗粒度、后细粒度
* 设定调优目标
* 寻找性能瓶颈
  * 寻组消耗资源的代码 CPU、文件IO、内存、网络IO
* 性能调优
  * 系统资源消耗过多的优化
    * CPU us（退出释放CPU、wait/nofity机制、JVM和内存的优化）、sy（减低锁的竞争、coroutine）
    * 文件IO 异步、限流、限制文件大小
    * 内存 GC调优、JVM堆的代大小调优、释放不需要应用的对象、对象缓存池
  * 资源消耗不多、仍然执行慢 降低锁的竞争（lock-free、拆分锁、CopyOnWrite）、充分使用硬件（充分使用多个CPU、内存）

### 5.1 寻找性能瓶颈 
#### 5.1.1 cpu消耗分析
* 上下文切换 对于Java应用，文件IO操作、网络IO操作、锁等待或线程sleep时，当前线程会进入阻塞或休眠状态，触发上下文切换。
* 运行队列 每个CPU核都维护了一个可运行的队列。
* 利用率
  * top 所占比例：us用户进程 sy内核进程 ni id空闲时间 wa等待 hi硬件中断 si软件中断
    * shift+h 1按核显示
  * pidstat 是sysstat中的工具

Java应用而言，CPU消耗严重主要体现在us、sy两个值上。
* us us高，表示运行的应用消耗了大部分CPU。频繁GC、运算多
  * kill -3 javapid
  * jstack -l javapid
* sy sy高，表示Linux花费更多时间在进行进程切换，Java应用的原因启动的线程比较多，且这些线程不断的阻塞和执行状态变化。
  * kill -3 jstack 查看线程状态和锁信息
  * vmstat 查看CPU的cs

#### 5.1.2 文件io消耗分析
跟踪线程的文件IO消耗，主要方法是通过pidstat来查找。

```shell
pidstat -d -t -p [pid]
iostat
```
#### 5.1.3 网络io消耗分析 
分布式Java应用，网络IO的消耗值得关注，尤其是注意网卡中断是不是均衡地分配到各CPU（cat /proc/interrupts）
```shell
# sar分析网络IO的消耗情况
sar -n FULL 1 2 # 1秒为频率，总共输出两次网络IO的消耗情况
# tcpdump跟踪tcp/ip通信过程的信息
```
#### 5.1.4 内存消耗分析 
Java应用对于内存的消耗主要在JVM堆内存上，在正式环境中，多数Java应用都会将-Xms和-Xmx设置为相同的值，避免运行期要不断申请内存。
目前Java应用只有在创建线程和使用DirectByteBuffer时才会操作JVM堆外内存。

在Linux中可通过vmstat、sar、top、pidstat等方式查看swap和物理内存的消耗情况。
* vmstat memory下的swpd free buff cache 以及swap下的si so
  * swpd指虚拟内存已使用的部分，值过高通常由于物理内存不够用了。swapIO频繁影响性能
* sar sar -r 
* top 可查看进程所消耗的内存量。vmstat和sar不能分析进程所占用的内存量
* pidstat pidstat -r -p 2013 1 100

Java应用对物理内存的消耗和对堆内存的消耗
* 物理内存 基于DirectByteBuffer实现对物理内存的直接操作，无须耗费JVM heap区。分析线程数量、DirectByteBuffer的使用。
* 堆内存 内存消耗多、GC频繁、OOM。结合JDK工具或外部工具分析具体对象的占用状况。

#### 5.1.5 程序执行慢原因分析
* 锁竞争激烈 数据库连接池，10个连接，50个线程竞争
* 未充分使用硬件资源 多核CPU都是单线程的操作
* 数据量增长 单表100万到1亿之后，读写速度下降，操作此表的Java程序速度下降

### 5.2 调优 
#### 5.2.1 jvm调优
主要是内存管理方面的调优，包括各个代的带小、GC策略等。
* 代大小的调优
* GC策略调优
* JVM调优案例 减少FullGC的次数

#### 5.2.2 程序调优 
#### 5.2.3 对于资源消耗不多，但程序执行慢的情况
* 锁竞争激烈 线程多了竞争
  * 使用并发包中的类
  * 使用Treiber算法 实现Stack，基于CAS以及AtomicReference
  * 使用Michael-Scott非阻塞队列算法 基于CAS以及AtomicReference
  * 尽可能少用锁
  * 拆分锁 读写锁拆分、ConcurrentHashMap分段锁16把
  * 去除读写操作的互斥锁 CopyOnWrite 修改时加锁，并复制对象进行修改，修改完毕之后切换对象的应用，读取时不加锁。缺点耗内存。
* 未充分使用硬件资源
  * 未充分使用CPU 线程太少
  * 未充分使用内存 数据的缓存、耗时资源的缓存

## 第6章 构建高可用的系统 
* 避免单点问题
  * 负载均衡技术 选址、failover、响应返回方式、硬件负载、软件负载VRRP、去中心化实现负载Gossip
  * 热备 LinuxHA
  * 使用多机房 多机房状态的一致性、机房隔离、机房切换
* 提高应用的可用性
  * 尽可能避免故障 明确使用场景、设计可容错的系统、设计具备自我保护能力的系统、限制使用资源
  * 及时发现故障 报警系统、日志记录和分析系统
  * 访问量和数据量不断上涨的应对策略 水平伸缩、拆分、读写分离、其他

### 6.1 避免系统中出现单点
解决单点现象最常见的方法是将系统部署到多台机器上，每台机器都对外提供同样的功能，通常将这种系统环境称为集群。
#### 6.1.1 负载均衡技术 
#### 6.1.2 热备
热备的情况下正真对外服务的机器只有一台，其他机器处于standby状态。standby机器通过心跳机制检查对外服务器的健康状态。
当出现问题时，其中一台standy机器即进行接管，机器间的状态同步至其他standby机器或写入一个集中式存储设备。

使用多机房

### 6.2 提高应用自身的可用性 
#### 6.2.1 尽可能地避免故障 
设计可容错的系统，意识FailFast原则，而是保证接口和对象设计的严谨。

设计具备自我保护能力的系统

限制使用资源

#### 6.2.2 及时发现故障 
报警系统

日志记录和分析系统

#### 6.2.3 及时处理故障 
1. 自动修复
2. 执行风险点应对措施
3. 全局资源调整
4. 功能降级
5. 降低对资源的使用量

#### 6.2.4 访问量及数据量不断上涨的应对策略 
数据库连接数很容易达到瓶颈。
* 拆分数据库，通常按业务拆分
* 拆分表，通常对业务中的大表按照一定规则拆分，例如按照时间、hash算法、取模
* 读写分离 一个写库、多个读库

## 第7章 构建可伸缩的系统 
* 垂直伸缩
  * 支持高访问量 增加CPU、增加内存
  * 支持大数据量 分表
  * 提升计算能力 并行多线程计算
* 水平伸缩
  * 支持高访问量 SNA、有状态情况、伸缩带来的数据库问题（cache、分库、异步数据库访问、中间层的DAL）
  * 支持大数据量 读写分离、多master
  * 提升计算能力 MapReduce、MPI

### 7.1 垂直伸缩

### 7.2 水平伸缩

