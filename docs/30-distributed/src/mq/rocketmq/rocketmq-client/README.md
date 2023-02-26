org.apache.rocketmq.client
- consumer 
  - push、pull
  - 集群和广播
- producer 同步发送、异步发送、延迟发送（延迟级别）、发送事务消息

## package
```
admin
common
consumer
    listener
        MessageListener
        MessageListenerConcurrently
        MessageListenerOrderly
    rebalance
    store
    MQConsumer
    MQPullConsumer
    MQPushConsumer
exception
hook
impl
    consumer
        RebalanceImpl
        RebalanceService    再平衡
    factory
        MQClientInstance
    producer
latency
    LatencyFaultTolerance   延迟容错
log
producer
    selector
        SelectMessageQueueByHash
        SelectMessageQueueByMachineRoom
        SelectMessageQueueByRandom
    DefaultMQProducer
    LocalTransactionState
    MessageQueueSelector
    MQProducer
    SendCallback
    SendResult
    SendStatus
    TransactionListener
    TransactionMQProducer
    TransactionSendResult    
stat

ClientConfig
MQAdmin
MQHelper
QueryResult
Validators
```