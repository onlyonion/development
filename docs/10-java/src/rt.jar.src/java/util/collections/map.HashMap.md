# HashMap
* jdk7 Entry数组 + 链表；没有使用volatile
* jdk8 Node数组 + 链表 + 红黑树；没有使用volatile

# 1. jdk7 HashMap

<img src="10-java/img/java7-map-HashMap.png" width="50%" height="50%">

## 1.1 hierarchy
```
AbstractMap (java.util)
    HashMap (java.util)
    LinkedHashMap (java.util)
```
线程不安全、并发修改快速失败

## 1.2 define
* 静态域
* 实例域
  * Entry<K, V>[] table
  * capacity：当前数组容量，始终保持 2^n，可以扩容，扩容后数组大小为当前的 2 倍。
  * loadFactor：负载因子，默认为 0.75。
  * threshold：扩容的阈值，等于 capacity * loadFactor
* 内部类
  * Holder
  * Entry
  * HashIterator
  * ValueIterator
  * KeyIterator
  * EntryIterator
  * KeySet
  * Values
  * EntrySet

```plantuml
@startuml

''''''''''''''''''''''''' Map ''''''''''''''''''''''''''''''
interface Map<K,V>
abstract class AbstractMap<K,V> 
Map ^.. AbstractMap

''''''''''''''''''''''''' HashMap ''''''''''''''''''''''''''''''
class HashMap<K,V> {
    .. 静态域 ..
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4
    static final int MAXIMUM_CAPACITY = 1 << 30
    static final float DEFAULT_LOAD_FACTOR = 0.75f
    .. 实例域 ..
    static final Entry<?,?>[] EMPTY_TABLE = {};
    transient Entry<K,V>[] table = (Entry<K,V>[]) EMPTY_TABLE;
    transient int size;
    transient int modCount
    int threshold
    final float loadFactor
    ransient int hashSeed = 0
    .. 数组填充（为table分配实际内存空间） ..
    - void inflateTable(int toSize) 
    final boolean initHashSeedAsNeeded(int capacity)
    .. 哈希函数 ..
    final int hash(Object k)
}

note right
Entry数组 + 链表
没有使用volatile
end note

Map ^.. HashMap
AbstractMap ^-- HashMap

''''''''''''''''''''''''' Entry ''''''''''''''''''''''''''''''
interface Map.Entry<K,V> 
Map.Entry ^.. Entry
class Entry<K,V> {
    final K key
    V value
    Entry<K,V> next
    int hash
}

HashMap +-- Entry

@enduml
```

## 1.3 methods

### put

key --> hashCode --> h --> index

1. 如果数组为空数组，则数组初始化
2. key为null的情况特殊处理，置于table[0]
3. 确定数组索引位置。根据key计算hash值，找到相应的数组下标：hash & (length – 1) 位运算
4. 如果数组位置存在元素，则替换，并返回老值
5. 添加节点到链表中
  * 容量大于阈值，需要rehash()，重新哈希计算数组索引位置
  * 创建新Entry，置于数组索引位置，压入单向链表，数量自增


