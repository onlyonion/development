java.util.TreeMap

## hierarchy
```
AbstractMap (java.util)
    TreeMap (java.util)
TreeMap (java.util)
    AbstractMap (java.util)
        Object (java.lang)
        Map (java.util)
    NavigableMap (java.util)
        SortedMap (java.util)
            Map (java.util)
    Cloneable (java.lang)
    Serializable (java.io)
```

## define
* 策略模式 Comparator
* 数据结构 红黑树

```plantuml
@startuml

interface Map<K,V>
interface Map.Entry<K,V>
Map +-- Map.Entry
Map ^.. AbstractMap
abstract class AbstractMap<K,V>  

AbstractMap ^-- TreeMap

interface SortedMap<K,V>
interface NavigableMap<K,V> 

Map ^-- SortedMap
SortedMap ^-- NavigableMap
NavigableMap ^-- TreeMap

class TreeMap<K,V> {
    .. 比较器 ..
    - final Comparator<? super K> comparator
    .. 根节点 ..
    - transient Entry<K,V> root
    .. 节点总数 ..
    - transient int size = 0
    - transient int modCount = 0
}

TreeMap +-- TreeMap.Entry
class TreeMap.Entry<K,V> {
    K key
    V value
    Entry<K,V> left
    Entry<K,V> right
    Entry<K,V> parent
    boolean color = BLACK
}
Map.Entry ^.. TreeMap.Entry
TreeMap.Entry o-- TreeMap.Entry

@enduml
```

```java
public class TreeMap<K,V>
    extends AbstractMap<K,V>
    implements NavigableMap<K,V>, Cloneable, java.io.Serializable
{
    /**
     * The comparator used to maintain order in this tree map, or
     * null if it uses the natural ordering of its keys.
     * @serial
     */
    private final Comparator<? super K> comparator;
    private transient Entry<K,V> root;
    private transient int size = 0;
    private transient int modCount = 0;
}    
```

## methods

### get
```java
    public V get(Object key) {
        Entry<K,V> p = getEntry(key);
        return (p==null ? null : p.value);
    }
    
    final Entry<K,V> getEntry(Object key) {
        // Offload comparator-based version for sake of performance
        if (comparator != null)
            return getEntryUsingComparator(key);
        if (key == null)
            throw new NullPointerException();
        @SuppressWarnings("unchecked")
            Comparable<? super K> k = (Comparable<? super K>) key;
        Entry<K,V> p = root;
        while (p != null) {
            int cmp = k.compareTo(p.key);
            if (cmp < 0)
                p = p.left;
            else if (cmp > 0)
                p = p.right;
            else
                return p;
        }
        return null;
    }
    
    final Entry<K,V> getEntryUsingComparator(Object key) {
        K k = (K) key;
        Comparator<? super K> cpr = comparator;
        if (cpr != null) {
            Entry<K,V> p = root;
            while (p != null) {
                int cmp = cpr.compare(k, p.key);
                if (cmp < 0)
                    p = p.left;
                else if (cmp > 0)
                    p = p.right;
                else
                    return p;
            }
        }
        return null;
    }
```

### firstKey
```java
    public K firstKey() {
        return key(getFirstEntry());
    }
    
    final Entry<K,V> getFirstEntry() {
        Entry<K,V> p = root;
        if (p != null)
            while (p.left != null)
                p = p.left;
        return p;
    }
    
    static <K> K key(Entry<K,?> e) {
        if (e==null)
            throw new NoSuchElementException();
        return e.key;
    }
```

### ceilingEntry
```java
    public Map.Entry<K,V> ceilingEntry(K key) {
        return exportEntry(getCeilingEntry(key));
    }
    
    final Entry<K,V> getCeilingEntry(K key) {
        Entry<K,V> p = root;
        while (p != null) {
            int cmp = compare(key, p.key);
            if (cmp < 0) {
                if (p.left != null)
                    p = p.left;
                else
                    return p;
            } else if (cmp > 0) {
                if (p.right != null) {
                    p = p.right;
                } else {
                    Entry<K,V> parent = p.parent;
                    Entry<K,V> ch = p;
                    while (parent != null && ch == parent.right) {
                        ch = parent;
                        parent = parent.parent;
                    }
                    return parent;
                }
            } else
                return p;
        }
        return null;
    }
    
    static <K,V> Map.Entry<K,V> exportEntry(TreeMap.Entry<K,V> e) {
        return (e == null) ? null :
            new AbstractMap.SimpleImmutableEntry<>(e);
    }
```

## desc

### 红黑树
红黑树（Red Black Tree） 是一种自平衡二叉查找树，是在计算机科学中用到的一种数据结构，典型的用途是实现关联数组。
* 性质1. 节点是红色或黑色。
* 性质2. 根节点是黑色。
* 性质3. 每个红色节点的两个子节点都是黑色。(从每个叶子到根的所有路径上不能有两个连续的红色节点)
* 性质4. 从任一节点到其每个叶子的所有路径都包含相同数目的黑色节点。

这些约束强制了红黑树的关键性质: 从根到叶子的**最长的可能路径**不多于**最短的可能路径**的两倍长。结果是这个树大致上是平衡的。
因为操作比如插入、删除和查找某个值的最坏情况时间都要求与树的高度成比例，这个在高度上的理论上限允许红黑树在最坏情况下都是高效的，
而不同于普通的二叉查找树。


### TreeMap
- TreeMap 是一个有序的key-value集合，它是通过红黑树实现的。
- TreeMap 继承于AbstractMap，所以它是一个Map，即一个key-value集合。
- TreeMap 实现了NavigableMap接口，意味着它支持一系列的导航方法。比如返回有序的key集合。
- TreeMap 实现了Cloneable接口，意味着它能被克隆。
- TreeMap 实现了java.io.Serializable接口，意味着它支持序列化。

TreeMap基于红黑树（Red-Black tree）实现。该映射根据其键的自然顺序进行排序，或者根据创建映射时提供的 Comparator 进行排序，具体取决于使用的构造方法。
TreeMap的基本操作 containsKey、get、put 和 remove 的时间复杂度是 log(n) 。
另外，TreeMap是非同步的。 它的iterator 方法返回的迭代器是fail-fastl的。

* [Java 集合系列12之 TreeMap详细介绍(源码解析)和使用示例](https://www.cnblogs.com/skywang12345/p/3310928.html)
* [红黑树(一)之 原理和算法详细介绍](https://www.cnblogs.com/skywang12345/p/3245399.html)

### 红黑树的应用
红黑树的应用比较广泛，主要是用它来存储有序的数据，它的时间复杂度是O(lgn)，效率非常之高。
例如，Java集合中的TreeSet和TreeMap，C++ STL中的set、map，以及Linux虚拟内存的管理，都是通过红黑树去实现的。

### PS
节点被认为是一个实体，有处理能力，比如说网络上的一台计算机；
结点则只是一个交叉点，像“结绳记事”，打个结，做个标记，仅此而已。一般算法中点都是结点。