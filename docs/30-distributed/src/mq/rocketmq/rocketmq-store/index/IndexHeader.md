org.apache.rocketmq.store.index.IndexHeader

8 + 8 + 8 + 8 + 4 + 4 Byte
- 开始时间戳
- 结束时间戳
- 开始物理偏移
- 结束物理偏移
- 哈希槽的数量
- 索引的数量

## define
```java
public class IndexHeader {
    public static final int INDEX_HEADER_SIZE = 40;
    private static int beginTimestampIndex = 0;
    private static int endTimestampIndex = 8;
    private static int beginPhyoffsetIndex = 16;
    private static int endPhyoffsetIndex = 24;
    private static int hashSlotcountIndex = 32;
    private static int indexCountIndex = 36;
    private final ByteBuffer byteBuffer;
    private AtomicLong beginTimestamp = new AtomicLong(0);
    private AtomicLong endTimestamp = new AtomicLong(0);
    private AtomicLong beginPhyOffset = new AtomicLong(0);
    private AtomicLong endPhyOffset = new AtomicLong(0);
    private AtomicInteger hashSlotCount = new AtomicInteger(0);

    private AtomicInteger indexCount = new AtomicInteger(1);
}    
```