```java
    public V put(K key, V value) {
        //如果table数组为空数组{}，进行数组填充（为table分配实际内存空间），入参为threshold，此时threshold为initialCapacity 默认是1<<4(24=16)
        if (table == EMPTY_TABLE) {
            inflateTable(threshold);
        }
       //如果key为null，存储位置为table[0]或table[0]的冲突链上
        if (key == null)
            return putForNullKey(value);
        int hash = hash(key);//对key的hashcode进一步计算，确保散列均匀
        int i = indexFor(hash, table.length);//获取在table中的实际位置
        for (Entry<K,V> e = table[i]; e != null; e = e.next) {
        //如果该对应数据已存在，执行覆盖操作。用新value替换旧value，并返回旧value
            Object k;
            if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
                V oldValue = e.value;
                e.value = value;
                e.recordAccess(this);
                return oldValue;
            }
        }
        modCount++;//保证并发访问时，若HashMap内部结构发生变化，快速响应失败
        addEntry(hash, key, value, i);//新增一个entry
        return null;
    }
    
    private void inflateTable(int toSize) {
        int capacity = roundUpToPowerOf2(toSize);//capacity一定是2的次幂
        threshold = (int) Math.min(capacity * loadFactor, MAXIMUM_CAPACITY + 1);//此处为threshold赋值，取capacity*loadFactor和MAXIMUM_CAPACITY+1的最小值，capaticy一定不会超过MAXIMUM_CAPACITY，除非loadFactor大于1
        table = new Entry[capacity];
        initHashSeedAsNeeded(capacity);
    }
    
    private static int roundUpToPowerOf2(int number) {
        // assert number >= 0 : "number must be non-negative";
        return number >= MAXIMUM_CAPACITY
                ? MAXIMUM_CAPACITY
                : (number > 1) ? Integer.highestOneBit((number - 1) << 1) : 1; // Integer.highestOneBit是用来获取最左边的bit（其他bit位为0）所代表的数值.
    }
    
    final int hash(Object k) {
        int h = hashSeed;
        if (0 != h && k instanceof String) {
            return sun.misc.Hashing.stringHash32((String) k);
        }

        h ^= k.hashCode();

        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }
    
    static int indexFor(int h, int length) {
        return h & (length-1); // length -1 二进制位全是1， 与运算，相同的位为1
    }
    
    void addEntry(int hash, K key, V value, int bucketIndex) {
        if ((size >= threshold) && (null != table[bucketIndex])) {
            resize(2 * table.length);//当size超过临界阈值threshold，并且即将发生哈希冲突时进行扩容
            hash = (null != key) ? hash(key) : 0;
            bucketIndex = indexFor(hash, table.length);
        }

        createEntry(hash, key, value, bucketIndex);
    }
```

#### put()时序
```plantuml
@startuml

Actor -> HashMap: put()
alt 空数组
    HashMap -> HashMap: inflateTable(threshold)         
end
alt key == null
    HashMap --> Actor: putForNullKey(value)
end
HashMap -> HashMap: hash(key) 哈希运算
HashMap -> HashMap: indexFor(hash, talbe.length) 确定数组索引的位置


''''''''''''' 遍历数组，存在替换，返回老值 '''''''''''''
loop Entry<K,V> e = table[i]; e != null; e = e.next
    alt e.hash == hash && ((k = e.key) == key || key.equals(k))
        HashMap --> Actor: 返回oldValue
    end
end

''''''''''''' 不存在添加，返回null '''''''''''''
HashMap -> HashMap: addEntry(hash, key, value, i) 添加新元素
HashMap --> Actor: 返回null
@enduml
```

#### 数组初始化
在第一个元素插入 HashMap 的时候做一次数组的初始化，就是先确定初始的数组大小，并计算数组扩容的阈值。
#### 数组下标确定流程
* key计算hashCode
* hashCode再哈希 位运算
* 确定数组索引 位运算

```mermaid
graph LR
key --hashCode--> hashCode;
hashCode --hash--> h;
h --indexFor--> 存储下标;
```
#### 添加节点到链表中
找到数组下标后，会先进行 key 判重，如果没有重复，就准备将新值放入到链表的**表头**。

#### 数组扩容 
前面我们看到，在插入新值的时候，如果当前的 size 已经达到了阈值，并且要插入的数组位置上已经有元素，那么就会触发扩容，扩容后，数组大小为原来的 2 倍。

PS:resize是HashMap，rehash是ConcurrentHashMap

