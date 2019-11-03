org.springframework.amqp.core.AbstractExchange

## hierarchy
```
AbstractDeclarable (org.springframework.amqp.core)
    AbstractExchange (org.springframework.amqp.core)
        DirectExchange (org.springframework.amqp.core)
        FanoutExchange (org.springframework.amqp.core)
        CustomExchange (org.springframework.amqp.core)
        TopicExchange (org.springframework.amqp.core)
        HeadersExchange (org.springframework.amqp.core)
```

## define
```java
public abstract class AbstractExchange extends AbstractDeclarable implements Exchange {
	private final String name;
	private final boolean durable;
	private final boolean autoDelete;
	private final Map<String, Object> arguments;
	private volatile boolean delayed;
	private boolean internal;
}	
```