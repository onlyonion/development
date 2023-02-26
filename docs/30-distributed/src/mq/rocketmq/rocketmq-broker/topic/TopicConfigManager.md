org.apache.rocketmq.broker.topic.TopicConfigManager

- ReentrantLock
- 自动创建Topic
  - AUTO_CREATE_TOPIC_KEY
  - TBW102

## define
```java
public class TopicConfigManager extends ConfigManager {
    private static final InternalLogger log = InternalLoggerFactory.getLogger(LoggerName.BROKER_LOGGER_NAME);
    private static final long LOCK_TIMEOUT_MILLIS = 3000;
    private transient final Lock lockTopicConfigTable = new ReentrantLock();

    private final ConcurrentMap<String, TopicConfig> topicConfigTable =
        new ConcurrentHashMap<String, TopicConfig>(1024);
    private final DataVersion dataVersion = new DataVersion();
    private final Set<String> systemTopicList = new HashSet<String>();
    private transient BrokerController brokerController;
}
```
## methods

### AUTO_CREATE_TOPIC_KEY
```java
    public TopicConfigManager(BrokerController brokerController) {
        // MixAll.AUTO_CREATE_TOPIC_KEY_TOPIC
        if (this.brokerController.getBrokerConfig().isAutoCreateTopicEnable()) {
            String topic = MixAll.AUTO_CREATE_TOPIC_KEY_TOPIC;
            TopicConfig topicConfig = new TopicConfig(topic);
            this.systemTopicList.add(topic);
            topicConfig.setReadQueueNums(this.brokerController.getBrokerConfig()
                .getDefaultTopicQueueNums());
            topicConfig.setWriteQueueNums(this.brokerController.getBrokerConfig()
                .getDefaultTopicQueueNums());
            int perm = PermName.PERM_INHERIT | PermName.PERM_READ | PermName.PERM_WRITE;
            topicConfig.setPerm(perm);
            this.topicConfigTable.put(topicConfig.getTopicName(), topicConfig);
        }
    }
```