### resize
```java
    void resize(int newCapacity) {
        Entry[] oldTable = table;
        int oldCapacity = oldTable.length;
        if (oldCapacity == MAXIMUM_CAPACITY) {
            threshold = Integer.MAX_VALUE;
            return;
        }

        Entry[] newTable = new Entry[newCapacity];
        transfer(newTable, initHashSeedAsNeeded(newCapacity));
        table = newTable;
        threshold = (int)Math.min(newCapacity * loadFactor, MAXIMUM_CAPACITY + 1);
    }
    
    void transfer(Entry[] newTable, boolean rehash) {
        int newCapacity = newTable.length;
　　　　　//for循环中的代码，逐个遍历链表，重新计算索引位置，将老数组数据复制到新数组中去（数组不存储实际数据，所以仅仅是拷贝引用而已）
        for (Entry<K,V> e : table) {
            while(null != e) {
                Entry<K,V> next = e.next;
                if (rehash) {
                    e.hash = null == e.key ? 0 : hash(e.key);
                }
                int i = indexFor(e.hash, newCapacity);
　　　　　　　　　 //将当前entry的next链指向新的索引位置,newTable[i]有可能为空，有可能也是个entry链，如果是entry链，直接在链表头部插入。
                e.next = newTable[i];
                newTable[i] = e;
                e = next;
            }
        }
    }
    
```

### get
1. key为null特殊处理
2. 确定数组索引位置；找到相应的数组下标：hash & (length - 1)
3. 遍历该数组位置处的链表，返回哈希值相等或者key equals（==或equals）的元素

```java
    public V get(Object key) {
　　　　 //如果key为null,则直接去table[0]处去检索即可。
        if (key == null)
            return getForNullKey();
        Entry<K,V> entry = getEntry(key);
        return null == entry ? null : entry.getValue();
    }
    
    final Entry<K,V> getEntry(Object key) {
        if (size == 0) {
            return null;
        }
        //通过key的hashcode值计算hash值
        int hash = (key == null) ? 0 : hash(key);
        //indexFor (hash&length-1) 获取最终数组索引，然后遍历链表，通过equals方法比对找出对应记录
        for (Entry<K,V> e = table[indexFor(hash, table.length)];
             e != null;
             e = e.next) {
            Object k;
            if (e.hash == hash && 
                ((k = e.key) == key || (key != null && key.equals(k)))) // 重写equals的方法的时候，注意重写hashCode方法。否则可能出现hashCode相同，但不是同一个对象。
                return e;
        }
        return null;
    }
```


### remove
1. key为null特殊处理
2. 确定数组索引位置
3. 遍历链表，当哈希值相等或者key equals的时候，将此元素离开链表
  * 如果存在前趋，将前趋的next指向次元素的后继
  * 如果不存在前趋，将此元素的后继置于数组索引位置

### size

# 2. jdk8 HashMap

<img src="10-java/img/java8-map-HashMap.png" width="50%" height="50%">

## 2.1 define
* 静态域
  * TREEIFY_THRESHOLD = 8
  * UNTREEIFY_THRESHOLD = 6
  * MIN_TREEIFY_CAPACITY = 64
* 实例域
  * Node<K,V>[] table
  * Set<Map.Entry<K,V>> entrySet
  * size
  * modCount
  * threshold
  * loadFactor
* 内部类
  * Node
  * KeySet
  * Values
  * EntrySet
  * HashIterator
  * KeyIterator
  * ValueIterator
  * EntryIterator
  * HashMapSpliterator
  * KeySpliterator
  * ValueSpliterator
  * EntrySpliterator
  * TreeNode

```plantuml
@startuml

''''''''''''''''''''''''' Map ''''''''''''''''''''''''''''''
interface Map<K,V>
abstract class AbstractMap<K,V> 
Map ^.. AbstractMap

''''''''''''''''''''''''' HashMap ''''''''''''''''''''''''''''''
class HashMap<K,V> {
    .. 静态域 ..
    ~ static final int DEFAULT_INITIAL_CAPACITY = 1 << 4
    ~ static final int MAXIMUM_CAPACITY = 1 << 30
    ~ static final float DEFAULT_LOAD_FACTOR = 0.75f
    ~ static final int TREEIFY_THRESHOLD = 8
    ~ static final int UNTREEIFY_THRESHOLD = 6
    ~ static final int MIN_TREEIFY_CAPACITY = 64
    .. 实例域 ..
    ~ transient Node<K,V>[] table
    ~ transient Set<Map.Entry<K,V>> entrySet
    ~ transient int size
    ~ transient int modCount
    ~ int threshold
    ~ final float loadFactor
    .. 扩容 ..
    ~ final Node<K,V>[] resize()
}

note right
Node数组 + 链表 + 红黑树
没有使用volatile
end note

Map ^.. HashMap
AbstractMap ^-- HashMap

''''''''''''''''''''''''' Node ''''''''''''''''''''''''''''''
class Node<K,V> {
    ~ final int hash
    ~ final K key
    ~ V value
    ~ Node<K,V> next
}

HashMap +-- Node

''''''''''''''''''''''''' 红黑树 ''''''''''''''''''''''''''''''
class LinkedHashMap.Entry<K,V> {
    ~ Entry<K,V> before, after;
}
class TreeNode<K,V> {
    .. red-black tree links ..
    ~ TreeNode<K,V> parent
    .. 左节点 ..
    ~ TreeNode<K,V> left
    .. 右节点 ..
    ~ TreeNode<K,V> right
    .. needed to unlink next upon deletion ..
    ~ TreeNode<K,V> prev
    ~ boolean red
}
Node ^-- LinkedHashMap.Entry
LinkedHashMap.Entry ^-- TreeNode
HashMap +-- TreeNode

@enduml
```

