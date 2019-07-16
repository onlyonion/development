《Java 8 函数式编程》王群锋 译

* lambda表达式、stream API
* 类库 接口默认方法、多重继承、Optional
* 高级集合类和收集器 方法引用
* 数据并行
* 

## 第1章 简介
### 1.1　为什么需要再次修改Java
* 利用多核CPU。
* 抽象级别还不够，面对大型数据集合，Java还欠缺高效的并行操作。
* 回调函数和事件处理程序时，匿名内部类的冗繁和可读性。

### 1.2　什么是函数式编程
在思考问题时，使用不可变值和函数，函数对一个值进行处理，映射成另一个值。

## 第2章 Lambda表达式
* 函数接口，只有一个抽象方法的接口，用作Lambda表达式的类型。接口default方法
* 类型推断，是Java7就引入的目标类型推断的扩展，`new HashMap<>`
* 匿名函数，传递行为

## 第3章 流
### 从外部迭代到内部迭代
* 外部迭代 `iterator`产生一个新的Iterator对象，进而控制整个迭代
* 内部迭代 Stream是用函数式编程方式在集合类上进行复杂操作的工具

```java
allArtits.stream().filter(artis -> artist.isFrom("london"));
```
像filter这样只描述stream，最终不产生新集合的方法叫做**惰性求值方法**。
整个过程和建造者模式有共通之处。建造者模式使用一系列操作设置属性和配置，最后调用一个build方法，这时对象才被真正创建。
### 常用的流操作
```java
// 及早求值（返回另一个值或空）与惰性求值（返回Stream）
// 由Stream里的值生成一个列表，是一个 及早求值操作
collect(toList())
// map
Stream.of("a", "b").map(string -> string.toUpperCase()).collect(Collectors.toList())
// T -> Function -> R
// filter
Stream.of("a", "b").filter(value -> isDigit(value.charAt(0))).collect(Collectors.toList())
// T -> Predicate -> boolean
```
* map 将一种类型的值转换成另一种类型
* filter
* flatMap 可用Stream替换值，然后将多个Stream连接成一个Stream
* max, min
* 通用模式
* reduce 实现从一组值中生成一个值。
* 整合模式

### 重构遗留代码
```java
return albums.stream()
            .flatMap(album -> album.getTracks())
            .filter(track -> track.getLength() > 60)
            .map(track -> track.getName())
            .collect(toSet());
```

### 高阶函数
接受另外一个函数作为参数，或返回一个函数的函数

## 第4章 类库
* 日志代码中的lambda
* logger.debug(() -> "look at this:" + expensiveOperation())
* @FuncionalInterface
* 默认方法
* 多重集成 需要制定集成哪个父类的方法`SuperClass.super.method()`
* 接口静态方法
* Optional

## 第5章 高级集合类和收集器
### 方法引用
### 收集器 collect(toList())
* 转换成其他集合 collect(toCollection())
* 转换成值 collect(averagintToInt()),  collect(maxBy())
* 数据分块 collect(partitioningBy())
* 数据分组 collect(groupBy())
* 字符串 collect(Collectors.joining(delimiter, prefix, suffix))
* 组合收集器 albums.collect(groupingBy(album -> album.getMainMusician(), counting()))
* 收集器归一化处理 Collectors.reduing()

## 第6章 数据并行化
### 并行和并发
并行和并发，并发是两个任务共享时间段，并行则是两个任务在同一时间发送，比如运行在多核CPU上。
并行是为了缩短任务执行时间，将一个任务分解成几部分，然后并行执行。

数据并行化是指将数据分块，为每块数据分配单独的处理单元。
它将**问题分解**为可在多块数据上求解的形式，然后对每块数据执行运算，最后将各数据块上得到的结果**汇总**，从而获得最终答案。

### 为什么并行化如此重要
主流的芯片厂商转向了多核处理器。这种变化影响了软件设计，想利用现代CPU的架构，必须编写并行化的代码。

### 并行化流操作
parallelStream

## 第7章 测试、调试和重构
### 重构候选项
```java
if(logger.isDebugEnabled) {
    logger.debug(() -> "look at this: " + expensiveOperation());
}
// lambda化
logger.debug(() -> "look at this: " + expensiveOperation());

ThreadLocal<Album> thisAlbum = ThreadLocal.withInitial(() -> database.lookupCurrentAlbum());

```
### Lambda表达式的单元测试
### 在测试替身时使用Lambda表达式
### 惰性求职和调试
### 日志和打印消息
### 解决方案：peak
流有一个方法让你能查看每个值，同时能继续操作流。peak可以输出流中的值，将输出值定向到日志系统中。
```java
Set<String> nationalities = album.getMussicaians().filter(artist -> artist.getName().startsWith("The")).map(artist -> artist.getNationality())
                                    .peak(nation -> System.out.println("Found nationality: " + nation))
                                    .collect(Collectors.<String>toSet());
```
## 第8章 设计和架构的原则
使用Lambda表达式实现SOLID原则。
### Lambda表达式改变了设计模式
* 命令者模式
* 代码简洁、去掉样板代码、意图明显
* 策略模式 构造新对象 `类::new`
* 观察者模式
* 模板方法模式 算法骨架封装

### 使用Lambda表达式的领域专用语言
使用java编写DSL

### 使用Lambda表达式的Solid原则
* 单一功能 程序中的类或方法只能有一个改变的理由
* 开闭原则 软件应该对扩展开发，对修改闭合
* 依赖反转原则 抽象不应该依赖细节、细节应该依赖抽象。
* SOLID
  * single responsibility
  * open/closed
  * liskov substitution
  * interface segregation
  * dependecy inversion

## 第9章 使用Lambda表达式编写并发程序
### 9.1 为什么要使用非阻塞式IO
非阻塞IO 处理大量并发网络连接，而且一个线程可以为多个连接服务。
### 9.2 回调
### 9.3 消息传递架构
通过事件总线发送消息通信，不需要保护任何共享状态，不需要加锁或使用synchronized关键字
### 9.4 末日金字塔
### 9.5 Future
Future.get()方法获取值，它会阻塞当前线程，直到返回值。
多个Future.get()将任务串行化了。
我们不必调用get方法阻塞线程，需要将Future和回调结合起来使用。
### 9.6 CompletableFuture
结合了Future对象打欠条的主意和使用回调处理事件驱动的任务。其要点是可以组合不同的实例，而不用担心末日金字塔问题。
* thenCompose 将一个CompletableFuture对象转换成另一个CompletableFuture对象
* thenCombine 将一个CompletableFuture对象的结果和另一个CompletableFuture对象组合起来。

创建CompletableFuture对象分两部分：创建对象和传递给他欠客户代码的值。

CompletableFuture 的常用情境之一是异步执行一段代码，该段代码计算并返回一个值。 
为了避免大家重复实现同样的代码，有一个工厂方法supplyAsync，用来创建CompletableFuture 实例。

### 9.7 响应式编程
CompletableFuture 背后的概念可以从**单一的返回值**推广到**数据流**，这就是响应式编程。
响应式编程其实是一种**声明式编程**方法， 它让程序员以自动流动的变化和数据流来编程。

事件驱动和响应式应用正在变得越来越流行。
第一种情况是业务逻辑本身就使用事件来描述。

另一种显然的用例是应用需要同时处理大量I/O操作。阻塞式I/O需要同时使用大量线程，这会导致大量锁之间的竞争和太多的上下文切换。
如果想要处理成千上万的连接， 非阻塞式I/O通常是更好的选择。