### Mybatis整体架构

#### 接口层
* SqlSession

#### 核心处理层
* 配置解析
* 参数映射
* sql解析
* sql执行
* 结果集映射
* 插件

#### 基础支撑层
* 数据源模块
* 事务管理模块
* 缓存模块
* Binding模块
* 反射模块
* 类型转换
* 日志模块
* 资源加载
* 解析器模块

SqlSession --> Executor         --> StatementHandler --> ParameterHandler --> Statement --> DB  --> 
ResultSet  --> ResultSetHandler