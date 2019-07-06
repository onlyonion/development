
* 操作系统、计算机网络、数据结构与算法
* Java集合、线程、并发、JVM
* spring、spring-boot、mybatis、netty、redis、mysql

## 操作系统与计算机网络
操作系统
* 进程与线程 线程调度、线程切换步骤、协程
  * Linux下的IPC 共享内存、MessageQueue、Pipe、UnixSocket、Singal、Sempahore
* Linux常用命令 awk、top、netstat、grep、less、tail
* 死锁
* 内存分页管理与swap
* 任务队列与CPU Load
* 扩展知识点 内存屏障、指令乱序、分支预测、CPU亲和性（affinity）、Netfilter与iptables

网络知识
* 4/7层网络模型
* TCP协议 建立链接三次握手、关闭链接四次握手、报文状态标志与链接状态
  * Nagel算法与ACK延迟、Keepalive、滑动窗口与流量控制
* UDP 非连接、非可靠传输、效率高
* HTTP
  * 协议 method、header、cookies
  * UrlEncode、状态码、HTTPS
  * HTTP2 多路复用、Stream、流量控制、头部压缩、服务端推送
* QUIK
  * 避免前序包阻塞（HOL阻塞）
  * 零RTT建连
  * FEC前向纠错

## 数据结构与算法
数据结构
* 队列
* 栈
* 表（数组、单链表、双链表、散列表）
* 图 有向图、无向图、带权图
* 多叉树 B、B+、字典树（字符串的多模匹配）
* 二叉树 平衡二叉树、红黑树、哈夫曼树、堆

算法
* 常用算法思路 分治、动态规划、贪心、回溯、分支界定
* 复杂度 时间复杂度、空间复杂度
* 字符串匹配 BF算法、BM算法、Sunday算法、KMP算法、Tire树
* 排序 
  * 插入 希尔、直插
  * 交换 冒泡、快速
  * 选择 简单选择、堆
  * 归并
  * 基数
* 查找 二分查找、二叉排序树、B树、hash、BloomFilter

## Java语言特性与设计模式
java语言特性
* 集合 HashMap、ConcurrentHashMap、ArrayList、LinkedList、HashSet、TreeMap
* 对象引用 强、软、弱、虚
* 异常机制
* 扩展点知识 SPI机制、注解处理机制
* JUC ConcurrentXXX、AtomicXXX、Executor、Caller&Future、Queue、Locks
* 版本差异新特性
  * 1.8 Lambda、StreamAPI、方法引用、接口默认方法、Metaspace
  * 1.9 模块系统、默认G1回收器、接口私有方法、局部变量推断、Graal编译器
  * 1.11 ZGC、字符串API增强、内建HTTP Client
* 动态代理与反射
* 数据类型 空间占用、基本数据结构、自动转型与强制转型、封箱与拆箱

## 并发与多线程
多线程
* 同步与互斥
  * synchronized
  * lock 锁类型、锁实现
  * cas Unsafe、原语
* 线程状态转换 
* 死锁
  * 竞争条、死锁检测与预防
  * 产生条件 互斥、请求并保持、不可剥夺、循环等待
* 线程通信 wait、notify、notifyAll
* 机制 ThreadLocal、Fork/Join、volatile、interrupt
* 常用工具类JUC
* 线程池 使用场景、原理与实现方式、线程池实现

## JVM
* JVM内存模型 程序计数器、方法区、堆、栈、本地方法栈
* GC
  * 分代回收 年轻代、年老代、持久代
  * 回收器实战 串行回收器、并行回收器、CMS、G1
* Java类加载机制 双亲委派机制、Bootstrap、Extension、System、自定义
* 编译器优化 公共子表达式消除、指令重排、内联、逃逸分析（方法逃逸、线程逃逸）、栈上分配、同步消除
* 执行模式 解释模式、编译模式、混合模式
* 性能调优
  * JVM参数
  * 性能分析工具 MAT、JMC、Jstack、jstat

## 常用工具类
* JVM相关 jmc、jstack、jmap、jstat、jinfo、jcmd、btrace、MAT
* 系统分析
  * vmstat
  * iostat iotop
  * ifstat iftop
  * netstat
  * dstat
  * strace
  * GDB
  * lsof
  * tcpdump
  * traceroute
* 文档管理 javaDoc、swagger
* 网络工具 postman、wireshark、finddler、charles
* 团队协作 ant、maven、gradle、git、svn
* 质量保证 checkstyle、findbugs、sonarqube
* 压测 jmeter、jmh、ab、loadrunner
* 容器与代理 tomcat、jetty、nginx、envcy、openResty、kong
* CI/CD gitlab-ci、jenkis、travis

## Spring
场景框架
* spring
  * spring boot
  * spring data
  * spring cloud
    * sleuth
    * netflix
    * config
    * bus
    * security
* struts
* orm
  * hibernate
  * mybatis
* netty
* rpc motan、dubbo、grpc
* jersey、restEasy、shiro

spring全家桶
* 机制与实现
  * 核心接口、类 ApplicationContext、BeanFactory、BeanWrapper、FactoryBean
  * scope
  * 事件机制
  * AOP
  * PlaceHolder动态体会
  * 事务
    * 隔离类型
    * 传播类型
* spring应用
  * 常用注解
  * 配置方式
  * 自动装配
  * 集合属性注入
  * 内部bean
