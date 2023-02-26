# MyCAT
mycat
corba
atlas
mysql router

### 功能
- 读写分离
- 数据分片 分库分表，分库同时分表
- 多数据源整合，mysql、oracle、nosql

### 原理
拦截
- 拦截用户的SQL语句，分片分析、路由分析、读写分离分析、缓存分析，
- 转发后端真实数据库，返回结果处理

映射模型
logical table -> partition(tragetName, schema, table)

### 分库分表
按照`一定规则`把`数据库中的表`拆分为`多个带有数据库实例`、`物理库`、`物理表访问路径`的分表。