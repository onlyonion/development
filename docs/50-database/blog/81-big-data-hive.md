

## Hive
- Hive 由 Facebook 实现并开源，是基于 Hadoop 的一个数据仓库工具，可以将**结构化的数据**映射为**一张数据库表**，并提供 HQL(Hive SQL)查询功能，底层数据是存储在 HDFS 上。
- Hive 本质: 将 SQL 语句转换为 MapReduce 任务运行，使不熟悉 MapReduce 的用户很方便地利用 HQL 处理和计算 HDFS 上的结构化的数据,是一款基于 HDFS 的 MapReduce **计算框架**
- 主要用途：用来做离线数据分析，比直接用 MapReduce 开发效率更高。

## Hue
Hue是一个Web用户界面，可在基于Cloudera的Hadoop框架中提供许多服务。