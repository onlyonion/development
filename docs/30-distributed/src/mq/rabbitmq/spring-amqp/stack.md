

## exception
```
org.springframework.amqp.rabbit.listener.adapter.MessagingMessageListenerAdapter.invokeHandler(MessagingMessageListenerAdapter.java:140)
	at org.springframework.amqp.rabbit.listener.adapter.MessagingMessageListenerAdapter.onMessage(MessagingMessageListenerAdapter.java:106)
	at org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer.doInvokeListener(AbstractMessageListenerContainer.java:822)
	at org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer.invokeListener(AbstractMessageListenerContainer.java:745)
	at org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer.access$001(SimpleMessageListenerContainer.java:97)
	at org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer$1.invokeListener(SimpleMessageListenerContainer.java:189)
	at org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer.invokeListener(SimpleMessageListenerContainer.java:1276)
	at org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer.executeListener(AbstractMessageListenerContainer.java:726)
	at org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer.doReceiveAndExecute(SimpleMessageListenerContainer.java:1219)
	at org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer.receiveAndExecute(SimpleMessageListenerContainer.java:1189)
	at org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer.access$1500(SimpleMessageListenerContainer.java:97)
	at org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer$AsyncMessageProcessingConsumer.run(SimpleMessageListenerContainer.java:1421)
	at java.lang.Thread.run(Thread.java:748)
```

## stack
```
invoke:-1, GeneratedMethodAccessor250 (sun.reflect)
invoke:43, DelegatingMethodAccessorImpl (sun.reflect)
invoke:498, Method (java.lang.reflect)

// method
doInvoke:180, InvocableHandlerMethod (org.springframework.messaging.handler.invocation)
invoke:112, InvocableHandlerMethod (org.springframework.messaging.handler.invocation)

// adapter
invoke:49, HandlerAdapter (org.springframework.amqp.rabbit.listener.adapter)
invokeHandler:126, MessagingMessageListenerAdapter (org.springframework.amqp.rabbit.listener.adapter)
onMessage:106, MessagingMessageListenerAdapter (org.springframework.amqp.rabbit.listener.adapter)

// listener
doInvokeListener:822, AbstractMessageListenerContainer (org.springframework.amqp.rabbit.listener)
invokeListener:745, AbstractMessageListenerContainer (org.springframework.amqp.rabbit.listener)
access$001:97, SimpleMessageListenerContainer (org.springframework.amqp.rabbit.listener)
invokeListener:189, SimpleMessageListenerContainer$1 (org.springframework.amqp.rabbit.listener)
invokeListener:1276, SimpleMessageListenerContainer (org.springframework.amqp.rabbit.listener)
executeListener:726, AbstractMessageListenerContainer (org.springframework.amqp.rabbit.listener)
doReceiveAndExecute:1219, SimpleMessageListenerContainer (org.springframework.amqp.rabbit.listener)
receiveAndExecute:1189, SimpleMessageListenerContainer (org.springframework.amqp.rabbit.listener)
access$1500:97, SimpleMessageListenerContainer (org.springframework.amqp.rabbit.listener)
run:1421, SimpleMessageListenerContainer$AsyncMessageProcessingConsumer (org.springframework.amqp.rabbit.listener)
run:748, Thread (java.lang)
```