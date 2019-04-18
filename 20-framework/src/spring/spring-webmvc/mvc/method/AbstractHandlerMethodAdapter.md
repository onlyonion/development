org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter

## hierarchy
```
ApplicationObjectSupport (org.springframework.context.support)
    WebApplicationObjectSupport (org.springframework.web.context.support)
        WebContentGenerator (org.springframework.web.servlet.support)
            AbstractHandlerMethodAdapter (org.springframework.web.servlet.mvc.method)
                RequestMappingHandlerAdapter (org.springframework.web.servlet.mvc.method.annotation)
```
## define
```plantuml
@startuml

abstract class AbstractHandlerMethodAdapter {
    + final boolean supports(Object handler)
    # abstract boolean supportsInternal(HandlerMethod handlerMethod)
    + final ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler)
    # abstract ModelAndView handleInternal(HttpServletRequest request,
    			HttpServletResponse response, HandlerMethod handlerMethod)
}

@enduml
```