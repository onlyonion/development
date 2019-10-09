org.apache.rocketmq.broker.transaction.TransactionalMessageCheckService
## hierarchy
```
ServiceThread (org.apache.rocketmq.common)
    TransactionalMessageCheckService (org.apache.rocketmq.broker.transaction)
    
    AcceptSocketService in HAService (org.apache.rocketmq.store.ha)
    AllocateMappedFileService (org.apache.rocketmq.store)
    FileWatchService (org.apache.rocketmq.srvutil)
    FlushCommitLogService in CommitLog (org.apache.rocketmq.store)
    FlushConsumeQueueService in DefaultMessageStore (org.apache.rocketmq.store)
    GroupTransferService in HAService (org.apache.rocketmq.store.ha)
    HAClient in HAService (org.apache.rocketmq.store.ha)
    PullMessageService (org.apache.rocketmq.client.impl.consumer)
    PullRequestHoldService (org.apache.rocketmq.broker.longpolling)
    ReadSocketService in HAConnection (org.apache.rocketmq.store.ha)
    RebalanceService (org.apache.rocketmq.client.impl.consumer)
    ReputMessageService in DefaultMessageStore (org.apache.rocketmq.store)
    StoreStatsService (org.apache.rocketmq.store)
    WriteSocketService in HAConnection (org.apache.rocketmq.store.ha)
```

## define
```java
public class TransactionalMessageCheckService extends ServiceThread {
    private BrokerController brokerController;
    private final AtomicBoolean started = new AtomicBoolean(false);
}    
```

## methods

### run
```java
    @Override
    public void run() {
        log.info("Start transaction check service thread!");
        long checkInterval = brokerController.getBrokerConfig().getTransactionCheckInterval(); // private long transactionCheckInterval = 60 * 1000;
        while (!this.isStopped()) {
            this.waitForRunning(checkInterval);
        }
        log.info("End transaction check service thread!");
    }
```

###
```java
    @Override
    protected void onWaitEnd() {
        long timeout = brokerController.getBrokerConfig().getTransactionTimeOut();
        int checkMax = brokerController.getBrokerConfig().getTransactionCheckMax();
        long begin = System.currentTimeMillis();
        log.info("Begin to check prepare message, begin time:{}", begin);
        this.brokerController.getTransactionalMessageService().check(timeout, checkMax, this.brokerController.getTransactionalMessageCheckListener());
        log.info("End to check prepare message, consumed time:{}", System.currentTimeMillis() - begin);
    }
```