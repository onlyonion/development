《Tomcat内核设计剖析》 汪健

## 第1章 Web服务器机制
## 第2章 Servlet规范
## 第3章 Tomcat的启动与关闭
## 第4章 从整体预览Tomcat
## 第5章 Server组件与Service组件
## 第6章 Connector组件
## 第7章 Engine容器
## 第8章 Host容器
## 第9章 Context容器
## 第10章 Wrapper容器
## 第11章 生命周期管理
## 第12章 日志框架及其国际化
## 第13章 公共与隔离的类加载器
### 13.1 类加载器
利用类加载器实现类库隔离及共享，保证不同级别的逻辑互不影响，提供安全性。
### 13.2 自定义类加载器
为了类库互相隔离，为了实现热部署
* 沿用双亲委派 继承ClassLoader，重写findClass()
* 打破双亲委派 继承ClassLoader，重写findClass()、loadClass()；loadClass()实现了双亲委派机制

### 13.3 Tomcat中的类加载器
* commonClassLoader 
  * shared
  * catalina
* WebAppClassLoader Web应用之间相互隔离、热部署；多个Web应用共享commonClassLoader类库

### 13.4 类加载器工厂——ClassLoaderFactory
### 13.5 遭遇ClassNotFoundException

## 第14章 请求URI映射器Mapper
负责Tomcat的请求路由，每个客户端的请求到达Tomcat后，都将由Mapper路由到对应的处理逻辑（Servlet）上。
### 14.1 请求的映射模型
每个玩着的请求，Tomcat内部都有Host、Context、Wrapper层次与之对应，而具体的路由工作则是由Mapper组件负责。
```
http://tomcat.apache.org/tomcat-7.0-doc/index.html

host tomcat.apache.org
context tomcat-7.0-doc
wrapper index.html
```
### 14.2 Mapper的实现
核心功能是根据请求路径的路由映射，根据某个请求路径，通过计算得到相应的Servlet（Wrapper）。  
Mapper类似Map结构。
### 14.3 局部路由Mapper
局部路由Mapper是指提供了Context内部路由导航功能的组件。它只存在于Context容器中，用于记录访问资源与Wrapper之间的映射。
### 14.4 全局路由Mapper
位于Tomcat的Connecto组件中，拥有完整的路由映射，负责完整的请求地址路由功能。

## 第15章 Tomcat的JNDI
## 第16章 JSP编译器Jasper

## 第17章 运行、通信及访问的安全管理
### 17.1 运行安全管理
### 17.2 安全的通信
#### 17.2.1 SSL／TLS协议
基于TCP/IP协议，传输层和应用层之间。
包含两个子协议：
* SSL记录协议 规定数据传输格式
* SSL握手协议 服务器和客户端之间身份互相认证，协商加密算法、MAC算法、SSL加密密钥。

SSL协议的作用：建立一个信息安全通道，用来保证数据传输的安全；确认网站的真实性。
SSL层提供的三方面服务：
1. 证书 认证客户端和服务器，确保数据发送到正确的客户端和服务器
2. 加密 加密数据防止数据中途被窃取
3. 签名 维护数据的完整性，确保数据在传输过程中不被篡改

TLS（Transport Layer Security，传输层安全）协议建立在SSL3.0协议之上。
#### 17.2.2 Java安全套接字扩展——JSSE
Java Security Socket Extension，包含了实现Internet安全通信的一系列包的集合，是SSL和TLS的纯Java实现。同时，是一个开放的标准，每个公司都可以自己实现JSSE。
#### 17.2.3 Tomcat中SSL安全信道的实现
HTTP与HTTPS的不同体现在创建通信套接字服务器时的不同，HTTP是没有任何加密措施的套接字服务，而HTTPS是靠嵌套了一层密码机制的套接字服务器。
ServerSocket、SSLServerSocket

### 17.3 客户端访问认证机制
#### 17.3.1 Web资源认证原理
#### 17.3.2 认证模式
Basic、Digest、Form、Spnego、SSL、NonLogin模式
#### 17.3.3 Realm域
Realm域是为了统一Web容器资源安全管理、统一抽离重复认证工作、方便Web应用资源权限管理开发而提出的一个概念。
JDBCRealm、DataSourceRealm、JNDIRealm、UserDatabaseRealm、MemoryRealm、JAASRealm
#### 17.3.4 Tomcat如何实现资源安全管理
Context容器级别，每种认证模式建立不同的认证器，例如Basic模式对应BasicAuthenticator、SSL模式对应SSLAuthenticator，这些认证器都要实现Valve接口以便被Context的管道调用。
认证器主要负责通过认证协议与客户端交互，收集用户的凭证信息并进行认证鉴权工作。
鉴权工作通过客户端用户名和密码找出相应的权限，然后根据查询出来的权限检查是否可以请求相应的资源。
#### 17.3.5 如何让你的Web具备权限认证

## 第18章 处理请求和响应的管道
### 18.1 管道模式——管道与阀门

## 第19章 多样化的会话管理器
### 19.1 Web容器的会话机制

## 第20章 高可用的集群实现

## 第21章 集群通信框架

## 第22章 监控与管理


