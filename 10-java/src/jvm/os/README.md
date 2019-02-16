```
linux
posix
solaris
windows
```

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