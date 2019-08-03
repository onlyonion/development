com.google.common.hash.BloomFilter


## define
```java
@Beta
public final class BloomFilter<T> implements Predicate<T>, Serializable {
    /** The bit set of the BloomFilter (not necessarily power of 2!) */
    private final LockFreeBitArray bits;

    /** Number of hashes per element */
    private final int numHashFunctions;

    /** The funnel to translate Ts to bytes */
    private final Funnel<? super T> funnel;

    /** The strategy we employ to map an element T to {@code numHashFunctions} bit indexes. */
    private final Strategy strategy;
}
```