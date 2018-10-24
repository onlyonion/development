### Disruptor

耗时比较：单线程 < CAS < 锁

Disruptor它是一个开源的并发框架，并获得2011 Duke’s 程序框架创新奖，能够在无锁的情况下实现网络的Queue并发操作。
Disruptor是一个高性能的异步处理框架，或者可以认为是最快的消息框架（轻量的JMS），也可以认为是一个观察者模式的实现，或者事件监听模式的实现。 

ClaimStrategy
1.	SingleThreadedStrategy（单线程策略）使用long，没有锁也没有CAS，只有一个生产者，不会产生序号上的冲突
2.	MultiThreadedStrategy（多线程策略）使用了AtomicLong（Java提供的CAS操作），有CAS

队列的目的就是为生产者和消费者提供一个地方存放要交互的数据，帮助缓冲它们之间传递的消息。这意味着缓冲常常是满的（生产者比消费者快）或者空的（消费者比生产者快）。生产者和消费者能够步调一致的情况非常少见。

Disruptor相对于传统方式的优点：

1.	没有竞争=没有锁=非常快。
2.	所有访问者都记录自己的序号的实现方式，允许多个生产者与多个消费者共享相同的数据结构。
3.	在每个对象中都能跟踪序列号（ring buffer，claim Strategy，生产者和消费者），加上神奇的cache line padding，就意味着没有为伪共享和非预期的竞争。
