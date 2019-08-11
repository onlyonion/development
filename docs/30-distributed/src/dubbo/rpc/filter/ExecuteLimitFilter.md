com.alibaba.dubbo.rpc.filter.ExecuteLimitFilter

- Semaphore 通过信号量来实现的对服务端的并发数的控制
- ConcurrentMap
- AtomicInteger
- AtomicLong
- volatile, synchronized

```xml
<!-- 给Provider配置线程池大小来控制系统提供服务的最大并行度，默认是200个 -->
<dubbo:provider token="true" threads="200"/>
<!-- 限制某个dubbo服务使用的最大线程数量时，dubbo提供了executes这一属性来提供这个功能，比如我们想限制某个接口最大能同时使用线程池中的100个线程 -->
<dubbo:service interface="com.onion.test.distributed.dubbo.rpc.DemoService" executes="100" />
```

## define
```java
@Activate(group = Constants.PROVIDER, value = Constants.EXECUTES_KEY)
public class ExecuteLimitFilter implements Filter {
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        URL url = invoker.getUrl();
        String methodName = invocation.getMethodName();
        Semaphore executesLimit = null;
        boolean acquireResult = false;
        int max = url.getMethodParameter(methodName, Constants.EXECUTES_KEY, 0);
        if (max > 0) {
            RpcStatus count = RpcStatus.getStatus(url, invocation.getMethodName());
//            if (count.getActive() >= max) {
            /**
             * http://manzhizhen.iteye.com/blog/2386408
             * use semaphore for concurrency control (to limit thread number)
             */
            executesLimit = count.getSemaphore(max);
            if(executesLimit != null && !(acquireResult = executesLimit.tryAcquire())) {
                throw new RpcException("Failed to invoke method " + invocation.getMethodName() + " in provider " + url + ", cause: The service using threads greater than <dubbo:service executes=\"" + max + "\" /> limited.");
            }
        }
        long begin = System.currentTimeMillis();
        boolean isSuccess = true;
        RpcStatus.beginCount(url, methodName);
        try {
            Result result = invoker.invoke(invocation);
            return result;
        } catch (Throwable t) {
            isSuccess = false;
            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RpcException("unexpected exception when ExecuteLimitFilter", t);
            }
        } finally {
            RpcStatus.endCount(url, methodName, System.currentTimeMillis() - begin, isSuccess);
            if(acquireResult) {
                executesLimit.release();
            }
        }
    }
}
```

## RpcStatus
```java
public class RpcStatus {
    
    private static final ConcurrentMap<String, RpcStatus> SERVICE_STATISTICS = new ConcurrentHashMap<String, RpcStatus>();
    private static final ConcurrentMap<String, ConcurrentMap<String, RpcStatus>> METHOD_STATISTICS = new ConcurrentHashMap<String, ConcurrentMap<String, RpcStatus>>();
    
    /**
     * Semaphore used to control concurrency limit set by `executes`
     */
    private volatile Semaphore executesLimit;
    private volatile int executesPermits;
    
    
    public static RpcStatus getStatus(URL url, String methodName) {
        String uri = url.toIdentityString();
        ConcurrentMap<String, RpcStatus> map = METHOD_STATISTICS.get(uri);
        if (map == null) {
            METHOD_STATISTICS.putIfAbsent(uri, new ConcurrentHashMap<String, RpcStatus>());
            map = METHOD_STATISTICS.get(uri);
        }
        RpcStatus status = map.get(methodName);
        if (status == null) {
            map.putIfAbsent(methodName, new RpcStatus());
            status = map.get(methodName);
        }
        return status;
    }
        
    public Semaphore getSemaphore(int maxThreadNum) {
        if(maxThreadNum <= 0) {
            return null;
        }

        if (executesLimit == null || executesPermits != maxThreadNum) {
            synchronized (this) {
                if (executesLimit == null || executesPermits != maxThreadNum) {
                    executesLimit = new Semaphore(maxThreadNum);
                    executesPermits = maxThreadNum;
                }
            }
        }

        return executesLimit;
    }
    
}
```