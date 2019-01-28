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


## RequestMappingHandlerAdapter

```mermaid
    %% 请求传递到适配器
    DispatcherServlet->>AbstractHandlerMethodAdapter:handle()
    AbstractHandlerMethodAdapter->>RequestMappingHandlerAdapter:handleInternal()
    
    %% 适配器调用处理方法
    RequestMappingHandlerAdapter->>RequestMappingHandlerAdapter:invokeHandlerMethod()
    
    %% 调用并处理
    RequestMappingHandlerAdapter->>ServletInvocableHandlerMethod:invokeAndHandle()
    ServletInvocableHandlerMethod->>InvocableHandlerMethod:invokeForRequest()
    
    %% 可调用的方法调用
    InvocableHandlerMethod->>InvocableHandlerMethod:doInvoke()
```
