org.springframework.web.servlet.handler.AbstractHandlerMethodMapping

## hierarchy
```
ApplicationObjectSupport (org.springframework.context.support)
    WebApplicationObjectSupport (org.springframework.web.context.support)
        AbstractHandlerMapping (org.springframework.web.servlet.handler)
            AbstractHandlerMethodMapping (org.springframework.web.servlet.handler)
                RequestMappingInfoHandlerMapping (org.springframework.web.servlet.mvc.method)
                    RequestMappingHandlerMapping (org.springframework.web.servlet.mvc.method.annotation)
AbstractHandlerMethodMapping (org.springframework.web.servlet.handler)
    AbstractHandlerMapping (org.springframework.web.servlet.handler)
        HandlerMapping (org.springframework.web.servlet)
        Ordered (org.springframework.core)
        WebApplicationObjectSupport (org.springframework.web.context.support)
            ApplicationObjectSupport (org.springframework.context.support)
                ApplicationContextAware (org.springframework.context)
                    Aware (org.springframework.beans.factory)
            ServletContextAware (org.springframework.web.context)
                Aware (org.springframework.beans.factory)
    InitializingBean (org.springframework.beans.factory)
```

## define

```plantuml
@startuml

''''''''''''''''''''''''''''AbstractHandlerMapping'''''''''''''''''''''''''''''''''
abstract class AbstractHandlerMapping
AbstractHandlerMapping "1" o-- "*" HandlerInterceptor
AbstractHandlerMapping ^-- AbstractHandlerMethodMapping

''''''''''''''''''''''''''''AbstractHandlerMethodMapping'''''''''''''''''''''''''''''''''
abstract class AbstractHandlerMethodMapping<T> {
    # HandlerMethod createHandlerMethod(Object handler, Method method)
}

AbstractHandlerMethodMapping ..> HandlerMethod

AbstractHandlerMethodMapping o-- MappingRegistry
AbstractHandlerMethodMapping +-- MappingRegistry
AbstractHandlerMethodMapping +-- MappingRegistration

class MappingRegistry {
    + void register(T mapping, Object handler, Method method)
}



@enduml
```

## methods
initHandlerMethods()

protected void detectHandlerMethods(final Object handler)

MappingRegistry.register(T mapping, Object handler, Method method)
