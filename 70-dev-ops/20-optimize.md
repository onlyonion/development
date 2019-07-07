
## 性能调优
1.	性能基准
2.	衡量维度

## JVM 调优
1.	jvm 运行时数据区
2.	jvm 内存模型
3.	垃圾收集算法与垃圾收集器
4.	GC日志
5.	eclipse MAR 分析dump文件

## MySQL 调优
1.	MySQL底层B+Tree机制
2.	SQL执行计划、索引优化
3.	SQL语句优化

## 容器性能调优
tomcat、nginx

### Tomcat 调优
1.	Tomcat运行机制及框架
2.	Tomcat线程模型
3.	Tomcat系统参数
4.	基准测试

JVM参数、线程参数、IO选择、APR
[闲谈Tomcat性能优化](https://www.cnblogs.com/zhuawang/p/5213192.html)

### nginx 调优
Nginx软件调优
1. 隐藏 Nginx 版本号
2. 隐藏 Nginx 版本号和软件名
3. 更改 Nginx 服务的默认用户
4. 优化 Nginx worker 进程数
5. 绑定 Nginx 进程到不同的 CPU 上
6. 优化 Nginx 处理事件模型
7. 优化 Nginx 单个进程允许的最大连接数
8. 优化 Nginx worker 进程最大打开文件数
9. 优化服务器域名的散列表大小
10. 开启高效文件传输模式
11. 优化 Nginx 连接超时时间
12. 限制上传文件的大小
13. FastCGI 相关参数调优
14. 配置 Nginx gzip 压缩
15. 配置 Nginx expires 缓存
16. 优化 Nginx日志(日志切割)
17. 优化 Nginx 站点目录
18. 配置 Nginx 防盗链
19. 配置 Nginx 错误页面优雅显示
20. 优化 Nginx 文件权限
21. Nginx 防爬虫优化
22. 控制 Nginx 并发连接数
23. 集群代理优化

1. gzip压缩优化
2. expires缓存有还
3. 网络IO事件模型优化
4. 隐藏软件名称和版本号
5. 防盗链优化
6. 禁止恶意域名解析
7. 禁止通过IP地址访问网站
8. HTTP请求方法优化
9. 防DOS攻击单IP并发连接的控制，与连接速率控制
10. 严格设置web站点目录的权限
11. 将nginx进程以及站点运行于监牢模式
12. 通过robot协议以及HTTP_USER_AGENT防爬虫优化
13. 配置错误页面根据错误码指定网页反馈给用户
14. nginx日志相关优化访问日志切割轮询，不记录指定元素日志、最小化日志目录权限
15. 限制上传到资源目录的程序被访问，防止木马入侵系统破坏文件
16. FastCGI参数buffer和cache配置文件的优化
17. php.ini和php-fpm.conf配置文件的优化
18. 有关web服务的Linux内核方面深度优化（网络连接、IO、内存等）
19. nginx加密传输优化（SSL）
20. web服务器磁盘挂载及网络文件系统的优化
21. 使用nginx cache

解释反向代理
反向代理（Reverse Proxy）方式是指以代理服务器来接受internet上的连接请求，然后将请求转发给内部网络上的服务器，并将从服务器上得到的结果返回给internet上请求连接的客户端，此时代理服务器对外就表现为一个反向代理服务器。

常用的负载均衡算法。ip_hash ，轮询，weight，fair。
配置动静分离

## 前端优化
前端优化的目的是什么 ?
1. 从用户角度而言，优化能够让页面加载得更快、对用户的操作响应得更及时，能够给用户提供更为友好的体验。
2. 从服务商角度而言，优化能够减少页面请求数、或者减小请求所占带宽，能够节省可观的资源。

按粒度大致可以分为两类，
* 页面级别的优化，例如 HTTP请求数、脚本的无阻塞加载、内联脚本的位置优化等

一个完整的请求都需要经过 DNS寻址、与服务器建立连接、发送数据、等待服务器响应、接收数据这样一个 “漫长” 而复杂的过程

* 代码级别的优化，例如 Javascript中的DOM 操作优化、CSS选择符优化、图片优化以及 HTML结构优化等等

* [Web前端性能优化——如何提高页面加载速度](https://www.cnblogs.com/MarcoHan/p/5295398.html)
* [前端性能优化方案都有哪些？](https://www.cnblogs.com/coober/p/8078847.html)

* 网络节点：HttpDNS优化
* 建连复用：SSL化，SPDY建连高复用
* 容器层面：离线化和预加载方案
* 前端组件：请求控制，域名收敛，图片库，前端性能CheckList