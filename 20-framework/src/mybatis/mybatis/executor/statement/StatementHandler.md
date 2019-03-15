org.apache.ibatis.executor.statement.StatementHandler

## hierarchy
```
StatementHandler (org.apache.ibatis.executor.statement)
    RoutingStatementHandler (org.apache.ibatis.executor.statement)
    BaseStatementHandler (org.apache.ibatis.executor.statement)
        PreparedStatementHandler (org.apache.ibatis.executor.statement)
        CallableStatementHandler (org.apache.ibatis.executor.statement)
        SimpleStatementHandler (org.apache.ibatis.executor.statement)
```


## overview

```plantuml
@startuml

''''''''''''''''''''''''''' StatementHandler ''''''''''''''''''''''''''''''
interface StatementHandler {
  Statement prepare(Connection connection, Integer transactionTimeout)
  void parameterize(Statement statement)
  void batch(Statement statement)
  int update(Statement statement)
  <E> List<E> query(Statement statement, ResultHandler resultHandler)
  <E> Cursor<E> queryCursor(Statement statement)
  BoundSql getBoundSql()
  ParameterHandler getParameterHandler()
}

class BoundSql
StatementHandler o-- BoundSql

class ParameterHandler
StatementHandler o-- ParameterHandler

''''''''''''''''''''''''''' BaseStatementHandler ''''''''''''''''''''''''''''''
abstract class BaseStatementHandler {
    # final Configuration configuration
    # final ObjectFactory objectFactory
    # final TypeHandlerRegistry typeHandlerRegistry
    # final ResultSetHandler resultSetHandler
    # final ParameterHandler parameterHandler
    # final Executor executor
    # final MappedStatement mappedStatement
    # final RowBounds rowBounds
    # BoundSql boundSql
    
    # abstract Statement instantiateStatement(Connection connection)
}
StatementHandler <|-.- BaseStatementHandler

''''''''''''''''''''''''''' PreparedStatementHandler ''''''''''''''''''''''''''''''
class PreparedStatementHandler {

}
BaseStatementHandler <|-- PreparedStatementHandler

class CallableStatementHandler
BaseStatementHandler <|-- CallableStatementHandler

class SimpleStatementHandler
BaseStatementHandler <|-- SimpleStatementHandler

''''''''''''''''''''''''''' RoutingStatementHandler ''''''''''''''''''''''''''''''
class RoutingStatementHandler {
    - final StatementHandler delegate
}
StatementHandler <|-.- RoutingStatementHandler
RoutingStatementHandler -.-> PreparedStatementHandler
RoutingStatementHandler -.-> CallableStatementHandler
RoutingStatementHandler -.-> SimpleStatementHandler

@enduml
```


## RoutingStatementHandler
路由语句处理：根据MappedStatement.statementType枚举类型，构造具体的实现