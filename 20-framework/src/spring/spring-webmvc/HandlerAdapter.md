org.springframework.web.servlet.HandlerAdapter
org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter
org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter
## define
```
@startuml
interface HandlerAdapter {
    + boolean supports(Object handler)
    + ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    + long getLastModified(HttpServletRequest request, Object handler)
}

abstract class AbstractHandlerMethodAdapter {

}
HandlerAdapter <|.. AbstractHandlerMethodAdapter

class RequestMappingHandlerAdapter {

}
AbstractHandlerMethodAdapter <|-- RequestMappingHandlerAdapter

@enduml
```
## package
```
HandlerAdapter (org.springframework.web.servlet)
    HttpRequestHandlerAdapter (org.springframework.web.servlet.mvc)
    SimpleServletHandlerAdapter (org.springframework.web.servlet.handler)
    AnnotationMethodHandlerAdapter (org.springframework.web.servlet.mvc.annotation)
    AbstractHandlerMethodAdapter (org.springframework.web.servlet.mvc.method)
        RequestMappingHandlerAdapter (org.springframework.web.servlet.mvc.method.annotation)
    SimpleControllerHandlerAdapter (org.springframework.web.servlet.mvc)
```

## RequestMappingHandlerAdapter



## RequestMappingHandlerAdapter.handle()
* 适配器处理
* 委托InvocableHandlerMethod
* 获得模型视图对象
```mermaid
sequenceDiagram
    %% 请求传递到适配器 抽象处理方法适配器 -- 请求映射处理器适配器
    DispatcherServlet->>AbstractHandlerMethodAdapter:handle(request, response, handler)
    AbstractHandlerMethodAdapter->>RequestMappingHandlerAdapter:handleInternal(request, response, handlerMethod)
    
    RequestMappingHandlerAdapter->>RequestMappingHandlerAdapter:invokeHandlerMethod()
    %% 适配器调用处理方法
    RequestMappingHandlerAdapter->>ServletInvocableHandlerMethod:invokeAndHandle()
    
    %% 调用并处理
    ServletInvocableHandlerMethod->>InvocableHandlerMethod:invokeForRequest()
    InvocableHandlerMethod->>InvocableHandlerMethod:getMethodArgumentValues()
    InvocableHandlerMethod->>InvocableHandlerMethod:doInvoke()反射调用方法

    InvocableHandlerMethod-->>ServletInvocableHandlerMethod:invokeForRequest()返回结果值
    
    %% 获得模型视图对象
    RequestMappingHandlerAdapter->>RequestMappingHandlerAdapter:getModelAndView()
    RequestMappingHandlerAdapter->>WebContentGenerator:prepareResponse()
    
    
    RequestMappingHandlerAdapter-->>AbstractHandlerMethodAdapter:handleInternal()返回
    AbstractHandlerMethodAdapter-->>DispatcherServlet:handle()返回
```

## ServletInvocableHandlerMethod.invokeHandlerMethod()

[ServletInvocableHandlerMethod](../spring-web/web/method/HandlerMethod.md)