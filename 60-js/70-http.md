## http
### 服务端推送
ajax
websocket
轮询 
长连接
### cors
Cross-origin resource sharing   
iframe JSONP
### 域
当两个域具有相同的协议(如http), 相同的端口(如80)，相同的host（如www.google.com)，那么我们就可以认为它们是相同的域（协议，域名，端口都必须相同）。
跨域就指着协议，域名，端口不一致，出于安全考虑，跨域的资源之间是无法交互的(例如一般情况跨域的JavaScript无法交互，当然有很多解决跨域的方案)

## https

## http2
多路复用、Stream、头部压缩、流量控制、服务端推送

## quic
避免前序包阻塞（HOL阻塞）、零RTT建连、FEC前向纠错

QUIC（Quick UDP Internet Connection）是谷歌制定的一种基于UDP的低时延的互联网传输层协议。
QUIC很好地解决了当今传输层和应用层面临的各种需求，包括处理更多的连接，安全性，和低延迟。QUIC融合了包括TCP，TLS，HTTP/2等协议的特性，但基于UDP传输。

## 示例
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
### CORS 配置项
1. Access-Control-Allow-Origin
2. Access-Control-Allow-Methods
3. Access-Control-Expose-Headers
该字段可选。CORS请求时，XMLHttpRequest对象的getResponseHeader()方法只能拿到6个基本字段：Cache-Control、Content-Language、Content-Type、Expires、Last-Modified、Pragma。如果想拿到其他字段，就必须在Access-Control-Expose-Headers里面指定。
4. Access-Control-Allow-Credentials
该字段可选。它的值是一个布尔值，表示是否允许发送Cookie.默认情况下，不发生Cookie，即：false。对服务器有特殊要求的请求，比如请求方法是PUT或DELETE，或者Content-Type字段的类型是application/json，这个值只能设为true。如果服务器不要浏览器发送Cookie，删除该字段即可。
5. Access-Control-Max-Age
该字段可选，用来指定本次预检请求的有效期，单位为秒。在有效期间，不用发出另一条预检请求。


### [form](https://blog.csdn.net/u012161134/article/details/79098749/ )
form表单中enctype属性可以用来控制对表单数据的发送前的如何进行编码，enctype有三种，分别为：
* multipart/form-data不对字符编码，用于发送二进制的文件，其他两种类型不能用于发送文件；
* text/plain用于发送纯文本内容，空格转换为 "+" 加号，不对特殊字符进行编码，一般用于email之类的；
* application/x-www-form-urlencoded，在发送前会编码所有字符，即在发送到服务器之前，所有字符都会进行编码（空格转换为 "+" 加号，"+"加号转换为空格，特殊符号转换为 ASCII HEX 值）。

其中application/x-www-form-urlencoded为默认类型。 