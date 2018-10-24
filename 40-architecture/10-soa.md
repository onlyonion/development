
## SOA
面向服务的架构（SOA）是一个组件模型，它将应用程序的不同功能单元（称为服务）通过这些服务之间定义良好的接口和契约联系起来。
接口是采用中立的方式进行定义的，它应该**独立于**实现服务的硬件平台、操作系统和编程语言。


SOA架构实现不依赖于技术，因此能够被各种不同的技术实现。

* SOAP(Simple Object Access Protocol), RPC(Remote Procedure Call)
* Web services
* REST(Representational State Transfer)
* COM(Component Object Model), DCOM(Microsoft Distributed Component Object Model)
* WCF(Microsoft's implementation of web services now forms a part of WCF)
* OPC-UA(Object Linking and Embedding(OLE) for Process Control)
* CORBA(Common Object Request Broker Architecture)公共对象请求代理体系结构
* Java RMI
* Apache Thrift
* DDS
* SORCER

> IPC RPC

### ESB
企业服务总线（EnterpriseServiceBus，ESB）从面向服务体系架构（Service-OrientedArchitecture，SOA）发展而来，是传统中间件技术与XML、Web服务等技术结合的产物。

### BPM
Business Process Management

activity, jbpm

### 规则引擎
规则引擎由推理引擎发展而来，是一种嵌入在应用程序中的组件，实现了将业务决策从应用程序代码中分离出来，并使用预定义的语义模块编写业务决策。接受数据输入，解释业务规则，并根据业务规则做出业务决策。

drools