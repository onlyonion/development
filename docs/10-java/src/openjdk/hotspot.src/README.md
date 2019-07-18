## hotspot
* 编译原理 前端、后端
* OS程序的编译、链接、装入、文件管理、IO
* OS内存管理、分配、虚拟内存
* OS进程、线程、调度、同步、锁
* OS用户接口
  
### hotspot.src
hotspot/src/

- 平台相关性 cpu、os、os_cpu
- 平台无关性
  * 编译器、解释器、汇编、机器码 `compiler(c1 opto) interpreter asm code`
  * 类文件结构、类加载机制 字节码指令集、执行引擎、对象系统`oops`
  * 内存管理 运行时数据区域、垃圾回收
  * 运行时环境 线程管理、编译器调度、并发、锁、反射等
  * 管理服务、对外服务、JMX接口 连接机制、类加载服务、诊断、内存管理、内存模型、线程服务
  * 抽象数据结构 libadt(dict port set vectset)《数据结构》

### package
* cpu CPU相关代码（汇编器、模板解释器、ad文件、部分runtime函数在这里实现）         
  * sparc
  * x86
  * zero
* os
  * bsd
  * linux
  * posix
  * solaris
  * windows
    * vm
      * atomic_windows_x86.inline.hpp
* os_cpu
  * linux_sparc
  * linux_x86
  * linux_zero
  * solaris_sparc
  * solaris_x86
  * windows_x86
* share
  * tool
    * hsdis
    * IdealGraphVisualizer
    * launcher 启动入口
    * LogCompilation
    * ProjectCreator
  * vm
    * adlc 平台描述文件（上面的cpu或os_cpu里的*.ad文件）的编译器
    * asm 汇编
    * c1 client编译器（又称“C1”）
    * ci 动态编译器的公共服务/从动态编译器到VM的接口
    * classfile 类文件的处理（包括类加载和系统符号表等）
    * code 动态生成的代码的管理
    * compiler 从VM调用动态编译器的接口
    * gc_impementation
    * gc_interface
    * interpreter
      * [interpreterRuntime](/docs/10-java/src/openjdk/hotspot.src/share.vm/interpreter/interpreterRuntime.md) `synchronized`监视器锁
    * libadt
    * memory 内存管理相关（老的分代式GC框架也在这里）
    * oops HotSpot VM的对象系统的实现
    * opto server编译器（又称“C2”或“Opto”）
    * precompiled
    * prims HotSpot VM的对外接口，包括部分标准库的native部分和JVMTI实现
    * runtime 运行时支持库（包括线程管理、编译器调度、锁、反射等）
    * services
    * shark
    * trace
    * utilities


## link
* [openjdk7 download](http://download.java.net/openjdk/jdk7/promoted/b147/openjdk-7-fcs-src-b147-27_jun_2011.zip)
* [《HotSpot实战》陈涛](/99-book/notes/10-java/HotSpot实战.md)