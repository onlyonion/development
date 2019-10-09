retrofit.RestAdapter

- 日志
- 请求-响应
- 动态代理
- 建造者

## define
```java
  private final Map<Class<?>, Map<Method, RestMethodInfo>> serviceMethodInfoCache = new LinkedHashMap<Class<?>, Map<Method, RestMethodInfo>>();

  final Endpoint server;
  final Executor httpExecutor;
  final Executor callbackExecutor;
  final RequestInterceptor requestInterceptor;
  final Converter converter;
  final Log log;
  final ErrorHandler errorHandler;

  private final Client.Provider clientProvider;
  private final Profiler profiler;
  private RxSupport rxSupport;

  volatile LogLevel logLevel;
```

## methods

### create
```java
  public <T> T create(Class<T> service) {
    Utils.validateServiceClass(service);
    return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class<?>[] { service }, new RestHandler(getMethodInfoCache(service)));
  }
```

### getMethodInfoCache
```java
  Map<Method, RestMethodInfo> getMethodInfoCache(Class<?> service) {
    synchronized (serviceMethodInfoCache) {
      Map<Method, RestMethodInfo> methodInfoCache = serviceMethodInfoCache.get(service);
      if (methodInfoCache == null) {
        methodInfoCache = new LinkedHashMap<Method, RestMethodInfo>();
        serviceMethodInfoCache.put(service, methodInfoCache);
      }
      return methodInfoCache;
    }
  }
```

## inner Class
### Log
### LogLevel
### Builder
### RestHandler
```java
    private class RestHandler implements InvocationHandler {
        private final Map<Method, RestMethodInfo> methodDetailsCache;
    }
```
#### invoke
- Object方法处理
- 方法缓存
- 三种情况
  1. 同步请求
  2. rx处理
  3. 线程池请求

```java
    public Object invoke(Object proxy, Method method, final Object[] args) throws Throwable {
    }
```

#### invokeRequest
- 方法初始化
- 获取服务地址
- 请求拦截
- Profiler 性能分析器

```java
    private Object invokeRequest(RequestInterceptor requestInterceptor, RestMethodInfo methodInfo, Object[] args) {
        try {
            // ...
            RequestBuilder requestBuilder = new RequestBuilder(serverUrl, methodInfo, converter);
            requestBuilder.setArguments(args);
            // ...
        } catch (RetrofitError e) {
            
        } finally {
          if (!methodInfo.isSynchronous) {
            Thread.currentThread().setName(IDLE_THREAD_NAME);
          }
        }
    }
```