org.springframework.amqp.rabbit.listener.adapter.MessagingMessageListenerAdapter

## hierarchy
```
AbstractAdaptableMessageListener (org.springframework.amqp.rabbit.listener.adapter)
    MessagingMessageListenerAdapter (org.springframework.amqp.rabbit.listener.adapter)
MessagingMessageListenerAdapter (org.springframework.amqp.rabbit.listener.adapter)
    AbstractAdaptableMessageListener (org.springframework.amqp.rabbit.listener.adapter)
        MessageListener (org.springframework.amqp.core)
        ChannelAwareMessageListener (org.springframework.amqp.rabbit.core)
```

## methods
- onMessage
- invokeHandler
- HandlerAdapter.invoke
- InvocableHandlerMethod.invoke
- InvocableHandlerMethod.doInvoke

### onMessage
```java
	public void onMessage(org.springframework.amqp.core.Message amqpMessage, Channel channel) throws Exception {
		Message<?> message = toMessagingMessage(amqpMessage);
		if (logger.isDebugEnabled()) {
			logger.debug("Processing [" + message + "]");
		}
		Object result = invokeHandler(amqpMessage, channel, message);
		if (result != null) {
			handleResult(result, amqpMessage, channel, message);
		}
		else {
			logger.trace("No result object given - no result to handle");
		}
	}
```