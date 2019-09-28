com.squareup.okhttp.OkHttpClient

## define

```java
public class OkHttpClient implements Cloneable {
  private static final List<Protocol> DEFAULT_PROTOCOLS = Util.immutableList(
      Protocol.HTTP_2, Protocol.SPDY_3, Protocol.HTTP_1_1);
}
```