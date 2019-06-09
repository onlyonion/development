《实战Nginx 取代Apache的高性能Web服务器》张宴 著 电子工业出版社

* Nginx 安装、配置、优化
* Nginx 与PHP、JSP、ASP.NET、Perl的安装配置
* Nginx HTTP负载均衡与反向代理、Rewrite规则；Web缓存服务、安全架构
* Nginx 模块开发、核心模块
* Nginx 的核心模块、HTTP模块、邮件模块

## 第1章 Nginx简介
### 1.1 常用的Web服务器简介
1. Apache服务器 selec模块，prefork模块为多进程模式
2. Lighttpd服务器
3. Tomcat服务器
4. IBM WebSphere服务器
5. Microsoft IIS Internet Information Server

### 1.2 Nginx的发展
Nginx能够选择高效的**epoll（Linxu 2.6内核）、kqueue（FreeBSD）、eventport（Solaris 10）**作为网络IO模型，
在高连接并发的请求下，Nginx是Apache服务器不错的替代品，能够支持高达50000个并发连接数的响应，而内容、
CPU等系统资源消耗却非常低，运行非常稳定。

### 1.3 选择Nginx的理由
1. 它可以高并发连接 官方测试支撑5万并发连接，实际可支撑2-4万并发连接数。
2. 内存消耗小
3. 成本低廉
4. 其他理由
   1. 配置文件简单
   2. 支持rewrite重写
   3. 内置的健康检查功能
   4. 节省宽带
   5. 稳定性高
   6. 支持热部署

## 第2章 Nginx服务器的安装与配置
gcc编译器，autoconf、automake工具

## 第3章 Nginx的基本配置与优化
### 3.1 Nginx的完整配置示例
### 3.2 Nginx的虚拟主机配置
虚拟主机，把一台运行在因特网上的服务器主机分成一台台虚拟的主机，每台虚拟主机都将可以是一个独立的网站，
可以具有独立的域名，具有完整的Internet服务器功能（www、ftp、email等），同一台主机上的虚拟主机之间是完
全独立的。
**关键词：虚拟机、资源隔离**

### 3.3 Nginx的日志文件配置与切割
### 3.4 Nginx的压缩输出配置
### 3.5 Nginx的自动列目录配置
### 3.6 Nginx的浏览器本地缓存设置

## 第4章 Nginx与PHP（FastCGI）的安装、配置与优化
LAMP（LInux+Apache+Mysql+Perl/PHP/Python）

FastCGI是语言无关的、可伸缩架构的CGI开放扩展，其主要行为是将CGI解释器进程保持在内存中并因此获得较高的性能。

## 第5章 Nginx与JSP、ASP.NET、Perl的安全与配置
## 第6章 Nginx HTTP负载均衡和反向代理的配置与优化
### 6.1 什么是负载均衡和反向代理
负载均衡是台多台服务器以对称的方式组成一个**服务器集合**，每台服务器都具有等价的地位，都可以单独对外提供服务而无须其他服务器的辅助。

反向代理是指以代理服务器接受Internet上的连接请求，然后将**请求转发**给内部网络上的服务器，
并将从服务器上得到的结果返回给Internet上请求连接的客户端，此时代理服务器对外就表现为一个服务器。
### 6.2 常见的Web负载均衡方法
#### 6.2.1 用户手动选择
#### 6.2.2 DNS轮询方式
#### 6.2.3 四/七层负载均衡设备
#### 6.2.4 多线多地区智能DNS解析与混合负载均衡方式
### 6.3 Nginx负载均衡与反向代理的配置实例
### 6.4 Nginx负载均衡的HTTP Upstream模块
```
upstream backend {
    server backend1.example.com weight=5;
    server backend2.example.com:8080;
    server unix:/tmp/backend3;
}
server {
    location / {
        proxy_pass http://backend;
    }
}
```
### 6.5 Nignx负载均衡服务器的双机高可用

## 第7章 Nginx的Rewrite规则与实例
## 第8章 Nginx模块开发
## 第9章 Nginx的Web缓存服务与新浪网的开源NCACHE模块
## 第10章 Nginx在国内知名网站中的应用案例
## 第11章 Nginx的非典型应用实例

## 第12章 Nginx的核心模块
### 12.1 主模块指令
```js
daemon on|off
env VAR|VAR=VALUE
debug_points [stop|abort]
erro_log file [debug|info|notice|warn|error|crit]
log_not_found on|off
include file|*
lock_file file
master_process on|off
pid file
ssl_engine engine
time_resolution t
try_files path1 [path2] uri
user user[group]
worker_cpu_affinity // 只能在linux环境下，绑定cpu
worker_priority
worker_processes number // 工作进程数
worker_rlimit_core
worker_rlimit_sigpending limit
working_directory path
```
### 12.2 主模块变量
### 12.3 事件模块指令
```js
accept_mutex [on|off] // Nignx使用连接互斥锁进行顺序的accept()系统调用
accept_mutex_delay Nms // 如果一个工作进程没有互斥锁，它至少在最少N（默认500）毫秒延迟之后再尝试获取互斥锁
debug_connection [ip|CIDR]
use [kqueue|rtsig|epoll|dev/poll|select|poll|eventport] // 使用哪种事件模型
worker_connections number // 设置每个工作进程能够处理的连接数。最大连接数 max_client = worker_processes * worker_connections
```
## 第13章 Nginx的标准Http模块
## 第14章 Nginx的其他Http模块
## 第15章 Nginx的邮件模块