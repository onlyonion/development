Java 8 函数式编程
## 1 简介
## 2 Lambda表达式
函数接口，接口里单个抽象方法，接口default方法
类型推断
匿名函数，传递行为
## 3 流
### 从外部迭代到内部迭代
### 常用的流操作
```java
 // 由Stream里的值生成一个列表，是一个及早求值操作
collect(toList())
// map
Stream.of("a", "b").map(string -> string.toUpperCase()).collect(Collectors.toList())
// T -> Function -> R
// filter
Stream.of("a", "b").filter(value -> isDigit(value.charAt(0))).collect(Collectors.toList())
// T -> Predicate -> boolean
```
flatMap
可用Stream替换值，然后将多个Stream连接成一个Stream
max, min
通用模式
reduce
整合模式
### 高阶函数
接受另外一个函数作为参数，或返回一个函数的函数

## 4 类库
日志代码中的lambda
logger.debug(() -> "look at this:" + expensiveOperation())
@FuncionalInterface
默认方法
接口静态方法
Optional

## 5 高级集合类和收集器
### 方法引用
### 收集器 collect(toList())
转换成其他集合 collect(toCollection())
转换成值 collect(averagintToInt()),  collect(maxBy())
数据分块 collect(partitioningBy())
数据分组 collect(groupBy())
字符串 collect(Collectors.joining(delimiter, prefix, suffix))
组合收集器
收集器归一化处理 Collectors.reduing()

## 6 数据并行化
并行和并发
parallelStream

## 7 测试、调试和重构
## 8 设计和架构的原则
### Lambda表达式改变了设计模式
命令者模式
代码简洁、去掉样板代码、意图明显
策略模式
观察者模式
模板方法模式
### 使用Lambda表达式的领域专用语言
使用java编写DSL
### 使用Lambda表达式的Solid原则
单一功能
开闭原则
依赖反转原则
single responsibility, open/closed, liskov substitution, interface segregation, dependecy inversion
## 9 使用Lambda表达式编写并发程序
* 非阻塞IO
* 回调
* 消息传递架构
* Future
* CompletableFuture
* 响应式编程
