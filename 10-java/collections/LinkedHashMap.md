LinkedHashMap

* HashMap + 双向链表合
* 保证插入顺序 default
* 保证访问顺序

HashMap.Entry
hash, key, value, next

LinkedHashMap.Entry extend HashMap.Entry
before, after

LinkedHashMap在不对HashMap做任何改变的基础上，给HashMap的任意节点间增加了两条连线（before指针和after指针），使这些节点形成一个双向链表。