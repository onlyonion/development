feign.Response

```java
public final class Response implements Closeable {

  private final int status;
  private final String reason;
  private final Map<String, Collection<String>> headers;
  private final Body body;
  private final Request request;

}
```