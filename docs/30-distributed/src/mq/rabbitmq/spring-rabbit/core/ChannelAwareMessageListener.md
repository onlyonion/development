org.springframework.amqp.rabbit.core.ChannelAwareMessageListener

## define
```java
public interface ChannelAwareMessageListener {
	void onMessage(Message message, Channel channel) throws Exception;
}
```