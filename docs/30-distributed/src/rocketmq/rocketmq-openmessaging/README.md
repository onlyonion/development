io.openmessaging.rocketmq

## package
```
config
    ClientConfig
consumer
    LocalMessageCache
    PullConsumerImpl
    PushConsumerImpl
domain
    BytesMessageImpl
    ConsumeRequest
    NonStandardKeys
    RocketMQConstants
    SendResultImpl
producer
    AbstractOMSProducer
    ProducerImpl
promise
    DefaultPromise
    FutureState
utils
    BeanUtils
    OMSUtil
MessagingAccessPointImpl
```


## openmessaging-api-0.3.1-alpha.jar
io.openmessaging
### package
```
consumer                                消费者
    MessageListener
    PullConsumer
    PushConsumer
    StreamingConsumer
    StreamingIterator
exception                               异常
    OMSException
    OMSMessageFormatException
    OMSNotSupportedException
    OMSResourceNotExistException
    OMSRuntimeException
    OMSTimeOutException 
interceptor                             拦截器
    ConsumerInterceptor
    ProducerInterceptor
internal
    AccessPointURI
    DefaultKeyValue
    InternalErrorCode
    MessagingAccessPointAdapter
producer                                生产者
    BatchMessageSender
    LocalTransactionExecutor
    Producer
    SendResult
BytesMessage
Future
FutureListener
KeyValue
Message
MessageFactory
MessagingAccessPoint
OMS
OMSBuiltinKeys
Promise
ResourceManager                         资源管理器
ServiceLifecycle                        服务生命周期
```