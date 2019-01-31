
## define
```
@startuml

class DefaultSqlSession {
    - final Configuration configuration
    - final Executor executor
}
@enduml
```


## select()

```mermaid
sequenceDiagram
    Actor ->> DefaultSqlSession: select()
    DefaultSqlSession ->> Configuration: getMappedStatement()
    DefaultSqlSession ->> Executor: query()
```

## insert(), update(), delete() --> update()

```mermaid
sequenceDiagram
    Actor ->> DefaultSqlSession: update()
    DefaultSqlSession ->> Configuration: getMappedStatement()
    DefaultSqlSession ->> Executor: update()
```