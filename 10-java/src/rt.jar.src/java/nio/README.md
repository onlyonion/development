java.nio
## package
```
channels
    AsynchronousChannel
    ByteChannel
    Channel
    Channels
    CompletionHandler
    FileChannel
    DatagramChannel
    FileLock
    NetworkChannel
    MulticastChannel
    InterruptibleChannel
    SocketChannel
    ServerSocketChannel
    Selector
    SelectionKey
    WritableByteChannel
charset
    CharsetDecoder
    CharsetEncoder
    Charset
file
    CopyOption
    DirectoryStream
    Path
    OpenOption
    PathMatcher
    Watchable
    WatchEvent
    WatchKey
    WatchService
Buffer
ByteBuffer
DirectByteBuffer
HeapByteBuffer
MappedByteBuffer
```


## NIO
selector, buffer, channel
stream  单向
channel 双向

FileChannel
SelectableChannel
	ServerSocketChannel
	SocketChannel
Selector 多路复用器 epoll();
Selector轮询注册在其上的Channel，发生读写被处于就绪，被轮询出，SelectKey获取就绪Channel的集合，后续IO操作


零拷贝
Java NIO中提供的FileChannel拥有transferTo和transferFrom两个方法，可直接把FileChannel中的数据拷贝到另外一个Channel，或者直接把另外一个Channel中的数据拷贝到FileChannel

不需要将源数据从内核态拷贝到用户态，再从用户态拷贝到目标通道的内核态，同时也避免了两次用户态和内核态间的上下文切换

在Reactor模式中，包含如下角色
Reactor 将I/O事件发派给对应的Handler
Acceptor 处理客户端连接请求
Handlers 执行非阻塞读/写

## AIO
Future表示异步操作的结果
CompletionHandler接口的实现类作为操作完成异步回调。