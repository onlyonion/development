
## tomcat package
```
servlet 规范
javax.security.auth.message
javax.servlet
org.apache.catalina
org.apache.coyote
org.apache.juli
org.apache.naming
org.apache.tomcat
```
### tomcat 设计模式
* facade ApplicationContextFacade
* chain of responsibility pipeline, valve
* factory ServerSocketFactory
* observer 

### Connector
* HTTP/AJP Connector
* Bio/Nio/Apr

每个ProtocolHandler内部都有一个Endpoint，ProtocolHandler初始化/启动的时候，将初始化/启动其内部的Endpoint
ProtocolHandler与Endpoint的对应关系 
* Http11Protocol/AjpProtocol使用JIoEndpoint 使用java io(也称为bio)技术，即一个请求对应一个线程。缺点：并发量高时，线程数较多，占资源
* Http11NioProtocol/AjpNioProtocol使用NioEndpoint 使用java nio技术，可以通过少量的线程处理大量的请求
* Http11AprProtocol/AjpAprProtocol使用AprEndpoint Apr即Apache Portable Runtime，从操作系统层面解决io阻塞问题。使用Apr时将用到Tomcat\bin\tcnative-1.dll (使用Apr时，应用了jni技术)。

## misc
* Jetty 是面向Handler的架构
* Spring 是面向Bean的架构
* iBATIS 是面向statement 

### tomcat-connector-container
![tomcat-connector-container](./img/tomcat-connector-container.jpg)
### tomcat-engine-host-context-wapper
![tomcat-engine-host-context-wapper](./img/tomcat-engine-host-context-wapper.jpg)
### tomcat-request-service
![tomcat-request-service](./img/tomcat-request-service.jpg)
### tomcat-server
![server](./img/tomcat-server.jpg)
### tomcat-server.xml
![tomcat-server.xml.jpg](./img/tomcat-server.xml.jpg)
