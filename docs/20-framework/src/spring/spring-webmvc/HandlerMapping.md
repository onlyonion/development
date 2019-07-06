org.springframework.web.servlet.HandlerMapping

## 1. hierachical
```
HandlerMapping (org.springframework.web.servlet)                                                                                    
    MatchableHandlerMapping (org.springframework.web.servlet.handler)
    AbstractHandlerMapping (org.springframework.web.servlet.handler)                                                                
        EmptyHandlerMapping in WebMvcConfigurationSupport (org.springframework.web.servlet.config.annotation)
        AbstractUrlHandlerMapping (org.springframework.web.servlet.handler)
            AbstractDetectingUrlHandlerMapping (org.springframework.web.servlet.handler)
                DefaultAnnotationHandlerMapping (org.springframework.web.servlet.mvc.annotation)
                BeanNameUrlHandlerMapping (org.springframework.web.servlet.handler)
                AbstractControllerUrlHandlerMapping (org.springframework.web.servlet.mvc.support)
                    ControllerClassNameHandlerMapping (org.springframework.web.servlet.mvc.support)
                    ControllerBeanNameHandlerMapping (org.springframework.web.servlet.mvc.support)
            WelcomePageHandlerMapping in WebMvcAutoConfiguration (org.springframework.boot.autoconfigure.web)
            SimpleUrlHandlerMapping (org.springframework.web.servlet.handler)
        AbstractHandlerMethodMapping (org.springframework.web.servlet.handler)                                                      
            RequestMappingInfoHandlerMapping (org.springframework.web.servlet.mvc.method)                                           
                RequestMappingHandlerMapping (org.springframework.web.servlet.mvc.method.annotation)                                
```

## 2. define
```plantuml
@startuml

interface HandlerMapping {
	+ HandlerExecutionChain getHandler(HttpServletRequest request) throws Exception
}

@enduml
```


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

// 抽象处理器方法映射
[AbstractHandlerMapping]^-[AbstractHandlerMethodMapping]
[InitializingBean]^-.-[AbstractHandlerMethodMapping]

// 请求映射信息处理器映射
[AbstractHandlerMethodMapping]^-[RequestMappingInfoHandlerMapping]

// 请求映射处理器映射
[RequestMappingInfoHandlerMapping]^-[RequestMappingHandlerMapping]

[EmbeddedValueResolverAware]^-.-[RequestMappingHandlerMapping]

// 感知接口
[Aware]^-[ApplicationContextAware]
[Aware]^-[ServletContextAware]
[Aware]^-[EmbeddedValueResolverAware]
```

## 3. RequestMappingHandlerMapping
```plantuml
@startuml

abstract class AbstractHandlerMapping {
    - PathMatcher pathMatcher
    - List<HandlerInterceptor> adaptedInterceptors
}

abstract class AbstractHandlerMethodMapping {
    - MappingRegistry mappingRegistry
    - UrlPathHelper urlPathHelper = new UrlPathHelper()
    - PathMatcher pathMatcher = new AntPathMatcher()
}

AbstractHandlerMethodMapping +-- MappingRegistry

class MappingRegistry {
    - Map<T, MappingRegistration<T>> registry = new HashMap<T, MappingRegistration<T>>()
    - Map<T, HandlerMethod> mappingLookup = new LinkedHashMap<T, HandlerMethod>()
    - ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock()
}

abstract class RequestMappingInfoHandlerMapping {
}

class RequestMappingHandlerMapping {
    - ContentNegotiationManager contentNegotiationManager = new ContentNegotiationManager()
    - StringValueResolver embeddedValueResolver
    - RequestMappingInfo.BuilderConfiguration config = new RequestMappingInfo.BuilderConfiguration();
}

HandlerMapping <|.. AbstractHandlerMapping

AbstractHandlerMapping <|-- AbstractHandlerMethodMapping
InitializingBean <|.. AbstractHandlerMethodMapping

AbstractHandlerMethodMapping <|-- RequestMappingInfoHandlerMapping
RequestMappingInfoHandlerMapping <|-- RequestMappingHandlerMapping

