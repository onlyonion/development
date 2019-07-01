[Dubbo学习笔记8：Dubbo的线程模型与线程池策略](https://www.cnblogs.com/xhj123/p/9095278.html)

IO线程池、业务线程池
 * all : (AllDispatcher类)所有消息都派发到业务线程池，这些消息包括请求/响应/连接事件/断开事件/心跳等
 * direct : (DirectDispacher类)所有消息都不派发到业务线程池，全部在IO线程上直接执行
 * message : (MessageOnlyDispatcher类)只有**请求响应消息**派发到业务线程池，其他连接断开事件/心跳等消息，直接在IO线程上执行
 * execution:(ExecutionDispatcher类)只把**请求类消息**派发到业务线程池处理，但是响应和其它连接断开事件，心跳等消息直接在IO线程上执行
 * connection:(ConnectionOrderedDispatcher类)在IO线程上，将连接断开事件放入队列，有序逐个执行，其它消息派发到业务线程池处理

#### Dubbo提供的线程池策略
扩展接口 ThreadPool 的SPI实现有如下几种：
* fixed：固定大小线程池，启动时建立线程，不关闭，一直持有（缺省）。
* cached：缓存线程池，空闲一分钟自动删除，需要时重建。
* limited：可伸缩线程池，但池中的线程数只会增长不会收缩。只增长不收缩的目的是为了避免收缩时突然带来大流量引起性能问题。