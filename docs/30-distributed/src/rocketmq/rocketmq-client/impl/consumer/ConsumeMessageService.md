org.apache.rocketmq.client.impl.consumer.ConsumeMessageService

## hierarchy
```
ConsumeMessageService (org.apache.rocketmq.client.impl.consumer)
    ConsumeMessageConcurrentlyService (org.apache.rocketmq.client.impl.consumer)
    ConsumeMessageOrderlyService (org.apache.rocketmq.client.impl.consumer)
```
## define
```java
public interface ConsumeMessageService {
    void start();
    void shutdown();
    void updateCorePoolSize(int corePoolSize);
    void incCorePoolSize();
    void decCorePoolSize();
    int getCorePoolSize();
    ConsumeMessageDirectlyResult consumeMessageDirectly(final MessageExt msg, final String brokerName);
    void submitConsumeRequest(
        final List<MessageExt> msgs,
        final ProcessQueue processQueue,
        final MessageQueue messageQueue,
        final boolean dispathToConsume);
}
```