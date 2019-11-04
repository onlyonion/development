# DevOps

DevOps（Development和Operations的组合词）
开发（应用程序/软件工程）、技术运营和质量保障（QA）部门协同

* Maven
* Nexus
* Jenkins
* OneOps
* Kubernetes

演化
* 传统运维 env, war, jar
* 自动化运维 maven, nexus, git, svn, jenkins, shell, python
* 云上运维 Kubernetes, docker

## Dev

### 开发模型
* 极限编程
* 敏捷开发
* CodeReview
* 持续集成

### 构建
* ant
* maven
* gradle

### CI/CD
* svn
* git
* github
* gitlab
* nexus
* jenkins
* GitLab CI
* Travis CI + github

### 质量保证
* Checkstyle
* FindBugs
* SonarQube
* searchcode
* upsource

### 压测工具
* JMeter
* jMH
* AB
* LoadRunner

### 网络工具
* PostMan
* WireShark
* Fiddier
* Charles

### 文档管理
* javaDoc 注解方式
* Swagger 支持多种语言，注解方式描述参数、返回值

## Ops
### 安全
* waf 防火墙

### 容器与代理
* dns 域名解析，DNS负载均衡
* nginx
* OpenResty nginx + lua
* Kong 一个使用了lua-nginx-module运行在Nginx之上的Lua应用。可扩展的开源API层(也称为API网关或API中间件)
* envoy 新型网络代理/网络服务器
* HAProxy C语言编写，提供高可用性、负载均衡，以及基于TCP和HTTP的应用程序代理。
* lvs
* keepalived
* nfs network file system
* 应用服务器
  * tomcat
  * jetty
  * undertow

### 监控
* [zabbix](/docs/70-dev-ops/blog/30-monitor.md) 一个基于WEB界面的提供分布式系统监视以及网络监视功能的企业级的开源解决方案
* prometheus 系统监控报警框架，一个开源的系统监控和告警的工具包，其采用pull方式采集时间序列，通过http协议传输
* openfalcon 分布式高性能监控系统
* grafana The open platform for beautiful analytics and monitoring
* pinpoint APM(Application Performance Management，应用性能管理)监控工具。是一个分析大型分布式系统的平台,提供解决方案来处理海量跟踪数据
* cat 实时应用和性能监控系统
* zipkin 分布式服务的调用链跟踪系统
* tingyun 应用性能监控平台

### 性能优化
* 前端优化
* nginx
* tomcat
* jvm
* mysql

### 分析
* linux shell
* [linux cmd](/docs/70-dev-ops/11-devops-cmd.md)
  * vmstat iostat iotop ifstat iftop netstat dstat strace GDB lsof tcpdump traceroutine
* jdk bin
  * JMC(java mission control) JFR jstack jmap jstat jinfo jcmd
  * btrace MAT
* [问题排查案例](/docs/70-dev-ops/12-devops-check.md)
* cobbler 运维自动化工具