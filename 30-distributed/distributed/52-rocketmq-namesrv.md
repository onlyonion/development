
[NameServer](https://www.cnblogs.com/sunshine-2015/p/6287404.html) 

### NameServer
作用：Producer和Consumer获取Broker的地址
目的：解耦Broker和Producer、Consumer
原理：使用netty作为通信工具，监听指定端口，如果是broker注册，将broker的信息保存在内存中并保存到文件中，producer和consumer获取broker地址的请求

### RocketMQ包含的组件
NameServer：单点，供Producer和Consumer获取Broker地址
Producer：产生并发送消息
Consumer：接受并消费消息
Broker：消息暂存，消息转发

### NamesrvController包含的组件
namesrvConfig：nameServer的配置
nettyServerConfig：NameServer的netty配置
remotingServer：NameServer 的netty服务器
scheduledExecutorService：routeInfoManager和kvCOnfigManager使用的定时线程池
remotingExecutor：netty使用的线程池
brokerHosekeppingService：
kvConfigManager：kv配置管理
routeInfoManager：包含broker的ip和对应的队列信息，说明producer可以往哪一个broker发送消息，consumer从哪一个broker pull消息


### NameServer启动

#### NamesrvStartup.main0
NettySystemConfig配置
解析命令行参数，NettyServerConfig配置
logback配置
NamesrvController初始化，initialize
注册shutdown钩子
NamesrvController.start

#### NamesrvController.initialize
KVConfigManager.load加载原来的key-value文件到内存中
初始化NettyRemotingServer
注册requestProcessor，默认为DefaultRequestProcessor，用来处理netty接收到的信息
启动定时线程，每隔10s判断broker是否依然存活
启动定时线程，每隔10min打印出所有k-v