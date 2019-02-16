## 新特性

## jdk7
Fork/Join
一个把大任务分割成若干个小任务，最终汇总每个小任务结果后得到大任务结果的框架
1.	任务分割：首先Fork/Join框架需要把大的任务分割成足够小的子任务，如果子任务比较大的话还要对子任务进行继续分割
2.	执行任务并合并结果：分割的子任务分别放到双端队列里，然后几个启动线程分别从双端队列里获取任务执行。子任务执行完的结果都放在另外一个队列里，启动一个线程从队列里取数据，然后合并这些数据。

## jdk8
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