io.netty.buffer.ByteBuf

## hierarchy
```
ByteBuf (io.netty.buffer)
    AbstractByteBuf (io.netty.buffer)
        AbstractDerivedByteBuf (io.netty.buffer)
        AbstractReferenceCountedByteBuf (io.netty.buffer)
            AbstractPooledDerivedByteBuf (io.netty.buffer)
            CompositeByteBuf (io.netty.buffer)
            FixedCompositeByteBuf (io.netty.buffer)
            PooledByteBuf (io.netty.buffer)
            UnpooledDirectByteBuf (io.netty.buffer)
            UnpooledHeapByteBuf (io.netty.buffer)
            UnpooledUnsafeDirectByteBuf (io.netty.buffer)
    AbstractByteBuf (io.netty.buffer)
        EmptyByteBuf (io.netty.buffer)
        ReplayingDecoderByteBuf (io.netty.handler.codec)
    SwappedByteBuf (io.netty.buffer)
    WrappedByteBuf (io.netty.buffer)
```

## define
```plantuml
@startuml

interface ReferenceCounted
ReferenceCounted ^.. ByteBuf
abstract class ByteBuf

ByteBuf ..> ByteBufAllocator
ByteBuf ..> ByteBuffer

ByteBuf ^-- AbstractByteBuf
abstract class AbstractByteBuf

AbstractByteBuf ^-- AbstractReferenceCountedByteBuf
abstract class AbstractReferenceCountedByteBuf 


AbstractReferenceCountedByteBuf ^-- CompositeByteBuf
AbstractReferenceCountedByteBuf ^-- UnpooledDirectByteBuf
AbstractReferenceCountedByteBuf ^-- UnpooledHeapByteBuf
AbstractReferenceCountedByteBuf ^-- UnpooledUnsafeDirectByteBuf

@enduml
```