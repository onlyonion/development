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

### 四、type
对表访问方式，表示MySQL在表中找到所需行的方式，又称“访问类型”。
常用的类型有： ALL、index、range、 ref、eq_ref、const、system、NULL（从左到右，性能从差到好）

### 五、possible_keys
指出MySQL能使用哪个索引在表中找到记录，查询涉及到的字段上若存在索引，则该索引将被列出，但不一定被查询使用（该查询可以利用的索引，如果没有任何索引显示 null）

### 六、Key
key列显示MySQL实际决定使用的键（索引），必然包含在possible_keys中

### 八、ref
列与索引的比较，表示上述表的连接匹配条件，即哪些列或常量被用于查找索引列上的值

### 九、rows
估算出结果集行数，表示MySQL根据表统计信息及索引选用情况，估算的找到所需的记录所需要读取的行数