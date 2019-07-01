《从Paxos到ZooKeeper 分布式一致性原理与实践》 倪超 电子工业出版社

倪超，阿里巴巴高级研发工程师。2011年毕业于杭州电子科技大学计算机系。


## 第1章 分布式架构
### 1.1 从集中式到分布式
### 1.2 从ACID到CAP/BASE
分布式事务是指事务的参与者、支持事务的服务器、资源服务器以及事务管理器分别位于分布式系统的不同节点上。

## 第2章 一致性协议
### 2.1 2PC与3PC
#### 2.1.1 2PC
事务的提交过程分成了两个阶段
* 提交事务请求 事务询问、执行事务（undo、redo）、各参与者向协调者反馈事务询问的响应
* 执行事务提交 
  * 执行事务提交、事务提交、反馈事务提交结果、结束
  * 中断事务 任何一个参与者向协调者反馈了NO响应

#### 2.1.2 3PC
* CanCommint 事务询问、反馈响应
* PreCommit
  * 执行事务预提交 发出preCommit，进入prepared阶段，undo、redo
  * 中断事务
* doCommit
  * 执行事务提交
  * 中断事务

### 2.2 Paxos算法
* proposer生成提案
  * 选择新编号M0，想某个Acceptor集合成员发请求
  * 半数以上Acceptor响应，可以产生的编号就是(M0,Vn)
* acceptor批准提案
  * prepare请求
  * accept请求

算法陈述，Proposer和Acceptor对提案处理，类似两阶段提交
* Prepare
  * Proposer选择Mn，向Acceptor的某个超过半数的子集成员发送编号为Mn的`Prepare请求`
  * Acceptor接收Mn的`Prepare请求`，并且Mn大于之前的最大编号，则批准，同时该Acceptor不批准比Mn小的提案
* Commit
  * Proposer接收半数以上Acceptor的编号Mn响应，发送`Accept请求`[Mn, Vn]提案
  * Acceptor接收到[Mn, Vn]的`Accept请求`，只要该Acceptor尚未对编号大于Mn的Prepare请求做出响应，就可以通过这个提案。

## 第3章 Paxos的工作实践
### 3.1 Chubby
Google Chubby分布式锁服务
### 3.2 Hypertable

## 第4章 ZooKeeper与Paxos
### 4.1 初始ZooKeeper
#### 4.1.3 Zookeeper的基本概念
* 集群角色 master/slave; Leader/Follwer/Observer
* 会话Session
* 数据节点Znode
* 版本
* Watcher 事件监听器
* ACL create, read, write, delete, admin

### 4.2 ZooKeeper的ZAB协议
Zookeeper Atomic Broadcast，支持崩溃恢复的原子广播协议。
单一的主进程接收并处理客户端的所有事务请求，并采用ZAB的原子广播协议，以事务提议广播到副本进程。

* Leader服务器 全局唯一的服务器协调处理事务。
  * 将客户端事务请求转换成一个事务提议（Proposal）
  * 分发给集群中所有的Follower服务器
  * 等待所有Follower服务器的反馈
  * 超过半数的Follower正确的反馈，再次向所有的Follower分发Commit，将前一个Proposal提交
* Follower服务器

##### 算法描述
* 发现 Leader选举
* 同步
* 广播 正式接收客户端新的请求，并进行消息广播

##### 运行分析
* looking Leader选举
* following Follower和Leader保持同步状态
* leading leader领导

#### 4.2.4 ZAB与Paxos算法
本质设计目标不一样：
* ZAB 构建**高可用**的分布式数据主备系统 CAP的A
* Paxos 构建一个分布式的**一致性**状态机系统 CAP的C

## 第5章 使用ZooKeeper
### 5.1 部署与运行
#### 5.1.1 系统环境
#### 5.1.2 集群与单机
#### 5.1.3 运行服务
### 5.2 客户端脚本
#### 5.2.1 创建
create
#### 5.2.2 读取
ls
get
#### 5.2.3 更新
set
#### 5.2.4 删除
delete
### 5.3 Java客户端API使用
#### 5.3.1 创建会话
#### 5.3.2 创建节点
#### 5.3.3 删除节点
#### 5.3.4 读取数据
* getChildren
* getData

#### 5.3.5 更新数据
setData
#### 5.3.6 检测节点是否存在
exits
#### 5.3.7 权限控制
addAuthInfo
### 5.4 开源客户端

## 第6章 ZooKeeper的典型应用场景
### 6.1 典型应用场景及实现
#### 6.1.1 数据发布/订阅
服务端推、客户端拉。

Zookeeper采用的是**推拉相结合**的方式：客户端向服务端注册自己需要关注的节点，一旦节点的数据发生变更，那么客户端就会向相应的客户端发送Watcher时间通知，
客户端接收到这个消息通知之后，需要主动到服务端获取最新的数据。

数据量比较小、数据内容运行时动态变化、集群中各机器共享和配置一致

#### 6.1.2 负载均衡
##### 动态DNS服务
* 域名配置 Zookeeper创建节点进行域名配置
* 域名解析 os解析
* 域名变更 Watcher监听 发布/订阅

##### 自动化DNS服务
* 域名注册 服务提供者将自己的域名信息注册到Register集群；Register获取域名、IP和端口，写入到相应的节点
* 域名解析
* 域名探测

#### 6.1.3 命名服务
* 分布式服务框架中的服务地址列表，通过使用命名服务，客户端应用能够根据指定名字获取资源的实体、服务地址和提供者的信息。
* JDNI命名服务。Zookeeper来实现**分布式全局唯一ID**。

