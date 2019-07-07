java.util.concurrent.atomic.LongAdder

* 基于cell，分段锁思想，空间换时间，更适合高并发场景

## hierarchy
```
Number (java.lang)
    Striped64 (java.util.concurrent.atomic)
        LongAdder (java.util.concurrent.atomic)
            LongAdderCounter (io.netty.util.internal)
            LongAdderCounter (io.netty.util.internal)
```