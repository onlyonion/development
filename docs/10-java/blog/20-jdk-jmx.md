## jmx
JMX全称Java Management Extensions, 为Java应用提供管理扩展功能。在Java 5的时候引入

## 概念
* MBean 全称为Managed Bean, 你可以实现一个MBean来JMX提供管理内容
* MBean Server(也叫JMX Agent) 提供集中注册管理MBean功能，允许远程通过他代理操作MBean
* JMX Connectors 通过实现不同的通讯协议，来允许远程访问
* Jconsole 一款JMX图形客户端，运行用户访问本地或者远程的JMX，默认包含在Java SDK工具中

[JMX学习笔记](https://www.jianshu.com/p/414647c1179e)

[实用类库](http://www.importnew.com/21928.html) 

jmx Java Management Extensions 一个为应用程序、设备、系统等植入**管理功能**的框架