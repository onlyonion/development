
应用程序 <--> 内核

### 同步与异步

1. 实际上同步与异步是针对应用程序与内核的交互而言的。
2. 同步过程中进程触发IO操作并等待或者轮询的去查看IO操作是否完成。
3. 异步过程中进程触发IO操作以后，直接返回，做自己的事情，IO交给内核来处理，完成后内核通知进程IO完成
	
A synchronous I/O operation causes the requesting process to be blocked until that I/O operation completes;

An asynchronous I/O operation does not cause the requesting process to be blocked; 