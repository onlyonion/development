## Java技术体系
Sun官方定义的Java技术体系包括：
1. Java程序设计语言
2. 各种硬件平台上的Java虚拟机
3. Class文件格式
4. Java API类库
5. 来自商业机构和开源社区的第三方Java类库

Java程序设计语言、Java虚拟机、Java API类库这三部分统称为JDK（Java Development Kit）

## Java基础
* [Java虚拟机](/docs/10-java/02-jvm.md)
* [集合框架](/docs/10-java/10-collection.md)
* [并发编程](/docs/10-java/50-concurrent.md)
* [Java新特性](/docs/10-java/blog/90-jdk-evolution.md)

## 计算机基础
* 集合 数据结构与算法、存储器管理
* 多线程 并发、锁；操作系统进程管理、处理机调度
* I/O 输入输出系统、文件系统；数据结构与算法、操作系统、组成原理
* 网络Socket [计算机网络](/docs/00-base/40-net.md)
* JVM 操作系统、数据结构与算法、组成原理、编译原理
* 反射 代理、序列化、运行时

## src
* java
  * rt.jar
    * [`java`](/docs/10-java/src/rt.jar.src/java/README.md)
    * [javax](/10-java/src/rt.jar.src/javax/README.md)
    * [org](/10-java/src/rt.jar.src/org/README.md)
    * [sun](/10-java/src/rt.jar.src/sun/README.md)
  * openjdk
    * [`hotspot.src`](/10-java/src/openjdk/hotspot.src/README.md)
    * [jdk](/10-java/src/openjdk/jdk/README.md)
* framework
  * [`spring`](/docs/20-framework/src/spring/README.md)
  * [`mybatis`](/docs/20-framework/src/mybatis/README.md)
  * server
    * [`tomcat`](/docs/20-framework/src/server/tomcat/README.md)
    * [`netty`](/docs/20-framework/src/server/netty/README.md)
    * [jetty](/docs/20-framework/src/server/jetty/README.md)
    * [undertow](/docs/20-framework/src/server/undertow/README.md)
    * [`nginx`](/docs/20-framework/src/server/nginx/README.md)
  * apache
    * [derby](/docs/20-framework/src/apache/derby/README.md)
    * [httpcomponents](/docs/20-framework/src/http/httpcomponents/README.md)
      * [httpclient](/docs/20-framework/src/http/httpcomponents/httpclient/README.md)
      * [httpasyncclient](/docs/20-framework/src/http/httpcomponents/httpasyncclient/README.md)
      * [httpcore](/docs/20-framework/src/http/httpcomponents/httpcore/README.md)
    * [shiro](/docs/20-framework/src/apache/shiro/README.md)
  * common
    * [activiti](/docs/20-framework/src/common/activiti/README.md)
    * [asm](/docs/20-framework/src/common/bytecode/asm/README.md)
    * [cglib](/docs/20-framework/src/common/bytecode/cglib/README.md)
    * [disruptor](/docs/20-framework/src/common/disruptor/README.md)
    * [guava](/docs/20-framework/src/common/guava/README.md)
    * [hutool](/docs/20-framework/src/common/hutool/README.md)
    * [javassist](/docs/20-framework/src/common/bytecode/javassist/README.md)
    * [prometheus](/docs/30-distributed/src/monitor/prometheus/README.md)
    * [quasar](/docs/20-framework/src/common/quasar/README.md)
* distributed
  * [`dubbo`](/docs/30-distributed/src/dubbo/README.md)
  * [`redis`](/docs/30-distributed/src/redis/README.md)
  * [`rocketmq`](/docs/30-distributed/src/rocketmq/README.md)
  * [zookeeper](/docs/30-distributed/src/registry/zookeeper/README.md)
  * [consul](/docs/30-distributed/src/registry/consul/README.md)
* database
  * [druid](/docs/50-database/src/druid/README.md)
  * [mysql-connector](/docs/50-database/src/mysql-connector/README.md)
  * [shardingsphere](/docs/50-database/src/shardingsphere/README.md)
  * [mycat](/docs/50-database/src/mycat/README.md)
  * [`mysql`](/docs/50-database/src/mysql/README.md)
  * [linux](/docs/00-base/src/linux/README.md)
  
## image
* [computer](/docs/00-base/99-image.md)  
* [java](/docs/10-java/99-image.md)  
* [framework](/docs/20-framework/99-image.md)
* [distributed](/docs/30-distributed/99-image.md)
* [architecture](/docs/40-architecture/99-image.md)
* [database](/docs/50-database/99-image.md)
* [js](/docs/60-js/99-image.md)
* [dev-ops](/docs/70-dev-ops/99-image.md)
* [project](/docs/80-project/99-image.md)
* [book](/docs/99-book/99-image.md)