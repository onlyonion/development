

## 类图
* 53个内部类
* 91个方法
* 1个静态代码块
* 37个字段，其中27个常量、10个字段

```yuml
// {type:class}

[Map]^-.-[AbstractMap]
[AbstractMap]^-[ConcurrentHashMap]
[Map]^-[ConcurrentMap]
[ConcurrentMap]^-.-[ConcurrentHashMap]


```