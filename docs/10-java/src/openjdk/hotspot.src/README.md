# hotspot
* 编译原理 前端、后端
* OS程序的编译、链接、装入、文件管理、IO
* OS内存管理、分配、虚拟内存
* OS进程、线程、调度、同步、锁
* OS用户接口
  
## overview
hotspot/src/

- 平台相关性 cpu、os、os_cpu
- 平台无关性
  * 编译器、解释器、汇编、机器码 `compiler(c1 opto) interpreter asm code`
  * 类文件结构、类加载机制 字节码指令集、执行引擎、对象系统`oops`
  * 内存管理 运行时数据区域、垃圾回收
  * 运行时环境 线程管理、编译器调度、并发、锁、反射等
  * 管理服务、对外服务、JMX接口 连接机制、类加载服务、诊断、内存管理、内存模型、线程服务
  * 抽象数据结构 libadt(dict port set vectset)《数据结构》

## src

### cpu
* cpu CPU相关代码（汇编器、模板解释器、ad文件、部分runtime函数在这里实现）         
  * sparc
  * x86
  * zero

### os
* os
  * bsd
  * linux
  * posix
  * solaris
  * windows
    * vm
      * atomic_windows_x86.inline.hpp

### os_cpu
* os_cpu
  * linux_sparc
  * linux_x86
  * linux_zero
  * solaris_sparc
  * solaris_x86
  * windows_x86

### share
* share
  * tool
    * hsdis
    * IdealGraphVisualizer
    * launcher 启动入口
    * LogCompilation
    * ProjectCreator
  * vm
    * adlc 平台描述文件（上面的cpu或os_cpu里的*.ad文件）的编译器
    * [asm](/docs/10-java/src/openjdk/hotspot.src/share.vm/asm/README.md) 汇编
    * c1 client编译器（又称“C1”）
    * ci 动态编译器的公共服务/从动态编译器到VM的接口
    * [classfile](/docs/10-java/src/openjdk/hotspot.src/share.vm/classfile/README.md) 类文件的处理（包括类加载和系统符号表等）
      * [classLoader](/docs/10-java/src/openjdk/hotspot.src/share.vm/classfile/classLoader.md)
    * code 动态生成的代码的管理
      * [codeCache](/hotspot/src/share/vm/code/codeCache.hpp)
    * [compiler](/docs/10-java/src/openjdk/hotspot.src/share.vm/compiler/README.md) 从VM调用动态编译器的接口
    * [gc_implementation](/docs/10-java/src/openjdk/hotspot.src/share.vm/gc_implementation/README.md)
      * [concurrentMarkSweep](/docs/10-java/src/openjdk/hotspot.src/share.vm/gc_implementation/concurrentMarkSweep/README.md)
        * [concurrentMarkSweepGeneration](/docs/10-java/src/openjdk/hotspot.src/share.vm/gc_implementation/concurrentMarkSweep/concurrentMarkSweepGeneration.md)
      * [g1](/docs/10-java/src/openjdk/hotspot.src/share.vm/gc_implementation/g1/README.md)
      * [parallelScavenge](/docs/10-java/src/openjdk/hotspot.src/share.vm/gc_implementation/parallelScavenge/README.md)
      * [parNew](/docs/10-java/src/openjdk/hotspot.src/share.vm/gc_implementation/parNew/README.md)
    * [gc_inferface](/docs/10-java/src/openjdk/hotspot.src/share.vm/gc_inferface/README.md)
      * [collectedHeap](/docs/10-java/src/openjdk/hotspot.src/share.vm/gc_inferface/collectedHeap.md)
      * [collectedHeap.inline](/docs/10-java/src/openjdk/hotspot.src/share.vm/gc_inferface/collectedHeap.inline.md)
      * [gcCause](/docs/10-java/src/openjdk/hotspot.src/share.vm/gc_inferface/gcCause.md)
    * interpreter
      * [interpreterRuntime](/docs/10-java/src/openjdk/hotspot.src/share.vm/interpreter/interpreterRuntime.md) `synchronized`监视器锁
      * [bytecodeInterpreter](/docs/10-java/src/openjdk/hotspot.src/share.vm/interpreter/bytecodeInterpreter.md) 字节码解释执行器
    * libadt
    * [memory](/docs/10-java/src/openjdk/hotspot.src/share.vm/memory/README.md) 内存管理相关（老的分代式GC框架也在这里）
      * [space](/docs/10-java/src/openjdk/hotspot.src/share.vm/memory/space.md)
      * [generation](/docs/10-java/src/openjdk/hotspot.src/share.vm/memory/generation.md)
      * [metaspace](/docs/10-java/src/openjdk/hotspot.src/share.vm/memory/metaspace.md)
      * [tenuredGeneration](/docs/10-java/src/openjdk/hotspot.src/share.vm/memory/tenuredGeneration.md)
      * [threadLocalAllocBuffer](/docs/10-java/src/openjdk/hotspot.src/share.vm/memory/threadLocalAllocBuffer.md)
    * [oops](/docs/10-java/src/openjdk/hotspot.src/share.vm/oops/README.md) HotSpot VM的对象系统的实现
      * [markOop](/docs/10-java/src/openjdk/hotspot.src/share.vm/oops/markOop.md)
    * [opto](/docs/10-java/src/openjdk/hotspot.src/share.vm/opto/README.md) server编译器（又称“C2”或“Opto”）
    * precompiled
    * prims HotSpot VM的对外接口，包括部分标准库的native部分和JVMTI实现
    * [runtime](/docs/10-java/src/openjdk/hotspot.src/share.vm/runtime/README.md) 运行时支持库（包括线程管理、编译器调度、锁、反射等）
      * [os](/docs/10-java/src/openjdk/hotspot.src/share.vm/runtime/os.md)
      * [thread](/docs/10-java/src/openjdk/hotspot.src/share.vm/runtime/thread.md)
      * [vmThread](/docs/10-java/src/openjdk/hotspot.src/share.vm/services/vmThread.md)
      * [objectMonitor](/docs/10-java/src/openjdk/hotspot.src/share.vm/runtime/objectMonitor.md)
      * [basicLock](/docs/10-java/src/openjdk/hotspot.src/share.vm/runtime/basicLock.md)
      * [atomic](/docs/10-java/src/openjdk/hotspot.src/share.vm/runtime/atomic.md)
      * [javaCalls](/docs/10-java/src/openjdk/hotspot.src/share.vm/runtime/javaCalls.md)
    * [services](/docs/10-java/src/openjdk/hotspot.src/share.vm/services/README.md)
    * shark
    * [trace](/docs/10-java/src/openjdk/hotspot.src/share.vm/trace/README.md)
    * utilities


## link
* [openjdk7 download](http://download.java.net/openjdk/jdk7/promoted/b147/openjdk-7-fcs-src-b147-27_jun_2011.zip)
* [《HotSpot实战》陈涛](/99-book/notes/10-java/HotSpot实战.md)