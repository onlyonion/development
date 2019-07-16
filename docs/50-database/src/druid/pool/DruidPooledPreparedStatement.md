com.alibaba.druid.pool.DruidPooledPreparedStatement

## hierachy
```
PoolableWrapper (com.alibaba.druid.pool)
    DruidPooledStatement (com.alibaba.druid.pool)
        DruidPooledPreparedStatement (com.alibaba.druid.pool)
            DruidPooledCallableStatement (com.alibaba.druid.pool)
DruidPooledStatement (com.alibaba.druid.pool)
    PoolableWrapper (com.alibaba.druid.pool)
        Object (java.lang)
        Wrapper (java.sql)
        Statement (java.sql)
    PreparedStatement (java.sql)
        Statement (java.sql)
            Wrapper (java.sql)
            AutoCloseable (java.lang)
```

## define
```plantuml
@startuml

interface Statement
interface PreparedStatement

class DruidPooledStatement {
    - final Statement stmt
    # DruidPooledConnection conn
}

class DruidPooledPreparedStatement {
    # PreparedStatement stmt
}

Statement <|-- PreparedStatement
DruidPooledStatement <|-- DruidPooledPreparedStatement

Statement <|.. DruidPooledStatement
PreparedStatement <|.. DruidPooledPreparedStatement

@enduml
```

## execute
```mermaid
sequenceDiagram
    %% 预编译语句执行
    PreparedStatement->>DruidPooledPreparedStatement:execute()
    
    %% 之前
    DruidPooledPreparedStatement->>DruidPooledConnection:beforeExecute()模板方法
    
    %% 代理执行
    DruidPooledPreparedStatement->>PreparedStatementProxyImpl:execute()
    
    %% 之后
    DruidPooledPreparedStatement->>DruidPooledConnection:afterExecute()模板方法
```
