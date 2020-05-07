org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver

## hierarchy
```
AbstractHandlerExceptionResolver (org.springframework.web.servlet.handler)
    AbstractHandlerMethodExceptionResolver (org.springframework.web.servlet.handler)
        ExceptionHandlerExceptionResolver (org.springframework.web.servlet.mvc.method.annotation)
```

## define
```plantuml
@startuml

abstract class AbstractHandlerExceptionResolver
abstract class AbstractHandlerMethodExceptionResolver

interface ApplicationContextAware
interface InitializingBean
class ExceptionHandlerExceptionResolver

AbstractHandlerExceptionResolver ^-- AbstractHandlerMethodExceptionResolver
AbstractHandlerMethodExceptionResolver ^-- ExceptionHandlerExceptionResolver

@enduml
```