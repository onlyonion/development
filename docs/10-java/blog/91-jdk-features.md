## 新特性

## jdk7
Fork/Join
一个把大任务分割成若干个小任务，最终汇总每个小任务结果后得到大任务结果的框架
1.	任务分割：首先Fork/Join框架需要把大的任务分割成足够小的子任务，如果子任务比较大的话还要对子任务进行继续分割
2.	执行任务并合并结果：分割的子任务分别放到双端队列里，然后几个启动线程分别从双端队列里获取任务执行。子任务执行完的结果都放在另外一个队列里，启动一个线程从队列里取数据，然后合并这些数据。

## jdk8

### completableFuture 
对Future进行增强，支持函数式编程的流式调用。提供更多功能，压缩编码量。

### LongAdder
类似automicLong, 但是提供了“热点分离”。过程如下：如果并发不激烈，则与automicLong 一样，cas赋值。
如果出现并发操作，则使用数组，数组的各元素之和为真实value，让操作分散在数组各个元素上，把并发操作压力分散，一遇到并发就扩容数组，最后达到高效率。

一般cas如果遇到高并发，可能一直赋值失败导致不断循环，热点分离可以解决这个问题。有点类似concurrenthashmap，分而治之。

### [StampedLock](https://blog.csdn.net/Ruiz_666/article/details/88638146)
ReentrantReadWriteLock写锁是互斥的。读和读--不互斥；读和写--互斥； 写和写--互斥

读操作的时候发现有写操作，会再去多读取一次。

StampedLock有两种锁：
* 一种是悲观锁：如果是悲观锁，读和写操作是互斥的。
* 一种是乐观锁：如果是乐观锁，读和写操作是不互斥的。

### Nashorn JavaScript 引擎
Google V8
Java调用Javascript函数
JavaScript调用Java方法
ScriptObjectMirror
语言扩展

### Lambda
a function (or a subroutine) defined, and possibly called, without being bound to an identifier。
一个不用被绑定到一个标识符上，并且可能被调用的函数
	
```
	(params) -> expression
	(params) -> statement
	(params) -> { statements }
```
	
[Java8 lambda表达式10个示例](http://www.importnew.com/16436.html "title") 

1.	使用Java 8 lambda表达式进行事件处理
2.	使用lambda表达式对列表进行迭代
3.	使用lambda表达式和函数式接口Predicate
4.	如何在lambda表达式中加入Predicate
5.	Java 8中使用lambda表达式的Map和Reduce示例 map(), reduce()
6.	 通过过滤创建一个String列表 filter()
7.	对列表的每个元素应用函数 map()
8.	复制不同的值，创建一个子列表 distinct()
9.	计算集合元素的最大值、最小值、总和以及平均值 primes.stream().mapToInt((x) -> x).summaryStatistics()  getMax(), getMin(), getSum(), getAverage()

Lambda表达式 vs 匿名类
this指向		匿名类的 this 关键字指向匿名类，而lambda表达式的 this 关键字指向包围lambda表达式的类
编译方式		Java编译器将lambda表达式编译成类的私有方法。使用了Java 7的 invokedynamic 字节码指令来动态绑定这个方法