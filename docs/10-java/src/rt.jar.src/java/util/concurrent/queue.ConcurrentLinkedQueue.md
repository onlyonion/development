java.util.concurrent.ConcurrentLinkedQueue

## hierarchy
```
AbstractCollection (java.util)
    AbstractQueue (java.util)
        ConcurrentLinkedQueue (java.util.concurrent)
```
## define

```java
public class ConcurrentLinkedQueue<E> extends AbstractQueue<E>
        implements Queue<E>, java.io.Serializable {
    
    private transient volatile Node<E> head;
    private transient volatile Node<E> tail;
    
}
```

## methods

### size 时间复杂度O(n)
Concurrent系列的集合类的size方法并不是常量时间返回。使用分桶的策略减少集合的线程竞争，在获取整体大小时需要进行统计。
```java
    public int size() {
        int count = 0;
        for (Node<E> p = first(); p != null; p = succ(p))
            if (p.item != null)
                // Collection.size() spec says to max out
                if (++count == Integer.MAX_VALUE)
                    break;
        return count;
    }
```

## inner class

### Node
```java
    private static class Node<E> {
        volatile E item;
        volatile Node<E> next;
    }
```