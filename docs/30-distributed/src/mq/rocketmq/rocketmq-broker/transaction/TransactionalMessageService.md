org.apache.rocketmq.broker.transaction.TransactionalMessageService

## hierarchy
```
TransactionalMessageService (org.apache.rocketmq.broker.transaction)
    TransactionalMessageServiceImpl (org.apache.rocketmq.broker.transaction.queue)
```

## define
```java
public interface TransactionalMessageService {
    
    PutMessageResult prepareMessage(MessageExtBrokerInner messageInner);
    boolean deletePrepareMessage(MessageExt messageExt);
    
    OperationResult commitMessage(EndTransactionRequestHeader requestHeader);
    OperationResult rollbackMessage(EndTransactionRequestHeader requestHeader);
    
    // 向生产者回查事务状态
    void check(long transactionTimeout, int transactionCheckMax, AbstractTransactionalMessageCheckListener listener);
    boolean open();
    void close();
}
```