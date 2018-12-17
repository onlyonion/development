
## quick start
Download & Build from Release
Start Name Server
Start Broker
Send & Receive Messages
Shutdown Servers


## rocketmq package
RocketMQ共包含9个模块

rocketmq-common：通用的枚举、基类方法、或者数据结构，包名有admin、consumer、filter、hook、message
rocketmq-remoting：使用netty的客户端、服务端，使用fastjson序列化，自定义二进制协议
rocketmq-srvutil：只有一个ServerUtil类，只提供Server程序依赖，尽可能减少客户端依赖
rocketmq-store：消息存储，索引，consumerLog，commitLog等
rocketmq-client：消息发送和接收，包含consumer和producer
rocketmq-filtersrv：消息过滤器
rocketmq-broker：服务端，接受消息，存储消息，consumer拉取消息
rocketmq-tools：命令行工具
rocketmq-namesrv：NameServer，类似服务注册中心，broker在这里注册，consumer和producer在这里找到broker地址

## 

NameServer
Producer
Consumer
Broker

Producer与broker间的心跳
Producer轮询某topic下的所有队列的方式来实现发送方的负载均衡

	轮询实现
	List<MessageQueue> messageQueueList
	AtomicInteger sendWitchQueue //自增整型
	
	(++sendWitchQueue)% messageQueueList.size
	
Rocketmq能够保证消息严格顺序，但是Rocketmq需要producer保证顺序消息按顺序发送到同一个queue中