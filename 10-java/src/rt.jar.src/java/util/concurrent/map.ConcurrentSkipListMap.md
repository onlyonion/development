java.util.concurrent.ConcurrentSkipListMap

## hierarchy
```
AbstractMap (java.util)
    ConcurrentSkipListMap (java.util.concurrent)
ConcurrentSkipListMap (java.util.concurrent)
    AbstractMap (java.util)
        Map (java.util)
    Cloneable (java.lang)
    ConcurrentNavigableMap (java.util.concurrent)
        ConcurrentMap (java.util.concurrent)
            Map (java.util)
        NavigableMap (java.util)
            SortedMap (java.util)
                Map (java.util)
```

## define
```plantuml
@startuml

class ConcurrentSkipListMap<K,V> {

}

ConcurrentSkipListMap +-- Node
ConcurrentSkipListMap +-- Index

class Node<K,V> {
    final K key
    volatile Object value
    volatile Node<K,V> next
}

class Index<K,V> {
    final Node<K,V> node
    final Index<K,V> down
    volatile Index<K,V> right
}        
      
@enduml
```

## methods