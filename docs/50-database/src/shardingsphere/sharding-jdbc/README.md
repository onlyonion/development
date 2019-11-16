io.shardingsphere.core

- 逻辑表、物理表、广播表、主键
- 分片字段、分片算法、分片策略
- 编译 => 优化 => 路由 => 改写 => 执行 => 结果合并

## package
```
api
    yaml
        YamlMasterSlaveDataSourceFactory
        YamlShardingDataSourceFactory
    MasterSlaveDataSourceFactory
    ShardingDataSourceFactory
executor
    batch
        BatchPreparedStatementExecutor
    prepared
        PreparedStatementExecuteUnit
        PreparedStatementExecutor
    statement
        StatementExecutor
jdbc
    adapter
        executor
        invocation
    core
        connection
        datasource
        resultset
        statement
        ShardingContext
    metadata
    unsupported
transaction
    TransactionTypeHolder
util
    DataSourceUtil
```