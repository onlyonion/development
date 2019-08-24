《疯狂workflow讲义：基于Activiti6.x的应用开发》 杨恩雄

## 第1章 Activiti介绍
工作流，是对工作流程及其各种操作步骤之间业务规则的抽象、概括和描述。
工作流建模，则是将工作流程中的工作如何前后组织在一起的逻辑和规则在计算机中以恰当的模型进行表示并对其进行计算。

## 第2章 安装与运行Activiti

## 第3章 Activiti数据库设计
### 3.1 通用数据表
- 资源表 act_ge_bytearray
- 属性表 act_ge_property

### 3.2 流程存储表
- 部署数据表 act_re_deployment
- 流程定义表 act_re_procdef

### 3.3 身份数据表
- 用户表 act_id_user
- 用户账户表 act_id_info
- 用户组 act_id_group
- 关系表 act_id_membership

### 3.4 运行时数据表
- 流程实例（执行流）表 act_ru_execution
- 流程任务表 act_ru_task
- 流参数表 act_ru_variable
- 流程与身份关系表 act_id_membership
- 工作数据表 act_ru_job
- 事件描述表 act_ru_event_subscr

### 3.5 历史数据表
- 流程实例表 ac_hi_procinst
- 流程明细表 ac_hi_detail
- 历史任务表和历史行为表 ac_hi_actinst
- 附件表和评论表 ac_hi_attachment act_hi_comment

## 第4章 Activiti流程引擎配置

## 第5章 流程引擎的创建

## 第6章 用户组与用户

## 第7章 流程存储

## 第8章 流程任务管理

## 第9章 流程控制

## 第10章 历史数据管理和流程引擎管理

## 第11章 流程事件

## 第12章 流程任务

## 第13章 其他流程引擎

## 第14章 Acitiviti与规则引擎
规则引擎是一种可以嵌入到具体业务体系中的组件，侧重与业务规则与程序分离，需要特定的规则语言。
流程引擎侧重与流程与程序的分离，需要特定的流程语言。

使用Drools可以实现业务逻辑与数据分离：使用对象来保存数据，使用规则文件来定义业务逻辑。
Drools的规则会被定义在一份规则文件中，一般情况下，规则文件的后缀为.drl，一份规则文件可以包含多个规则或者方法。

### 14.3 Drools规则语法概述
- package
- import
- global
- function
- query
- rule

规则编译

### 14.4 类型声明
Drools会根据传入的事实数据来匹配最合适的规则，一般情况下，事实数据均为Java对象。

在规则文件中使用declare定义的新类型后，Drools会加载该类型，并根据declare的定义生成响应的JavaBean的字节码。

### 14.5 函数和查询
在规则文件中如果存在一些公用的逻辑代码，则可以将这部分逻辑代码独立出来，成为一个可以让各个规则共用的函数。

向规则引擎的工作存储空间插入事实对象后，如果需要对这些对象进行查询，则可以在规则文件中使用query关键字定义查询。
### 14.6 规则语法
- 全局变量
- 规则属性
- 条件语法
- 行为语法

### 14.7 Activiti调用规则
使用Activiti中的业务规则任务（BusinessRuleTask）可以执行一个或者多个业务规则。

## 第15章 整合第三方框架

## 第16章 Activiti开发的WebService

## 第17章 Activiti功能进阶
Process Virtual Machine（简称PVM），翻译为流程虚拟机，是一套控制流程行为、表示流程节点的API。

## 第18章 办公自动化