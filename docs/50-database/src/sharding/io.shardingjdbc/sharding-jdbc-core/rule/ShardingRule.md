io.shardingjdbc.core.rule.ShardingRule

## define
- 数据源
- 分片策略-数据源
- 分片策略-表
- 表规则
- 绑定表规则
- 主键生成器

```java
public final class ShardingRule {
    private final Map<String, DataSource> dataSourceMap;
    private final String defaultDataSourceName;
    private final Collection<TableRule> tableRules;
    private final Collection<BindingTableRule> bindingTableRules = new LinkedList<>();
    private final ShardingStrategy defaultDatabaseShardingStrategy;
    private final ShardingStrategy defaultTableShardingStrategy;
    private final KeyGenerator defaultKeyGenerator;
}
```