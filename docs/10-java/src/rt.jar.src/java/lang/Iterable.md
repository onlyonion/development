java.lang.Iterable

## hierarchy
```
Iterable (java.lang)
    Collection (java.util)
    ServiceLoader (java.util)
    SpinedBuffer (java.util.stream)
    Path (java.nio.file)
    DirectoryStream (java.nio.file)
    SQLException (java.sql)
    OfPrimitive in SpinedBuffer (java.util.stream)
```

## define
```java
public interface Iterable<T> {
    
    Iterator<T> iterator();

    default void forEach(Consumer<? super T> action) {
        Objects.requireNonNull(action);
        for (T t : this) {
            action.accept(t);
        }
    }

    default Spliterator<T> spliterator() {
        return Spliterators.spliteratorUnknownSize(iterator(), 0);
    }
}


public interface Iterator<E> {
    
    boolean hasNext();

    E next();

    default void remove() {
        throw new UnsupportedOperationException("remove");
    }

    default void forEachRemaining(Consumer<? super E> action) {
        Objects.requireNonNull(action);
        while (hasNext())
            action.accept(next());
    }
}
```
