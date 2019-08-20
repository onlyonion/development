java.nio.DirectByteBuffer

## hierarchy
```
Buffer (java.nio)
    ByteBuffer (java.nio)
        MappedByteBuffer (java.nio)
            DirectByteBuffer (java.nio)
                DirectByteBufferR (java.nio)
```

## define
```java
class DirectByteBuffer extends MappedByteBuffer implements DirectBuffer {
        // Cached unsafe-access object
        protected static final Unsafe unsafe = Bits.unsafe();
        // Cached array base offset
        private static final long arrayBaseOffset = (long)unsafe.arrayBaseOffset(byte[].class);
        // Cached unaligned-access capability
        protected static final boolean unaligned = Bits.unaligned();
        // Base address, used in all indexing calculations
        // NOTE: moved up to Buffer.java for speed in JNI GetDirectBufferAddress
        //    protected long address;
        // An object attached to this buffer. If this buffer is a view of another
        // buffer then we use this field to keep a reference to that buffer to
        // ensure that its memory isn't freed before we are done with it.
        private final Object att;
}
```