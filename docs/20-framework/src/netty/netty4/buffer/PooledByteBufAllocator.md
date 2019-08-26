io.netty.buffer.PooledByteBufAllocator

## hierarchy
```
AbstractByteBufAllocator (io.netty.buffer)
    PooledByteBufAllocator (io.netty.buffer)
PooledByteBufAllocator (io.netty.buffer)
    AbstractByteBufAllocator (io.netty.buffer)
        Object (java.lang)
        ByteBufAllocator (io.netty.buffer)
    ByteBufAllocatorMetricProvider (io.netty.buffer)
```

```java

public class PooledByteBufAllocator extends AbstractByteBufAllocator implements ByteBufAllocatorMetricProvider {


}

```

## methods


### validateAndCalculateChunkSize
- page
- chunk

```java
    private static int validateAndCalculateChunkSize(int pageSize, int maxOrder) {
        if (maxOrder > 14) {
            throw new IllegalArgumentException("maxOrder: " + maxOrder + " (expected: 0-14)");
        }

        // Ensure the resulting chunkSize does not overflow.
        int chunkSize = pageSize;
        for (int i = maxOrder; i > 0; i --) {
            if (chunkSize > MAX_CHUNK_SIZE / 2) {
                throw new IllegalArgumentException(String.format(
                        "pageSize (%d) << maxOrder (%d) must not exceed %d", pageSize, maxOrder, MAX_CHUNK_SIZE));
            }
            chunkSize <<= 1;
        }
        return chunkSize;
    }
```