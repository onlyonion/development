java.lang.Class

* volatile

## fields
```java
private volatile transient Constructor<T> cachedConstructor;
private volatile transient Class<?>       newInstanceCallerCache;
private transient String name;
private final ClassLoader classLoader;
private volatile transient SoftReference<ReflectionData<T>> reflectionData;
private volatile transient T[] enumConstants = null;

```
