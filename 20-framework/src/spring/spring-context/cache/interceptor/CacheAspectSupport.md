org.springframework.cache.interceptor.CacheAspectSupport

## hierarchy
```
AbstractCacheInvoker (org.springframework.cache.interceptor)
    CacheAspectSupport (org.springframework.cache.interceptor)
        CacheInterceptor (org.springframework.cache.interceptor)
```

## define

```plantuml
@startuml

''''''''''''''''''''''''''''AbstractCacheInvoker'''''''''''''''''''''''''''''''''''
abstract class AbstractCacheInvoker {
    # SingletonSupplier<CacheErrorHandler> errorHandler
}
interface CacheErrorHandler
AbstractCacheInvoker o-- CacheErrorHandler
AbstractCacheInvoker ^-- CacheAspectSupport

''''''''''''''''''''''''''''CacheAspectSupport'''''''''''''''''''''''''''''''''''
interface BeanFactoryAware
interface InitializingBean
interface SmartInitializingSingleton

BeanFactoryAware ^.. CacheAspectSupport
InitializingBean ^.. CacheAspectSupport
SmartInitializingSingleton ^.. CacheAspectSupport

abstract class CacheAspectSupport {
    - SingletonSupplier<KeyGenerator> keyGenerator
    - Object execute(final CacheOperationInvoker invoker, Method method, CacheOperationContexts contexts)
    # Object invokeOperation(CacheOperationInvoker invoker)
    - Object wrapCacheValue(Method method, Object cacheValue) 
}


CacheAspectSupport +-- CacheOperationContexts
CacheAspectSupport +-- CacheOperationMetadata
CacheAspectSupport +-- CacheOperationContext
CacheAspectSupport +-- CachePutRequest
CacheAspectSupport +-- CacheOperationCacheKey
CacheAspectSupport +-- ptionalUnwrapper

@enduml
```
