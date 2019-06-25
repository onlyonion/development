java.util.WeakHashMap

## define

```plantuml
@startuml
interface Map<K,V> 
abstract class AbstractMap<K,V>

Map ^.. AbstractMap
AbstractMap ^-- WeakHashMap
Map ^-- WeakHashMap

class WeakHashMap<K,V> {
    Entry<K,V>[] table
    - int threshold
    - final float loadFactor
    - final ReferenceQueue<Object> queue
}


WeakHashMap o-- Entry
WeakHashMap o-- ReferenceQueue

'''''''''''''''''''节点''''''''''''''''''''''
interface Map.Entry<K,V>
class WeakReference<Object>

WeakReference ^-- Entry
Map.Entry ^.. Entry

class Entry<K,V>


@enduml
```

## methods
### private void expungeStaleEntries() 删除过期的实体
size(), resize()调用