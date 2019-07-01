sun.misc.Unsafe

## define
native的，需要调用JNI接口，也即通过操作系统来保证这些方法的执行

### 内存管理
* reallocateMemory
* allocateMemory
* freeMemory
* setMemory
* copyMemory
* pageSize
* addressSize

### thread + schedule
* park
* unpark

### synchronized
* monitorEnter
* monitorExit

### cas
* compareAndSwapObject
* compareAndSwapInt
* compareAndSwapLong

### volatile
* getObjectVolatile
* getIntVolatile
* getBooleanVolatile
* getByteVolatile
* getShortVolatile
* getCharVolatile

### volatile + cas + for/while 操作原子性
* getAndAddInt
* getAndAddLong
* getAndSetInt
* getAndSetLong

```
public final long getAndSetLong(Object o, long offset, long newValue) {
    long v;
    do {
        v = getLongVolatile(o, offset);
    } while (!compareAndSwapLong(o, offset, v, newValue));
    return v;
}
```

* getAndSetObject
```
public final Object getAndSetObject(Object o, long offset, Object newValue) {
    Object v;
    do {
        v = getObjectVolatile(o, offset);
    } while (!compareAndSwapObject(o, offset, v, newValue));
    return v;
}
```

## overview
```java
public final class Unsafe {
    //扩充内存  
    public native long reallocateMemory(long address, long bytes);  
      
    //分配内存  
    public native long allocateMemory(long bytes);  
      
    //释放内存  
    public native void freeMemory(long address);  
      
    //在给定的内存块中设置值  
    public native void setMemory(Object o, long offset, long bytes, byte value);  
      
    //从一个内存块拷贝到另一个内存块  
    public native void copyMemory(Object srcBase, long srcOffset, Object destBase, long destOffset, long bytes);  
      
    //获取值，不管java的访问限制，其他有类似的getInt，getDouble，getLong，getChar等等  
    public native Object getObject(Object o, long offset);  
      
    //设置值，不管java的访问限制，其他有类似的putInt,putDouble，putLong，putChar等等  
    public native void putObject(Object o, long offset);  
      
    //从一个给定的内存地址获取本地指针，如果不是allocateMemory方法的，结果将不确定  
    public native long getAddress(long address);  
      
    //存储一个本地指针到一个给定的内存地址,如果地址不是allocateMemory方法的，结果将不确定  
    public native void putAddress(long address, long x);  
      
    //该方法返回给定field的内存地址偏移量，这个值对于给定的filed是唯一的且是固定不变的  
    public native long staticFieldOffset(Field f);  
      
    //报告一个给定的字段的位置，不管这个字段是private，public还是保护类型，和staticFieldBase结合使用  
    public native long objectFieldOffset(Field f);  
      
    //获取一个给定字段的位置  
    public native Object staticFieldBase(Field f);  
      
    //确保给定class被初始化，这往往需要结合基类的静态域（field）  
    public native void ensureClassInitialized(Class c);  
      
    //可以获取数组第一个元素的偏移地址  
    public native int arrayBaseOffset(Class arrayClass);  
      
    //可以获取数组的转换因子，也就是数组中元素的增量地址。将arrayBaseOffset与arrayIndexScale配合使用， 可以定位数组中每个元素在内存中的位置  
    public native int arrayIndexScale(Class arrayClass);  
      
    //获取本机内存的页数，这个值永远都是2的幂次方  
    public native int pageSize();  
      
    //告诉虚拟机定义了一个没有安全检查的类，默认情况下这个类加载器和保护域来着调用者类  
    public native Class defineClass(String name, byte[] b, int off, int len, ClassLoader loader, ProtectionDomain protectionDomain);  
      
    //定义一个类，但是不让它知道类加载器和系统字典  
    public native Class defineAnonymousClass(Class hostClass, byte[] data, Object[] cpPatches);  
      
    //锁定对象，必须是没有被锁的
    public native void monitorEnter(Object o);  
      
    //解锁对象  
    public native void monitorExit(Object o);  
      
    //试图锁定对象，返回true或false是否锁定成功，如果锁定，必须用monitorExit解锁  
    public native boolean tryMonitorEnter(Object o);  
      
    //引发异常，没有通知  
    public native void throwException(Throwable ee);  
      
    //CAS，如果对象偏移量上的值=期待值，更新为x,返回true.否则false.类似的有compareAndSwapInt,compareAndSwapLong,compareAndSwapBoolean,compareAndSwapChar等等。  
    public final native boolean compareAndSwapObject(Object o, long offset,  Object expected, Object x);  
      
    // 该方法获取对象中offset偏移地址对应的整型field的值,支持volatile load语义。类似的方法有getIntVolatile，getBooleanVolatile等等  
    public native Object getObjectVolatile(Object o, long offset);   
      
    //线程调用该方法，线程将一直阻塞直到超时，或者是中断条件出现。  
    public native void park(boolean isAbsolute, long time);  
      
    //终止挂起的线程，恢复正常.java.util.concurrent包中挂起操作都是在LockSupport类实现的，也正是使用这两个方法
    public native void unpark(Object thread);  
      
    //获取系统在不同时间系统的负载情况  
    public native int getLoadAverage(double[] loadavg, int nelems);  
      
    //创建一个类的实例，不需要调用它的构造函数、初使化代码、各种JVM安全检查以及其它的一些底层的东西。即使构造函数是私有，我们也可以通过这个方法创建它的实例,对于单例模式，简直是噩梦，哈哈  
    public native Object allocateInstance(Class cls) throws InstantiationException;
}
```