## jvm

### hotspot.src
hotspot/src/

* 平台相关性
  * cpu
    * sparc
    * x86
    * zero
  * os
    * linux
    * posix
    * solaris
    * windows
  * os_cpu
* 平台无关性
  * 类文件结构 类加载 classfile
  * 内存管理 memory
    * 内存区域
    * 内存分配
    * 垃圾回收 gc_interface gc_impementation
  * 运行时环境 runtime 包括线程管理、编译器调度、锁、反射等
    * vframe
    * osThread vmThread
    * objectMonitor basicLock
    * reflection
  * 对象系统 oops
  * 对外服务 prims services
  * 编译器、解释器、汇编、机器码 compiler(c1 opto) interpreter asm code
  * 抽象数据结构 libadt(dict port set vectset)

### package
```
    cpu CPU相关代码（汇编器、模板解释器、ad文件、部分runtime函数在这里实现）         
        sparc
        x86
        zero
    os
        bsd
        linux
        posix
        solaris
        windows
            vm
                atomic_windows_x86.inline.hpp
    os_cpu
        linux_sparc
        linux_x86
        linux_zero
        solaris_sparc
        solaris_x86
        windows_x86
    share
        tool
        vm
            adlc 平台描述文件（上面的cpu或os_cpu里的*.ad文件）的编译器
            asm 汇编
            c1 client编译器（又称“C1”）
            ci 动态编译器的公共服务/从动态编译器到VM的接口
            classfile 类文件的处理（包括类加载和系统符号表等）
            code 动态生成的代码的管理
            compiler 从VM调用动态编译器的接口
            gc_impementation
            gc_interface
            interpreter
            libadt
            memory 内存管理相关（老的分代式GC框架也在这里）
            oops HotSpot VM的对象系统的实现
            opto server编译器（又称“C2”或“Opto”）
            precompiled
            prims HotSpot VM的对外接口，包括部分标准库的native部分和JVMTI实现
            runtime 运行时支持库（包括线程管理、编译器调度、锁、反射等）
            services
            shark
            trace
            utilities
```

## link
[openjdk7 download](http://download.java.net/openjdk/jdk7/promoted/b147/openjdk-7-fcs-src-b147-27_jun_2011.zip)