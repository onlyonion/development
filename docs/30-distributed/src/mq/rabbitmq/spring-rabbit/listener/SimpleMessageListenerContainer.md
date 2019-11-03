org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer

## hierarchy
```
RabbitAccessor (org.springframework.amqp.rabbit.connection)
    AbstractMessageListenerContainer (org.springframework.amqp.rabbit.listener)
        SimpleMessageListenerContainer (org.springframework.amqp.rabbit.listener)
SimpleMessageListenerContainer (org.springframework.amqp.rabbit.listener)
    AbstractMessageListenerContainer (org.springframework.amqp.rabbit.listener)
        RabbitAccessor (org.springframework.amqp.rabbit.connection)
        MessageListenerContainer (org.springframework.amqp.rabbit.listener)
        ApplicationContextAware (org.springframework.context)
        BeanNameAware (org.springframework.beans.factory)
        DisposableBean (org.springframework.beans.factory)
        SmartLifecycle (org.springframework.context)
    ApplicationEventPublisherAware (org.springframework.context)
        Aware (org.springframework.beans.factory)
```

## define

## fields

## methods

### receiveAndExecute
```java
    private boolean receiveAndExecute(final BlockingQueueConsumer consumer) throws Throwable {
        if (this.transactionManager != null) {
            // ... 事务执行
            return doReceiveAndExecute(consumer);
        }
        return doReceiveAndExecute(consumer);
    }
```

### doReceiveAndExecute
```java
    private boolean doReceiveAndExecute(BlockingQueueConsumer consumer) throws Throwable {
        // try-catch ...
        executeListener(channel, message);
        return consumer.commitIfNecessary(isChannelLocallyTransacted(channel));
    }
```
### AbstractMessageListenerContainer.executeListener
```java
    protected void executeListener(Channel channel, Message messageIn) throws Throwable {
        // nio.ByteBuffer
        
        Message fragment = new Message(body, messageProperties);
        invokeListener(channel, fragment);
    }
```

## inner class

### ContainerDelegate

### AsyncMessageProcessingConsumer

### WrappedTransactionException

### DefaultExclusiveConsumerLogger