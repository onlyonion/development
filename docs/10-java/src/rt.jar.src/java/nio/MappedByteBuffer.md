java.nio.MappedByteBuffer

A direct byte buffer whose content is a memory-mapped region of a file.

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
public abstract class MappedByteBuffer extends ByteBuffer {
    private final FileDescriptor fd;
    
    MappedByteBuffer(int mark, int pos, int lim, int cap, FileDescriptor fd) {
        super(mark, pos, lim, cap);
        this.fd = fd;
    }    
}
```