
```mermaid
sequenceDiagram
    DruidPooledPreparedStatement->>PreparedStatementProxyImpl:execute()
    PreparedStatementProxyImpl->>StatementProxyImpl:createChain()
    
    StatementProxyImpl-->>PreparedStatementProxyImpl:返回过滤器链
    PreparedStatementProxyImpl->>FilterChainImpl:preparedStatement_execute()
```
