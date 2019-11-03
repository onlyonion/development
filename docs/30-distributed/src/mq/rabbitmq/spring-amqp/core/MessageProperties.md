org.springframework.amqp.core.MessageProperties

## define
```java
public class MessageProperties implements Serializable {
	private final Map<String, Object> headers = new HashMap<String, Object>();

	private volatile Date timestamp;
	private volatile String messageId;
	private volatile String userId;
	private volatile String appId;
	private volatile String clusterId;
	private volatile String type;

	private volatile byte[] correlationId;
	private volatile String correlationIdString;

	private volatile String replyTo;
	private volatile String contentType = DEFAULT_CONTENT_TYPE;
	private volatile String contentEncoding;
	private volatile long contentLength;
	private volatile boolean contentLengthSet;
	
	private volatile MessageDeliveryMode deliveryMode = DEFAULT_DELIVERY_MODE;
	private volatile String expiration;
	private volatile Integer priority = DEFAULT_PRIORITY;

	private volatile Boolean redelivered;
	private volatile String receivedExchange;
	private volatile String receivedRoutingKey;
	private volatile String receivedUserId;
	private volatile long deliveryTag;
	private volatile boolean deliveryTagSet;
	private volatile Integer messageCount;

	// Not included in hashCode()
	private volatile String consumerTag;
	private volatile String consumerQueue;
	private volatile Integer receivedDelay;
	private volatile MessageDeliveryMode receivedDeliveryMode;

	private volatile transient Type inferredArgumentType;
	private volatile transient Method targetMethod;
	private volatile transient Object targetBean;
}
```