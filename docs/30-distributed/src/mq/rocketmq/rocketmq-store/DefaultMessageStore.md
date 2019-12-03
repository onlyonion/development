org.apache.rocketmq.store.DefaultMessageStore

## define
```java
public class DefaultMessageStore implements MessageStore {
    private static final InternalLogger log = InternalLoggerFactory.getLogger(LoggerName.STORE_LOGGER_NAME);

    private final MessageStoreConfig messageStoreConfig;
    // CommitLog
    private final CommitLog commitLog;
    private final ConcurrentMap<String/* topic */, ConcurrentMap<Integer/* queueId */, ConsumeQueue>> consumeQueueTable;

    private final FlushConsumeQueueService flushConsumeQueueService;
    private final CleanCommitLogService cleanCommitLogService;
    private final CleanConsumeQueueService cleanConsumeQueueService;
    private final IndexService indexService;
    private final AllocateMappedFileService allocateMappedFileService;
    private final ReputMessageService reputMessageService;
    private final HAService haService;
    private final ScheduleMessageService scheduleMessageService;
    private final StoreStatsService storeStatsService;
    
    private final TransientStorePool transientStorePool;
    private final RunningFlags runningFlags = new RunningFlags();
    private final SystemClock systemClock = new SystemClock();
    private final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor(new ThreadFactoryImpl("StoreScheduledThread"));
    private final BrokerStatsManager brokerStatsManager;
    private final MessageArrivingListener messageArrivingListener;
    private final BrokerConfig brokerConfig;

    private volatile boolean shutdown = true;
    private StoreCheckpoint storeCheckpoint;
    private AtomicLong printTimes = new AtomicLong(0);
    private final LinkedList<CommitLogDispatcher> dispatcherList;
    private RandomAccessFile lockFile;
    private FileLock lock;
    boolean shutDownNormal = false;
}    
```

## methods

### start
```java
    public void start() throws Exception {

        lock = lockFile.getChannel().tryLock(0, 1, false);
        if (lock == null || lock.isShared() || !lock.isValid()) {
            throw new RuntimeException("Lock failed,MQ already started");
        }

        lockFile.getChannel().write(ByteBuffer.wrap("lock".getBytes()));
        lockFile.getChannel().force(true);

        this.flushConsumeQueueService.start();
        this.commitLog.start();
        this.storeStatsService.start();

        if (this.scheduleMessageService != null && SLAVE != messageStoreConfig.getBrokerRole()) {
            this.scheduleMessageService.start();
        }

        if (this.getMessageStoreConfig().isDuplicationEnable()) {
            this.reputMessageService.setReputFromOffset(this.commitLog.getConfirmOffset());
        } else {
            this.reputMessageService.setReputFromOffset(this.commitLog.getMaxOffset());
        }
        this.reputMessageService.start();

        this.haService.start();

        this.createTempFile();
        this.addScheduleTask();
        this.shutdown = false;
    }
```


## inner class

### CommitLogDispatcherBuildConsumeQueue
```java
    class CommitLogDispatcherBuildConsumeQueue implements CommitLogDispatcher {

        @Override
        public void dispatch(DispatchRequest request) {
            final int tranType = MessageSysFlag.getTransactionValue(request.getSysFlag());
            switch (tranType) {
                case MessageSysFlag.TRANSACTION_NOT_TYPE:
                case MessageSysFlag.TRANSACTION_COMMIT_TYPE:
                    DefaultMessageStore.this.putMessagePositionInfo(request);
                    break;
                case MessageSysFlag.TRANSACTION_PREPARED_TYPE:
                case MessageSysFlag.TRANSACTION_ROLLBACK_TYPE:
                    break;
            }
        }
    }
```

### CommitLogDispatcherBuildIndex
```java
    class CommitLogDispatcherBuildIndex implements CommitLogDispatcher {

        @Override
        public void dispatch(DispatchRequest request) {
            if (DefaultMessageStore.this.messageStoreConfig.isMessageIndexEnable()) {
                DefaultMessageStore.this.indexService.buildIndex(request);
            }
        }
    }
```

### CleanCommitLogService
```java
    class CleanCommitLogService {

        private final static int MAX_MANUAL_DELETE_FILE_TIMES = 20;
        private final double diskSpaceWarningLevelRatio =
            Double.parseDouble(System.getProperty("rocketmq.broker.diskSpaceWarningLevelRatio", "0.90"));

        private final double diskSpaceCleanForciblyRatio =
            Double.parseDouble(System.getProperty("rocketmq.broker.diskSpaceCleanForciblyRatio", "0.85"));
        private long lastRedeleteTimestamp = 0;

        private volatile int manualDeleteFileSeveralTimes = 0;

        private volatile boolean cleanImmediately = false;

        public void excuteDeleteFilesManualy() {
            this.manualDeleteFileSeveralTimes = MAX_MANUAL_DELETE_FILE_TIMES;
            DefaultMessageStore.log.info("executeDeleteFilesManually was invoked");
        }

        public void run() {
            try {
                this.deleteExpiredFiles();

                this.redeleteHangedFile();
            } catch (Throwable e) {
                DefaultMessageStore.log.warn(this.getServiceName() + " service has exception. ", e);
            }
        }
    }    
```

### CleanConsumeQueueService
```java
    class CleanConsumeQueueService {
        private long lastPhysicalMinOffset = 0;

        public void run() {
            try {
                this.deleteExpiredFiles();
            } catch (Throwable e) {
                DefaultMessageStore.log.warn(this.getServiceName() + " service has exception. ", e);
            }
        }
    }    
```

### FlushConsumeQueueService
刷盘
```java
    class FlushConsumeQueueService extends ServiceThread {
        private static final int RETRY_TIMES_OVER = 3;
        private long lastFlushTimestamp = 0;
        
        public void run() {
            DefaultMessageStore.log.info(this.getServiceName() + " service started");

            while (!this.isStopped()) {
                try {
                    int interval = DefaultMessageStore.this.getMessageStoreConfig().getFlushIntervalConsumeQueue();
                    this.waitForRunning(interval);
                    this.doFlush(1);
                } catch (Exception e) {
                    DefaultMessageStore.log.warn(this.getServiceName() + " service has exception. ", e);
                }
            }

            this.doFlush(RETRY_TIMES_OVER);

            DefaultMessageStore.log.info(this.getServiceName() + " service end");
        }
    }    
```


### ReputMessageService
重放消息服务
```java
    class ReputMessageService extends ServiceThread {
        @Override
        public void run() {
            DefaultMessageStore.log.info(this.getServiceName() + " service started");
            while (!this.isStopped()) {
                try {
                    Thread.sleep(1); // 睡眠1毫秒
                    this.doReput();
                } catch (Exception e) {
                    DefaultMessageStore.log.warn(this.getServiceName() + " service has exception. ", e);
                }
            }
            DefaultMessageStore.log.info(this.getServiceName() + " service end");
        }
    }
```