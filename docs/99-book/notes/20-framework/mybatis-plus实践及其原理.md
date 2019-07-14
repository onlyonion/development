《mybatis-plus实践及其原理》

#### mybatis架构
* 接口层
  * insert、delete、update、select
  * 接口调用方式、基于statementID、基于Mapper接口
* 数据处理层
  * 参数映射 ParameterHandler 参数映射配置、参数映射解析、参数类型解析
  * sql解析 SqlSource sql语句配置、sql语句解析、sql语句动态生成
  * sql执行 Executor SimpleExecutor、BatchExecutor、ReuseExecutor
  * 结果处理和映射 ResultSetHandler 结果映射配置、结果类型转换
* 框架支撑层
  * sql语句配置方式 基于xml配置、基于注解
  * 事务管理、连接池管理、缓存机制
* 引导层
  * 基于xml配置
  * 基于Java api方式

#### mybatis层次结构

#### sqlSession查询工作时序图

#### mybatis整体流程图

#### mybatis启动流程

session生命周期
* 开启一个会话，这时会创建一个SqlSession对象，并且为SQLSession创建一个Executor对象，而Executor内部持有一个Cache实例对象
* 会话结束，释放和销毁SqlSession，Executor对象和内部的Cache对象也回一并消除