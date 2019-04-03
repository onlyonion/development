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

interface ByteBufAllocator
ByteBufAllocator ^.. AbstractByteBufAllocator

abstract class AbstractByteBufAllocator
AbstractByteBufAllocator ^-- PooledByteBufAllocator
AbstractByteBufAllocator ^-- UnpooledByteBufAllocator

class PooledByteBufAllocator
class UnpooledByteBufAllocator
 
class PreferHeapByteBufAllocator
ByteBufAllocator ^.. PreferHeapByteBufAllocator

@enduml
```