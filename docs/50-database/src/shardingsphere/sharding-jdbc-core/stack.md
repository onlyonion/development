
## insert
- mybatis
- sharding
- mysql-jdbc

```
execute:1140, PreparedStatement (com.mysql.jdbc)
execute:44, ProxyPreparedStatement (com.zaxxer.hikari.pool)
execute:-1, HikariProxyPreparedStatement (com.zaxxer.hikari.pool)

executeInternal:192, PreparedStatementExecutor (com.dangdang.ddframe.rdb.sharding.executor)
execute:169, PreparedStatementExecutor (com.dangdang.ddframe.rdb.sharding.executor)
execute:115, ShardingPreparedStatement (com.dangdang.ddframe.rdb.sharding.jdbc)

update:46, PreparedStatementHandler (org.apache.ibatis.executor.statement)
update:74, RoutingStatementHandler (org.apache.ibatis.executor.statement)

doUpdate:50, SimpleExecutor (org.apache.ibatis.executor)
update:117, BaseExecutor (org.apache.ibatis.executor)
update:76, CachingExecutor (org.apache.ibatis.executor)

update:198, DefaultSqlSession (org.apache.ibatis.session.defaults)
insert:185, DefaultSqlSession (org.apache.ibatis.session.defaults)
invoke0:-1, NativeMethodAccessorImpl (sun.reflect)
invoke:62, NativeMethodAccessorImpl (sun.reflect)
invoke:43, DelegatingMethodAccessorImpl (sun.reflect)
invoke:498, Method (java.lang.reflect)
invoke:433, SqlSessionTemplate$SqlSessionInterceptor (org.mybatis.spring)

insert:-1, $Proxy89 (com.sun.proxy)
insert:278, SqlSessionTemplate (org.mybatis.spring)
execute:57, MapperMethod (org.apache.ibatis.binding)
invoke:59, MapperProxy (org.apache.ibatis.binding)
insertOne:-1, $Proxy90 (com.sun.proxy)
main:22, MybatisMain (com.t)
```