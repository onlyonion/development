org.apache.kafka.common.TopicPartitionInfo
## define
```java
public class TopicPartitionInfo {
    private final int partition;
    private final Node leader;
    private final List<Node> replicas;
    private final List<Node> isr;
}    
```