
Proxy
- InvocationHandler
  - MapperProxy
  - SqlSessionInterceptor
  
## invoke
* binding
  * `MapperProxy`.invoke 映射代理
  * MapperMethod.execute
    * 委派 INSERT|UPDATE|DELETE|SELECT|FLUSH
* spring
  * SqlSessionTemplate.insert|update|delete|select
  * SqlSessionTemplate.`SqlSessionInterceptor`.invoke 会话拦截器
  * SqlSessionUtils.getSqlSession
  * SqlSessionFactory.openSession
* session
  * DefaultSqlSession
  * Configuration.getMappedStatement
* executor
  * CachingExecutor
  * BaseExecutor
  * ReuserExecutor
* statement
* parameter
* resultset
  
```

// proxy binding
$Proxy Mapper接口的jdk动态代理
MapperProxy
MapperMethod

// spring
SqlSessionTemplate
$Proxy SqlSession接口的jdk动态代理
SqlSessionTemplate$SqlSessionInterceptor
DefaultSqlSession

// config
Configuration
MappedStatement

/// executor
CachingExecutor
BaseExecutor
SimpleExecutor

// statementHandler
RoutingStatementHandler
PreparedStatementHandler

// druid
DruidPooledPreparedStatement

// mysql-connector
```


## stack
```
// jdbc
execute:1148, PreparedStatement (com.mysql.jdbc)

// filter
preparedStatement_execute:3051, FilterChainImpl (com.alibaba.druid.filter)
preparedStatement_execute:440, FilterEventAdapter (com.alibaba.druid.filter)
preparedStatement_execute:3049, FilterChainImpl (com.alibaba.druid.filter)
preparedStatement_execute:619, WallFilter (com.alibaba.druid.wall)
preparedStatement_execute:3049, FilterChainImpl (com.alibaba.druid.filter)
preparedStatement_execute:440, FilterEventAdapter (com.alibaba.druid.filter)
preparedStatement_execute:3049, FilterChainImpl (com.alibaba.druid.filter)

// pooled statement
execute:167, PreparedStatementProxyImpl (com.alibaba.druid.proxy.jdbc)
execute:498, DruidPooledPreparedStatement (com.alibaba.druid.pool)

// jdk reflect
invoke:-1, GeneratedMethodAccessor185 (sun.reflect)
invoke:43, DelegatingMethodAccessorImpl (sun.reflect)
invoke:498, Method (java.lang.reflect)

// statement
invoke:59, PreparedStatementLogger (org.apache.ibatis.logging.jdbc)
execute:-1, $Proxy129 (com.sun.proxy)
query:63, PreparedStatementHandler (org.apache.ibatis.executor.statement)
query:79, RoutingStatementHandler (org.apache.ibatis.executor.statement)

// executor
doQuery:63, SimpleExecutor (org.apache.ibatis.executor)
queryFromDatabase:324, BaseExecutor (org.apache.ibatis.executor)
query:156, BaseExecutor (org.apache.ibatis.executor)                    一级缓存
query:109, CachingExecutor (org.apache.ibatis.executor)
query:83, CachingExecutor (org.apache.ibatis.executor)                  二级缓存

// sqlSession
selectList:148, DefaultSqlSession (org.apache.ibatis.session.defaults)
selectList:141, DefaultSqlSession (org.apache.ibatis.session.defaults)

// jdk reflect
invoke0:-1, NativeMethodAccessorImpl (sun.reflect)
invoke:62, NativeMethodAccessorImpl (sun.reflect)
invoke:43, DelegatingMethodAccessorImpl (sun.reflect)
invoke:498, Method (java.lang.reflect)

// spring-mybatis sql会话模板
invoke:433, SqlSessionTemplate$SqlSessionInterceptor (org.mybatis.spring)
selectList:-1, $Proxy91 (com.sun.proxy)     动态代理
selectList:230, SqlSessionTemplate (org.mybatis.spring)

// MapperMethod
executeForMany:137, MapperMethod (org.apache.ibatis.binding)
execute:75, MapperMethod (org.apache.ibatis.binding)

// MapperProxy
invoke:59, MapperProxy (org.apache.ibatis.binding)
selectList:-1, $Proxy92 (com.sun.proxy)     动态代理
```

## update stack
```

// statement
update:47, PreparedStatementHandler (org.apache.ibatis.executor.statement)
update:74, RoutingStatementHandler (org.apache.ibatis.executor.statement)

// executor
doUpdate:50, SimpleExecutor (org.apache.ibatis.executor)
update:117, BaseExecutor (org.apache.ibatis.executor)
update:76, CachingExecutor (org.apache.ibatis.executor)

// sqlSession
update:198, DefaultSqlSession (org.apache.ibatis.session.defaults)

invoke0:-1, NativeMethodAccessorImpl (sun.reflect)
invoke:62, NativeMethodAccessorImpl (sun.reflect)
invoke:43, DelegatingMethodAccessorImpl (sun.reflect)
invoke:498, Method (java.lang.reflect)

// spring-mybatis sql会话模板
invoke:433, SqlSessionTemplate$SqlSessionInterceptor (org.mybatis.spring)
update:-1, $Proxy85 (com.sun.proxy)
update:294, SqlSessionTemplate (org.mybatis.spring)

// proxy
execute:62, MapperMethod (org.apache.ibatis.binding)
invoke:59, MapperProxy (org.apache.ibatis.binding)
updateByPrimaryKeySelective:-1, $Proxy88 (com.sun.proxy)
```