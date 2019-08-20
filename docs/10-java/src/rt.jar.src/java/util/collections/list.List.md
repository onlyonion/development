java.util.List

## hierarchy
```
List (java.util)
    Collection (java.util)
        Iterable (java.lang)
```

## define
```java
public interface List<E> extends Collection<E> {
    boolean add(E e);
    void add(int index, E element);
    E set(int index, E element);

    boolean remove(Object o);
    E remove(int index);
    boolean removeAll(Collection<?> c);
}
```