#### 6.1.4 分布式协调/通知
Watcher注册与异步通知机制，实现分布式环境下不同系统之间的**协调与通知**。

MySQL数据复制总线：Mysql_Replicator
* 任务注册
* 任务热备份
* 热备切换
* 记录执行状态
* 控制台协调
* 冷备切换
* 冷热备份对比

通用的分布式系统机器间通信方式
* 心跳检测 创建临时节点，不同机器间根据临时节点判断是否存活
* 工作进度汇报
* 系统调度 修改指令发送给客户端，变更以事件通知的实行发送给对应的订阅客户端

#### 6.1.5 集群管理
集群监控（集群运行状态的收集）与集群控制。

Zookeeper两大特性
* 对数据节点注册Watcher监听，节点内容或子节点列表发生变更进行变更通知
* 创建临时节点，一旦客户端与服务器会话失效，临时节点自动清除

分布式日志收集
* 注册收集器机器
* 任务分发
* 状态汇报
* 动态分配

在线云主机管理
* 机器上下线 机器列表节点下创建临时节点；节点监控中心（订阅者）收到异步通知
* 机器监控

#### 6.1.6 Master选举
ZooKeeper的强一致性：保证客户端无法重复创建一个已经存在的数据节点。

客户端集群每天都会定时在zookeeper集群上创建一个临时节点，只有一个客户端能够创建成功，那么它成为Master。
其他的注册一个节点变更的Watcher，监听当前master是否存活，一旦发现挂了，其余的重新创建节点（重新进行master选举）。

#### 6.1.7 分布式架锁
* 排它锁
  * 获取锁 create() Zookeeper保证所有的客户端中，最终只有一个客户端能创建成功，那么就认为客户端获取了锁。没有获取的注册Watcher监听
  * 释放锁

1. create() `/share_lock/host-请求类型R/W-序号`临时顺序节点
2. getChildren()获取所有子节点列表
3. 如果无法获取共享锁，那么调用exist()对比自己小的节点注册Watcher。
4. 等待Watcher通知，重复2

#### 6.1.8 分布式队列
FIFO
1. 创建临时节点
2. getChildren()获取队列中的所有元素（子节点）
3. 确定自己的节点序号在所有节点中的顺序
4. 如果自己不是序号最小的子节点，那么就需要进入等待，同事向比自己序号小的最后一个节点注册Watcher监听
5. 接收到Watcher事件之后，重复2

Barrier：分布式屏障
1. getData()获取节点数据内容N（barrier的值）
2. getChildren()获取节点下的所有子节点（获取队列中的所有元素），同时注册对子节点列表变更的Watcher监听
3. 统计子节点个数
4. 如果子节点个数还不足N个，那么等待
5. 接收Watcher通知，重复2

### 6.2 ZooKeeper在大型分布式系统中的应用
#### 6.2.1 hadoop
#### 6.2.2 hbase
#### 6.2.3 kafka
### 6.3 ZooKeeper在阿里巴巴的实践与应用
#### 6.3.1 案例一 消息中间件：metamorphosis
#### 6.3.2 案例二 RPC服务框架：dubbo
Dubbo的核心
* 远程通信 基于长连接NIO框架抽象封装，包括多种线程模型、序列化、请求-响应模式的信息交换
* 集群容错 负载均衡、失败容错、地址路由、动态配置
* 自动发现 基于注册中心的目录服务，消费方动态查找服务方，地址透明，服务方可以平滑第增加或减少

* Root
  * Service
    * Type providers consumers
      * url

* Provider 注册url
* Consumer 注册url、订阅providers
* Monitor 订阅 Service

#### 6.3.3 案例三 基于MySQL binlog的增量订阅和消费组件：Canal
#### 6.3.4 案例四 分布式数据库同步系统：otter
#### 6.3.5 案例五 轻量级分布式通用搜索平台：终搜
#### 6.3.6 案例六 实时计算引擎：jstorm


## 第7章 ZooKeeper技术内幕
### 7.1 系统模型
#### 7.1.1 数据模型
#### 7.1.2 节点特性
#### 7.1.3 版本--保证分布式数据原子性操作
#### 7.1.4 Watcher--数据变更的通知
#### 7.1.5 ACL--保障数据的安全

### 7.2 序列化与协议
### 7.3 客户端
### 7.4 会话
### 7.5 服务器启动
### 7.6 Leader选举
### 7.7 各服务器角色介绍
### 7.8 请求处理
### 7.9 数据与存储

## 第8章 ZooKeeper运维
### 8.1 配置详解
### 8.2 四字命令
### 8.3 JMX
### 8.4 监控
### 8.5 构建一个高可用的机器
### 8.6 日常运维

## 附录D Zookeeper源码
* 客户端API设计与实现
* 序列化与协议 jute组件进行序列化和反序列化
* 网络通信 NIOServerCnxn NIOServerCnxnFactory NettyServerCnxn NettyServerCnxnFactory 
* Watcher机制 Watcher WatcherEvent ClientWatchManager
* 数据与存储 LearnerHandler
* 请求处理链 RequestProcessor PreRequestProcessor ProposalRequestProcessor SyncRequestProcessor
* Leaer选举 Election AuthFastLeaderElection LeaderElection FastleaderElection QuorumCnxManager
* 服务端各角色工作原理 ZookeeperServer QuorumZookeeperServer 
  * LeaderZooKeeperServer FollowerZooKeeperServer ObserverZooKeeperServer
  * Leader Follower LearnerHandler
* 权限认证 
* JMX相关
* 静态变量定义 ZooDefs 操作类型、权限控制、ACL定义
* Zookeeper异常定义 ConnectionLossException NoNodeException NoAuthException KeeperException



