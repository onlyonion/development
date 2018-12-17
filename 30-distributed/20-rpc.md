# RPC

RPC是远程过程调用的简称，广泛应用在大规模分布式应用中，作用是有助于系统的垂直拆分，使系统更易拓展。Java中的RPC框架比较多，各有特色，广泛使用的有RMI、Hessian、Dubbo等。

## RPC的主要依赖技术
*	序列化、反序列化
*	传输协议

## 实例
*	RMI的序列化和反序列化是JAVA自带的
*	Hessian里的序列化和反序列化是私有的，传输协议则是HTTP
*	Dubbo的序列化可以多种选择，一般使用Hessian的序列化协议，传输则是TCP协议，使用了高性能的NIO框架Netty。
*	pigeon大众点评分布式服务治理框架


对于序列化，我还了解一些，像Google的ProBuffer、JBossMarshalling和Apache Thrift等


dubbo			二进制序列化	+ tcp协议
http invoker	二进制序列化	+ http协议
hessian			二进制序列化	+ http协议
WebServices		文本序列化	+ http协议

Pigeon			hessian + tcp(netty)	美团大众点评
