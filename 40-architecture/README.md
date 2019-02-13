
## 架构
* SOA 面向服务架构
* 微服务架构 微框架
* 设计原则
* 设计模式

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

## 中台-共享服务体系
服务框架、服务中心、数据拆分、异步与缓存、监控运营、稳定性建设