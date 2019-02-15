java.util.concurrent.ConcurrentHashMap

## 类图
* 53个内部类
* 91个方法
* 1个静态代码块
* 37个字段，其中27个常量、10个字段

```yuml
// {type:class}

[Map]^-.-[AbstractMap]
[AbstractMap]^-[ConcurrentHashMap]
[Map]^-[ConcurrentMap]
[ConcurrentMap]^-.-[ConcurrentHashMap]


```

## Java7 ConcurrentHashMap

Segment[], HashEntry[], slist


#### 初始化

#### put 过程分析

* Segment 内部是由 数组+链表 组成的。
* 初始化槽: ensureSegment
* 获取写入锁: scanAndLockForPut
* 扩容: rehash segment 数组不能扩容，扩容是 segment 数组某个位置内部的数组 HashEntry\[] 进行扩容，扩容后，容量为原来的 2 倍。

#### get 过程分析

#### 并发问题分析