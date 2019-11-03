org.springframework.amqp.rabbit.core.RabbitTemplate

## define
```java
public class RabbitTemplate extends RabbitAccessor implements BeanFactoryAware, RabbitOperations, MessageListener,
		ListenerContainerAware, PublisherCallbackChannel.Listener {
    
    protected void initDefaultStrategies() {
        setMessageConverter(new SimpleMessageConverter());
    }
}
```

## fields

## methods
- convertAndSend
- send
- execute
- doExecute
- doInRabbit
- doSend


## inner class

### PendingReply

### ConfirmCallback
```java
	public interface ConfirmCallback {
		void confirm(CorrelationData correlationData, boolean ack, String cause);
	}
```
### ReturnCallback
```java
	public interface ReturnCallback {
		void returnedMessage(Message message, int replyCode, String replyText,
				String exchange, String routingKey);
	}
```
 