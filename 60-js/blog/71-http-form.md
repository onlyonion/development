
四种常见的 POST 提交数据方式
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


[摘自](https://www.cnblogs.com/aaronjs/p/4165049.html)

