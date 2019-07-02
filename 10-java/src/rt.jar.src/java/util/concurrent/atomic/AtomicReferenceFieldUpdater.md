java.util.concurrent.atomic.AtomicReferenceFieldUpdater

## hierarchy
```
AtomicReferenceFieldUpdater (java.util.concurrent.atomic)
    AtomicReferenceFieldUpdaterImpl in AtomicReferenceFieldUpdater (java.util.concurrent.atomic)
    UnsafeAtomicReferenceFieldUpdater (com.alibaba.rocketmq.shade.io.netty.util.internal)
```
## define


## methods
### public abstract boolean compareAndSet(T obj, V expect, V update)
cas操作数：内存位置、预期值、新值