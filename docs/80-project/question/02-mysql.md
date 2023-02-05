
MySQL
1. MySQL索引
   - 索引、B+树、回表
   - 聚簇索引、非聚簇索引、覆盖索引、3层B+数据存储多少记录
2. MySQL内部技术架构
   - 查询缓存、执行计划、解析器、优化器、存储引擎
3. MySQL事务
   - ACID、脏读、不可重复读、幻读、快照读、当前读
   - MVCC、LBCC、表锁、行锁
4. MySQL日志
   - binlog、undolog、redolog
   - errorlog、slowquerylog慢查询日志、一般查询日志
   - 刷盘、一致性2PC
5. MySQL开发
   - 字段类型、子查询、join算法、预编译sql、mysql调优
   - 分库分表


## 1. MySQL索引

## 2. MySQL内部技术架构

## 3. MySQL事务

## 4. MySQL日志

### undolog的作用
- 用来回滚到某一个版本，是一种逻辑日志
- 记录的是修改之前的数据
- 提供MVCC的读取

### undolog与redolog的刷盘机制
数据脏页、change buffer、log buffer

sync_binlog 写入策略

innodb_flush_log_at_trx_commit
- 取值0：每秒（一秒内提交的事务）写入磁盘，每秒触发一次缓存日志写磁盘操作，并调用操作系统fsync刷新IO缓存
- 取值1：有事务提交就立即刷盘
- 取值2：每次事务提交都写给操作系统，每次都把redolog写入page cache，由操作系统决定什么时候写入磁盘

redolog与binlog的两阶段提交
- prepare
- commit

### MySQL的binglog的录入格式
binlog_format
- statement 默认，
- row 记录sql，记录上下文信息
- mixed


## 5. MySQL开发

