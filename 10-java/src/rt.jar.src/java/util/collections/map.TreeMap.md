java.util.TreeMap

## hierarchy
```
AbstractMap (java.util)
    TreeMap (java.util)
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
    - final Comparator<? super K> comparato
    - transient Entry<K,V> root
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

@enduml
```

### 红黑树
红黑树（Red Black Tree） 是一种自平衡二叉查找树，是在计算机科学中用到的一种数据结构，典型的用途是实现关联数组。
* 性质1. 节点是红色或黑色。
* 性质2. 根节点是黑色。
* 性质3. 每个红色节点的两个子节点都是黑色。(从每个叶子到根的所有路径上不能有两个连续的红色节点)
* 性质4. 从任一节点到其每个叶子的所有路径都包含相同数目的黑色节点。

这些约束强制了红黑树的关键性质: 从根到叶子的最长的可能路径不多于最短的可能路径的两倍长。结果是这个树大致上是平衡的。
因为操作比如插入、删除和查找某个值的最坏情况时间都要求与树的高度成比例，这个在高度上的理论上限允许红黑树在最坏情况下都是高效的，
而不同于普通的二叉查找树。

