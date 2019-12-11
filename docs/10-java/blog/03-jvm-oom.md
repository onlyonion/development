
- 内存溢出(Out Of Memory，简称OOM)是指应用系统中存在无法回收的内存或使用的内存过多，最终使得程序运行要用到的内存大于能提供的最大内存。
- 内存泄漏（Memory Leak）是指程序中己动态分配的堆内存由于某种原因程序未释放或无法释放，造成系统内存的浪费，导致程序运行速度减慢甚至系统崩溃等严重后果。

## 继承体系
* Throwable (java.lang)
  * Error (java.lang)
    * VirtualMachineError (java.lang) 虚拟机错误
      * InternalError (java.lang)
      * StackOverflowError (java.lang)
      * UnknownError (java.lang)
      * OutOfMemoryError (java.lang) 内存溢出错误
        * OutOfDirectMemoryError (io.netty.util.internal)

## java.lang.OutOfMemoryError
### java.lang.OutOfMemoryError: Java heap space
需要创建大量的对象，层次比较深的递归操作等

### java.lang.OutOfMemoryError: PermGen space 
用来存储被加载的类的定义（class definition）和元数据（metadata）

### java.lang.OutOfMemoryError: Metaspace

### java.lang.OutOfMemoryError: Direct buffer memory
Netty启用了堆外内存DirectByteBuffer实现了零拷贝，堆外内存对young gc免疫，只有在full gc的时候才被收回

### java.lang.OutOfMemoryError: GC overhead limit exceeded
GC占用了多余98%（默认值）的CPU时间却只回收了少于2%（默认值）的堆空间

一般是应用程序在有限的内存上创建了大量的临时对象或者弱引用对象，从而导致该异常。

[解决办法](https://blog.csdn.net/qq_40162735/article/details/81775828 )
1. 查看项目中是否有大量的死循环或有使用大内存的代码，优化代码。
2. JVM给出这样一个参数：`-XX:-UseGCOverheadLimit`禁用这个检查，其实这个参数解决不了内存问题，只是把错误的信息延后，替换成 java.lang.OutOfMemoryError: Java heap space。
3. 增大堆内存 set JAVA_OPTS=-server -Xms512m -Xmx1024m -XX:MaxNewSize=1024m -XX:MaxPermSize=1024m  

### java.lang.OutOfMemoryError: unable to create new native thread
1. 系统内存耗尽，无法为新线程分配内存
2. 创建线程数超过了操作系统的限制

## other
- stackOverFlow
- socketException:too many open files
