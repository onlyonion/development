org.apache.ibatis.session.defaults.DefaultSqlSession

## define

```
@startuml

interface SqlSession

class DefaultSqlSession {
    - Configuration configuration
    - Executor executor
    - boolean autoCommit
    - boolean dirty
    - List<Cursor<?>> cursorList
}

SqlSession <|.. DefaultSqlSession

@enduml
```

org.apache.ibatis.session.defaults.DefaultSqlSession

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