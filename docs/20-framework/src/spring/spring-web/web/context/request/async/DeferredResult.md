org.springframework.web.context.request.async.DeferredResult

## define
```java
public class DeferredResult<T> {

	private static final Object RESULT_NONE = new Object();
	private static final Log logger = LogFactory.getLog(DeferredResult.class);
	@Nullable
	private final Long timeout;
	private final Object timeoutResult;
	private Runnable timeoutCallback;
	private Consumer<Throwable> errorCallback;
	private Runnable completionCallback;
	private DeferredResultHandler resultHandler;
	private volatile Object result = RESULT_NONE;
	private volatile boolean expired = false;
}	

```