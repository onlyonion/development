org.apache.ibatis.binding.MapperMethod

## define
* 内部类
  * ParamMap
  * SqlCommand
  * MethodSignature
* 实例域
* 实例方法
  * execute 根据sql命令类型调用不同的方法
    * rowCountResult
    * executeWithResultHandler
    * executeForMany
    * executeForCursor

```plantuml
@startuml
''''''''''''''''''''' 映射方法 '''''''''''''''''''''''
class MapperMethod {
    - final SqlCommand command
    - final MethodSignature method
    + Object execute(SqlSession sqlSession, Object[] args)
    - Object rowCountResult(int rowCount)
    - void executeWithResultHandler(SqlSession sqlSession, Object[] args)
    - <E> Object executeForMany(SqlSession sqlSession, Object[] args)
    - <T> Cursor<T> executeForCursor(SqlSession sqlSession, Object[] args)
}

''''''''''''''''''''' sql命令 '''''''''''''''''''''''
class SqlCommand {
    - final String name
    - final SqlCommandType type
}
MapperMethod +-- SqlCommand

''''''''''''''''''''' sql命令类型枚举 增删改查、冲刷 '''''''''''''''''''''''
enum SqlCommandType {
  UNKNOWN, INSERT, UPDATE, DELETE, SELECT, FLUSH;
}
SqlCommand o-- SqlCommandType

''''''''''''''''''''' 方法签名 '''''''''''''''''''''''
class MethodSignature {
    + Object convertArgsToSqlCommandParam(Object[] args)
}
MapperMethod +-- MethodSignature

@enduml
```

## execute()
* 根据command类型判断 增删改查、刷新
* rowCountResult 受影响的行数（增、删、改）