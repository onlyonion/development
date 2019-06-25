java.util.concurrent.atomic.AtomicInteger

## define
* volatile int
* cas
* for/while

```plantuml
@startuml

class AtomicInteger {
    - volatile int value
}


AtomicInteger o-- Unsafe

class Unsafe {
    + final int getAndSetInt(Object var1, long var2, int var4) 

}

@enduml
```

### Unsafe
* getAndSetInt
* getIntVolatile
* compareAndSwapInt


* getObjectVolatile
* getAndSetObject
* compareAndSwapObject

```
public final Object getAndSetObject(Object var1, long var2, Object var4) {
    Object var5;
    do {
        var5 = this.getObjectVolatile(var1, var2);
    } while(!this.compareAndSwapObject(var1, var2, var5, var4));

    return var5;
}
```