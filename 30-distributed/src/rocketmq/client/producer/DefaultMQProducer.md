com.alibaba.rocketmq.client.producer.DefaultMQProducer

## hierachy
```
ClientConfig (com.alibaba.rocketmq.client)
    DefaultMQProducer (com.alibaba.rocketmq.client.producer)
        TransactionMQProducer (com.alibaba.rocketmq.client.producer)
```

## define

```plantuml
@startuml

''''''''''''''''接口''''''''''''''''
class ClientConfig
ClientConfig ^-- DefaultMQProducer

''''''''''''''''父类''''''''''''''''
interface MQAdmin
MQAdmin ^-- MQProducer
interface MQProducer
MQProducer ^.. DefaultMQProducer

class DefaultMQProducer {
    - int retryTimesWhenSendFailed = 2
    - int retryTimesWhenSendAsyncFailed = 2
    # final transient DefaultMQProducerImpl defaultMQProducerImpl
    + SendResult send(Message msg, long timeout)
    + void send(Message msg, SendCallback sendCallback, long timeout)
    + void sendOneway(Message msg)
}

''''''''''''''''域''''''''''''''''
DefaultMQProducer o-- DefaultMQProducerImpl
interface MQProducerInner 
MQProducerInner ^.. DefaultMQProducerImpl
class DefaultMQProducerImpl
DefaultMQProducerImpl o-- DefaultMQProducer

''''''''''''''''依赖''''''''''''''''
DefaultMQProducer ..> SendResult
class SendResult
SendResult o-- SendStatus
enum SendStatus {
    SEND_OK,
    FLUSH_DISK_TIMEOUT,
    FLUSH_SLAVE_TIMEOUT,
    SLAVE_NOT_AVAILABLE,
}

DefaultMQProducer ..> SendCallback
interface SendCallback {
    + void onSuccess(final SendResult sendResult)
    + void onException(final Throwable e)
}
SendCallback ..> SendResult

@enduml
```