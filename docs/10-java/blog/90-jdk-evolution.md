# Java版本演化

[Java1.0-1.11各个版本的新特性](https://www.cnblogs.com/Ranyuangang/p/9707712.html)

## JDK Version 1.0
1996-01-23 Oak(橡树)

初代版本，伟大的一个里程碑，但是是纯解释运行，使用外挂JIT，性能比较差，运行速度慢。

## JDK Version 1.1
1997-02-19

* JDBC(Java DataBase Connectivity);
* 支持内部类;
* RMI(Remote Method Invocation) ;
* 反射;
* Java Bean;

## JDK Version 1.2
1998-12-08 Playground(操场)

* 集合框架
* JIT(Just In Time)编译器
* 对打包的Java文件进行数字签名
* JFC(Java Foundation Classes), 包括Swing 1.0, 拖放和Java2D类库
* Java插件
* JDBC中引入可滚动结果集,BLOB,CLOB,批量更新和用户自定义类型
* Applet中添加声音支持

## JDK Version 1.3
2000-05-08 Kestrel(红隼)

* Java Sound API
* jar文件索引
* 对Java的各个方面都做了大量优化和增强

## JDK Version 1.4
2004-02-06 Merlin(隼)

* XML处理
* Java打印服务
* Logging API
* Java Web Start
* JDBC 3.0 API
* 断言
* Preferences API
* 链式异常处理
* 支持IPV6
* 支持正则表达式
* 引入Imgae I/O API

## Java 5
2004-09-30 Tiger(老虎)

1. 自动装箱、拆箱 
2. 静态导入(static import) 
3. 增强for循环（for-each） 
4. 可变参数 
5. 枚举(enmu) 
6. 泛型 
7. 并发API

## Java 6
2006-12-11 Mustang(野马)

1. AWT中新增两个类：Desktop和SystemTray 
2. 使用JAXB2来实现对象与XML之间的映射 
3. 新增处理DOM和SAX之外又一种处理XML文档的API：STAX 
4. 使用Compiler API动态编译java源文件 
5. 轻量级Http Server API 
6. 插入式注解处理API（用于处理Annotations） 
7. 用Console开发控制台程序 
8. 对ruby、groovy、javascript等脚本语言的支持 
9. Common Annotations

## Java 7
2011-07-28 Dolphin(海豚)

1. 对Java集合（Collections）的增强支持 
2. 在switch中可用String 
3. 数值可加下划线（eg:int one_million=123_1） 
4. 支持二进制文字（int binary=0b1001_1001） 
5. 简化可变参数方法的调用 
6. 自动资源管理 

## Java 8
2014年3月18

1.	Lambda 表达式
2.	方法引用
3.	函数式接口
4.	新增接口：默认方法与静态方法 
5.	Stream Java集合框架（Java Collections framework）对列表（Lists）和集合（Collections）数据进行提取、过滤和排序
6.	Optional 类
7.	Nashorn, JavaScript引擎(Rhino的接替者，轻量级高性能的javascript运行环境) 
8.	新的日期时间 API
9.	Base64
10. 数组并行（parallel）操作 
11. JVM的permGen空间移除，被Metaspace元空间取代

[jdk8](https://www.oracle.com/technetwork/java/javase/8-whats-new-2157071.html)

## Java 9
September 2017

1. Jigsaw 项目：颠覆性的模块化 JDK
2. 简化了的进程 API
3. 轻量级的 JSON API
4. 钱和货币的相关 API
5. 改善锁争用机制
6. 代码分段缓存
7. 智能 Java 编译工具

[JDK 9](https://baijiahao.baidu.com/s?id=1585986204842398970&wfr=spider&for=pc)

## Java 10

1. 一个局部变量类型推断，通过增强语言特性将类型推断扩展到局部变量，目的是减少与编码相关的“仪式”，同时保持对静态类型的安全承诺。
2. 一个干净的垃圾收集器接口，用来改善垃圾收集器源代码之间的隔离效果，这样可以为HotSpot 虚拟机中的内部垃圾收集代码提供更好的模块化功能，也可以更容易向 HotSpot 添加新的垃圾收集器。
3. 并行、完整的 G1 垃圾收集器，通过实现并行性来改善最坏情况下的延迟问题。
4. 启用 HotSpot 将对象堆分配给用户指定的备用内存设备（如 NVDIMM 内存模块），这个特性也侧面预示了未来的系统可能会采用异构的内存架构。
5. 在 Linux / x64 平台上以实验性方式启用基于 Java 的即时编译器
6. 将 JDK 的多个存储库合并成一个，简化开发。目前的代码库被分解成了多个库，容易出现源代码的管理问题。
7. 应用程序数据共享，通过跨进程共享通用类的元数据，减少空间占用及启动时长。
8. 线程本地握手，不执行全局 VM 安全点也能对线程执行回调，同时实现单线程停止回调。
9. JDK 提供了一组默认证书，开源 Java SE 的 CA程序，对开发人员更具吸引力。

## Java 11
October 16, 2018

* 181 嵌套类可见性控制
* 309 动态文件常量
* 315 改进 Aarch64 Intrinsics
* 318 Epsilon–一个无操作的垃圾收集器
* 320 删除 Java EE 和 CORBA 模块
* 321 `HttpClient`
* 323 用于 Lambda 参数的局部变量语法
* 324 Curve25519 和 Curve448 算法的密钥协议
* 327 Unicode 10
* 328 Flight Recorder(飞行记录器)
* 329 haCha20 和 Poly1305 加密算法支持
* 330 Launch Single-File Source-Code Programs（启动单一文件的源代码程序）
* 331 低开销的 Heap Profiling
* 332 TLS 1.3支持
* 333 `ZGC`: A Scalable Low-Latency Garbage Collector（可伸缩低延迟垃圾收集器）
* 335 弃用 Nashorn JavaScript 引擎
* 336 弃用 Pack200 工具和 API

[JDK 11主要特性一览](https://blog.csdn.net/cun_chen/article/details/82807552 )


## Java 12
java12不是LTS(Long-Term Support)版本(oracle版本才有LTS)，oracle对该版本的support周期为6个月。
这个版本主要有几个更新点，一个是语法层更新，一个是API层面的更新，另外主要是GC方面的更新。
