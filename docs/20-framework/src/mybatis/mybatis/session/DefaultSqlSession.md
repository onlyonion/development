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
## methods

### select()

```mermaid
sequenceDiagram
    Actor ->> DefaultSqlSession: select()
    DefaultSqlSession ->> Configuration: getMappedStatement()
    DefaultSqlSession ->> Executor: query()
```

### insert(), update(), delete() --> update()

```mermaid
sequenceDiagram
    Actor ->> DefaultSqlSession: update()
    DefaultSqlSession ->> Configuration: getMappedStatement()
    DefaultSqlSession ->> Executor: update()
```

### wrapCollection
```java
  private Object wrapCollection(final Object object) {
    if (object instanceof Collection) {
      StrictMap<Object> map = new StrictMap<Object>();
      map.put("collection", object);
      if (object instanceof List) {
        map.put("list", object);
      }
      return map;
    } else if (object != null && object.getClass().isArray()) {
      StrictMap<Object> map = new StrictMap<Object>();
      map.put("array", object);
      return map;
    }
    return object;
  }
```