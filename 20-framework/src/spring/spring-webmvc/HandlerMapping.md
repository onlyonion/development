org.springframework.web.servlet.HandlerMapping
## 1. define
```java
public interface HandlerMapping {
	HandlerExecutionChain getHandler(HttpServletRequest request) throws Exception;
}
```


## 2. hierachical
```yuml
// {type:class}

// 
[HandlerMapping{bg:tomato}]
[ApplicationObjectSupport{bg:thistle}]
[WebApplicationObjectSupport{bg:thistle}]
[AbstractHandlerMapping{bg:wheat}]
[AbstractHandlerMethodMapping{bg:wheat}]
[RequestMappingInfoHandlerMapping{bg:wheat}]
[RequestMappingHandlerMapping{bg:tomato}]


// 1. HandlerMapping接口
[HandlerMapping]^-[MatchableHandlerMapping]
[MatchableHandlerMapping]^-.-[RequestMappingHandlerMapping]


// 2. RequestMappingHandlerMapping的继承层次
[ApplicationContextAware]^-.-[ApplicationObjectSupport]
[ApplicationObjectSupport]^-[WebApplicationObjectSupport]
[ServletContextAware]^-.-[WebApplicationObjectSupport]

[WebApplicationObjectSupport]^-[AbstractHandlerMapping]
[HandlerMapping]^-.-[AbstractHandlerMapping]
[Ordered]^-.-[AbstractHandlerMapping]

// 
[AbstractHandlerMapping]^-[AbstractHandlerMethodMapping]
[InitializingBean]^-.-[AbstractHandlerMethodMapping]

[AbstractHandlerMethodMapping]^-[RequestMappingInfoHandlerMapping]
[RequestMappingInfoHandlerMapping]^-[RequestMappingHandlerMapping]

[EmbeddedValueResolverAware]^-.-[RequestMappingHandlerMapping]

// 感知接口
[Aware]^-[ApplicationContextAware]
[Aware]^-[ServletContextAware]
[Aware]^-[EmbeddedValueResolverAware]
```