```java
public class HashMap<K,V> extends AbstractMap<K,V>
    implements Map<K,V>, Cloneable, Serializable {
    
    /* static field */
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16
    static final int MAXIMUM_CAPACITY = 1 << 30;
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    static final int TREEIFY_THRESHOLD = 8;    
    static final int UNTREEIFY_THRESHOLD = 6;    
    static final int MIN_TREEIFY_CAPACITY = 64;
    
    /* field */
    transient Node<K,V>[] table;
    transient Set<Map.Entry<K,V>> entrySet;
    transient int size;
    transient int modCount;
    int threshold;
    final float loadFactor;
}
```

## 2.2 methods

### HashMap
```java
    public HashMap(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal initial capacity: " +
                                               initialCapacity);
        if (initialCapacity > MAXIMUM_CAPACITY)
            initialCapacity = MAXIMUM_CAPACITY;
        if (loadFactor <= 0 || Float.isNaN(loadFactor))
            throw new IllegalArgumentException("Illegal load factor: " +
                                               loadFactor);
        this.loadFactor = loadFactor;
        this.threshold = tableSizeFor(initialCapacity);
    }
    
    public HashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }
    
    public HashMap() {
        this.loadFactor = DEFAULT_LOAD_FACTOR; // all other fields defaulted
    }
    
    public HashMap(Map<? extends K, ? extends V> m) {
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        putMapEntries(m, false);
    }
```

### hash
```java
    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16); // int 32位充分应用，高16位移动低16位
    }
```

### put
```java
    public V put(K key, V value) {
        return putVal(hash(key), key, value, false, true);
    }

    final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                   boolean evict) {
        Node<K,V>[] tab; Node<K,V> p; int n, i;
        if ((tab = table) == null || (n = tab.length) == 0)
            n = (tab = resize()).length;
        
        if ((p = tab[i = (n - 1) & hash]) == null) // (length -1) & hash 
            tab[i] = newNode(hash, key, value, null);
        else {
            Node<K,V> e; K k;
            if (p.hash == hash &&
                ((k = p.key) == key || (key != null && key.equals(k))))
                e = p;
            else if (p instanceof TreeNode)
                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
            else {
                for (int binCount = 0; ; ++binCount) {
                    if ((e = p.next) == null) {
                        p.next = newNode(hash, key, value, null);
                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st TREEIFY_THRESHOLD = 8;
                            treeifyBin(tab, hash);
                        break;
                    }
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                        break;
                    p = e;
                }
            }
            if (e != null) { // existing mapping for key
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                afterNodeAccess(e);
                return oldValue;
            }
        }
        ++modCount;
        if (++size > threshold)
            resize();
        afterNodeInsertion(evict);
        return null;
    }
    
    Node<K,V> newNode(int hash, K key, V value, Node<K,V> next) {
        return new Node<>(hash, key, value, next);
    }
    
    final void treeifyBin(Node<K,V>[] tab, int hash) {
        int n, index; Node<K,V> e;
        if (tab == null || (n = tab.length) < MIN_TREEIFY_CAPACITY) // MIN_TREEIFY_CAPACITY = 64;
            resize();
        else if ((e = tab[index = (n - 1) & hash]) != null) {
            TreeNode<K,V> hd = null, tl = null;
            do {
                TreeNode<K,V> p = replacementTreeNode(e, null);
                if (tl == null)
                    hd = p;
                else {
                    p.prev = tl;
                    tl.next = p;
                }
                tl = p;
            } while ((e = e.next) != null);
            if ((tab[index] = hd) != null)
                hd.treeify(tab);
        }
    }
```

