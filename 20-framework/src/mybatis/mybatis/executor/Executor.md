org.apache.ibatis.executor.Executor

## hierarchy
```
Executor (org.apache.ibatis.executor)
    CachingExecutor (org.apache.ibatis.executor)
    BaseExecutor (org.apache.ibatis.executor)
        SimpleExecutor (org.apache.ibatis.executor)
        ClosedExecutor in ResultLoaderMap (org.apache.ibatis.executor.loader)
        ReuseExecutor (org.apache.ibatis.executor)
        BatchExecutor (org.apache.ibatis.executor)
```

## define
```plantuml
@startuml

interface Executor

'''''''''''''''''''' 基类实现 '''''''''''''''''''''''
abstract class BaseExecutor {
    - <E> List<E> queryFromDatabase(MappedStatement ms, Object parameter,
        RowBounds rowBounds, ResultHandler resultHandler, CacheKey key, BoundSql boundSql)
    # abstract int doUpdate(MappedStatement ms, Object parameter)
    # abstract <E> List<E> doQuery(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql)
    # abstract <E> Cursor<E> doQueryCursor(MappedStatement ms, Object parameter, RowBounds rowBounds, BoundSql boundSql)
}

Executor <|-.- BaseExecutor

'''''''''''''''''''' 简单执行器实现 '''''''''''''''''''''''
class SimpleExecutor {
}
BaseExecutor <|-- SimpleExecutor

'''''''''''''''''''' 批量执行器实现 '''''''''''''''''''''''
class BatchExecutor {
}
BaseExecutor <|-- BatchExecutor

@enduml
```