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
## methods
## #execute()
* 根据command类型判断 增删改查、刷新
  - INSERT
  - UPDATE
  - DELETE
  - SELECT
    - returnsMany
    - returnsMap
    - returnsCursor
    - selectOne
  - FLUSH
* rowCountResult 受影响的行数（增、删、改）

```java
  public Object execute(SqlSession sqlSession, Object[] args) {
    Object result;
    switch (command.getType()) {
      case INSERT: {
    	Object param = method.convertArgsToSqlCommandParam(args);
        result = rowCountResult(sqlSession.insert(command.getName(), param));
        break;
      }
      case UPDATE: {
        Object param = method.convertArgsToSqlCommandParam(args);
        result = rowCountResult(sqlSession.update(command.getName(), param));
        break;
      }
      case DELETE: {
        Object param = method.convertArgsToSqlCommandParam(args);
        result = rowCountResult(sqlSession.delete(command.getName(), param));
        break;
      }
      case SELECT:
        if (method.returnsVoid() && method.hasResultHandler()) {
          executeWithResultHandler(sqlSession, args);
          result = null;
        } else if (method.returnsMany()) {
          result = executeForMany(sqlSession, args);
        } else if (method.returnsMap()) {
          result = executeForMap(sqlSession, args);
        } else if (method.returnsCursor()) {
          result = executeForCursor(sqlSession, args);
        } else {
          Object param = method.convertArgsToSqlCommandParam(args);
          result = sqlSession.selectOne(command.getName(), param);
        }
        break;
      case FLUSH:
        result = sqlSession.flushStatements();
        break;
      default:
        throw new BindingException("Unknown execution method for: " + command.getName());
    }
    if (result == null && method.getReturnType().isPrimitive() && !method.returnsVoid()) {
      throw new BindingException("Mapper method '" + command.getName() 
          + " attempted to return null from a method with a primitive return type (" + method.getReturnType() + ").");
    }
    return result;
  }
  
```