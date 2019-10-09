org.apache.rocketmq.client.impl.consumer.PullRequest

## define
```java
public class PullRequest {
    private String consumerGroup;
    private MessageQueue messageQueue;
    private ProcessQueue processQueue;
    private long nextOffset;
    private boolean lockedFirst = false;
}    
```