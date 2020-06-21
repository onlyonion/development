org.apache.ibatis.executor.statement.PreparedStatementHandler

## methods

### update
- [KeyGenerator](/docs/20-framework/src/mybatis/mybatis/executor/keygen/KeyGenerator.md)

```java
  @Override
  public int update(Statement statement) throws SQLException {
    PreparedStatement ps = (PreparedStatement) statement;
    ps.execute();
    int rows = ps.getUpdateCount();
    Object parameterObject = boundSql.getParameterObject();
    KeyGenerator keyGenerator = mappedStatement.getKeyGenerator();
    keyGenerator.processAfter(executor, mappedStatement, ps, parameterObject);
    return rows;
  }
```