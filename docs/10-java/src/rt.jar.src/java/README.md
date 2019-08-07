## java

### 核心类库
java.lang
  * annotation
  * instrument
  * [invoke](/docs/10-java/src/rt.jar.src/java/lang/invoke/README.md)
    * [MethodHandle](/docs/10-java/src/rt.jar.src/java/lang/invoke/MethodHandle.md)
  * management
  * [ref](/docs/10-java/src/rt.jar.src/java/lang/ref/README.md)
    * [Reference](/docs/10-java/src/rt.jar.src/java/lang/ref/Reference.md) 对象引用 强、软、弱、虚
    * [Finalizer](/docs/10-java/src/rt.jar.src/java/lang/ref/Finalizer.md) 终结器
  * reflect
    * [Proxy](/docs/10-java/src/rt.jar.src/java/lang/reflect/Proxy.md) 
  * [String](/docs/10-java/src/rt.jar.src/java/lang/String.md)
  * [Thread](/docs/10-java/src/rt.jar.src/java/lang/Thread.md) 线程
  * [ThreadLocal](/docs/10-java/src/rt.jar.src/java/lang/ThreadLocal.md) 线程本地变量
  * [Throwable](/docs/10-java/src/rt.jar.src/java/lang/Throwable.md) 错误、异常
    * error
      * [VirtualMachineError](/docs/10-java/src/rt.jar.src/java/lang/throwable.error.VirtualMachineError.md)
      * [LinkageError](/docs/10-java/src/rt.jar.src/java/lang/throwable.error.LinkageError.md)
  * [ClassLoader](/docs/10-java/src/rt.jar.src/java/lang/ClassLoader.md) 类加载器、双亲委派
  * [Iterable](/docs/10-java/src/rt.jar.src/java/lang/Iterable.md)

### 集合框架
java.util
  * list
    * [ArrayList](/docs/10-java/src/rt.jar.src/java/util/collections/list.ArrayList.md)
    * [LinkedList](/docs/10-java/src/rt.jar.src/java/util/collections/list.LinkedList.md)
  * set
    * [HashSet](/docs/10-java/src/rt.jar.src/java/util/collections/set.HashSet.md)
    * [TreeSet](/docs/10-java/src/rt.jar.src/java/util/collections/set.TreeSet.md)
  * queue
    * [Queue](/docs/10-java/src/rt.jar.src/java/util/collections/queue.Queue.md)
    * [Deque](/docs/10-java/src/rt.jar.src/java/util/collections/queue.Deque.md) 双端队列
    * [PriorityQueue](/docs/10-java/src/rt.jar.src/java/util/collections/queue.PriorityQueue.md) 优先级队列
  * stack
    * [Stack](/docs/10-java/src/rt.jar.src/java/util/collections/stack.Stack.md)
  * map
    * [HashMap](/docs/10-java/src/rt.jar.src/java/util/collections/map.HashMap.md) 数组、链表、红黑树
    * [HashTable](/docs/10-java/src/rt.jar.src/java/util/collections/map.HashTable.md)
    * [LinkedHashMap](/docs/10-java/src/rt.jar.src/java/util/collections/map.LinkedHashMap.md)
    * [TreeMap](/docs/10-java/src/rt.jar.src/java/util/collections/map.TreeMap.md)
  * 工具类
    * [Arrays](/docs/10-java/src/rt.jar.src/java/util/collections/Arrays.md)  
    * [Collections](/docs/10-java/src/rt.jar.src/java/util/collections/Collections.md)  
    * [DualPivotQuicksort](/docs/10-java/src/rt.jar.src/java/util/collections/sort.DualPivotQuicksort.md)
    * [TimSort](/docs/10-java/src/rt.jar.src/java/util/collections/sort.TimSort.md)

