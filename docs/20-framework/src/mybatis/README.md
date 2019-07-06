
## mybatis
* [mybatis](/20-framework/src/mybatis/mybatis/README.md)
  * binding
    * [MapperMethod](/20-framework/src/mybatis/mybatis/binding/MapperMethod.md)
    * [MapperProxy](/20-framework/src/mybatis/mybatis/binding/MapperProxy.md)
    * [MapperRegistry](/20-framework/src/mybatis/mybatis/binding/MapperRegistry.md) 注册中心
  * [builder](/20-framework/src/mybatis/mybatis/builder/README.md) 建造者
    * xml
      * [XMLConfigBuilder](/20-framework/src/mybatis/mybatis/builder/xml/XMLConfigBuilder.md)
      * [XMLMapperBuilder](/20-framework/src/mybatis/mybatis/builder/xml/XMLMapperBuilder.md)
      * [XMLStatementBuilder](/20-framework/src/mybatis/mybatis/builder/xml/XMLStatementBuilder.md)
    * [BaseBuilder](/20-framework/src/mybatis/mybatis/builder/BaseBuilder.md)
  * [cache](/20-framework/src/mybatis/mybatis/cache/README.md) 缓存
  * [executor](/20-framework/src/mybatis/mybatis/executor/README.md) 执行器
    * [Executor](/20-framework/src/mybatis/mybatis/executor/Executor.md) 
    * [CachingExecutor](/20-framework/src/mybatis/mybatis/executor/CachingExecutor.md)
    * statement
      * [StatementHandler](/20-framework/src/mybatis/mybatis/executor/statement/StatementHandler.md) 语句处理
    * parameter
      * [ParameterHandler](/20-framework/src/mybatis/mybatis/executor/parameter/ParameterHandler.md) 参数处理
    * keygen
      * [KeyGenerator](/20-framework/src/mybatis/mybatis/executor/keygen/KeyGenerator.md) 主键生成策略
    * resultset
      * [ResultSetHandler](/20-framework/src/mybatis/mybatis/executor/resultset/ResultSetHandler.md) 结果集处理
  * [mapping](/20-framework/src/mybatis/mybatis/mapping/README.md) 映射、请求映射、路由
    * [MappedStatement](/20-framework/src/mybatis/mybatis/mapping/MappedStatement.md)
  * [ognl](/20-framework/src/mybatis/mybatis/ognl/README.md)
  * [parsing](/20-framework/src/mybatis/mybatis/parsing/README.md) 配置解析模块，xml解析
    * [XPathParser](/20-framework/src/mybatis/mybatis/parsing/XPathParser.md)
  * [plugin](/20-framework/src/mybatis/mybatis/plugin/README.md) 插件
  * [reflection](/20-framework/src/mybatis/mybatis/reflection/README.md)
  * [scripting](/20-framework/src/mybatis/mybatis/scripting/README.md)
    * xmltags
      * [SqlNode](20-framework/src/mybatis/mybatis/scripting/xmltags/SqlNode.md)
  * [session](/20-framework/src/mybatis/mybatis/session/README.md) 接口层----会话
    * [SqlSession](/20-framework/src/mybatis/mybatis/session/SqlSession.md) 
    * [DefaultSqlSession](/20-framework/src/mybatis/mybatis/session/DefaultSqlSession.md)
    * [Configuration](/20-framework/src/mybatis/mybatis/session/Configuration.md) 配置中心
  * [transaction](/20-framework/src/mybatis/mybatis/transaction/README.md) 事务管理模块
    * [Transaction](/20-framework/src/mybatis/mybatis/transaction/Transaction.md) 
  * [type](/20-framework/src/mybatis/mybatis/type/README.md)
    * [TypeHandler](/20-framework/src/mybatis/mybatis/type/TypeHandler.md) 类型转换，java类型与jdbc类型转换
* [mybatis-plus](/20-framework/src/mybatis/mybatis-plus/README.md)
  * override
    * [PageMapperMethod](/20-framework/src/mybatis/mybatis-plus/override/PageMapperMethod.md)
    * [PageMapperProxy](/20-framework/src/mybatis/mybatis-plus/override/PageMapperProxy.md)
* mybatis-spring
  * [SqlSessionFactoryBean](/20-framework/src/mybatis/mybatis-spring/SqlSessionFactoryBean.md)
  * [SqlSessionTemplate](/20-framework/src/mybatis/mybatis-spring/SqlSessionTemplate.md)
  * [MapperScannerConfigurer](/20-framework/src/mybatis/mybatis-spring/mapper/MapperScannerConfigurer.md)

## init
[init](/20-framework/src/mybatis/mybatis/init.md) 初始化
## sql 执行
[invoke](/20-framework/src/mybatis/mybatis/invoke.md) 调用流程
* SqlSession
* Executor
* StatementHandler
  * ParameterHandler
  * Statement
  * database
  * ResultSet
  * ResultHandler
* 缓存模块
* scripting模块
* 事务模块

## links
* [《MyBatis技术内幕》徐郡明](/99-book/notes/20-framework/Mybatis技术内幕.md)