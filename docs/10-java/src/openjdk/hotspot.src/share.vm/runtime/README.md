hotspot/src/share/vm/runtime

## package

```
advancedThresholdPolicy.cpp
advancedThresholdPolicy.hpp
arguments.cpp
arguments.hpp
atomic.cpp
atomic.hpp
atomic.inline.hpp
basicLock.cpp
basicLock.hpp 偏向锁
biasedLocking.cpp
biasedLocking.hpp
compilationPolicy.cpp
compilationPolicy.hpp
deoptimization.cpp
deoptimization.hpp
dtraceJSDT.cpp
dtraceJSDT.hpp
extendedPC.hpp
fieldDescriptor.cpp
fieldDescriptor.hpp
fieldType.cpp
fieldType.hpp
fprofiler.cpp
fprofiler.hpp
frame.cpp
frame.hpp
frame.inline.hpp
globals.cpp
globals.hpp
globals_ext.hpp
globals_extension.hpp
handles.cpp
handles.hpp
handles.inline.hpp
icache.cpp
icache.hpp
init.cpp
init.hpp
interfaceSupport.cpp
interfaceSupport.hpp
java.cpp
java.hpp
javaCalls.cpp
javaCalls.hpp
javaFrameAnchor.hpp
jfieldIDWorkaround.hpp
jniHandles.cpp
jniHandles.hpp
jniPeriodicChecker.cpp
jniPeriodicChecker.hpp
memprofiler.cpp
memprofiler.hpp
monitorChunk.cpp
monitorChunk.hpp
mutex.cpp
mutex.hpp
mutexLocker.cpp
mutexLocker.hpp
objectMonitor.cpp
objectMonitor.hpp
objectMonitor.inline.hpp
orderAccess.cpp
orderAccess.hpp
os.cpp
os.hpp
os_ext.hpp
osThread.cpp 
osThread.hpp 操作系统线程
park.cpp
park.hpp
perfData.cpp
perfData.hpp
perfMemory.cpp
perfMemory.hpp
prefetch.hpp
reflection.cpp
reflection.hpp
reflectionUtils.cpp
reflectionUtils.hpp
registerMap.hpp
relocator.cpp
relocator.hpp
rframe.cpp
rframe.hpp
safepoint.cpp
safepoint.hpp
serviceThread.cpp
serviceThread.hpp
sharedRuntime.cpp
sharedRuntime.hpp
sharedRuntimeTrans.cpp
sharedRuntimeTrig.cpp
signature.cpp
signature.hpp
simpleThresholdPolicy.cpp
simpleThresholdPolicy.hpp
simpleThresholdPolicy.inline.hpp
stackValue.cpp
stackValue.hpp
stackValueCollection.cpp
stackValueCollection.hpp
statSampler.cpp
statSampler.hpp
stubCodeGenerator.cpp
stubCodeGenerator.hpp
stubRoutines.cpp
stubRoutines.hpp
sweeper.cpp
sweeper.hpp
synchronizer.cpp
synchronizer.hpp
task.cpp
task.hpp
thread.cpp
thread.hpp
thread.inline.hpp
threadCritical.hpp
threadLocalStorage.cpp
threadLocalStorage.hpp
timer.cpp
timer.hpp
unhandledOops.cpp
unhandledOops.hpp
vframe.cpp
vframe.hpp
vframe_hp.cpp
vframe_hp.hpp
vframeArray.cpp
vframeArray.hpp
virtualspace.cpp
virtualspace.hpp
vm_operations.cpp
vm_operations.hpp
vm_version.cpp
vm_version.hpp
vmStructs.cpp
vmStructs.hpp
vmThread.cpp
vmThread.hpp
```


### 并发编程
* jmm
* synchronized
* volatitle

### synchronized
偏向锁 --> 轻量级锁 --> 自旋锁 --> 重量级锁

C++中的监视器锁数据结构
oopDesc --继承--> markOopDesc --方法monitor()--> ObjectMonitor --> enter、exit 获取、释放锁

oop.hpp