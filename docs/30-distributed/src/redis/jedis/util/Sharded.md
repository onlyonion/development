redis.clients.util.Sharded

## define
```java
public class Sharded<R, S extends ShardInfo<R>> {

  public static final int DEFAULT_WEIGHT = 1;
  private TreeMap<Long, S> nodes;
  private final Hashing algo;
  private final Map<ShardInfo<R>, R> resources = new LinkedHashMap<ShardInfo<R>, R>();
}  
```

## methods

### Sharded
```java
  public Sharded(List<S> shards) {
    this(shards, Hashing.MURMUR_HASH); // MD5 is really not good as we works
    // with 64-bits not 128
  }
```