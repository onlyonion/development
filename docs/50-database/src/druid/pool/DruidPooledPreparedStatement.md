

## hierachy

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


## execute
```mermaid
sequenceDiagram
    %% 预编译语句执行
    PreparedStatement->>DruidPooledPreparedStatement:execute()
    
    %% 之前
    DruidPooledPreparedStatement->>DruidPooledConnection:beforeExecute()
    
    %% 代理执行
    DruidPooledPreparedStatement->>PreparedStatementProxyImpl:execute()
    
    %% 之后
    DruidPooledPreparedStatement->>DruidPooledConnection:afterExecute()
```
