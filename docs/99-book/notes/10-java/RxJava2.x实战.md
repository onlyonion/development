《RxJava2.x实战》沈哲

## 第1章 RxJava简介

### 1.1 你需要了解的函数响应式编程

#### 响应式编程
响应式编程是一种面向数据流和变化传播的编程范式。
如果需要响应迅速，就得把同步执行的方式转换成异步，方法执行编程消息发送，这就是异步编程的方式。

响应式编程的特点：
- 异步编程
- 数据流
- 变化传播

#### 函数式编程
- 函数是“第一等公民”：函数与其他类型一样，处于平等地位，可以赋值给其他变量，也可以作为参数，传入另一个函数，或者作为别的函数的返回值。
- 闭包和高阶函数
- 递归
- 惰性求值
- 没有“副作用”

#### 函数响应式编程
- 函数解决组合问题
- 响应式解决回调地域

### 1.2 RxJava简介
RxJava是Reactive Extension的Java实现，用于通过Observable/Flowable序列来构建异步和基于事件的程序的库。

Rx是一个编程模型，目标是提供一致的编程接口，帮助开发者更方便地处理异步数据流。Rx库支持.net、Java和C++。RxJava、RxJS、Rx.NET。

Rx = Observable + LINQ + Schedulers

ReactiveX.io给的定义是，Rx是一个使用可观察数据流进行异步编程的编程接口，ReactiveX结合了观察者模式、迭代器模式和函数式编程的精华。

Rx模式
- 使用观察者模式
- 简化代码

RxJava是Reactive Extension在JVM平台上的一个实现，通过使用观察者序列来构建异步、基于事件的程序。

Rx提供了一系列的操作符，可以用来过滤、选择、变换、结合、组合多个Observable，这些操作符让执行和复合变得非常高效。

## 第2章 RxJava基础知识
### 2.1 Observable、
RxJava的使用通常需要三步：
- 创建Observable 被观察者
- 创建Observer 观察者
- 使用subscribe()进行订阅

## 第3章 创建操作符
## 第4章 RxJava的线程操作
## 第5章 变换符合过滤操作符
## 第6章 条件操作符和布尔操作符
## 第7章 合并操作符与连接操作符
## 第8章 RxJava的背压
## 第9章 Diposable和Transformer的使用
## 第10章 RxJava的并行编程
## 第11章 RxBinding的使用
## 第12章 RxAndroid2.x和Retrofit的使用
## 第13章 开发EventBus
## 第14章 使用RxJava封装HttpClient4.5
## 第15章 SpringBoot和RxJava2
## 第16章 Java8的函数式编程
## 第17章 Kotlin和RxJava
## 第18章 展望未来