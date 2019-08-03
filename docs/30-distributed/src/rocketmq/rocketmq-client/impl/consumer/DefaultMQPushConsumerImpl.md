org.apache.rocketmq.client.impl.consumer.DefaultMQPushConsumerImpl

## define

```java
public interface MQConsumerInner {
    String groupName();

    MessageModel messageModel();

    ConsumeType consumeType();

    ConsumeFromWhere consumeFromWhere();

    Set<SubscriptionData> subscriptions();

    void doRebalance();

    void persistConsumerOffset();

    void updateTopicSubscribeInfo(final String topic, final Set<MessageQueue> info);

    boolean isSubscribeTopicNeedUpdate(final String topic);

    boolean isUnitMode();

    ConsumerRunningInfo consumerRunningInfo();
}
```

```java
public class DefaultMQPushConsumerImpl implements MQConsumerInner {
    
}
```

## fields


## methods

### pullMessage

```java

```


## class

### PullCallback

```java
public interface PullCallback {
    void onSuccess(final PullResult pullResult);

    void onException(final Throwable e);
}
```
