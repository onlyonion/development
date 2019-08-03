《Mycat权威指南》Mycat开源项目组


# 入门篇
## 第一章 概述
## 第二章 Mycat前世今生
## 第三章 Mycat中的概念
## 第四章 快速入门
## 第五章 日志分析
## 第六章 Mycat防火墙配置
## 第七章 Mycat的配置
### 7.1 join概述
### 7.2 全局表
### 7.3 ER Join
### 7.4 Share join
### 7.5 catlet（人工智能）
### 7.6 Spark/Storm 对join扩展
## 第八章 Mycat的分片join
### 8.1 全局序列号介绍
### 8.2 本地文件方式
### 8.3 数据库方式
### 8.4 本地时间戳方式
### 8.5 分布式ZK ID生成器
### 8.6 Zk递增方式
### 8.7 其他方式
### 8.8 自增长主键
## 第九章 全局序列号
## 第十章 Mycat 分片规则
## 第十一章 常见问题与解决方案
## 第十二章Mycat性能测试指南

# 高级进阶篇
## 第一章 读写分离

对于MySQL来说，标准的读写分离是主从模式，一个写节点Master后面跟着多个读节点，读节点的数据取决于系统的压力。

## 第二章 高可用与集群
主从复制 + 读写分离

## 第三章 事务支持
## 第四章 Mycat SQL拦截机制
## 第五章 Mycat注解
## 第六章 MyCAT支持的Catlet实现
## 第七章 jdbc多数据库支持
## 第八章 管理命令与监控
## 第九章 压缩协议支持
## 第十章 Mycat-Web
## 第十一章 MyCAT对存储过程的支持

# 生产实践篇
## 第一章 生产实践案例-Mycat读写分离案例
## 第二章分表分库案例
## 第三章 生产环境部署
## 第四章 Mycat最佳实践
## 第五章 Mycat实施指南
## 第六章 数据迁移与扩容实践
## 第七章 版本选择与升级指南
## 第八章 性能调优
### 8.1 主机调优
Linux主机的网络性能优化，mycat所在服务器多网卡绑定，bond技术，增加网络吞吐量。
BDP 给出一种简单的方法计算理论上最优的 TCP Socket 缓冲区大小(其中保存排队等待传输和等待应用程序接收的数据)
### 8.2 JVM调优
内存空闲设置，垃圾回收（吞吐量优先策略、暂停时间优先策略）
### 8.3 MyCAT调优
### 8.4 MySQL通用调优

# 开发篇
## 第一章 加入Mycat
目前 Mycat 所用的语言为 Java，相关技术主要如下：
* Java Web 技术，参与 MyCAT Web 开发
* JDBC 技术，可以完善 MyCAT Server 中的 JDBC 驱动部分
* Java IO，多线程，算法，参与 MyCAT Server 与 MyCAT Balance 的代码优化和完善
* SQL 优化与数据库技术，提供 MyCAT 智能优化的需求，实现和设计
* NoSQL 技术，参与 MyCAT 支持 NoSQL 引擎的工作

## 第二章 Mycat开发基础
### 2.2 中间件开发技能
对中间件开发技能进行图形化展示，方便团队内各成员业余时间自学相关技能，其中
- 多线程、网络编程、 JVM 调优是无止境的，能多熟就多熟 :)
- 流程控制需要个人多思考，对于高性能框架，就是引入很多异步逻辑，进行碎片化编程
- 不能一碰到需求就加一段代码而不管整体的融合性，不要只加不减，不时重构下结构删些代码多做些乘法
- 各种理论知识要跟实践相结合，理论算法一个表现形式，真正落地时代码上则可能是另一种考虑，但总要略懂些

## 第三章 Mycat架构分析
TDDL 不同于其它几款产品，并非独立的中间件，只能算作中间层，是以 Jar 包方式提供给应用调用。
属于 JDBC Shard 的思想，网上也有很多其它类似产品

MyCat 又是在 Cobar 基础上发展的版本，两个显著点是：
* 后端由 BIO 改为 NIO，并发量有大幅提高
* 增加了对 Order By、 Group By、 limit 等聚合功能的支持

## 第四章 MyCAT线程模型分析
### 4.1 MyCAT线程模型
* 主线程 Server, Manager, NIOConnector 
* IO线程 Reactor
* 调度线程 Timer
* 业务线程（池） TimerExecutor, BusinessExecutor
* 基础服务线程
* VM系统线程 VMThread, VM Periodic Task Thread, GC task thread, ReferenceHandler, Finalizer, Signal Dispatcher, C2 CompilerThread

## 第五章 mycat的连接池模型
通过共享一个 MySQL 上的所有物理连接，并结合连接状态同步的特性，MyCAT 的连接池做到了最佳的吞吐
量，也在一定程度上提升了整个系统的并发支撑能力

## 第六章 Mycat的网络通信框架
系统 I/O 可分为阻塞型, 非阻塞同步型以及非阻塞异步型.

#### 6.2.2 Reactor和Proactor
MyCAT 同时实现了 NIO 和 AIO，为了便于读者更清楚理解代码实现，先介绍 NIO 和 AIO 分布对应的两种设计模式：Reactor 和 Proactor。

#### 6.2.3 支持AIO和NIO的框架 
* SocketConnector 发起连接请求类，如 MyCAT 与 MySQL 数据库的连接，都是由 MyCAT 主动发起连接请求
* SocketAcceptor 接收连接请求类，如 MyCAT 启动 9066 和 8066 分别侦听管理员和应用程序的连接请求
* SocketWR 读写操作类，SocketConnector 和 SocketAcceptor 只负责 socket 建立，当 socket 连接建立后进行字节的读写操作则由 SocketWR 来完成。

这几个接口分别处理网络通道的四种不同类型的事件：
- Connect 客户端连接服务端事件
- Accept 服务端接收客户端连接事件
- Read 读事件
- Write 写事件
  
## 第七章 Mycat的路由与分发流程
### 7.1 路由的作用
#### 7.1.1 为什么需要路由
还得从 Mycat 原理上来看（具体见前文 Mycat 原理）。从原理上来看，可以把 mycat 看成一个 sql 转发器。 

## 第八章 Mycat的JDBC后端框架
## 第九章 Mycat的事务管理机制
## 第十章 Mycat的分页和跨库Join
## 第十一章 Mycat缓存
Mycat 缓存的数据分别是：SQLRouteCache(SQL 语句路由缓存),TableID2DataNodeCache(表主键节点缓
存),ER_SQL2PARENTID(ER 关系缓存)。
Mycat 缓存支持 ehcache,mapdb,leveldb,通过配置文件 cacheservice.properties，决定使用种缓存。
缓存的代码在 org.opencloudb.cache 和 org.opencloudb.cache.impl 包中

## 第十二章 Mycat 的分片规则设计
## 第十三章 Mycat Load Data源码
## 第十四章 Mycat外传-群英会