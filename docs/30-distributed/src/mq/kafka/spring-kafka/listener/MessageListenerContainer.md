org.springframework.kafka.listener.MessageListenerContainer

## hierarchy
```
MessageListenerContainer (org.springframework.kafka.listener)
    AbstractMessageListenerContainer (org.springframework.kafka.listener)
        ConcurrentMessageListenerContainer (org.springframework.kafka.listener)
        KafkaMessageListenerContainer (org.springframework.kafka.listener)
MessageListenerContainer (org.springframework.kafka.listener)
    SmartLifecycle (org.springframework.context)
        Lifecycle (org.springframework.context)
        Phased (org.springframework.context)
```

## define
```plantuml
@startuml

interface MessageListenerContainer
abstract class AbstractMessageListenerContainer<K, V>
MessageListenerContainer ^.. AbstractMessageListenerContainer

AbstractMessageListenerContainer ^-- ConcurrentMessageListenerContainer
AbstractMessageListenerContainer ^-- KafkaMessageListenerContainer
class ConcurrentMessageListenerContainer<K, V>
class KafkaMessageListenerContainer<K, V>

AbstractMessageListenerContainer +-- AckMode
enum AckMode {
    RECORD,
    BATCH,
    TIME,
    COUNT,
    COUNT_TIME,
    MANUAL,
    MANUAL_IMMEDIATE,
}
	
@enduml
```

```java
public interface MessageListenerContainer extends SmartLifecycle {
	void setupMessageListener(Object messageListener);
}
```