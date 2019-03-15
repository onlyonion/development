org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter

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

'''''''''''''''''''''''''''''''' HandlerAdapter ''''''''''''''''''''''''''''''''
interface HandlerAdapter {
    + ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
}

'''''''''''''''''''''''''''''''' AbstractHandlerMethodAdapter ''''''''''''''''''''''''''''''''
abstract class ApplicationObjectSupport 
abstract class WebApplicationObjectSupport
abstract class WebContentGenerator
abstract class AbstractHandlerMethodAdapter {
    + final ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler)
    # abstract ModelAndView handleInternal(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod)
}
ApplicationObjectSupport <|-- WebApplicationObjectSupport
WebApplicationObjectSupport <|-- WebContentGenerator
WebContentGenerator <|-- AbstractHandlerMethodAdapter
HandlerAdapter <|.. AbstractHandlerMethodAdapter

'''''''''''''''''''''''''''''''' RequestMappingHandlerAdapter ''''''''''''''''''''''''''''''''
class RequestMappingHandlerAdapter {
    # ModelAndView handleInternal(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod)
    # ModelAndView invokeHandlerMethod(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod)
}
AbstractHandlerMethodAdapter <|-- RequestMappingHandlerAdapter

interface BeanFactoryAware
interface InitializingBean
BeanFactoryAware <|.. RequestMappingHandlerAdapter
InitializingBean <|.. RequestMappingHandlerAdapter

@enduml
```