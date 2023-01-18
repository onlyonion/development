
## jars
- javax.activation:activation:1.1
- javax.annotation:javax.annotation-api:1.2
- javax.inject:javax.inject:1
- javax.mail:mail:1.4
- javax.portlet:portlet-api:1.0
- javax.servlet:jstl:1.2
- javax.servlet:servlet-api:2.5
- javax.transaction:jta:1.1
- javax.validation:validation-api:1.1.0.Final
- javax.ws.rs:javax.ws.rs-api:2.0.1


## version
Servlet3.0引入异步 Servlet，Servlet3.1引入非阻塞 IO 来进一步增强异步处理的性能
Servlet3.0 对应的 Tomcat 版本是 7.0.x，
Servlet3.1 对应的 Tomcat 版本是 8.0.x。

Servlet4.0是使用HTTP/2协议
其中服务器推送是最主要的更新，如果要使用服务器推送的功能，则我们必须使用HTTP/2.0版本的协议。
