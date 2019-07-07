java.io.FileDescriptor

## fields
```java
public final class FileDescriptor {
    private int fd;
    private long handle;
    private Closeable parent;
    private List<Closeable> otherParents;
    private boolean closed;
}
```