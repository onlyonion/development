com.alibaba.dubbo.remoting.Dispatcher

Dispatcher
* all 所有消息都派发到线程池，包括请求，响应，连接事件，断开事件，心跳等。
* direct 所有消息都不派发到线程池，全部在 IO 线程上直接执行。
* message 只有请求响应消息派发到线程池，其它连接断开事件，心跳等消息，直接在 IO 线程上执行。
* execution 只请求消息派发到线程池，不含响应，响应和其它连接断开事件，心跳等消息，直接在 IO 线程上执行。
* connection 在 IO 线程上，将连接断开事件放入队列，有序逐个执行，其它消息派发到线程池。

## package
```
Dispatcher (com.alibaba.dubbo.remoting)
    ExecutionDispatcher (com.alibaba.dubbo.remoting.transport.dispatcher.execution)
    DirectDispatcher (com.alibaba.dubbo.remoting.transport.dispatcher.direct)
    MessageOnlyDispatcher (com.alibaba.dubbo.remoting.transport.dispatcher.message)
    ConnectionOrderedDispatcher (com.alibaba.dubbo.remoting.transport.dispatcher.connection)
    AllDispatcher (com.alibaba.dubbo.remoting.transport.dispatcher.all)
```

## define
```yuml
// {type:class}
[Dispatcher]^-.-[ExecutionDispatcher]
[Dispatcher]^-.-[DirectDispatcher]
[Dispatcher]^-.-[MessageOnlyDispatcher]
[Dispatcher]^-.-[ConnectionOrderedDispatcher]
[Dispatcher]^-.-[AllDispatcher]

// 依赖
[Dispatcher]uses->[ChannelHandler]
[Dispatcher]uses->[URL]

```