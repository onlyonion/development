

### hotspot.src

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
    share
        tool
        vm
            adlc 平台描述文件（上面的cpu或os_cpu里的*.ad文件）的编译器
            asm
                assembler.hpp
                codeBuffer.hpp
            c1 client编译器（又称“C1”）
            ci 动态编译器的公共服务/从动态编译器到VM的接口
            classfile 类文件的处理（包括类加载和系统符号表等）
                bytecodeAssembler.hpp
                classLoader.hpp
            code 动态生成的代码的管理
                codeCahce.hpp
            compiler 从VM调用动态编译器的接口
            gc_impementation
                concurrentMarkSweep
                g1
                parallelScavenge ParallelScavenge GC的实现（server VM默认，不使用老的分代式GC框架）
                parNew
                shared
            gc_interface
            interpreter
                bytecode.hpp
                linkResovler.hpp
            libadt
            memory 内存管理相关（老的分代式GC框架也在这里）
                allocation.hpp
                defNewGeneration.hpp
                generation.hpp
                space.hpp
                    EdenSpace
                    TenuredSpace
            oops HotSpot VM的对象系统的实现
                markOop.hpp 对象头
            opto server编译器（又称“C2”或“Opto”）
                escape.hpp 逃逸分析
            precompiled
            prims HotSpot VM的对外接口，包括部分标准库的native部分和JVMTI实现
            runtime 运行时支持库（包括线程管理、编译器调度、锁、反射等）
                atomic.hpp
                ObjectMonitor.hpp
                os.hpp
                osThread.hpp
                reflection.hpp
                synchronizer.hpp
                thread.hpp
                vframe.hpp
            services
            shark
            trace
            utilities
```

### risc 
reduced instruction set computer
sparc Scalable Processor ARChitecture 可扩充处理器架构

complex instruction set computer

### 新生代
defNewGeneration.hpp
```c++
EdenSpace*       eden() const { return _eden_space; }
ContiguousSpace* from() const { return _from_space; }
ContiguousSpace* to()   const { return _to_space;   }
```

### 面向对象实现
markOop.hpp

### synchronized
偏向锁 --> 轻量级锁 --> 重量级锁

C++中的监视器锁数据结构
oopDesc --继承--> markOopDesc --方法monitor()--> ObjectMonitor --> enter、exit 获取、释放锁

oop.hpp

### CAS
os/windows/vm/atomic_windows_x86.inline.hpp
```c++
inline jlong    Atomic::cmpxchg    (jlong    exchange_value, volatile jlong*    dest, jlong    compare_value) {
  int mp = os::is_MP();
  jint ex_lo  = (jint)exchange_value;
  jint ex_hi  = *( ((jint*)&exchange_value) + 1 );
  jint cmp_lo = (jint)compare_value;
  jint cmp_hi = *( ((jint*)&compare_value) + 1 );
  __asm {
    push ebx
    push edi
    mov eax, cmp_lo
    mov edx, cmp_hi
    mov edi, dest
    mov ebx, ex_lo
    mov ecx, ex_hi
    LOCK_IF_MP(mp)
    cmpxchg8b qword ptr [edi]
    pop edi
    pop ebx
  }
}
// os::is_MP()  这个是runtime/os.hpp，实际就是返回是否多处理器
// LOCK_IF_MP:会根据当前处理器的类型来决定是否为cmpxchg指令添加lock前缀。如果程序是在多处理器上运行，就为cmpxchg指令加上lock前缀（lock cmpxchg）。
// 反之，如果程序是在单处理器上运行，就省略lock前缀（单处理器自身会维护单处理器内的顺序一致性，不需要lock前缀提供的内存屏障效果）
```