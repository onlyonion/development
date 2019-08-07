org.apache.rocketmq.client.consumer.AllocateMessageQueueStrategy

## hierarchy
```
AllocateMessageQueueStrategy (org.apache.rocketmq.client.consumer)
    AllocateMessageQueueAveragelyByCircle (org.apache.rocketmq.client.consumer.rebalance)
    AllocateMessageQueueByConfig (org.apache.rocketmq.client.consumer.rebalance)
    AllocateMessageQueueConsistentHash (org.apache.rocketmq.client.consumer.rebalance)
    AllocateMessageQueueAveragely (org.apache.rocketmq.client.consumer.rebalance)
    AllocateMessageQueueByMachineRoom (org.apache.rocketmq.client.consumer.rebalance)
    AllocateMachineRoomNearby (org.apache.rocketmq.client.consumer.rebalance)
```

## define
```java
public interface AllocateMessageQueueStrategy {

    List<MessageQueue> allocate(
        final String consumerGroup,
        final String currentCID,
        final List<MessageQueue> mqAll,
        final List<String> cidAll
    );

    String getName();
}
```