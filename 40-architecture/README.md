# 架构

体系结构风格、模式、描述、评估

## 架构设计
* [设计](/40-architecture/design/README.md)
  * [设计原则](/40-architecture/design/01-design.md)
  * [设计模式](/40-architecture/design/09-design-pattern.md)
  * [DDD领域驱动设计](/40-architecture/design/30-ddd.md)
  * TDD测试驱动开发
* [面向服务系统架构SOA](/40-architecture/02-soa.md)
* [微服务架构](/40-architecture/03-ms.md)
  * [微框架](/40-architecture/31-ms-framework.md)
  * [网站架构](/40-architecture/90-misc-architecture.md)
* [云架构](/40-architecture/cloud/README.md)

## 架构分层
* 前端
* web服务
* 应用层
* 业务逻辑
* 存储层

## 架构演化

单一应用架构 -> 垂直应用架构 -> 分布式服务架构 -> 流动计算架构(云架构)

关注点分离；解耦合

### 后端架构演化
* MODEL-1 JSP + JavaBean
* MODEL-2 Servlet + JSP + JavaBean
* 三层 Controller + Service + Dao + JSP(html + js + css) + JavaBean
* Spring n多配置
  - controller
    - gateway
    - api
  - service
    - webservice
    - rpc
    - microservice  service mesh; function
    - mq
  - dao 
    - jdbc
    - mybatis
    - sharding
    - mycat
    - cache rdbms -> nosql -> newsql
    - searchengine
    - bigdata
* Springboot 简约配置
* springcloud

### 前端架构演化
* jsp/vm/flt + (html + js + css)
* jsp/vm/flt + (jquery)
* react/angular/vue

## links
* [《企业IT架构转型之道 阿里巴巴中台战略思想与架构实战》钟华](/99-book/notes/40-architecture/企业IT架构转型之道.md)
  * 中台-共享服务体系 
  * 服务框架、服务中心、数据拆分、异步与缓存、监控运营、稳定性建设
* [《云计算》刘鹏](/99-book/notes/40-architecture/云计算.md)
* [系统架构设计师教程(第4版)](/99-book/notes/80-project/系统架构设计师教程(第4版).md)
* [系统架构设计师教程](/99-book/notes/80-project/系统架构设计师教程.md)