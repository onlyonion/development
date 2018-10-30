

### 一致性哈希
1. 先构造一个长度为2^32的整数环（这个环被称为一致性Hash环）
2. 根据节点名称的Hash值（其分布为[0, 2^32-1]）将服务器节点放置在这个Hash环上
3. 根据数据的Key值计算得到其Hash值（其分布也为[0, 2^32-1]）
4. 接着在Hash环上顺时针查找距离这个Key值的Hash值最近的服务器节点，完成Key到服务器的映射查找。

引入“虚拟节点”。其工作原理是：将一个物理节点拆分为多个虚拟节点，并且同一个物理节点的虚拟节点尽量均匀分布在Hash环上。采取这样的方式，就可以有效地解决增加或减少节点时候的负载不均衡的问题。