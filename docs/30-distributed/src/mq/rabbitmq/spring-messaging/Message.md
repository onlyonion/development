org.springframework.messaging.Message

## hierarchy
```
Message (org.springframework.messaging)
    GenericMessage (org.springframework.messaging.support)
        ErrorMessage (org.springframework.messaging.support)
```

## define
```java
public interface Message<T> {
	T getPayload();
	MessageHeaders getHeaders();
}
```