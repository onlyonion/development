Join算法
https://blog.csdn.net/qq_49173222/article/details/123053182

Nested Loop Join，Hash join，Sort Merge Join

## Nested Loop Join。
NLJ是通过两层循环，用第一张表做Outter Loop，第二张表做Inner Loop，Outter Loop的每一条记录跟Inner Loop的记录作比较，符合条件的就输出。
而NLJ又有3种细分的算法：

### Simple Nested Loop Join（SNLJ）
SNLJ就是两层循环全量扫描连接的两张表，得到符合条件的两条记录则输出，这也就是让两张表做笛卡尔积，比较次数是R * S，是比较暴力的算法，会比较耗时。
```c
   // 伪代码
    for (r in R) {
        for (s in S) {
            if (r satisfy condition s) {
                output <r, s>;
            }
        }
    }
```

### Index Nested Loop Join（INLJ）
INLJ是在SNLJ的基础上做了优化，通过连接条件确定可用的索引，在Inner Loop中扫描索引而不去扫描数据本身，从而提高Inner Loop的效率。
而INLJ也有缺点，就是如果扫描的索引是非聚簇索引，并且需要访问非索引的数据，会产生一个回表读取数据的操作，这就多了一次随机的I/O操作。
```c
    // 伪代码
    for (r in R) {
        for (si in SIndex) {
            if (r satisfy condition si) {
                output <r, s>;
            }
        }
    }
```

### Block Nested Loop Join（BNLJ）
一般情况下，MySQL优化器在索引可用的情况下，会优先选择使用INLJ算法，但是在无索引可用，
或者判断full scan可能比使用索引更快的情况下，还是不会选择使用过于粗暴的SNLJ算法。

这里就出现了BNLJ算法了，BNLJ在SNLJ的基础上使用了join buffer，会提前读取Inner Loop所需要的记录到buffer中，以提高Inner Loop的效率。

```c
    // 伪代码
    for (r in R) {
        for (sbu in SBuffer) {
            if (r satisfy condition sbu) {
                output <r, s>;
            }
        }
    }
```

MySQL中控制join buffer大小的参数名是join_buffer_size