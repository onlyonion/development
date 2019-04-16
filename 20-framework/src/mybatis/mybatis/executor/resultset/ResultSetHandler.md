org.apache.ibatis.executor.resultset.ResultSetHandler

## hierarchy
```
ResultSetHandler (org.apache.ibatis.executor.resultset)
    DefaultResultSetHandler (org.apache.ibatis.executor.resultset)
```
## define

```plantuml
@startuml
interface ResultSetHandler {
  + <E> List<E> handleResultSets(Statement stmt) throws SQLException
  + <E> Cursor<E> handleCursorResultSets(Statement stmt) throws SQLException
  + void handleOutputParameters(CallableStatement cs) throws SQLException
}

class DefaultResultSetHandler {
    - final Executor executor
    - final Configuration configuration
    - final MappedStatement mappedStatement
    - final RowBounds rowBounds
    - final ParameterHandler parameterHandler
    - final ResultHandler<?> resultHandler
    - final BoundSql boundSql
    - final TypeHandlerRegistry typeHandlerRegistry
    - final ObjectFactory objectFactory
    - final ReflectorFactory reflectorFactory
}
    

@enduml
```

