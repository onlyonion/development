## MySQL 的常用引擎

[深入理解 MySQL 底层实现](http://blog.csdn.net/gitchat/article/details/78787837 "title") 

### 1. InnoDB
InnoDB 的存储文件有两个，后缀名分别是 .frm 和 .idb，其中 .frm 是表的定义文件，而 idb 是数据文件。
InnoDB 中存在表锁和行锁，不过行锁是在命中索引的情况下才会起作用。
InnoDB 支持事务，且支持四种隔离级别（读未提交、读已提交、可重复读、串行化），默认的为可重复读；
而在 Oracle 数据库中，只支持串行化级别和读已提交这两种级别，其中默认的为读已提交级别。

### 2. Myisam
Myisam 的存储文件有三个，后缀名分别是 .frm、.MYD、MYI，其中 .frm 是表的定义文件，.MYD 是数据文件，.MYI 是索引文件。
Myisam 只支持表锁，且不支持事务。Myisam 由于有单独的索引文件，在读取数据方面的性能很高 。

### 3. 存储结构
InnoDB 和 Myisam 都是用 B+Tree 来存储数据的。

## MySQL 的数据、索引存储结构
### 1. 数据存储的原理（硬盘）
### 2. 数据读写的原理
### 3. 访盘请求完成过程
### 4. 磁盘的读写原理
### 5. 减少 I/O 的预读原理
### 6. MySQL 的索引
索引是一种用来实现 MySQL 高效获取数据的数据结构。
### 7. MySQL 的 B+Tree
B+ 树索引是 B+ 树在数据库中的一种实现，是最常见也是数据库中使用最为频繁的一种索引。B+ 树中的 B 代表平衡，而不是二叉。
因为 B+ 树是从最早的平衡二叉树演化而来的。B+ 树是由二叉查找树、平衡二叉树（AVLTree）和平衡多路查找树（B-Tree）逐步优化而来。
- 二叉查找树：左子树的键值小于根的键值，右子树的键值大于根的键值。
- AVL 树：平衡二叉树（AVL 树）在符合二叉查找树的条件下，还满足任何节点的两个子树的高度最大差为 1。
- 平衡多路查找树（B-Tree）：为磁盘等外存储设备设计的一种平衡查找树。

### 8. Myisam 中的 B+Tree
### 9. InnoDB 中的 B+Tree

## MySQL 的相关优化
### 1. 表结构与索引
- 开启查询缓存。避免某些 SQL 函数直接在 SQL 语句中使用，从而导致 Mysql 缓存失效。
- 避免画蛇添足。目的是什么就取什么，例如某个逻辑是只需要判断是否存在女性，若是查到了一条即可，勿要全部都查一遍，此时要善用 limit。
- 建合适的索引。所以要建在合适的地方，合适的对象上。经常操作 / 比较 / 判断的字段应该建索引。
- 字段大小合宜。字段的取值是有限而且是固定的，这种情况下可以用 enum，IP 字段可以用 unsigned int 来存储。
- 表的设计。垂直分割表，使得固定表与变长表分割，从而降低表的复杂度和字段的数目。

### 2. SQL语句优化：避免全表扫描
- 建索引：一般在 where 及 order by 中涉及到的列上建索引，尽量不要对可以重复的字段建索引。
- 尽量避免在 where 中使用 !（<>）或 or，也不要进行 null 值判断。
- 尽量避免在 where 中对字段进行函数操作、表达式操作。
- 尽量避免使用 like- %，在此种情况下可以进行全文检索。

### 3. 
主从复制、读写分离
冷热分离，冷数据归档

### 分库分表