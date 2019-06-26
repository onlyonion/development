
### java.lang.OutOfMemoryError: Java heap space
需要创建大量的对象，层次比较深的递归操作等

### java.lang.OutOfMemoryError: PermGen space 
用来存储被加载的类的定义（class definition）和元数据（metadata）
### Metaspace

### Direct buffer memory
Netty启用了堆外内存DirectByteBuffer实现了零拷贝，堆外内存对young gc免疫，只有在full gc的时候才被收回

### java.lang.OutOfMemoryError: GC overhead limit exceeded
GC占用了多余98%（默认值）的CPU时间却只回收了少于2%（默认值）的堆空间

一般是应用程序在有限的内存上创建了大量的临时对象或者弱引用对象，从而导致该异常。

### unable to create new native thread
1. 系统内存耗尽，无法为新线程分配内存
2. 创建线程数超过了操作系统的限制