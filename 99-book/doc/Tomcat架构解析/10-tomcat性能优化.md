## 10.1 tomcat性能测试及诊断

* 响应时间
* 吞吐量 tps

### 10.1.1 测试方法

* 负载测试
* 压力测试
* 持续运行时间测试

### 10.1.2 性能测试工具

* ApacheBench

```
ab -n 500 -c 5 http://localhost:8080/sample/index.jsp
```

* Apache JMeter

### 10.1.3 数据采集及分析

* 网络

带宽   nload, bmon, slurm
套接字 iftop, iptraf, tcptrack, pkstat, netwatch

* 内存/CPU

top

vmstat

* java

```
jstat   查看内存统计（javaHeap）、GC

jmap    进程内堆详情、对象数量、内存使用；jhat；eclipse mat

jstack  栈信息

jconsole

visualvm
```

## 10.2 tomcat性能优化

### 10.2.1 JVM优化

jvm垃圾回收性能度量：吞吐量；暂停

-Xms
-Xmx
-Xmn
-XX:NewSize=
-XX:MaxNewSize=
-XX:MetaspaceSize=
-XX:MaxMetaspaceSize=
-Xss

### 10.2.2 Tomcat配置

#### 调整server.xml配置

* maxConnection
* tcpNoDelay
* maxKeepAliveRequest
* socketBuffer
* enableLookups
* http静态文件压缩
* 高性能链接器提升IO效率
* 关闭自动部署autoDeploy

#### 调整JSP页面设置

* jsp标签body池

#### 与Web服务器集成

* 动静分离
* 集群、负载均衡

## 10.3 应用性能优化建议