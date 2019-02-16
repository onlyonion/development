hotspot/src/share/vm/runtime

## package
```
atomic.hpp
basicLock.hpp 偏向锁
frame.hpp
javaCalls.hpp
monitorChunk.hpp
mutex.hpp
objectMonitor.hpp
os.hpp
osThread.hpp 操作系统线程
park.hpp
perfData.hpp
perfMemory.hpp
reflection.hpp
safepoint.hpp
serviceThread.hpp
synchronizer.hpp 监视器锁
thread.hpp
vframe.hpp
vm_operations.hpp
vm_version.hpp
vmStructs.hpp
vmThread.hpp
```

### 并发编程
* jmm
* synchronized
* volatitle

### synchronized
偏向锁 --> 轻量级锁 --> 重量级锁

C++中的监视器锁数据结构
oopDesc --继承--> markOopDesc --方法monitor()--> ObjectMonitor --> enter、exit 获取、释放锁

oop.hpp