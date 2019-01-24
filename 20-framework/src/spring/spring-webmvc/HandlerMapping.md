org.springframework.web.servlet.HandlerMapping
## 1. 定义
## 2. 类图
```yuml
// {type:class}

// 
[HandlerMapping||+getHandler(request)]
[ApplicationObjectSupport{bg:thistle}]
[WebApplicationObjectSupport{bg:thistle}]
[AbstractHandlerMapping{bg:wheat}]
[AbstractHandlerMethodMapping{bg:wheat}]
[RequestMappingInfoHandlerMapping{bg:wheat}]
[RequestMappingHandlerMapping{bg:wheat}]


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