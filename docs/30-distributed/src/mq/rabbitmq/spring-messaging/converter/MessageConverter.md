org.springframework.messaging.converter.MessageConverter

## hierarchy
```
MessageConverter (org.springframework.messaging.converter)
    SmartMessageConverter (org.springframework.messaging.converter)
        CompositeMessageConverter (org.springframework.messaging.converter)
        AbstractMessageConverter (org.springframework.messaging.converter)
            MarshallingMessageConverter (org.springframework.messaging.converter)
            MappingJackson2MessageConverter (org.springframework.messaging.converter)
            ByteArrayMessageConverter (org.springframework.messaging.converter)
            StringMessageConverter (org.springframework.messaging.converter)
            MappingFastJsonMessageConverter (com.alibaba.fastjson.support.spring.messaging)
    SimpleMessageConverter (org.springframework.messaging.converter)
        GenericMessageConverter (org.springframework.messaging.converter)
```

## define
```java
public interface MessageConverter {
	Object fromMessage(Message<?> message, Class<?> targetClass);
	Message<?> toMessage(Object payload, MessageHeaders headers);
}
```