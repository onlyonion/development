## Java技术体系
Sun官方定义的Java技术体系包括：
1. Java程序设计语言
2. 各种硬件平台上的Java虚拟机
3. Class文件格式
4. Java API类库
5. 来自商业机构和开源社区的第三方Java类库

Java程序设计语言、Java虚拟机、Java API类库这三部分统称为JDK（Java Development Kit）

## Java基础
* [Java虚拟机](10-java/02-jvm.md)
* [集合框架](10-java/10-collection.md)
* [并发编程](10-java/50-concurrent.md)

## 计算机基础
* 集合 涉及数据结构、存储器管理
* 多线程 并发、锁；涉及操作系统进程管理、处理机调度
* IO 输入输出系统、文件系统；涉及数据结构、操作系统、组成原理
* 网络Socket 计算机网络
* JVM 涉及操作系统、数据结构、组成原理、编译原理
* 反射 代理 序列化

## 源码
* java
  * rt.jar
    * [java](/10-java/src/rt.jar.src/java/README.md)
    * [javax](/10-java/src/rt.jar.src/javax/README.md)
    * [org](/10-java/src/rt.jar.src/org/README.md)
    * [sun](/10-java/src/rt.jar.src/sun/README.md)
  * openjdk
    * [hotspot.src](/10-java/src/openjdk/hotspot.src/README.md)
    * [jdk](/10-java/src/openjdk/jdk/README.md)
* framework
  * [spring](/docs/20-framework/src/spring/)
  * [mybatis](/docs/20-framework/src/mybatis/)
  * server
    * [tomcat](/docs/20-framework/src/tomcat/)
    * [netty](/docs/20-framework/src/netty/)
    * [jetty](/docs/20-framework/src/jetty/)
    * [nginx](/docs/20-framework/src/nginx/)
    * [undertow](/docs/20-framework/src/undertow/)
  * apache
    * [curator-recipes](/docs/20-framework/src/apache/curator-recipes/README.md)
    * [derby](/docs/20-framework/src/apache/derby/README.md)
    * [httpclient](/docs/20-framework/src/apache/httpclient/README.md)
    * [shiro](/docs/20-framework/src/apache/shiro/README.md)
  * common
    * [activiti](/docs/20-framework/src/common/activiti/)
    * [asm](/docs/20-framework/src/common/asm/)
    * [cglib](/docs/20-framework/src/common/cglib/)
    * [disruptor](/docs/20-framework/src/common/disruptor/)
    * [guava](/docs/20-framework/src/common/guava/)
    * [hutool](/docs/20-framework/src/common/hutool/)
    * [javassist](/docs/20-framework/src/common/javassist/)
    * [prometheus](/docs/20-framework/src/common/prometheus/)
    * [quasar](/docs/20-framework/src/common/quasar/)
* distributed
  * [consul](/docs/30-distributed/src/consul/)
  * [dubbo](/docs/30-distributed/src/dubbo/)
  * [redis](/docs/30-distributed/src/redis/)
  * [rocketmq](/docs/30-distributed/src/rocketmq/)
  * [zookeeper](/docs/30-distributed/src/zookeeper/)
* database
  * [druid](/docs/50-database/src/druid/)
  * [mycat](/docs/50-database/src/mycat/)
  * [mysql](/docs/50-database/src/mysql/)
  * [mysql-connector](/docs/50-database/src/mysql-connector/)
  * [shardingsphere](/docs/50-database/src/shardingsphere/)