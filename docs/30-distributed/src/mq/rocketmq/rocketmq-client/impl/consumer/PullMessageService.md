org.apache.rocketmq.client.impl.consumer.PullMessageService

- LinkedBlockingQueue
- ScheduledExecutorService
- [PullRequest](/docs/30-distributed/src/rocketmq/rocketmq-client/impl/consumer/PullRequest.md)

## hierarchy
```
ServiceThread (org.apache.rocketmq.common)
    PullMessageService (org.apache.rocketmq.client.impl.consumer)
PullMessageService (org.apache.rocketmq.client.impl.consumer)
    ServiceThread (org.apache.rocketmq.common)
        Runnable (java.lang)
```

## define
```java
public class PullMessageService extends ServiceThread {
    private final InternalLogger log = ClientLogger.getLog();
    private final LinkedBlockingQueue<PullRequest> pullRequestQueue = new LinkedBlockingQueue<PullRequest>();
    private final MQClientInstance mQClientFactory;
    private final ScheduledExecutorService scheduledExecutorService = Executors
        .newSingleThreadScheduledExecutor(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "PullMessageServiceScheduledThread");
            }
        });
}    
```

## methods

### run
```java
    @Override
    public void run() {
        log.info(this.getServiceName() + " service started");

        while (!this.isStopped()) {
            try {
                PullRequest pullRequest = this.pullRequestQueue.take();
                this.pullMessage(pullRequest);
            } catch (InterruptedException ignored) {
            } catch (Exception e) {
                log.error("Pull Message Service Run Method exception", e);
            }
        }

        log.info(this.getServiceName() + " service end");
    }
```

### pullMessage
````java
    private void pullMessage(final PullRequest pullRequest) {
        final MQConsumerInner consumer = this.mQClientFactory.selectConsumer(pullRequest.getConsumerGroup());
        if (consumer != null) {
            DefaultMQPushConsumerImpl impl = (DefaultMQPushConsumerImpl) consumer;
            impl.pullMessage(pullRequest);
        } else {
            log.warn("No matched consumer for the PullRequest {}, drop it", pullRequest);
        }
    }
````