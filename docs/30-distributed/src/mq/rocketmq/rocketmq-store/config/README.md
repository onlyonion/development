org.apache.rocketmq.store.config

## package
```
BrokerRole
FlushDiskType
MessageStoreConfig
StorePathConfigHelper
```

## BrokerRole
代理角色
```java
public enum BrokerRole {
    ASYNC_MASTER,
    SYNC_MASTER,
    SLAVE;
}
```

## FlushDiskType
刷盘方式
```java
public enum FlushDiskType {
    SYNC_FLUSH,
    ASYNC_FLUSH
}
```

## MessageStoreConfig
```java
public class MessageStoreConfig {
    private String messageDelayLevel = "1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h";
}
```