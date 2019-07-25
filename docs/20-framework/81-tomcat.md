
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
## tomcat 设计模式
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


### [线程模型](https://blog.csdn.net/fd2025/article/details/80007435)
* BIO 阻塞式IO，采用传统的java IO进行操作，该模式下每个请求都会创建一个线程，适用于并发量小的场景
  1. Http11Protocol组件，是HTTP协议1.1 版本的抽象，它包含客户端连接、接收客户端消息报文、报文解析处理、对客户端响应等整个过程。它主要包含JIoEndpoint组件和Http11Processor,启动时，JIoEndpoint组件内部的Acceptor组件将启动某个端口的监听，一个请求到来后将被扔进线程池Executor，线程池进行任务处理处理，处理过程中将通过Http11Processor组件对HTTP协议解析并传递到Engine容器继续处理。
  2. Mapper组件，可以通过请求地址找到对应的servlet
  3. CoyoteAdapter组件，将一个Connect和Container适配起来的适配器。
* NIO 同步非阻塞，比传统BIO能更好的支持大并发，tomcat 8.0 后默认采用该模式
* APR tomcat 以JNI形式调用http服务器的核心动态链接库来处理文件读取或网络传输操作，需要编译安装APR库
* AIO 异步非阻塞，tomcat8.0后支持

#### tomcat-jio-protocol-endpoint-processor
* Connector
  * Http11Protocol
    * JioEndpoint
      * Acceptor
      * Executor
    * Http11Processor
  * Mapper
  * CoyoteAdapter
![tomcat-jio-protocol-endpoint-processor](./img/tomcat-jio-protocol-endpoint-processor.png)

#### tomcat-nio-protocol-endpoint-processor
* Connector
  * Http11NioProtocol
    * NioEndpoint
      * Acceptor
      * Executor
      * Poller 非阻塞的方式下轮询多个客户端连接，不断检测，处理各种事件
    * Http11NioProcessor
  * Mapper 通过请求地址找到对应的servlet
  * CoyoteAdapter 将一个Connect和Container适配起来的适配器

![tomcat-nio-protocol-endpoint-processor](./img/tomcat-nio-protocol-endpoint-processor.png)

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

***********************************************************************************************

# Tomcat component

## Server

* Connector
* Container, Engine, Host, Wrapper, Context
* Lifecycle
* pipeline, valve
* Executor
* Boostrap, Catalina

## ClassLoader

* bootstrap, extension, system
* tomcat classloader
* webapp classloader

## javax.servlet

```
annotation
descriptor
http
    Cookie
    HttpServletRequest
    HttpServertResponse
    HttpSession
resources

Filter
FilterChain
FilterConfig
Registration

Servlet
ServletConfig
ServletContext

ServletRequest
ServletResponse
```

## Connector

Connector使用ProtocolHandler来处理请求的，不同的ProtocolHandler代表不同的连接类型，
比如：Http11Protocol使用的是普通Socket来连接的，Http11NioProtocol使用的是NioSocket来连接的。

```
    Endpoint  --> TCP/IP  --> Acceptor, Handler, AsyncTimeout
    Processor --> HTTP
    Adaper
```

其中ProtocolHandler由包含了三个部件：Endpoint、Processor、Adapter。
1. Endpoint用来处理底层Socket的网络连接，Processor用于将Endpoint接收到的Socket封装成Request，Adapter用于将Request交给Container进行具体的处理。
2. Endpoint由于是处理底层的Socket网络连接，因此Endpoint是用来实现TCP/IP协议的，而Processor用来实现HTTP协议的，Adapter将请求适配到Servlet容器进行具体的处理。
3. Endpoint的抽象实现AbstractEndpoint里面定义的Acceptor和AsyncTimeout两个内部类和一个Handler接口。
Acceptor用于监听请求，AsyncTimeout用于检查异步Request的超时，Handler用于处理接收到的Socket，在内部调用Processor进行处理。

## Container

1. Engine：引擎，用来管理多个站点，一个Service最多只能有一个Engine； 
2. Host：代表一个站点，也可以叫虚拟主机，通过配置Host就可以添加站点； 
3. Context：代表一个应用程序，对应着平时开发的一套程序，或者一个WEB-INF目录以及下面的web.xml文件； 
4. Wrapper：每一Wrapper封装着一个Servlet；

## Container使用Pipeline-Value管道处理请求

Pipeline-Value是责任链模式，责任链模式是指在一个请求处理的过程中有很多处理者依次对请求进行处理，每个处理者负责做自己相应的处理，
处理完之后将处理后的请求返回，再让下一个处理着继续处理。

1. Connector在接收到请求后会首先调用最顶层容器的Pipeline来处理
2. StandardEngineValue --> StandardHostValue --> StandardContextValue --> StandardWrapperValue
3. StandardWrapperValue --> FilterChain --> Filter的doFilter()和Servlet的service()
4. 当所有的Pipeline-Value都执行完之后，并且处理完了具体的请求，将返回的结果交给Connector了，Connector在通过Socket的方式将结果返回给客户端。


## Session共享
解决Session跨域共享问题
1. session sticky
2. session replication
3. session集中存储
4. cookie主流 access_token(userid/token/timestamp)
5. 