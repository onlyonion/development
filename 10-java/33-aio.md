
应用程序 <--> 内核

### 同步与异步
1. 实际上同步与异步是针对应用程序与内核的交互而言的。
2. 同步过程中进程触发IO操作并等待或者轮询的去查看IO操作是否完成。
3. 异步过程中进程触发IO操作以后，直接返回，做自己的事情，IO交给内核来处理，完成后内核通知进程IO完成
	
A synchronous I/O operation causes the requesting process to be blocked until that I/O operation completes;
An asynchronous I/O operation does not cause the requesting process to be blocked; 

### 分层
* cpu层 os的IO和任务调度层次
* 线程层 多线程（同步阻塞）；IO多路复用（select, poll, epoll）（同步非阻塞，严格讲，把阻塞点改变了位置）；异步IO接口（kernal-aio, IOCP）
* 感知层

### 概念性解释 1
同步和异步通常用来形容一次方法调用。
同步方法调用一旦开始，调用者必须等到方法调用返回后，才能继续后续的行为。
异步方法调用更像一个消息传递，一旦开始，方法调用就会立即返回，调用者就可以继续后续的操作。
异步方法通常会在另外一个线程中，“真实”地执行着。整个过程，不会阻碍调用者的工作。

### 概念性解释 2
Java中交互方式分为同步和异步两种：
相同的地方：都属于交互方式，都是发送请求。
不同的地方：
同步交互：指发送一个请求,需要等待返回,然后才能够发送下一个请求，有个等待过程；
异步交互：指发送一个请求,不需要等待返回,随时可以再发送下一个请求，即不需要等待。 
