org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod

## hierarchy
* [HandlerMethod](/20-framework/src/spring/spring-web/web/method/HandlerMethod.md)
* [InvocableHandlerMethod](/20-framework/src/spring/spring-web/web/method/support/InvocableHandlerMethod.md)
```
HandlerMethod (org.springframework.web.method)
    InvocableHandlerMethod (org.springframework.web.method.support)
        ServletInvocableHandlerMethod (org.springframework.web.servlet.mvc.method.annotation)
```

## define

```plantuml
@startuml

class HandlerMethod {
    + Object getBean()
    # Method getBridgedMethod()
}

class InvocableHandlerMethod {
    + Object invokeForRequest(NativeWebRequest request, ModelAndViewContainer mavContainer, Object... providedArgs)
    # Object doInvoke(Object... args)
}
HandlerMethod <|-- InvocableHandlerMethod

class ServletInvocableHandlerMethod {
    - HttpStatus responseStatus
    - String responseReason
    - HandlerMethodReturnValueHandlerComposite returnValueHandlers
    + void invokeAndHandle(ServletWebRequest webRequest,
    			ModelAndViewContainer mavContainer, Object... providedArgs)
}
InvocableHandlerMethod <|-- ServletInvocableHandlerMethod

@enduml
```