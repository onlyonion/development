[详解tomcat的连接数与线程池](https://www.cnblogs.com/kismetv/p/7806063.html)

### Nio、Bio、APR
Connector的protocol
Connector在处理HTTP请求时，会使用不同的protocol。不同的Tomcat版本支持的protocol不同，其中最典型的protocol包括BIO、NIO和APR

Connector使用哪种protocol，可以通过`<connector>`元素中的protocol属性进行指定，也可以使用默认值。

* org.apache.coyote.http11.Http11Protocol：BIO
* org.apache.coyote.http11.Http11NioProtocol：NIO
* org.apache.coyote.http11.Http11Nio2Protocol：NIO2
* org.apache.coyote.http11.Http11AprProtocol：APR


在accept队列中接收连接（当客户端向服务器发送请求时，如果客户端与OS完成三次握手建立了连接，则OS将该连接放入accept队列）；
在连接中获取请求的数据，生成request；
调用servlet容器处理请求；返回response。

###
Acceptor -- queue -->  Poller --> Worker

Acceptor接收socket后，不是直接使用Worker中的线程处理请求，而是先将请求发送给了Poller，而Poller是实现NIO的关键。
Acceptor向Poller发送请求通过队列实现，使用了典型的生产者-消费者模式。
在Poller中，维护了一个Selector对象；当Poller从队列中取出socket后，注册到该Selector中；
然后通过遍历Selector，找出其中可读的socket，并使用Worker中的线程处理相应请求。
与BIO类似，Worker也可以被自定义的线程池代替。


### acceptCount、maxConnections、maxThreads

#### acceptCount
accept队列的长度；当accept队列中连接的个数达到acceptCount时，队列满，进来的请求一律被拒绝。默认值是100。

#### maxConnections
Tomcat在任意时刻接收和处理的最大连接数。
当Tomcat接收的连接数达到maxConnections时，Acceptor线程不会读取accept队列中的连接；这时accept队列中的线程会一直阻塞着，直到Tomcat接收的连接数小于maxConnections。

#### maxThreads
请求处理线程的最大数量。默认值是200（Tomcat7和8都是的）。如果该Connector绑定了Executor，这个值会被忽略，因为该Connector将使用绑定的Executor，而不是内置的线程池来执行任务。


### 线程池Executor

Executor的主要属性包括：
* name：该线程池的标记
* maxThreads：线程池中最大活跃线程数，默认值200（Tomcat7和8都是）
* minSpareThreads：线程池中保持的最小线程数，最小值是25
* maxIdleTime：线程空闲的最大时间，当空闲超过该值时关闭线程（除非线程数小于minSpareThreads），单位是ms，默认值60000（1分钟）
* daemon：是否后台线程，默认值true
* threadPriority：线程优先级，默认值5
* namePrefix：线程名字的前缀，线程池中线程名字为：namePrefix+线程编号

## Linux命令行，查看服务器中的连接数和线程数

* 连接数 `netstat –nat | grep 8083`
* 进程 `ps –e | grep java` 
* 线程 `ps –o nlwp 27989` 该进程内有多少个线程；其中，nlwp含义是number of light-weight process 


[tomcat 线程模型](https://blog.csdn.net/qq_16681169/article/details/75003640)

Acceptor 接收socket线程
Poller线程中维护了一个Selector对象，NIO就是基于Selector来完成逻辑的。Poller是NIO实现的主要线程。
首先作为events queue的消费者，从queue中取出PollerEvent对象，然后将此对象中的channel以OP_READ事件注册到主Selector中，
然后主Selector执行select操作，遍历出可以读数据的socket，
并从Worker线程池中拿到可用的Worker线程，然后将socket传递给Worker。整个过程是典型的NIO实现。

Worker线程拿到Poller传过来的socket后，将socket封装在SocketProcessor对象中。
然后从Http11ConnectionHandler中取出Http11NioProcessor对象，从Http11NioProcessor中调用CoyoteAdapter的逻辑，跟BIO实现一样。


 tomcat8的并发参数控制
 ##### acceptCount 
 The maximum queue length for incoming connection requests when all possible request processing threads are in use. Any requests received when the queue is full will be refused. The default value is 100. 

 ##### acceptorThreadCount 
 The number of threads to be used to accept connections. Increase this value on a multi CPU machine, although you would never really need more than 2. Also, with a lot of non keep alive connections, you might want to increase this value as well. Default value is 1. 

 ##### maxConnections
 For NIO and NIO2 the default is 10000. For APR/native, the default is 8192. 

 ##### maxThreads 
 The maximum number of request processing threads to be created by this Connector, which therefore determines the maximum number of simultaneous requests that can be handled. If not specified, this attribute is set to 200. If an executor is associated with this connector, this attribute is ignored as the connector will execute tasks using the executor rather than an internal thread pool. 