java.nio.Buffer

缓存实质上就是一个数组，提供了对数据的结构化访问以及维护读写位置等信息。

## hierarchy
```
Buffer (java.nio)
    IntBuffer (java.nio)
    FloatBuffer (java.nio)
    CharBuffer (java.nio)
    DoubleBuffer (java.nio)
    ShortBuffer (java.nio)
    LongBuffer (java.nio)
    ByteBuffer (java.nio)
```

## define
```plantuml
@startuml

abstract class Buffer {
    // Invariants: mark <= position <= limit <= capacity
    - int mark = -1
    - int position = 0
    - int limit
    - int capacity
    // Used only by direct buffers
    long address
}

abstract class ByteBuffer
abstract class IntBuffer
abstract class ShortBuffer
abstract class LongBuffer
abstract class FloatBuffer
abstract class DoubleBuffer
abstract class CharBuffer

Buffer ^-- ByteBuffer
Buffer ^-- IntBuffer
Buffer ^-- ShortBuffer
Buffer ^-- LongBuffer
Buffer ^-- FloatBuffer
Buffer ^-- DoubleBuffer
Buffer ^-- CharBuffer

class HeapByteBuffer
class MappedByteBuffer
ByteBuffer ^-- HeapByteBuffer
ByteBuffer ^-- MappedByteBuffer

@enduml
```