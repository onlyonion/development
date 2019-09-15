《RxJava响应式编程》李衍顺 

## 第1章 走进RxJava的世界
RxJava可以看做是Observable、Subscriber和Scheduler组成的。Scheduler是对线程的一种抽象，不同的Scheduler代表了不同的线程。

## 第2章 RxJava中的操作符 
### 2.1 创建Observable的操作符
### 2.2 转换Observable的操作符
### 2.3 过滤操作符
### 2.4 组合操作符
### 2.5 错误处理操作符
### 2.6 辅助操作符
### 2.7 条件操作符
### 2.8 聚合操作符
### 2.9 与Connectalbe Observable相关的操作符
### 2.10 自定义操作符

## 第3章 使用Scheduler进行线程调度
### 3.1 什么是Scheduler
RxJava使用Scheduler来实现异步的。Observable默认是单线程的。线程隔离（Observable与Scheduler隔离）

### 3.2 Scheduler的类型
- computation
- newThread
- io
- immediate
- trampoline
- from

### 3.3 总结

## 第4章 RxJava的实现原理 
### 4.1 数据的发送和接收
### 4.2 操作符的实现
### 4.3 Scheduler的工作原理

## 第5章 RxJava的应用实例

## 第6章 RxJava 2的改进

