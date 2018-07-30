
## quick start
Download & Build from Release
Start Name Server
Start Broker
Send & Receive Messages
Shutdown Servers


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