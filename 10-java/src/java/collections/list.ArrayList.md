java.util.ArrayList

## hierarchy
```
AbstractCollection (java.util)
    AbstractList (java.util)
        ArrayList (java.util)
```

## define
* 静态域
  * DEFAULT_CAPACITY = 10
  * EMPTY_ELEMENTDATA
  * DEFAULTCAPACITY_EMPTY_ELEMENTDATA
  * MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8
* 实例域
  * Object[] elementData
  * int size
* 内部类
  * Itr
  * ListItr
  * SubList
  * ArrayListSpliterator

```plantuml
@startuml

'''''''''''''''''''''''''''Collection、List'''''''''''''''''''''''''''
interface Iterable<T>
interface Collection<E>
Iterable <|-- Collection
interface List<E>
Collection <|-- List

abstract class AbstractCollection<E>
Collection <|.. AbstractCollection

abstract class AbstractList<E>
List <|.. AbstractList
AbstractCollection <|-- AbstractList

'''''''''''''''''''''''''''随机访问'''''''''''''''''''''''''''
interface RandomAccess

class ArrayList<E> {
    ~ transient Object[] elementData
    - int size
}
List <|.. ArrayList
RandomAccess <|.. ArrayList
AbstractList <|-- ArrayList

@enduml
```