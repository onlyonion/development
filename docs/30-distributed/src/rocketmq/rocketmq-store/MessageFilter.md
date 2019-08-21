org.apache.rocketmq.store.MessageFilter

## hierarchy
```
MessageFilter (org.apache.rocketmq.store)
    DefaultMessageFilter (org.apache.rocketmq.store)
    ExpressionMessageFilter (org.apache.rocketmq.broker.filter)
        ExpressionForRetryMessageFilter (org.apache.rocketmq.broker.filter)
```

## define
```java
public interface MessageFilter {
    boolean isMatchedByConsumeQueue(final Long tagsCode, final ConsumeQueueExt.CqExtUnit cqExtUnit);
    boolean isMatchedByCommitLog(final ByteBuffer msgBuffer, final Map<String, String> properties);
}
```

## methods