* springboot
  * 启动流程
  * 配置文件
  * 注解
  * 模块

## RPC与ORM
Netty知识点
* 特点 高性能NIO框架、多着decode/encoder支持TCP粘包/分包、池化ByteBuffs、减少不必要对象创建、GC友好
* 主要概念 Channel、ChannelHandler、ChannelPipeline、EventLoop、Boostrap
* 线程模型
  * Boss线程
  * worker线程 IO线程分配，读写线程有worker线程处理，一个eventLoop可以处理多个channel，ChannelPipeline
  * bizThreads 业务线程池，处理完成，封装成task，提交给channel对应的eventLoop
* 零内存复制 堆外内存进行socket读写、compositeByteBuf减少多buffer合并复制、FileRegion文件传输
* 请求的粘包与半包 FixedLengthFrameDecoder（定长）、LineBasedFrameDecoder（行分割）、DelimterBasedFrameDecoder（特殊字符分割）
* Netty3与netty4区别

Mybatis知识点
* 特点
  * 优点 原生sql、sql语句与代码解耦合、简单易学、sql调优灵活
  * 缺点 半自动ORM、数据库移植性差
* 缓存
  * 一级缓存 作用域session、hashmap实现、默认开启
  * 二级缓存 作用域mapper、支持echache等缓存实现、可配置删除策略、刷新间隔、缓存数量
* 应用相关 预防sql注入、获取自增sql、动态sql标签
* 执行流程
* 主要对象 SqlSessionFactory、SqlSession、Executor、StatementHandler、ParameterHandler、ResultSetHandler、TypeHandler
* 插件机制 拦截器链、作用点、常用插件

## 缓存
* 类型
  * 本地缓存
  * 分布式缓存
  * 多级缓存
* 淘汰策略 FIFO、LRU、LFU
* 缓存问题 缓存不一致、缓存更新、缓存击穿、缓存雪崩
* Memcache
  * 特点 多线程、异步IO、KV存储、内存存储、没有持久化
  * 内存结构 Slab（1MB）：Chunk、Page
  * 钙化问题、失效与剔除机制
  * 限制 key小于250B、value小于1MB、过期时间小于30天
* Redis 单线程异步IO、支持持久化、多数据结构、主从模式
  * 数据结构 string、hash、set、list、zset
    * dictEntry 字典
    * redisObject 
    * SDS
    * ziplist、linkedlist、quicklist双向无环列表
    * zipmap、intset、hashtable
    * ziplist、skiplist
  * 功能 bitmap、hypeLogLog、geospatial、pub/sub、lua脚本、事务
  * 持久化 RDF（fork子进程）、AOF（追加策略）
  * redis cluster sentinel、主从同步、master选举
  * key失效机制 主动删除、被动删除
  * 淘汰策略
    * voltile-lru
    * voltile-ttl
    * voltile-random
    * voltile-random
    * allkeys-lru
    * allkeys-random
    * no-eviction
  * 4.0与5.0新特性 module、stream、psync 2.0、混合rdb-aof持久化机制

## 消息队列
消息队列
* 队列
  * 使用场景 应用解耦合、服务通信、异步任务、削峰填谷、消息广播
  * 消息协议 JMS（java的消息队列协议）、AMQP（应用层协议标准、跨语言）
  * 常用队列 
    * RabbitMQ 单机万级
    * ActiveMQ 单机万级，不轻巧、会丢消息
    * RocketMQ 单机十万级，高吞吐量
    * Kafka 单机十万级，依赖zookeeper，消息可能重复、但不会丢失
      * 架构 Producer、KafkaCluster（Broker Topic-Partition1）、Consumer、ZooKeeper
* 数据中间件
  * sharding-sphere
  * mycat
* 数据库
  * 关系数据库 oracle、mysql、mariaDB、posgreSQL
  * NoSQL redis、mongoDB、hbase、cassanra、pika
  * NewSQL TIDB、OceanBase
  * 事务特性 ACID
  * 事务类型 扁平事务、带保存点的扁平事务、嵌套事务、分布式事务
  * 数据库范式

## MySQL
* 常用sql语句 条件、关联、排序、分组、逻辑关键字、函数
* 数据类型 整型、浮点、字符、二进制、时间
* 引擎 myIASM、innoDB、tokuDB
* 锁 表锁、行锁、共享锁
* 索引 读多写少的场景
  * 类型 唯一索引、主键索引、普通索引、联合索引（最左匹配）、全文索引（char、varchar、text上）
  * 实现 Btree、R-tree（多维数据）、hash（效率高、不支持范围、排序）、fullText
* 存储过程与函数
* 新特性 默认utf-8、隐藏索引、通用表达式、窗口函数
* sql优化
  * 查看慢日志
  * explain查看执行计划
  * 优化sql语句

### mysql优化
* 表结构与索引
  * 分库分表、读写分离
  * 为字段选择合适的数据类型
  * 将字段多的表分解为多个表，增加中间件
  * 混合范式与反范式，适当冗余
  * 善用索引，避免滥用
  * 列字段尽可能的使用 not null
* sql语句
  * 寻找最需要优化的语句：分析慢查询日志
  * 分析工具，explain、profile
  * 避免使用select *
  * 尽量使用 prepare statements
  * 尽量使用索引的字段上排序
* mysql参数参数
* 硬件及系统配置

## 架构演进与前沿技术

