
## consumer
```
// invocation
doInvoke:180, InvocableHandlerMethod (org.springframework.messaging.handler.invocation)
invoke:112, InvocableHandlerMethod (org.springframework.messaging.handler.invocation)

// adapter
invoke:48, HandlerAdapter (org.springframework.kafka.listener.adapter)
invokeHandler:174, MessagingMessageListenerAdapter (org.springframework.kafka.listener.adapter)
onMessage:72, RecordMessagingMessageListenerAdapter (org.springframework.kafka.listener.adapter)
onMessage:47, RecordMessagingMessageListenerAdapter (org.springframework.kafka.listener.adapter)

// listenerContainer
invokeRecordListener:794, KafkaMessageListenerContainer$ListenerConsumer (org.springframework.kafka.listener)
invokeListener:738, KafkaMessageListenerContainer$ListenerConsumer (org.springframework.kafka.listener)
run:570, KafkaMessageListenerContainer$ListenerConsumer (org.springframework.kafka.listener)

call:511, Executors$RunnableAdapter (java.util.concurrent)
run$$$capture:266, FutureTask (java.util.concurrent)
run:-1, FutureTask (java.util.concurrent)

 - Async stack trace
<init>:151, FutureTask (java.util.concurrent)
<init>:51, ListenableFutureTask (org.springframework.util.concurrent)
submitListenable:208, SimpleAsyncTaskExecutor (org.springframework.core.task)
doStart:197, KafkaMessageListenerContainer (org.springframework.kafka.listener)
start:202, AbstractMessageListenerContainer (org.springframework.kafka.listener)
doStart:125, ConcurrentMessageListenerContainer (org.springframework.kafka.listener)
start:202, AbstractMessageListenerContainer (org.springframework.kafka.listener)
startIfNecessary:287, KafkaListenerEndpointRegistry (org.springframework.kafka.config)
start:236, KafkaListenerEndpointRegistry (org.springframework.kafka.config)
doStart:175, DefaultLifecycleProcessor (org.springframework.context.support)
access$200:50, DefaultLifecycleProcessor (org.springframework.context.support)
start:348, DefaultLifecycleProcessor$LifecycleGroup (org.springframework.context.support)
startBeans:151, DefaultLifecycleProcessor (org.springframework.context.support)
onRefresh:114, DefaultLifecycleProcessor (org.springframework.context.support)
finishRefresh:879, AbstractApplicationContext (org.springframework.context.support)
finishRefresh:144, EmbeddedWebApplicationContext (org.springframework.boot.context.embedded)
refresh:545, AbstractApplicationContext (org.springframework.context.support)
refresh:122, EmbeddedWebApplicationContext (org.springframework.boot.context.embedded)
refresh:737, SpringApplication (org.springframework.boot)
refreshContext:370, SpringApplication (org.springframework.boot)
run:314, SpringApplication (org.springframework.boot)
```