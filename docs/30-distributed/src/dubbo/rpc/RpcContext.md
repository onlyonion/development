com.alibaba.dubbo.rpc.

## define
```java
public class RpcContext {

    private static final ThreadLocal<RpcContext> LOCAL = new ThreadLocal<RpcContext>() {
        @Override
        protected RpcContext initialValue() {
            return new RpcContext();
        }
    };
    private final Map<String, String> attachments = new HashMap<String, String>();
    private final Map<String, Object> values = new HashMap<String, Object>();
    private Future<?> future;
    private List<URL> urls;
    private URL url;
    private String methodName;
    private Class<?>[] parameterTypes;
    private Object[] arguments;
    private InetSocketAddress localAddress;
    private InetSocketAddress remoteAddress;
}    
```

## methods

### asyncCall
异步调用 ，需要返回值，即使步调用Future.get方法，也会处理调用超时问题.
```java
    public <T> Future<T> asyncCall(Callable<T> callable) {
        try {
            try {
                setAttachment(Constants.ASYNC_KEY, Boolean.TRUE.toString());
                final T o = callable.call();
                //local调用会直接返回结果.
                if (o != null) {
                    FutureTask<T> f = new FutureTask<T>(new Callable<T>() {
                        public T call() throws Exception {
                            return o;
                        }
                    });
                    f.run();
                    return f;
                } else {

                }
            } catch (Exception e) {
                throw new RpcException(e);
            } finally {
                removeAttachment(Constants.ASYNC_KEY);
            }
        } catch (final RpcException e) {
            return new Future<T>() {
                public boolean cancel(boolean mayInterruptIfRunning) {
                    return false;
                }

                public boolean isCancelled() {
                    return false;
                }

                public boolean isDone() {
                    return true;
                }

                public T get() throws InterruptedException, ExecutionException {
                    throw new ExecutionException(e.getCause());
                }

                public T get(long timeout, TimeUnit unit)
                        throws InterruptedException, ExecutionException,
                        TimeoutException {
                    return get();
                }
            };
        }
        return ((Future<T>) getContext().getFuture());
    }
```

### asyncCall
oneway调用，只发送请求，不接收返回结果.
```java
    public void asyncCall(Runnable runable) {
        try {
            setAttachment(Constants.RETURN_KEY, Boolean.FALSE.toString());
            runable.run();
        } catch (Throwable e) {
            //FIXME 异常是否应该放在future中？
            throw new RpcException("oneway call error ." + e.getMessage(), e);
        } finally {
            removeAttachment(Constants.RETURN_KEY);
        }
    }
```