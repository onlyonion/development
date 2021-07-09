《Java多线程编程核心技术》高洪岩 著 机械工业出版社

* 多线程 isAlive、sleep、getId、优先级、守护线程
* 对象及变量的并发 synchronized、volatile
* 线程间通信 等待/通知机制（wait/notify/notifyAll）、join、ThreadLocal、InheritableThreadLocal
* Lock的使用 ReentrantLock、ReentrantReadWriteLock
* 定时器Timer
* 单例模式与多线程 饿汉、懒汉、静态内部类、序列化与反序列化、enum

## 第1章 Java多线程技能
### 1.1 进程和多线程的概念及线程的优点
### 1.2 使用多线程
#### 1.2.1 继承Thread类
#### 1.2.2 实现Runnable接口
#### 1.2.3 实例变量与线程安全

### 1.3 currentThread()方法
### 1.4 isAlive()方法
### 1.5 sleep()方法
### 1.6 getId()方法
### 1.7 停止线程
### 1.8 暂停线程
### 1.9 yield方法
### 1.10 线程的优先级
### 1.11 守护线程

## 第2章 对象及变量的并发访问
### 2.1 synchronized同步方法
#### 2.1.1 方法内的变量为线程安全
#### 2.1.2 实例变量非线程安全
#### 2.1.3 多个对象多个锁
#### 2.1.4 synchronized方法与锁对象
#### 2.1.5 脏读
#### 2.1.6 synchronized锁重入
#### 2.1.7 出现异常，锁自动释放
#### 2.1.8 同步不具有继承性
子类需要同步的方法依然要加`synchronized`
### 2.2 synchronized同步语句块
### 2.3 volatile关键字

## 第3章 线程间通信
### 3.1 等待/通知机制
### 3.2 方法join的使用
### 3.3 类ThreadLocal的使用
#### 3.3.1 方法get()与null
#### 3.3.2 验证线程变量的隔离性
#### 3.3.3 解决get()返回null问题
initialValue()重写
#### 3.3.4 再次验证线程变量的隔离性
### 3.4 类InheritableThreadLocal的使用
子线程中取得父线程继承下来的值
#### 3.4.1 值继承
#### 3.4.2 值继承再修改
使用InheritableThreadLocal类需要注意，如果子线程在取得值的同时，主线程将InheritableThreadLocal中的值进行更改，
那么子线程取得的值还是旧值。

## 第4章 Lock的使用
### 4.1 使用ReentrantLock类
### 4.2 使用ReentrantReadWriteLock类

## 第5章 定时器Timer
### 5.1 定时器Timer的使用

## 第6章 单例模式与多线程
### 6.1 立即加载/“饿汉模式”
### 6.2 延迟加载/“懒汉模式”
### 6.3 使用静态内置类实现单例模式
### 6.4 序列化与反序列化的单例模式实现
### 6.5 使用static代码块实现单例模式
### 6.6 使用enum枚举数据类型实现单例模式
### 6.7 完善使用enum枚举实现单例模式
枚举类enum和静态代码块的特性相似。

## 第7章 拾遗增补
### 7.1 线程的状态
new runnable blocked wait time_wait terminated
### 7.2 线程组
可以把线程归属到某一个线程组中，线程组中可以有线程对象，也可以有线程组。
组织结构类似于树的形成。可以批量的管理线程或线程组对象，有效地对线程或线程组对象进行组织。

### 7.3 使线程具有有序性
### 7.4 SimpleDateFormat非线程安全
### 7.5 线程中出现异常的处理
### 7.6 线程组内处理异常
### 7.7 线程异常处理的传递

