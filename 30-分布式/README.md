
*	CAP, ACID, BASE
*	一致性算法-paxos
*	分布式缓存 redis
*	分布式消息服务
*	分布式计算 Mapreduce, storm
*	分布式文件系统
*	分布式数据库
*	软负载均衡 nginx
*	集群
*	主从
*	冷备热备
*	大数据 Hadoop, hbase

### 



|com		|配置中心	|文件系统	|数据库		|分布式计算	|
|:---:		|:---:		|:---:		|:---:		|:---:		|
|google		|chubby		|gfs		|big-table	|map-reduce	|
|apache		|zookeeper	|hadoop		|hbase		|map-reduce	|

zookeeper是分布式系统中一个负载均衡框架，google的chubby的一个开源实现，是是Hadoop和Hbase的重要组件。
同样的在http中，常听说的nginx也是一个负载均衡服务器，它面向的是分布式web服务器
