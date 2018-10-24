
### 消息重试

RocketMQ的消息重试包含了producer发送消息的重试和consumer消息消费的重试。

#### producer发送消息重试

* 重试几次？
由上面可以看出发送消息的重试次数区分不同的情况：
同步发送：org.apache.rocketmq.client.producer.DefaultMQProducer#retryTimesWhenSendFailed + 1，默认retryTimesWhenSendFailed是2，所以除了正常调用一次外，发送消息如果失败了会重试2次
异步发送：不会重试（调用总次数等于1）

* 什么时候重试？
发生异常的时候，需要注意的是发送的时候并不是catch所有的异常，只有内部异常才会catch住并重试。

* 怎么重试？
每次重试都会重新进行负载均衡（会考虑发送失败的因素），重新选择MessageQueue，这样增大发送消息成功的可能性。

* 隔多久重试？
立即重试，中间没有单独的间隔时间。

#### consumer消费重试

消息处理失败之后，该消息会和其他正常的消息一样被broker处理，之所以能重试是因为consumer会把失败的消息发送回broker，broker对于重试的消息做一些特别的处理，供consumer再次发起消费 。

消息重试的主要流程：
consumer消费失败，将消息发送回broker
broker收到重试消息之后置换topic，存储消息
consumer会拉取该topic对应的retryTopic的消息
consumer拉取到retryTopic消息之后，置换到原始的topic，把消息交给listener消费

### 延时消息

一个延时消息被发出到消费成功经历以下几个过程：

1. 设置消息的延时级别delayLevel
2. producer发送消息
3. broker收到消息在准备将消息写入存储的时候，判断是延时消息则更改Message的topic为延时消息队列的topic，也就是将消息投递到延时消息队列
4. 有定时任务从延时队列中读取消息，拿到消息后判断是否达到延时时间，如果到了则修改topic为原始topic。并将消息投递到原始topic的队列
5. consumer像消费其他消息一样从broker拉取消息进行消费
注意：批量消息是不支持延时消息的


#### DelayQueue

DelayQueue是什么？
DelayQueue是一个无界的BlockingQueue，用于放置实现了Delayed接口的对象，其中的对象只能在其到期时才能从队列中取走。这种队列是有序的，即队头对象的延迟到期时间最长。注意：不能将null元素放置到这种队列中。

DelayQueue能做什么？
在我们的业务中通常会有一些需求是这样的： 
1. 淘宝订单业务:下单之后如果三十分钟之内没有付款就自动取消订单。 
2. 饿了吗订餐通知:下单成功后60s之后给用户发送短信通知。

[youzan-delay-queue](https://tech.youzan.com/queuing_delay/) 


### 顺序消息

发送顺序消息
send方法带有参数MessageQueueSelector，MessageQueueSelector是让用户自己决定消息发送到哪一个队列，如果是局部消息的话，用来决定消息与队列的对应关系。

``` java
SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
    @Override
    public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
        Integer id = (Integer) arg;
        int index = id % mqs.size();
        return mqs.get(index);
    }
}, orderId);
```

顺序消息消费

``` java
consumer.registerMessageListener(new MessageListenerOrderly() {
    AtomicLong consumeTimes = new AtomicLong(0);

    @Override
    public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
        context.setAutoCommit(false);
        System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
        return ConsumeOrderlyStatus.SUCCESS;
    }
});
```


从发送到消费整个过程中保证有序

* 发送消息是顺序的
* broker存储消息是顺序的
* consumer消费是顺序的
