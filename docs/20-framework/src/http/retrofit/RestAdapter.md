retrofit.RestAdapter

- 日志
- 请求-响应
- 动态代理
- 建造者

## package

## methods

## inner Class
### Builder 
### RestHandler
```java
    private class RestHandler implements InvocationHandler {
        private final Map<Method, RestMethodInfo> methodDetailsCache;
    }
```
#### invoke
```java
    public Object invoke(Object proxy, Method method, final Object[] args)
    }
```

#### invokeRequest
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