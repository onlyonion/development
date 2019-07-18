org.jboss.netty.channel.socket.nio.NioWorker

3.2.5

* volatile
* ReentrantReadWriteLock
* LinkedTransferQueue

## fields
```java
private final int bossId;
private final int id;
private final Executor executor;
private boolean started;
private volatile Thread thread;
volatile Selector selector;
private final AtomicBoolean wakenUp = new AtomicBoolean();
private final ReadWriteLock selectorGuard = new ReentrantReadWriteLock();
private final Object startStopLock = new Object();
private final Queue<Runnable> registerTaskQueue = new LinkedTransferQueue<Runnable>();
private final Queue<Runnable> writeTaskQueue = new LinkedTransferQueue<Runnable>();
private volatile int cancelledKeys; // should use AtomicInteger but we just need approximation

private final SocketReceiveBufferPool recvBufferPool = new SocketReceiveBufferPool();
private final SocketSendBufferPool sendBufferPool = new SocketSendBufferPool();
```