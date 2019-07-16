com.alibaba.druid.proxy.jdbc.StatementProxy

## hierarchy
```
StatementProxy (com.alibaba.druid.proxy.jdbc)
    Statement (java.sql)
        Wrapper (java.sql)
        AutoCloseable (java.lang)
    WrapperProxy (com.alibaba.druid.proxy.jdbc)
        Wrapper (java.sql)
StatementProxy (com.alibaba.druid.proxy.jdbc)
    StatementProxyImpl (com.alibaba.druid.proxy.jdbc)
        PreparedStatementProxyImpl (com.alibaba.druid.proxy.jdbc)
            CallableStatementProxyImpl (com.alibaba.druid.proxy.jdbc)
    PreparedStatementProxy (com.alibaba.druid.proxy.jdbc)
        CallableStatementProxy (com.alibaba.druid.proxy.jdbc)
            CallableStatementProxyImpl (com.alibaba.druid.proxy.jdbc)
        PreparedStatementProxyImpl (com.alibaba.druid.proxy.jdbc)
            CallableStatementProxyImpl (com.alibaba.druid.proxy.jdbc)
```

## sequence
```mermaid
sequenceDiagram
    DruidPooledPreparedStatement->>PreparedStatementProxyImpl:execute()
    PreparedStatementProxyImpl->>StatementProxyImpl:createChain()
    
    StatementProxyImpl-->>PreparedStatementProxyImpl:返回过滤器链
    PreparedStatementProxyImpl->>FilterChainImpl:preparedStatement_execute()
```
