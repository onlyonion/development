[nginx问题](https://www.cnblogs.com/mmdln/p/8952292.html)

Nginx是一个web服务器和方向代理服务器，用于HTTP、HTTPS、SMTP、POP3和IMAP协议。
### 请解释Nginx如何处理HTTP请求。
Nginx使用反应器模式。主事件循环等待操作系统发出准备事件的信号，这样数据就可以从套接字读取，在该实例中读取到缓冲区并进行处理。单个线程可以提供数万个并发连接。

### 使用“反向代理服务器”的优点是什么?
反向代理服务器可以隐藏源服务器的存在和特征。它充当互联网云和web服务器之间的中间层。这对于安全方面来说是很好的，特别是当您使用web托管服务时。

### 请列举Nginx服务器的最佳用途
Nginx服务器的最佳用法是在网络上部署动态HTTP内容，使用SCGI、WSGI应用程序服务器、用于脚本的FastCGI处理程序。它还可以作为负载均衡器。


### 请解释Nginx服务器上的Master和Worker进程分别是什么?
Master进程：读取及评估配置和维持
Worker进程：处理请求

### 请解释什么是C10K问题?
C10K问题是指无法同时处理大量客户端(10,000)的网络套接字。

### 解释Nginx是否支持将请求压缩到上游?
您可以使用Nginx模块gunzip将请求压缩到上游。gunzip模块是一个过滤器，它可以对不支持“gzip”编码方法的客户机或服务器使用“内容编码:gzip”来解压缩响应。

### 解释如何在Nginx中获得当前的时间?
要获得Nginx的当前时间，必须使用SSI模块、$date_gmt和$date_local的变量。
Proxy_set_header THE-TIME $date_gmt;

### FastCGI
FastCGI全称 快速通用网关接口（FastCommonGatewayInterface）。
FastCGI像是一个常驻(long-live)型的CGI，它可以一直执行着，只要激活后，不会每次都要花费时间去fork一次(这是CGI最为人诟病的fork-and-execute 模式)。它还支持分布式的运算, 即 FastCGI 程序可以在网站服务器以外的主机上执行并且接受来自其它网站服务器来的请求。


[nginx的5个问题](https://blog.csdn.net/ningyuxuan123/article/details/86705631)

### 1.什么是nginx,它的优势和功能？
* 高扩展性，跨平台
* 高可靠性：用于反向代理，宕机的概率微乎其微
* 低内存消耗
* 单机支持10万以上的并发连接
* 热部署
* 最自由的BSD许可协议

### 2.nginx的常用算法实现
1. round-robin
2. least-connected `least_conn;`
3. ip-hash `ip_hash;`
4. weighted `server 10.10.10.1 weight=3;`

### 3.为什么不使用多线程？
Apache: 创建多个进程或线程，而每个进程或线程都会为其分配cpu和内存（线程要比进程小的多，所以worker支持比perfork高的并发），并发过大会榨干服务器资源。

Nginx: 采用单线程来异步非阻塞处理请求（管理员可以配置Nginx主进程的工作进程的数量）(epoll)，
不会为每个请求分配cpu和内存资源，**节省了大量资源**，同时也**减少**了大量的CPU的**上下文切换**。

### 4.Nginx是如何处理一个请求的呢？
首先，nginx在启动时，会解析配置文件，得到需要监听的端口与ip地址，然后在nginx的master进程里面
先初始化好这个监控的socket，再进行listen。然后再fork出多个子进程出来, 子进程会竞争accept新的连接。

此时，客户端就可以向nginx发起连接了。
当客户端与nginx进行三次握手，与nginx建立好一个连接后，此时，某一个子进程会accept成功，
然后创建nginx对连接的封装，即ngx_connection_t结构体接着，
根据事件调用相应的事件处理模块，如http模块与客户端进行数据的交换，
最后，nginx或客户端来主动关掉连接，到此，一个连接就寿终正寝了

### 5.动态资源、静态资源分离的原因
高用户访问静态代码的速度，降低对后台应用访问
静态资源放到nginx中，动态资源转发到tomcat服务器中

PS：设计模式之单一职责