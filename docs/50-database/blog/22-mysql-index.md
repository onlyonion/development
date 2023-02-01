## 索引

### 概念
索引：在对表需要进行查询或者排序操作时，可以对表中某个或者某几个字段添加索引，对一个字段添加索引就是单个索引，对多个字段添加索引时就是组合索引。

不要滥用索引。因为过多的索引不仅仅会增加**物理存储**的开销，对于插入、删除、更新操作也会增加处理上的开销，而且会增加优化器在选择索引时的计算代价。

### 存储引擎工作原理
![存储引擎工作原理](../img/存储引擎工作原理.jpg) 

### 磁盘
磁道、扇区、柱面和柱头数

### 聚簇索引
“聚簇” 表示数据行和相邻的键值进错的存储在一起
* 聚簇索引（Clustered Index） 索引顺序就是数据的物理存储顺序，一个表最多只能有一个聚簇索引
* 非聚簇索引（Non-Clustered Index）索引顺序与数据物理排列顺序无关。

## [MySQL索引原理及BTree（B-/+Tree）结构详解](/https://blog.csdn.net/u013967628/article/details/84305511)
MySQL官方对索引的定义为：索引（Index）是帮助MySQL高效获取数据的数据结构。提取句子主干，就可以得到索引的本质：索引是数据结构。

顺序查找、二叉查找、二叉树查找。实际的数据库系统几乎没有使用二叉查找树或其进化品种红黑树（red-black tree）实现的

结合存储器原理及计算机存取原理讨论

B-Tree
* d为大于1的一个正整数，称为B-Tree的度
* h为一个正整数，称为B-Tree的高度
* 每个**非叶子节点**由n-1个key和n个指针组成，其中d<=n<=2d
* 每个**叶子节点**最少包含一个key和两个指针，最多包含2d-1个key和2d个指针，叶节点的指针均为null 
* 所有叶节点具有相同的深度，等于树高h
* key和指针互相间隔，节点两端是指针
* 一个节点中的key从左到右非递减排列
* 所有节点组成树结构
* 每个指针要么为null，要么指向另外一个节点。
* 如果某个指针在节点node最左边且不为null，则其指向节点的所有key小于v(key1)v(key1)，其中v(key1)v(key1)为node的第一个key的值。
* 如果某个指针在节点node最右边且不为null，则其指向节点的所有key大于v(keym)v(keym)，其中v(keym)v(keym)为node的最后一个key的值。
* 如果某个指针在节点node的左右相邻key分别是keyikeyi和keyi+1keyi+1且不为null，则其指向节点的所有key小于v(keyi+1)v(keyi+1)且大于v(keyi)v(keyi)。

```c
BTree_Search(node, key) {
    if(node == null) return null;
    foreach(node.key) {
        if(node.key[i] == key) return node.data[i];
        if(node.key[i] > key) return BTree_Search(point[i]->node);
    }
    return BTree_Search(point[i+1]->node);
}
data = BTree_Search(root, my_key);
```

B+Tree
* 每个节点的指针上限为2d而不是2d+1
* 内节点不存储data，只存储key；叶子节点不存储指针

### MySQL索引实现
在MySQL中，索引属于存储引擎级别的概念
#### MyISAM索引实现
#### InnoDB索引实现
聚集索引这种实现方式使得按主键的搜索十分高效，但是辅助索引搜索需要检索两遍索引：首先检索辅助索引获得主键，然后用主键到主索引中检索获得记录。

#### 索引分类
开发层次
* 单列索引，一个表可以有多个单值索引
* 主键索引，不能为null
* 唯一索引，不能重复，可以为null
* 复合索引，多个列构成（相当于书的二级目录：赵  z -> zhao）

数据管理员层次
* Btree索引
* hash索引
* 空间数据索引
* 全文索引

#### 创建
create 索引类型 索引名 on 表（字段）
alter table table_name add index index_name(column_name)
drop index index_name on table_name

201802220395617539
9223372036854775807

#### 指定使用索引
select * from table_name force index (index_name) where conditions;
SELECT * FROM user u force index(idx_user_id_update_time) where u.id=100 order by u.update_time

#### 联合索引的意义
减少开销，因为每多一个索引，都会增加磁盘空间的开销。
可能会覆盖索引，直接通过遍历索引取得数据，无需回表。

### 3层高的B+数存储多少条数据
一颗B+树的层高最好控制在3层之内，到第4层性能会急剧下降。

计算机存储数据时，一个扇区512KB，一个文件是4KB，innodb存储一页是16KB，innodb的所有文件大小一定是16KB的整数倍，一定是16384 byte的整倍数。
一个叶子节点有多少字节可以存放指向下一节点的指针，取决于主键的类型，比如bigint是8字节，而指针又占用6字节，所以指针可以指向1170个非叶子节点： 
16384 / （8+6）= 1170 个非叶子节点
同理，指向叶子节点的个数也可以是1170个，那么3层高的一颗B+树可以存储：
第一层指向1170个非叶子节点，第二层再指向1170个叶子节点，1170 * 1170 = 1368900 个叶子节点
那么假设一条记录1KB大小，那么一页16KB就可以存储16行，那么 16行 * 1368900 个叶子节点 = 21902400 行。

结论：在每行数据是1KB的情况下，3层高的B+数可以存放2190万多条数据。