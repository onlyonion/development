
* 单机支持1万以上持久队列
* 刷盘策略
* 消息过滤
* 长轮询pull
* 发送消息负载均衡  路由哪个broker, 哪个queue
* 消费消息的负载均衡
* HA，同步双写，异步复制

```java
 // AllocateMessageQueueAveragely.allocate实现了consumer消费的默认负载均衡算法
// 基本原则，每个队列只能被一个consumer消费
// 当messageQueue个数小于等于consume的时候，排在前面（在list中的顺序）的consumer消费一个queue，index大于messageQueue之后的consumer消费不到queue，也就是为0
// 当messageQueue个数大于consumer的时候，分两种情况
// 当有余数（mod > 0）并且index < mod的时候，当前comsumer可以消费的队列个数是 mqAll.size() / cidAll.size() + 1
// 可以整除或者index 大于余数的时候，队列数为：mqAll.size() / cidAll.size()
```