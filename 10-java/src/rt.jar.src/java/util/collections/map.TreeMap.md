java.util.TreeMap

## hierarchy
```
AbstractMap (java.util)
    TreeMap (java.util)
```

## define
```plantuml
@startuml


interface Map
interface Map.Entry<K,V>
Map +-- Map.Entry
Map +.. AbstractMap
abstract class AbstractMap<K,V>  

AbstractMap ^-- TreeMap
class TreeMap<K,V> {
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