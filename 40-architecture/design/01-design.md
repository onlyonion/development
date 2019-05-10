
### 分析与设计
* 结构化分析与设计（面向过程、SAD）
  - 分析工具：数据流图、数据字典、结构化语言、判定表、判定树
  - 结构化设计：概要设计与详细设计、结构图、程序流程图、盒图
  - 模块设计
* 面向对象的分析与设计
  - OOA/OOD：标识对象类、标识结构与关联、划分主题、定义属性、定义服务 
  - UML：
    - 用例图、类图、对象图
    - 交互图 序列图（时序图）、协作图（通信图）、交互概览图
    - 状态图、活动图
    - 构件图、部署图
* 领域驱动设计（面向领域）
  - 分层：User Interface、Application、Service、Domain、Infrastructure
  - 分类：Entity、Value Object、Aggregate、Service、Factory、Repository
  - [领域驱动设计到底难在哪？](https://www.jianshu.com/p/ab80cb9f307c?from=groupmessage)

#### 面向
* 面向过程 过程式
* 面向对象 对象式
* 面向数据表 数据式
* 面向函数 函数式
* 面向方面 AOP

#### architecture
* 面向服务 SOA Microservice
* 面向资源 RESTful
* EDA事件驱动架构

高内聚、低耦合

#### 设计原则
SOLID (单一功能、开闭原则、里氏替换、接口隔离以及依赖反转)
* 单一职责原则(Single Responsibility Principle, SRP)：一个类只负责一个功能领域中的相应职责，或者可以定义为：就一个类而言，应该只有一个引起它变化的原因。
* 开闭原则(Open-Closed Principle, OCP)：一个软件实体应当对扩展开放，对修改关闭。即软件实体应尽量在不修改原有代码的情况下进行扩展。
* 里氏代换原则(Liskov Substitution Principle, LSP)：所有引用基类（父类）的地方必须能透明地使用其子类的对象。
* 依赖倒转原则(Dependency Inversion  Principle, DIP)：抽象不应该依赖于细节，细节应当依赖于抽象。换言之，要针对接口编程，而不是针对实现编程。
* 接口隔离原则(Interface  Segregation Principle, ISP)：使用多个专门的接口，而不使用单一的总接口，即客户端不应该依赖那些它不需要的接口。
* 迪米特法则(Law of  Demeter, LoD)：一个软件实体应当尽可能少地与其他实体发生相互作用。最少知识原则(LeastKnowledge Principle, LKP)

#### 设计模式