### get
```java
    public V get(Object key) {
        Node<K,V> e;
        return (e = getNode(hash(key), key)) == null ? null : e.value;
    }
    
    final Node<K,V> getNode(int hash, Object key) {
        Node<K,V>[] tab; Node<K,V> first, e; int n; K k;
        if ((tab = table) != null && (n = tab.length) > 0 &&
            (first = tab[(n - 1) & hash]) != null) {
            
            if (first.hash == hash && // always check first node
                ((k = first.key) == key || (key != null && key.equals(k))))
                return first;
            
            if ((e = first.next) != null) {
                if (first instanceof TreeNode)
                    return ((TreeNode<K,V>)first).getTreeNode(hash, key);
                do {
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                        return e;
                } while ((e = e.next) != null);
            }
            
        }
        return null;
    }    
```

## 2.3 inner class

### Node

```java
    static class Node<K,V> implements Map.Entry<K,V> {
        final int hash;
        final K key;
        V value;
        Node<K,V> next;

        Node(int hash, K key, V value, Node<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public final K getKey()        { return key; }
        public final V getValue()      { return value; }
        public final String toString() { return key + "=" + value; }

        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        public final boolean equals(Object o) {
            if (o == this)
                return true;
            if (o instanceof Map.Entry) {
                Map.Entry<?,?> e = (Map.Entry<?,?>)o;
                if (Objects.equals(key, e.getKey()) &&
                    Objects.equals(value, e.getValue()))
                    return true;
            }
            return false;
        }
    }
```

### LinkedHashMap.Entry
```java
    static class Entry<K,V> extends HashMap.Node<K,V> {
        Entry<K,V> before, after;
        Entry(int hash, K key, V value, Node<K,V> next) {
            super(hash, key, value, next);
        }
    }
```


### TreeNode
```java
    static final class TreeNode<K,V> extends LinkedHashMap.Entry<K,V> {
        TreeNode<K,V> parent;  // red-black tree links
        TreeNode<K,V> left;
        TreeNode<K,V> right;
        TreeNode<K,V> prev;    // needed to unlink next upon deletion
        boolean red;
        
        TreeNode(int hash, K key, V val, Node<K,V> next) {
            super(hash, key, val, next);
        }
        
        final TreeNode<K,V> root() {
            for (TreeNode<K,V> r = this, p;;) {
                if ((p = r.parent) == null)
                    return r;
                r = p;
            }
        }
        
        final TreeNode<K,V> find(int h, Object k, Class<?> kc) {
            TreeNode<K,V> p = this;
            do {
                int ph, dir; K pk;
                TreeNode<K,V> pl = p.left, pr = p.right, q;
                if ((ph = p.hash) > h)
                    p = pl;
                else if (ph < h)
                    p = pr;
                else if ((pk = p.key) == k || (k != null && k.equals(pk)))
                    return p;
                else if (pl == null)
                    p = pr;
                else if (pr == null)
                    p = pl;
                else if ((kc != null ||
                          (kc = comparableClassFor(k)) != null) &&
                         (dir = compareComparables(kc, k, pk)) != 0)
                    p = (dir < 0) ? pl : pr;
                else if ((q = pr.find(h, k, kc)) != null)
                    return q;
                else
                    p = pl;
            } while (p != null);
            return null;
        }
        
        final TreeNode<K,V> getTreeNode(int h, Object k) {
            return ((parent != null) ? root() : this).find(h, k, null);
        }
        
    }
```


## links
[Java7/8 中的 HashMap 和 ConcurrentHashMap 全解析](http://www.importnew.com/28263.html)


