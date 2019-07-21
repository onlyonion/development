java.util.concurrent.atomic.AtomicReference


## define
```plantuml
@startuml

class AtomicReference<V> {
    - volatile V value
}

AtomicReference o-- Unsafe

@enduml
```

### Unsafe
* compareAndSwapObject
* getObjectVolatile
* getAndSetObject

```
public final Object getAndSetObject(Object var1, long var2, Object var4) {
    Object var5;
    do {
        var5 = this.getObjectVolatile(var1, var2);
    } while(!this.compareAndSwapObject(var1, var2, var5, var4));

    return var5;
}
```