@enduml
```

### 3.1 RequestMappingHandlerMapping 初始化
* 上下文 预实例化非懒加载单例bean AbstractApplicationContext.finishBeanFactoryInitialization()
* [InitializingBean.afterPropertiesSet()](/20-framework/src/spring/spring-beans/factory/support/AbstractAutowireCapableBeanFactory.md) 
* 初始化处理器方法
* 查找处理映射方法，创建HandlerMethod，注册MappingRegistry

```mermaid
sequenceDiagram
    %% getBean()触发 配置信息实例化
    AbstractAutowireCapableBeanFactory->>RequestMappingHandlerMapping:afterPropertiesSet()
    RequestMappingHandlerMapping->>RequestMappingHandlerMapping:请求映射信息配置
    RequestMappingHandlerMapping->>AbstractHandlerMethodMapping:afterPropertiesSet()
    
    AbstractHandlerMethodMapping->>AbstractHandlerMethodMapping:initHandlerMethods()
    
    loop 遍历所有的bean
        opt isHandler()
            AbstractHandlerMethodMapping->>AbstractHandlerMethodMapping:detectHandlerMethods()
        end
    end
    
    %% handlerMethodsInitialized(getHandlerMethods())
    AbstractHandlerMethodMapping->>AbstractHandlerMethodMapping:处理方法初始化
```

### 3.2 AbstractHandlerMethodMapping.detectHandlerMethods()
```mermaid
sequenceDiagram
    AbstractHandlerMethodMapping->>AbstractHandlerMethodMapping:detectHandlerMethods()
    
    %% 查找请求映射方法
    loop 遍历所有的方法
        AbstractHandlerMethodMapping->>RequestMappingHandlerMapping初始化:getMappingForMethod()
    end
    
    %% 注册请求映射方法
    loop
        AbstractHandlerMethodMapping->>AbstractHandlerMethodMapping:registerHandlerMethod()
        AbstractHandlerMethodMapping->>MappingRegistry:register()
    end
```

### 3.3 MappingRegistry.register()
```mermaid
sequenceDiagram
    AbstractHandlerMethodMapping->>MappingRegistry:register()
    
    %% 读写锁-加锁
    MappingRegistry->>ReentrantReadWriteLock:writeLock().lock()
    
    MappingRegistry->>AbstractHandlerMethodMapping:createHandlerMethod()
    MappingRegistry->>MappingRegistry:assertUniqueMethodMapping()
    opt
        MappingRegistry->>AbstractHandlerMethodMapping:addMappingName()
    end
    MappingRegistry->>AbstractHandlerMethodMapping:initCorsConfiguration()
    
    %% 读写锁-解锁
    MappingRegistry->>ReentrantReadWriteLock:writeLock().unlock()
```


## 4. SimpleUrlHandlerMapping

### 4.1 SimpleUrlHandlerMapping 初始化
* 上下文 预实例化非懒加载单例bean AbstractApplicationContext.finishBeanFactoryInitialization()
* [AbstractAutowireCapableBeanFactory.initializeBean()后处理器方法，在InitializingBean.afterPropertiesSet()之前执行](/20-framework/src/spring/spring-beans/factory/support/AbstractAutowireCapableBeanFactory.md) 


```mermaid
sequenceDiagram
    ApplicationContextAwareProcessor->>ApplicationObjectSupport:setApplicationContext(context)
    ApplicationObjectSupport->>WebApplicationObjectSupport:initApplicationContext(context)
    
    %% 初始化
    WebApplicationObjectSupport->>ApplicationObjectSupport:initApplicationContext()
    ApplicationObjectSupport->>SimpleUrlHandlerMapping:initApplicationContext()
    SimpleUrlHandlerMapping->>AbstractHandlerMapping:initApplicationContext()
    
    %% 抽象处理器映射初始化
    AbstractHandlerMapping->>AbstractHandlerMapping:extendInterceptors()
    AbstractHandlerMapping->>AbstractHandlerMapping:detectMappedInterceptors()
    AbstractHandlerMapping->>AbstractHandlerMapping:initInterceptors()
    
    %% 注册处理器
    SimpleUrlHandlerMapping->>SimpleUrlHandlerMapping:registerHandlers()
    loop
        SimpleUrlHandlerMapping->>AbstractUrlHandlerMapping:registerHandler()
    end
```