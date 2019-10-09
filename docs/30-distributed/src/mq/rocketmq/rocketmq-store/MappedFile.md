org.apache.rocketmq.store.MappedFile

## hierarchy
```
ReferenceResource (org.apache.rocketmq.store)
    MappedFile (org.apache.rocketmq.store)
```

## define
```java
public class MappedFile extends ReferenceResource {
    public static final int OS_PAGE_SIZE = 1024 * 4; // 操作系统每页大小，默认4K

    private static final AtomicLong TOTAL_MAPPED_VIRTUAL_MEMORY = new AtomicLong(0);

    private static final AtomicInteger TOTAL_MAPPED_FILES = new AtomicInteger(0); // 当前JVM实例中MappedFile对象个数
    protected final AtomicInteger wrotePosition = new AtomicInteger(0); // 当前该文件的写指针
    //ADD BY ChenYang
    protected final AtomicInteger committedPosition = new AtomicInteger(0); // 当前文件的提交指针
    private final AtomicInteger flushedPosition = new AtomicInteger(0); // 刷写到磁盘的指针
    protected int fileSize; // 文件大小
    protected FileChannel fileChannel; // 文件通道
    /**
     * Message will put to here first, and then reput to FileChannel if writeBuffer is not null.
     */
    protected ByteBuffer writeBuffer = null; // 堆内存ByteBuffer
    protected TransientStorePool transientStorePool = null; // 堆内存池
    private String fileName;
    private long fileFromOffset;
    private File file;
    private MappedByteBuffer mappedByteBuffer; // java.nio.MappedByteBuffer 物理文件对于的内存映射Buffer
    private volatile long storeTimestamp = 0;
    private boolean firstCreateInQueue = false;
}
```

## methods

### MappedFile
```java
    public MappedFile(final String fileName, final int fileSize) throws IOException {
        init(fileName, fileSize);
    }
```

### init
```java
    public void init(final String fileName, final int fileSize,
        final TransientStorePool transientStorePool) throws IOException {
        init(fileName, fileSize);
        this.writeBuffer = transientStorePool.borrowBuffer();
        this.transientStorePool = transientStorePool;
    }

    private void init(final String fileName, final int fileSize) throws IOException {
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.file = new File(fileName);
        this.fileFromOffset = Long.parseLong(this.file.getName());
        boolean ok = false;

        ensureDirOK(this.file.getParent());

        try {
            this.fileChannel = new RandomAccessFile(this.file, "rw").getChannel();
            this.mappedByteBuffer = this.fileChannel.map(MapMode.READ_WRITE, 0, fileSize);
            TOTAL_MAPPED_VIRTUAL_MEMORY.addAndGet(fileSize);
            TOTAL_MAPPED_FILES.incrementAndGet();
            ok = true;
        } catch (FileNotFoundException e) {
            log.error("create file channel " + this.fileName + " Failed. ", e);
            throw e;
        } catch (IOException e) {
            log.error("map file " + this.fileName + " Failed. ", e);
            throw e;
        } finally {
            if (!ok && this.fileChannel != null) {
                this.fileChannel.close();
            }
        }
    }
```

### flush
```java
    public int flush(final int flushLeastPages) {
        if (this.isAbleToFlush(flushLeastPages)) {
            if (this.hold()) {
                int value = getReadPosition();

                try {
                    //We only append data to fileChannel or mappedByteBuffer, never both.
                    if (writeBuffer != null || this.fileChannel.position() != 0) {
                        this.fileChannel.force(false);
                    } else {
                        this.mappedByteBuffer.force();
                    }
                } catch (Throwable e) {
                    log.error("Error occurred when force data to disk.", e);
                }

                this.flushedPosition.set(value);
                this.release();
            } else {
                log.warn("in flush, hold failed, flush offset = " + this.flushedPosition.get());
                this.flushedPosition.set(getReadPosition());
            }
        }
        return this.getFlushedPosition();
    }
```