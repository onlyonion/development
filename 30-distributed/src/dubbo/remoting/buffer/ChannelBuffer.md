
## ChannelBuffer
```yuml
// {type:class}
[ChannelBuffer]^-.-[NettyBackedChannelBuffer]
[ChannelBuffer]^-.-[AbstractChannelBuffer]
[AbstractChannelBuffer]^-[HeapChannelBuffer]
[AbstractChannelBuffer]^-[ByteBufferBackedChannelBuffer]
[AbstractChannelBuffer]^-[DynamicChannelBuffer]

```