io.netty.buffer.CompositeByteBuf

## hierarchy
```
ByteBuf (io.netty.buffer)
    AbstractByteBuf (io.netty.buffer)
        AbstractReferenceCountedByteBuf (io.netty.buffer)
            CompositeByteBuf (io.netty.buffer)
                WrappedCompositeByteBuf (io.netty.buffer)
```

## define
```plantuml
@startuml

abstract class ByteBuf 
abstract class AbstractByteBuf 
abstract class AbstractReferenceCountedByteBuf
class CompositeByteBuf

ByteBuf ^-- AbstractByteBuf
AbstractByteBuf ^-- AbstractReferenceCountedByteBuf
AbstractReferenceCountedByteBuf ^-- CompositeByteBuf

@enduml
```

## fields
```java
private final ByteBufAllocator alloc;
private final boolean direct;
private final int maxNumComponents;

private int componentCount;
private Component[] components; // resized when needed

private boolean freed;
```

## methods