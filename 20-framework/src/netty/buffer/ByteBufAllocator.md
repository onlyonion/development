io.netty.buffer.ByteBufAllocator

## hierarchy
```
ByteBufAllocator (io.netty.buffer)
    AbstractByteBufAllocator (io.netty.buffer)
        UnpooledByteBufAllocator (io.netty.buffer)
        PooledByteBufAllocator (io.netty.buffer)
    PreferHeapByteBufAllocator (io.netty.channel)
```

## define
```plantuml
@startuml

interface ByteBufAllocator {
    ByteBuf ioBuffer(int initialCapacity, int maxCapacity)
    ByteBuf heapBuffer(int initialCapacity, int maxCapacity)
    ByteBuf directBuffer(int initialCapacity, int maxCapacity)
}

ByteBufAllocator ^.. AbstractByteBufAllocator
ByteBufAllocator ..> CompositeByteBuf


''''''''''''''''''''''''分配器''''''''''''''''''''''''
abstract class AbstractByteBufAllocator
AbstractByteBufAllocator ^-- PooledByteBufAllocator
AbstractByteBufAllocator ^-- UnpooledByteBufAllocator

class PooledByteBufAllocator
class UnpooledByteBufAllocator
 
class PreferHeapByteBufAllocator
ByteBufAllocator ^.. PreferHeapByteBufAllocator

@enduml
```