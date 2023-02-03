
## explain
[MySQL Explain详解](https://www.cnblogs.com/tufujie/p/9413852.html)

id:选择标识符
select_type:表示查询的类型。
table:输出结果集的表
partitions:匹配的分区
type:表示表的连接类型
possible_keys:表示查询时，可能使用的索引
key:表示实际使用的索引
key_len:索引字段的长度
ref:列与索引的比较
rows:扫描出的行数(估算的行数)
filtered:按表条件过滤的行百分比
Extra:执行情况的描述和说明

### type
对表访问方式，表示MySQL在表中找到所需行的方式，又称“访问类型”。
常用的类型有： ALL、index、range、ref、eq_ref、const、system、NULL（从左到右，性能从差到好）
- system
- const 常量连接
  - 场景: 命中主键(primary key)或者唯一索引(unique)，被连接的部分是一个常量值(const)
- eq_ref 主键索引(primary key)或者非空唯一索引(unique not null)等值扫描
  - 场景：联表(join)查询，命中主键(primary key)或者非空唯一索引(unique not null)，等值连接
- ref 非主键非唯一索引等值扫描
  - 场景：联表查询，普通非唯一索引
- range 范围扫描
- index 索引树扫描，当使用覆盖索引，但需要扫描全部的索引记录
- ALL 全表扫描(full table scan)

### possible_keys
指出MySQL能使用哪个索引在表中找到记录，查询涉及到的字段上若存在索引，则该索引将被列出，但不一定被查询使用（该查询可以利用的索引，如果没有任何索引显示 null）

### Key
key列显示MySQL实际决定使用的键（索引），必然包含在possible_keys中

### key length
The length of the chosen key，所选键的长度。其单位是字节
1. 先看索引上字段的类型+长度。比如：int=4 ; varchar(20) =20 ; char(20) =20
2. 如果是varchar或者char这种字符串字段，视字符集要乘不同的值，比如utf8要乘 3，如果是utf8mb4要乘4，GBK要乘2
3. varchar这种动态字符串要加2个字节
4. 允许为空的字段要加1个字节

### ref
列与索引的比较，表示上述表的连接匹配条件，即哪些列或常量被用于查找索引列上的值

### rows
估算出结果集行数，表示MySQL根据表统计信息及索引选用情况，估算的找到所需的记录所需要读取的行数。值越小越好。

### filtered
最后查询出来的数据占所有服务器端检查行数（rows）的百分比。值越大越好。

### extra
包含不适合在其他列中显示但十分重要的额外信息。通过这些额外信息来理解MySQL到底将如何执行当前的查询语句。
MySQL提供的额外信息有好几十个，这里只挑介绍比较重要的介绍。
- Impossible WHERE：where子句的值总是false
- Impossible WHERE noticed after reading const tables
- Using where：使用了where，但在where上有字段没有创建索引
- Using temporary：使了用临时表保存中间结果
- Using filesort 在对查询结果中的记录进行排序时，是可以使用索引的，
- Using index condition：叫作 Index Condition Pushdown Optimization （索引下推优化）
- Using join buffer：在连接查询时，当被驱动表不能有效的利用索引时，MySQL会为其分配一块名为连接缓冲区（join buffer）的内存来加快查询速度

Using filesort表示在索引之外，需要额外进行外部的排序动作。
导致该问题的原因一般和order by有者直接关系，一般可以通过合适的索引来减少或者避免。
https://zhuanlan.zhihu.com/p/101571164
filesort 使用的算法是QuickSort，即对需要排序的记录生成元数据进行分块排序，然后再使用mergesort方法合并块。
其中filesort可以使用的内存空间大小为参数 sort_buffer_size 的值，默认为2M。

## show profiles
show variables like 'profiling';
set profiling = on;
show profiles;

show profile cpu,block io for query Query_ID;

## cpu
show full processlist;