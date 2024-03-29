## 分析与设计
* 结构化分析与设计（面向过程、SAD）
  - 分析工具：数据流图、数据字典、结构化语言、判定表、判定树
  - 结构化设计：概要设计与详细设计、结构图、程序流程图、盒图
  - 模块设计
  - 局限性，局限于某个点
* 面向对象的分析与设计
  - OO：抽象、封装、继承、多态
  - OOP：用需求转化为代码实现
    - 用程序描述世界，用代码描述生活
  - OOA/OOD：标识对象类、标识结构与关联、划分主题、定义属性、定义服务 
  - [UML建模](/40-architecture/design/01-design.md)
    - 用例图、类图、对象图
    - 交互图 序列图（时序图）、协作图（通信图）、交互概览图
    - 状态图、活动图
    - 构件图、部署图
  - [设计模式](/40-architecture/design/10-design-pattern.md)
    - [创建型](/40-architecture/design/11-design-pattern-create.md)
    - [结构型](/40-architecture/design/12-design-pattern-struct.md)
    - [行为型](/40-architecture/design/13-design-pattern-behaviour.md)
* [领域驱动设计](/40-architecture/design/30-ddd.md)
  - 分层：User Interface、Application、Service、Domain、Infrastructure
  - 分类：Entity、Value Object、Aggregate、Service、Factory、Repository
  - [领域驱动设计到底难在哪？](https://www.jianshu.com/p/ab80cb9f307c?from=groupmessage)
* 系统架构设计
* 分布式系统设计
  * 服务框架
  * 消息队列
  * 分布式缓存、Session
  * 分布式事务
  * 分布式数据库
* 面向服务的设计 SOA Microservice
  * 面向资源 RESTful
* EDA事件驱动架构
  * 响应式编程
* [区块链](/40-architecture/design/01-design.md)

## 面向
* 面向过程 过程式
* 面向对象 对象式
* 面向Bean BOP 面向构件
  * IoC 控制权反转，把创建对象的权力交给容器（BeanFactory）管理
  * DI/DL 依赖注入、依赖查找lookup，**赋值**（构造方法、set、直接赋值）
* 面向方面 AOP，面向规则、标准、协议编程；非功能需求抽离（拆分）
* 面向函数 函数式编程、响应式编程
* 面向数据表 数据式，面向数据表，贫血
* 面向领域
