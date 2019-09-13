

## 生成的方法
- defulat 6个方法
  - deleteByPrimaryKey
  - insert
  - insertSelective
  - selectByPrimaryKey
  - updateByPrimaryKeySelective
  - updateByPrimaryKey
- Example
  - countByExample
  - deleteByExample
  - selectByExample
  - updateByExampleSelective
  - updateByExample

```java
int countByExample(CommRuleExample example);
int deleteByExample(CommRuleExample example);
int deleteByPrimaryKey(Long autoid);
int insert(CommRule record);
int insertSelective(CommRule record);
List<CommRule> selectByExample(CommRuleExample example);
CommRule selectByPrimaryKey(Long autoid);
int updateByExampleSelective(@Param("record") CommRule record, @Param("example") CommRuleExample example);
int updateByExample(@Param("record") CommRule record, @Param("example") CommRuleExample example);
int updateByPrimaryKeySelective(CommRule record);
int updateByPrimaryKey(CommRule record);
```