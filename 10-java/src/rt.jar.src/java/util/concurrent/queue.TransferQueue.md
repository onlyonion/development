java.util.concurrent.TransferQueue

顾名思义，阻塞就是发生在元素从一个线程transfer到另一个线程的过程中，
它有效地实现了元素在线程之间的传递（以建立Java内存模型中的happens-before关系的方式）。

生产者会一直阻塞直到所添加到队列的元素被某一个消费者所消费（不仅仅是添加到队列里就完事）。
## hierarchy
```
Iterable (java.lang)
    Collection (java.util)
        Queue (java.util)
            BlockingQueue (java.util.concurrent)
                TransferQueue (java.util.concurrent)
                    LinkedTransferQueue (java.util.concurrent)
```
## define



## others

### LinkedTransferQueue
LinkedTransferQueue实际上是ConcurrentLinkedQueue、SynchronousQueue（公平模式）和LinkedBlockingQueue的超集。
而且LinkedTransferQueue更好用，因为它不仅仅综合了这几个类的功能，同时也提供了更高效的实现。

### 对比SynchronousQueue
SynchronousQueue使用两个队列（一个用于正在等待的生产者、另一个用于正在等待的消费者）和一个用来保护两个队列的锁。
而LinkedTransferQueue使用CAS操作实现一个非阻塞的方法，这是避免序列化处理任务的关键。