

## 大数据特点
* 数据体量巨大 volumn
* 数据类型繁多variety
* 价值密度低，商业价值高value
* 处理速度快velocity

## 大数据应用技术
* 大数据采集技术
* 大数据预处理技术
* 大数据存储及管理技术
* 大数据分析及挖掘技术
* 大数据展示与应用技术

## 云计算
* 资源池
* 并行计算、分布式计算、网格计算的发展
* IaaS Infrastructure as a Service
* PaaS Platform as a Service
* SaaS Software as a Service

## google云计算
| com    | 配置中心  | 文件系统 | 数据库    | 分布式计算 |
|:-------|:----------|:---------|:----------|:-----------|
| google | chubby    | gfs      | big-table | map-reduce |
| apache | zookeeper | hadoop   | hbase     | map-reduce |

Google FS、MapReduce、BigTable

[Google三大理论（论文）](http://blog.csdn.net/w1573007/article/details/52966742 "title") 

### Google FS -- 2003
2003年，Google发布Google File System论文，这是一个可扩展的分布式文件系统，用于大型的、分布式的、对大量数据进行访问的应用。
它运行于廉价的普通硬件上，提供容错功能。从根本上说：文件被分割成很多块，使用冗余的方式储存于商用机器集群上。

### MapReduce -- 2004
论文描述了大数据的分布式计算方式，主要思想是将任务分解然后在多台处理能力较弱的计算节点中同时处理，然后将结果合并从而完成大数据处理

### BigTable -- 2006
BigTable 是建立在 GFS 和 MapReduce 之上的。每个Table都是一个多维的稀疏图

### chubby
zookeeper是分布式系统中一个负载均衡框架，google的chubby的一个开源实现，是是Hadoop和Hbase的重要组件。
同样的在http中，常听说的nginx也是一个负载均衡服务器，它面向的是分布式web服务器