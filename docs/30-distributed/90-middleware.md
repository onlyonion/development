中间件
* 服务治理框架
* 数据访问中间件
* 消息中间件
* 分布式配置

## 常用工具
disruptor
diamond
guava

## 代码管理
git/gitlab, svn
maven/nexus,
ci, jenkins, 
wiki, jira

## 监控
错误日志聚合平台-sentry
指标监控平台-promethenus + grafana
监控平台 cat

## 基础平台
* rpc dubbo
* task legends
* config diamond

## 存储
* fs fastdfs
* nosql redis, mongodb
* mq kafka, rocketmq
* elk

## 业务平台
sso, msg


### 分布式监控平台 CAT
* [CAT](https://www.oschina.net/news/78563/cat-depth-analysis) 
* [Central Application Tracking](https://www.cnblogs.com/yeahwell/p/cat.html)

通常Java客户端在业务上使用容易出问题的地方就是内存，另外一个是CPU。内存往往是内存泄露，
占用内存较多导致业务方GC压力增大； CPU开销最终就是看代码的性能。

### prometheus 
分布式业务监控解决方案，采用时间序数据库 

一个开源的系统监控和告警的工具包，其采用pull方式采集时间序列，通过http协议传输