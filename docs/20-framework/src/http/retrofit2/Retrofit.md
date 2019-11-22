retrofit2.Retrofit

## define
```java
public final class Retrofit {
  private final Map<Method, ServiceMethod> serviceMethodCache = new LinkedHashMap<>();

  private final okhttp3.Call.Factory callFactory;
  private final HttpUrl baseUrl;
  private final List<Converter.Factory> converterFactories;
  private final List<CallAdapter.Factory> adapterFactories;
  private final Executor callbackExecutor;
  private final boolean validateEagerly;
}
```

## methods

### loadServiceMethod
```java
  ServiceMethod<?> loadServiceMethod(Method method) {
    ServiceMethod<?> result = serviceMethodCache.get(method);
    if (result != null) return result;

    synchronized (serviceMethodCache) {
      result = serviceMethodCache.get(method);
      if (result == null) {
        result = ServiceMethod.parseAnnotations(this, method);
        serviceMethodCache.put(method, result);
      }
    }
    return result;
  }
```