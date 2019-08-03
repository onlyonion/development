org.apache.rocketmq.client.producer.DefaultMQProducer

## hierarchy
```
ClientConfig (org.apache.rocketmq.client)
    DefaultMQProducer (org.apache.rocketmq.client.producer)
        TransactionMQProducer (org.apache.rocketmq.client.producer)
```

## define
```java
public class DefaultMQProducer extends ClientConfig implements MQProducer {

    protected final transient DefaultMQProducerImpl defaultMQProducerImpl;
    private String producerGroup;
    private String createTopicKey = MixAll.AUTO_CREATE_TOPIC_KEY_TOPIC;
    
    private int retryTimesWhenSendFailed = 2;
    private int retryTimesWhenSendAsyncFailed = 2;
    private int maxMessageSize = 1024 * 1024 * 4; // 4M
    
}    
```

## methods


## class

### TransactionMQProducer
```java
public class TransactionMQProducer extends DefaultMQProducer {
    private TransactionListener transactionListener;

    private ExecutorService executorService;
    
    @Override
    public TransactionSendResult sendMessageInTransaction(final Message msg, final Object arg) throws MQClientException {
        if (null == this.transactionListener) {
            throw new MQClientException("TransactionListener is null", null);
        }

        return this.defaultMQProducerImpl.sendMessageInTransaction(msg, transactionListener, arg);
    }
}
```

### TransactionListener
```java
public interface TransactionListener {
    /**
     * When send transactional prepare(half) message succeed, this method will be invoked to execute local transaction.
     *
     * @param msg Half(prepare) message
     * @param arg Custom business parameter
     * @return Transaction state
     */
    LocalTransactionState executeLocalTransaction(final Message msg, final Object arg);

    /**
     * When no response to prepare(half) message. broker will send check message to check the transaction status, and this
     * method will be invoked to get local transaction status.
     *
     * @param msg Check message
     * @return Transaction state
     */
    LocalTransactionState checkLocalTransaction(final MessageExt msg);
}
```