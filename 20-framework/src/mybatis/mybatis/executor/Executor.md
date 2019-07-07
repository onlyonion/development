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
interface StatementHandler
interface ParameterHandler
interface ResultHandler
interface TypeHandler

Executor ..> StatementHandler
StatementHandler ..> ParameterHandler
ParameterHandler ..> TypeHandler
TypeHandler ..> ResultHandler
StatementHandler ..> ResultHandler

'''''''''''''''''''''''''''''''''''''''''''''''''''
class CachingExecutor
CachingExecutor *-- TransactionalCacheManager

abstract class BaseExecutor
class SimpleExecutor
class BatchExecutor
class ClosedExecutor
class ReuseExecutor

Executor ^.. CachingExecutor
Executor ^... BaseExecutor
BaseExecutor ^--- SimpleExecutor
BaseExecutor ^--- BatchExecutor
BaseExecutor ^-- ClosedExecutor
BaseExecutor ^-- ReuseExecutor

@enduml
```
