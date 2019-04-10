org.springframework.cache.interceptor.CacheInterceptor

## hierarchy
```
AbstractCacheInvoker (org.springframework.cache.interceptor)
    CacheAspectSupport (org.springframework.cache.interceptor)
        CacheInterceptor (org.springframework.cache.interceptor)
```

## define
```plantuml
@startuml

interface CacheErrorHandler
abstract class AbstractCacheInvoker {
    # SingletonSupplier<CacheErrorHandler> errorHandler
}
AbstractCacheInvoker o-- CacheErrorHandler

AbstractCacheInvoker ^-- CacheAspectSupport
BeanFactoryAware ^.. CacheAspectSupport
InitializingBean ^.. CacheAspectSupport
SmartInitializingSingleton ^.. CacheAspectSupport
abstract class CacheAspectSupport {
    - SingletonSupplier<KeyGenerator> keyGenerator
}

CacheAspectSupport ^-- CacheInterceptor
class CacheInterceptor {

}

@enduml
```