### 并发编程
java.util.concurrent
* [并发编程](/docs/10-java/src/rt.jar.src/java/util/concurrent/README.md) 线程、缓存、队列、异步、限流
  * [atomic](/docs/10-java/src/rt.jar.src/java/util/concurrent/atomic/README.md) 原子操作
    * number
      * [AtomicInteger](/docs/10-java/src/rt.jar.src/java/util/concurrent/atomic/AtomicInteger.md) 计数器，只能更新一个变量
      * AtomicLong
      * AtomicBoolean
    * array
      * AtomicIntegerArray
      * AtomicLongArray
      * AtomicReferenceArray
    * adder
      * [Striped64](/docs/10-java/src/rt.jar.src/java/util/concurrent/atomic/Striped64.md)
      * [LongAccumulator](/docs/10-java/src/rt.jar.src/java/util/concurrent/atomic/LongAccumulator.md)
      * [LongAdder](/docs/10-java/src/rt.jar.src/java/util/concurrent/atomic/LongAdder.md)
    * reference
      * [AtomicReference](/docs/10-java/src/rt.jar.src/java/util/concurrent/atomic/AtomicReference.md) 更新多个原子变量
      * AtomicIntegerFieldUpdater
      * [AtomicLongFieldUpdater](/docs/10-java/src/rt.jar.src/java/util/concurrent/atomic/AtomicLongFieldUpdater.md) volatile字段 + newUpdater(Clazz.class, "filedName"")
      * [AtomicReferenceFieldUpdater](/docs/10-java/src/rt.jar.src/java/util/concurrent/atomic/AtomicReferenceFieldUpdater.md)
    * ABA
      * [AtomicStampedReference](/docs/10-java/src/rt.jar.src/java/util/concurrent/atomic/AtomicStampedReference.md)
      * [AtomicMarkableReference](/docs/10-java/src/rt.jar.src/java/util/concurrent/atomic/AtomicMarkableReference.md)
  * [locks](/docs/10-java/src/rt.jar.src/java/util/concurrent/locks/README.md) 锁
    * [AbstractQueuedSynchronizer](/docs/10-java/src/rt.jar.src/java/util/concurrent/locks/AbstractQueuedSynchronizer.md) AQS CLH
    * [ReentrantLock](/docs/10-java/src/rt.jar.src/java/util/concurrent/locks/ReentrantLock.md)
    * [ReadWriteLock](/docs/10-java/src/rt.jar.src/java/util/concurrent/locks/ReadWriteLock.md)
    * [StampedLock](/docs/10-java/src/rt.jar.src/java/util/concurrent/locks/StampedLock.md)
  * 并发容器
    * list
      * [CopyOnWriteArrayList](/docs/10-java/src/rt.jar.src/java/util/concurrent/list.CopyOnWriteArrayList.md)
    * set
      * [CopyOnWriteArraySet](/docs/10-java/src/rt.jar.src/java/util/concurrent/set.CopyOnWriteArraySet.md)
      * [ConcurrentSkipListSet](/docs/10-java/src/rt.jar.src/java/util/concurrent/set.ConcurrentSkipListSet.md)
    * queue
      * [ConcurrentLinkedQueue](/docs/10-java/src/rt.jar.src/java/util/concurrent/queue.ConcurrentLinkedQueue.md)
      * [LinkedTransferQueue](/docs/10-java/src/rt.jar.src/java/util/concurrent/queue.LinkedTransferQueue.md)
      * [BlockingQueue](/docs/10-java/src/rt.jar.src/java/util/concurrent/queue.BlockingQueue.md) 阻塞队列
      * [LinkedBlockingQueue](/docs/10-java/src/rt.jar.src/java/util/concurrent/queue.LinkedBlockingQueue.md) 两把锁，两个条件
      * [LinkedBlockingDeque](/docs/10-java/src/rt.jar.src/java/util/concurrent/queue.LinkedBlockingDeque.md) 双端队列，一把锁，两个条件
      * [SynchronousQueue](/docs/10-java/src/rt.jar.src/java/util/concurrent/queue.SynchronousQueue.md)
      * [DelayQueue](/docs/10-java/src/rt.jar.src/java/util/concurrent/queue.DelayQueue.md)
      * [TransferQueue](/docs/10-java/src/rt.jar.src/java/util/concurrent/queue.TransferQueue.md)
    * map
      * [ConcurrentHashMap](/docs/10-java/src/rt.jar.src/java/util/concurrent/map.ConcurrentHashMap.md) 分段锁、1.8中的CAS自旋锁、红黑树
      * [ConcurrentSkipListMap](/docs/10-java/src/rt.jar.src/java/util/concurrent/map.ConcurrentSkipListMap.md)
  * 并发工具
    * [CountDownLatch](/docs/10-java/src/rt.jar.src/java/util/concurrent/tool.CountDownLatch.md) 一个或多个线程等待其他线程完成操作
    * [CyclicBarrier](/docs/10-java/src/rt.jar.src/java/util/concurrent/tool.CyclicBarrier.md) 一组线程到达一个屏障时阻塞，直到最后一个线程到达，才继续
    * [Semaphore](/docs/10-java/src/rt.jar.src/java/util/concurrent/tool.Semaphore.md) 控制并发特定资源的线程数
    * [Exchanger](/docs/10-java/src/rt.jar.src/java/util/concurrent/tool.Exchanger.md) 数据交换
  * 线程池
    * [ThreadPoolExecutor](/docs/10-java/src/rt.jar.src/java/util/concurrent/executor.ThreadPoolExecutor.md)
    * [ScheduledThreadPoolExecutor](/docs/10-java/src/rt.jar.src/java/util/concurrent/executor.ScheduledThreadPoolExecutor.md)
    * [ForkJoinPool](/docs/10-java/src/rt.jar.src/java/util/concurrent/ForkJoinPool.md)
    * [Executors](/docs/10-java/src/rt.jar.src/java/util/concurrent/executor.Executors.md)
  * 异步编程  
    * [Future](/docs/10-java/src/rt.jar.src/java/util/concurrent/Future.md)
    * [FutureTask](/docs/10-java/src/rt.jar.src/java/util/concurrent/FutureTask.md)
    * [CompletionStage](/docs/10-java/src/rt.jar.src/java/util/concurrent/CompletionStage.md)
    * [CompletableFuture](/docs/10-java/src/rt.jar.src/java/util/concurrent/CompletableFuture.md)

### 输入输出
java.io
  * [bio](/docs/10-java/src/rt.jar.src/java/io/README.md)
    * [InputStream](/docs/10-java/src/rt.jar.src/java/io/InputStream.md)
    * [OutputStream](/docs/10-java/src/rt.jar.src/java/io/OutputStream.md)
    * [Reader](/docs/10-java/src/rt.jar.src/java/io/Reader.md)
    * [Writer](/docs/10-java/src/rt.jar.src/java/io/Writer.md)
    * [File](/docs/10-java/src/rt.jar.src/java/io/File.md)
    * [FileDescriptor](/docs/10-java/src/rt.jar.src/java/io/FileDescriptor.md)
  * [nio](/docs/10-java/src/rt.jar.src/java/nio/README.md)
    * [channels](/docs/10-java/src/rt.jar.src/java/nio/channels/README.md)
      * [Channel](/docs/10-java/src/rt.jar.src/java/nio/channels/Channel.md) 对BIO流的模拟，双向的。比流更好地反映底层操作系统的真实情况。
      * [Selector](/docs/10-java/src/rt.jar.src/java/nio/channels/Selector.md) 多路复用器，同时检测多个通道的事件
      * [SelectionKey](/docs/10-java/src/rt.jar.src/java/nio/channels/SelectionKey.md)
      * [ServerSocketChannel](/docs/10-java/src/rt.jar.src/java/nio/channels/ServerSocketChannel.md)
      * aio/nio2
        * [AsynchronousChannel](/docs/10-java/src/rt.jar.src/java/nio/channels/aio.AsynchronousChannel.md) 异步通道
        * [AsynchronousChannelGroup](/docs/10-java/src/rt.jar.src/java/nio/channels/aio.AsynchronousChannelGroup.md) 异步通道组，没给异步通道属于一个异步通道组，同一个通道组内的通道共享一个线程池。
        * [AsynchronousServerSocketChannel](/docs/10-java/src/rt.jar.src/java/nio/channels/aio.AsynchronousServerSocketChannel.md)
        * [AsynchronousSocketChannel](/docs/10-java/src/rt.jar.src/java/nio/channels/aio.AsynchronousSocketChannel.md)
        * [AsynchronousFileChannel](/docs/10-java/src/rt.jar.src/java/nio/channels/aio.AsynchronousFileChannel.md)
        * [CompletionHandler](/docs/10-java/src/rt.jar.src/java/nio/channels/aio.CompletionHandler.md) 两种操作方式，Future与CompletionHandler回调
    * [file](/docs/10-java/src/rt.jar.src/java/nio/file/README.md)
      * Files
      * FileSystem
      * FileVisitor
      * Path
    * [Bits](/docs/10-java/src/rt.jar.src/java/nio/Bits.md)
    * [Buffer](/docs/10-java/src/rt.jar.src/java/nio/Buffer.md) 缓冲区，容器对象，通常是一个字节数组。
    * [ByteBuffer](/docs/10-java/src/rt.jar.src/java/nio/ByteBuffer.md)
    * [DirectByteBuffer](/docs/10-java/src/rt.jar.src/java/nio/DirectByteBuffer.md)

### 网络编程
java.net
* [net](/docs/10-java/src/rt.jar.src/java/net/README.md) 网络编程
  * [Socket](/docs/10-java/src/rt.jar.src/java/net/Socket.md) 套接字，传输层接口
  * [ServerSocket](/docs/10-java/src/rt.jar.src/java/net/ServerSocket.md)
  * [SocketOptions](/docs/10-java/src/rt.jar.src/java/net/SocketOptions.md) 套接字配置
  * [InetAddress](/docs/10-java/src/rt.jar.src/java/net/InetAddress.md) 网络层接口
  * [URI](/docs/10-java/src/rt.jar.src/java/net/URI.md)
  * [URL](/docs/10-java/src/rt.jar.src/java/net/URL.md)

### 数据库编程
java.sql
* [sql](/docs/10-java/src/rt.jar.src/java/sql/README.md)
  * [Driver](/docs/10-java/src/rt.jar.src/java/sql/Driver.md) jdbc驱动接口，SPI
  * [DriverManager](/docs/10-java/src/rt.jar.src/java/sql/DriverManager.md) 驱动管理
  * [Connection](/docs/10-java/src/rt.jar.src/java/sql/Connection.md)
  * [PreparedStatement](/docs/10-java/src/rt.jar.src/java/sql/PreparedStatement.md)
  * [ResultSet](/docs/10-java/src/rt.jar.src/java/sql/ResultSet.md)
  * [Savepoint](/docs/10-java/src/rt.jar.src/java/sql/Savepoint.md)

### 远程方法调用
java.rmi
[rmi](/docs/10-java/src/rt.jar.src/java/rmi/README.md)

### 新特性
java.util
  * [function](/docs/10-java/src/rt.jar.src/java/util/function/README.md) 函数式编程
  * [stream](/docs/10-java/src/rt.jar.src/java/util/stream/README.md) 流式风格
  * observer
    * [Observer](/docs/10-java/src/rt.jar.src/java/util/Observer.md)
  * spi机制
    * [ServiceLoader](/docs/10-java/src/rt.jar.src/java/util/ServiceLoader.md) SPI服务提供者


## package
```
applet
awt
beans
io
lang
math
net
nio
rmi
security
sql
text
time
util
```

## book
* [《Java并发编程的艺术》 方腾飞](/docs/99-book/notes/10-java/Java并发编程的艺术.md) 阿里巴巴技术专家/Java并发编程领域领军人物撰写，从JDK源码、JVM、CPU多角度剖析并发编程原理和核心技术
* [《Java多线程编程核心技术》高洪岩 著 机械工业出版社](/docs/99-book/notes/10-java/Java多线程编程核心技术.md)
* [《Java 8函数式编程》王群锋 译](/docs/99-book/notes/10-java/Java%208函数式编程.md)
* [《Java 8实战 Java 8 in Action Lambdas, streams and functional-sytle programming》 陆明刚 劳佳 译](/docs/99-book/notes/10-java/Java%208实战.md)
