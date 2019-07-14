## 分布式特点
* 对等：各个节点没有主次之分
* 分布：在空间上随意分布，水平扩展，比如我们sinai随意再lf和dx随意添加机器
* 并发：对等的节点可能并发访问访问共享资源，如同时访问redis，db，或一个接口
* 缺乏全局的时钟：因为分布和对等性，你不知道两个事件发生的前后顺序
* 故障不固定：很依赖节点之间的通信，因为网路分区或通信异常会导致机器状态不一致

## 概念与实现
* 负载均衡:    Nginx：高性能、高并发的web服务器；功能包括负载均衡、反向代理、静态内容缓存、访问控制；工作在应用层 
              LVS： Linux virtual server，基于集群技术和Linux操作系统实现一个高性能、高可用的服务器；工作在网络层
* webserver： Java：Tomcat，Apache，Jboss
              Python：gunicorn、uwsgi、twisted、webpy、tornado
* service：    SOA、微服务、spring boot，django
* 容器：       docker，kubernetes
* cache：      memcache、redis等
* 协调中心：    zookeeper、etcd等 zookeeper使用了Paxos协议Paxos是强一致性，高可用的去中心化分布式。
* rpc框架：     grpc、dubbo、brpcdubbo是阿里开源的Java语言开发的高性能RPC框架，在阿里系的诸多架构中，都使用了dubbo + spring boot
* 消息队列：    kafka、rabbitMQ、rocketMQ、QSP 消息队列的应用场景：异步处理、应用解耦、流量削锋和消息通讯
* 实时数据平台： storm、akka
* 离线数据平台： hadoop、spark PS: apark、akka、kafka都是scala语言写的，看到这个语言还是很牛逼的
* dbproxy：     cobar也是阿里开源的，在阿里系中使用也非常广泛，是关系型数据库的sharding + replica 代理
* db：          mysql、oracle、MongoDB、HBase
* 搜索：        elasticsearch、solr
* 日志：        rsyslog、elk、flume

[分布式原理介绍](https://blog.csdn.net/elricboa/article/details/78698197)

[什么是分布式系统，如何学习分布式系统](https://www.cnblogs.com/xybaby/p/7787034.html)

