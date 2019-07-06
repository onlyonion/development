
* Throwable (java.lang)
  * Error (java.lang)
    * VirtualMachineError (java.lang)
      * InternalError (java.lang)
      * StackOverflowError (java.lang)
      * UnknownError (java.lang)
      * OutOfMemoryError (java.lang)
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