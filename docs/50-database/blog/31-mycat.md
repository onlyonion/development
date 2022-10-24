# mycat

mycat 拦截

拦截sql -> 分片分析、路由分析、读写分离分析、缓存分析 -> real db -> result handle

mycat order by, limit 二次处理，跨分片join

ER分片、全局表、HBT(Human Brain Tech，人工智能)的catlet，结合Storm、Spark引擎

## tx
### XA规范
* 应用程序 AP
* 事务管理器 TM 交易中间件
* 资源管理器 RM 数据库
* 通信资源管理器 CRM 消息中间件

### 2PC
* 协调者、参与者
* PrepareCommit
* DoCommit

### 3PC
* CanCommit
* PreCommit
* DoCommit

## 分片规则
* 分片表与非分片表