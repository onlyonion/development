《实战Nginx 取代Apache的高性能Web服务器》张宴 著 电子工业出版社

## 第1章 Nginx简介
### 1.1 常用的Web服务器简介
1. Apache服务器 selec模块，prefork模块为多进程模式
2. Lighttpd服务器
3. Tomcat服务器
4. IBM WebSphere服务器
5. Microsoft IIS Internet Information Server

### 1.2 Nginx的发展
Nginx能够选择高效的epoll（Linxu 2.6内核）、kqueue（FreeBSD）、eventport（Solaris 10）作为网络IO模型，
在高连接并发的请求下，Nginx是Apache服务器不错的替代品，能够支持高达50000个并发连接数的响应，而内容、
CPU等系统资源消耗却非常低，运行非常稳定。

### 1.3 选择Nginx的理由
1. 它可以高并发连接
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
## 第7章 Nginx的Rewrite规则与实例

## 第8章 Nginx模块开发
## 第9章 Nginx的Web缓存服务与新浪网的开源NCACHE模块
## 第10章 Nginx在国内知名网站中的应用案例
## 第11章 Nginx的非典型应用实例

## 第12章 Nginx的核心模块
## 第13章 Nginx的标准Http模块
## 第14章 Nginx的其他Http模块
## 第15章 Nginx的邮件模块