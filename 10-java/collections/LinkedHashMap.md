LinkedHashMap

## 背景
HashMap是无序的，也就是说，迭代HashMap所得到的元素顺序并不是它们最初放置到HashMap的顺序

## 特性
* HashMap + 双向链表合
* 保持插入顺序 default
* 保持访问顺序
* 可以用来实现LRU (Least recently used, 最近最少使用)算法

## 原理
HashMap.Entry
hash, key, value, next

LinkedHashMap.Entry extend HashMap.Entry
before, (hash, key, value, next), after

LinkedHashMap在不对HashMap做任何改变的基础上，给HashMap的任意节点间增加了两条连线（before指针和after指针），使这些节点形成一个双向链表。

```
	// construtor
	public LinkedHashMap(int initialCapacity,
             				float loadFactor,
                         boolean accessOrder) {
        super(initialCapacity, loadFactor);   // 调用HashMap对应的构造函数
        this.accessOrder = accessOrder;    // 迭代顺序的默认值
    }
    
    // init
    void init() {
        header = new Entry<K,V>(-1, null, null, null);
        header.before = header.after = header;
    }
```
