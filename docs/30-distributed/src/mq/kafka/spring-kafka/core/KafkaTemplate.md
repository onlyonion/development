org.springframework.kafka.core.KafkaTemplate

## define
```java
public class KafkaTemplate<K, V> implements KafkaOperations<K, V> {

	private final ProducerFactory<K, V> producerFactory;
	private final boolean autoFlush;
	private RecordMessageConverter messageConverter = new MessagingMessageConverter();
	private volatile String defaultTopic;
	private volatile ProducerListener<K, V> producerListener = new LoggingProducerListener<K, V>();
}	
```

## method
- send
- doSend
- DefaultKafkaProducerFactory.CloseSafeProducer.send
- KafkaProducer.send