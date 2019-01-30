
## jstack
[性能分析之-- JAVA Thread Dump 分析综述](https://blog.csdn.net/rachel_luo/article/details/8920596)


### jvm thread
* Attach Listener
* Signal Dispatcher
* Service Thread
* C1 CompilerThread
* C2 CompilerThread
* Reference Handler
* Finalizer
* HotSpot GC Thread
* VM Periodic Task Thread
* JNI global references count

### 问题
进程ID --> 线程ID --> 线程dump分析

* cpu飙高，load高，响应很慢
* cpu使用率不高但是响应很慢  io、数据库
* 请求无法响应    死循环