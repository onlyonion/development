Root Cause Analysis

[问题排查工具单](https://www.testwo.com/article/968 "title") 

[问题排查](https://www.cnblogs.com/mfmdaoyou/p/7349117.html "title") 

### 工具单
1.	tail tail -f filename -n 100
2.	grep
3.	awk
4.	find
5.	pgm
6.	top	
	ps -ef | grep java
	top -H -p pid
7.	eclipse MAT
8.	jps jps -mlvV
9.	jstack 
10.	jinfo jinfo -flags pid
11.	jmap jmap -heap pid 查看堆情况；jmap -dump:live,format=b,file=filename pid 导出堆快照
12.	jstat	jstat -gcutil pid

### 常见问题定位过程

#### 频繁GC问题或内存溢出OOM问题

1.	使用jps查看线程ID
2.	使用jstat -gc 3331 250 20 查看gc情况，一般比较关注PERM区的情况，查看GC的增长情况。
3.	使用jstat -gccause：额外输出上次GC原因
4.	使用jmap -dump:format=b,file=heapDump 3331生成堆转储文件
5.	使用jhat或者可视化工具（Eclipse Memory Analyzer 、IBM HeapAnalyzer）分析堆情况。
6.	结合代码解决内存溢出或泄露问题。

### 死锁问题

1.	使用jps查看线程ID
2.	使用jstack 3331：查看线程情况


### 示例
1.	top 命令发现某个进程cpu占用高
2.	禁用此机器对外服务接口（如dubbo禁用）
3.	通过jstat -gc  pid 10  命令， 发现 FULL GC 特别频繁
4.	查看error日志，出现 OOM 异常
5.	通过 jmap -dump:live,format=b,file=heap.dmp -F pid  生成堆dump文件
6.	通过 Eclipse 插件  MAT （Memory Analyzer Tool）分析内存对象情况。
可能会发现某个对象占用百分比很高，数量很多，应该能找到出问题的sql等，如limit offset,rows 返回数据过多，大表查询等
7.	解决