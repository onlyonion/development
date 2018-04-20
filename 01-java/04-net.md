
## TCP/IP

![tcp/ip](./img/tcp-ip-01.jpg "tcp/ip")


## Http协议
基于请求/响应模式的、无状态的协议

### HTTP URL
URL 的一个例子
http://www.mywebsite.com/sj/test/test.aspx?name=sviergn&x=true#stuff

```
Schema:                 http
host:                   www.mywebsite.com
path:                   /sj/test/test.aspx
Query String:           name=sviergn&x=true
Anchor:                 stuff
```

### HTTP的Request/Response：

HTTP Request消息的结构
1.	Request line 请求方法URI协议/版本 
2.	Request header 请求头
3.	body 请求正文 (header和body之间有个空行)

```
GET/sample.jsp HTTP/1.1
Accept:image/gif.image/jpeg,*/*
Accept-Language:zh-cn
Connection:Keep-Alive
Host:localhost
User-Agent:Mozila/4.0(compatible;MSIE5.01;Window NT5.0)
Accept-Encoding:gzip,deflate

username=jinqiao&password=1234
```

HTTP Response消息的结构

1.	Response line 协议状态版本代码描述
2.	Response header 响应头
3.	body 响应正文 (header和body之间有个空行)

```
HTTP/1.1 200 OK
Server:Apache Tomcat/5.0.12
Date:Mon,6Oct2003 13:23:42 GMT
Content-Length:112
 
<html>
<head>
<title>HTTP响应示例<title>
</head>
<body>
Hello HTTP!
</body>
</html>
```


## Socket
这是为了实现以上的通信过程而建立成来的通信管道，其真实的代表是客户端和服务器端的一个通信进程，双方进程通过socket进行通信，而通信的规则采用指定的协议。socket只是一种连接模式，不是协议，tcp、udp，简单的说（虽然不准确）是两个最基本的协议,很多其它协议都是基于这两个协议如，http就是基于tcp的，用socket可以创建tcp连接，也可以创建udp连接，这意味着，用socket可以创建任何协议的连接，因为其它协议都是基于此的。

## https
HTTP 协议中没有加密机制，但可以通过和SSL（Secure Socket Layer，安全套接层）或TLS（Transport LayerSecurity，安全层传输协议）的组合使用，加密 HTTP 的通信内容。

HTTP + 加密 + 认证 + 完整性保护 = HTTPS
HTTPS 是身披 SSL 外壳的 HTTP

### http隐患
HTTP 协议的实现本身非常简单，不论是谁发送过来的请求都会返回响应，因此不确认通信方，会存在以下各种隐患。
1.	无法确定请求发送至目标的 Web 服务器是否是按真实意图返回响应的那台服务器。有可能是已伪装的 Web 服务器。
2.	无法确定响应返回到的客户端是否是按真实意图接收响应的那个客户端。有可能是已伪装的客户端。
3.	无法确定正在通信的对方是否具备访问权限。因为某些Web 服务器上保存着重要的信息， 只想发给特定用户通信的权限。
4.	无法判定请求是来自何方、出自谁手。
5.	即使是无意义的请求也会照单全收。无法阻止海量请求下的DoS 攻击（ Denial of Service， 拒绝服务攻击） 。


四种常见的 POST 提交数据方式
https://www.cnblogs.com/aaronjs/p/4165049.html
协议规定 POST 提交的数据必须放在消息主体（entity-body）中，但协议并没有规定数据必须使用什么编码方式。

服务端通常是根据请求头（headers）中的 Content-Type 字段来获知请求中的消息主体是用何种方式编码，再对主体进行解析。所以说到 POST 提交数据方案，包含了 Content-Type 和消息主体编码方式两部分。

#### 1. application/x-www-form-urlencoded
浏览器的原生 form 表单，如果不设置 enctype 属，那么最终就会以 application/x-www-form-urlencoded 方式提交数据

```
POST http://www.example.com HTTP/1.1
Content-Type: application/x-www-form-urlencoded;charset=utf-8
title=test&sub%5B%5D=1&sub%5B%5D=2&sub%5B%5D=3
```

#### 2. multipart/form-data
使用表单上传文件时，必须让 form 的 enctyped 等于这个值

```
Content-Type:multipart/form-data; boundary=----WebKitFormBoundaryrGKCBY7qhFd3TrwA
------WebKitFormBoundaryrGKCBY7qhFd3TrwA
Content-Disposition: form-data; name="text"
title
------WebKitFormBoundaryrGKCBY7qhFd3TrwA
Content-Disposition: form-data; name="file"; filename="chrome.png"
Content-Type: image/png
PNG ... content of chrome.png ...
------WebKitFormBoundaryrGKCBY7qhFd3TrwA--
```

#### 3. application/json
消息主体是序列化后的 JSON 字符串。
方便的提交复杂的结构化数据，特别适合 RESTful 的接口

```
POST http://www.example.com HTTP/1.1
Content-Type: application/json;charset=utf-8
{"title":"test","sub":[1,2,3]}
```

#### 4. text/xml

XML-RPC（XML Remote Procedure Call）。它是一种使用 HTTP 作为传输协议，XML 作为编码方式的远程调用规范
http + xml


```
POST http://www.example.com HTTP/1.1
Content-Type: text/xml
<!--?xml version="1.0"?-->
<methodcall>
<methodname>examples.getStateName</methodname>
<params>
<param>
<value><i4>41</i4></value>
</params>
</methodcall>
```
