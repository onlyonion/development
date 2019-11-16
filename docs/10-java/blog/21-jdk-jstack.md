
## jstack
[性能分析之-- JAVA Thread Dump 分析综述](https://blog.csdn.net/rachel_luo/article/details/8920596)

### jvm thread
* Attach Listener 负责接收到外部的命令，而对该命令进行执行的并且把结果返回给发送者
* Signal Dispatcher 当AttachListener接收命令成功后，会交给signal dispather 线程去进行分发到各个不同的模块处理命令，并且返回处理结果。
* Service Thread
* C1 CompilerThread
* C2 CompilerThread
* Reference Handler JVM在创建main线程后就创建Reference Handler线程，其优先级最高，为10，它主要用于处理引用对象本身（软引用、弱引用、虚引用）的垃圾回收问题
* Finalizer 主要用于在垃圾收集前，调用对象的finalize()方法
* HotSpot GC Thread  
* VM Periodic Task Thread 周期性任务调度的线程，它由WatcherThread创建，是一个单例对象
* JNI global references count

### 问题
进程ID --> 线程ID --> 线程dump分析

* cpu飙高，load高，响应很慢
* cpu使用率不高但是响应很慢  io、数据库
* 请求无法响应    死循环

```
java -classpath %JAVA_HOME%\lib\sa-jdi.jar sun.jvm.hotspot.HSDB
java -classpath "D:\opt\java\jdk1.8.0_172\lib\sa-jdi.jar" sun.jvm.hotspot.HSDB
```