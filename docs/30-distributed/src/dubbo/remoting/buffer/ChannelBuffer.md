com.alibaba.dubbo.remoting.buffer.ChannelBuffer

## hierarchy
```
ChannelBuffer (com.alibaba.dubbo.remoting.buffer)
    AbstractChannelBuffer (com.alibaba.dubbo.remoting.buffer)
        ByteBufferBackedChannelBuffer (com.alibaba.dubbo.remoting.buffer)
        DynamicChannelBuffer (com.alibaba.dubbo.remoting.buffer)
        HeapChannelBuffer (com.alibaba.dubbo.remoting.buffer)
    NettyBackedChannelBuffer (com.alibaba.dubbo.remoting.transport.netty)
```

## ChannelBuffer
```plantuml
@startuml

ChannelBuffer ^.. NettyBackedChannelBuffer
ChannelBuffer ^.. AbstractChannelBuffer
AbstractChannelBuffer ^-- HeapChannelBuffer
AbstractChannelBuffer ^-- ByteBufferBackedChannelBuffer
AbstractChannelBuffer ^-- DynamicChannelBuffer

@enduml
```