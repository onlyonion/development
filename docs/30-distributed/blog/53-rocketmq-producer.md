# rocketmq producer
[Producer消息发送过程](https://www.cnblogs.com/sunshine-2015/p/6291116.html) 

## producer start
Producer DefaultMQProducer DefaultMQProducerImpl

## 消息发送流程
消息发送过程：先由producer封装通过netty发送到broker，然后由broker进行保存，过程如下


## 自动创建 Topic
https://blog.csdn.net/qq_34679704/article/details/120102203
自动创建 Topic 的流程概括为【偷梁换柱】，这个活是由 Broker，NameServer，Producer 配合完成的
- Producer发送消息时，如果指定的 Topic 不存在，NameServer 会返回一个`默认主题`的`路由信息`，使得生产者能够正常发生消息
- Broker 收到消息后，发现消息对应 Topic 不存在，且 Broker 允许自动创建 Topic，则会`为消息创建 Topic `，并`定时(30s)`把路由信息同步至 NameServer
- Producer也会定时从 NameServer 同步最新的路由信息，缓存至本地
- 后续Producer发送消息时，就可以从本地的缓存中查询到对应 Topic 的路由信息了
