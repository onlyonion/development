org.springframework.web.servlet.HandlerAdapter

## define
```java
public interface HandlerAdapter {
	boolean supports(Object handler);
	ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception;
	long getLastModified(HttpServletRequest request, Object handler);
}
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


## RequestMappingHandlerAdapter.handle()

```mermaid
sequenceDiagram
    %% 请求传递到适配器
    DispatcherServlet->>AbstractHandlerMethodAdapter:handle()
    AbstractHandlerMethodAdapter->>RequestMappingHandlerAdapter:handleInternal()
    
    %% 适配器调用处理方法
    RequestMappingHandlerAdapter->>ServletInvocableHandlerMethod:invokeHandlerMethod()
    
    %% 调用并处理
    ServletInvocableHandlerMethod->>InvocableHandlerMethod:invokeForRequest()
    InvocableHandlerMethod->>InvocableHandlerMethod:getMethodArgumentValues()
    InvocableHandlerMethod->>InvocableHandlerMethod:doInvoke()反射调用方法

    InvocableHandlerMethod-->>ServletInvocableHandlerMethod:invokeForRequest()返回结果值
```

## ServletInvocableHandlerMethod.invokeHandlerMethod()

[ServletInvocableHandlerMethod](../spring-web/HandlerMethod.md)