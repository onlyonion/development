org.springframework.amqp.core.Message

## define
```java
public class Message implements Serializable {
	private static final long serialVersionUID = -7177590352110605597L;
	private static final String ENCODING = Charset.defaultCharset().name();

	private final MessageProperties messageProperties;
	private final byte[] body;
}	
```