org.springframework.boot.SpringApplication

## class

```plantuml
@startuml

class SpringApplication {
    - ResourceLoader resourceLoader
    - ConfigurableEnvironment environment
    - Class<? extends ConfigurableApplicationContext> applicationContextClass
    - List<ApplicationContextInitializer<?>> initializers
    - List<ApplicationListener<?>> listeners
    + SpringApplication(ResourceLoader resourceLoader, Class<?>... primarySources)
    + ConfigurableApplicationContext run(String... args)
    .. 准备环境 ..
    - ConfigurableEnvironment prepareEnvironment(SpringApplicationRunListeners listeners,
    			ApplicationArguments applicationArguments)
    .. 创建应用上下文 ..
    # ConfigurableApplicationContext createApplicationContext()	
    .. 准备上下文 ..
    - void prepareContext(ConfigurableApplicationContext context, 
                ConfigurableEnvironment environment, SpringApplicationRunListeners listeners,
                ApplicationArguments applicationArguments, Banner printedBanner)
    .. 刷新上下文 ..
    - void refreshContext(ConfigurableApplicationContext context)
    .. 刷新之后 ..
    # void afterRefresh(ConfigurableApplicationContext context, ApplicationArguments args)
}


@enduml
```

## 启动
* initialize
* createApplicationContext
* prepareContext
* refreshContext
* afterRefresh
* listeners.finished

```mermaid
sequenceDiagram
    SpringApplication->>SpringApplication:run(source, args)
    
    %% 1. 实例化，初始化Initializers、Listeners
    SpringApplication->>SpringApplication:new SpringApplication()
    SpringApplication->>SpringApplication:initialize()
    
    %% 2. 运行
    
    %% 2.1 监听器启动
    SpringApplication->>SpringApplicationRunListeners:starting()
    
    %% 2.2 准备环境、打印banner
    SpringApplication->>SpringApplication:prepareEnvironment();
    SpringApplication->>SpringApplication:printBanner()
    
    %% 2.3 上下文启动
    
    %% 2.3.1 上下文实例化、准备
    SpringApplication->>SpringApplication:createApplicationContext()
    SpringApplication->>SpringApplication:prepareContext()
    
    %% 2.3.2 刷新
    SpringApplication->>SpringApplication:refreshContext()
    SpringApplication->>AbstractApplicationContext:refresh()
    SpringApplication->>AbstractApplicationContext:registerShutdownHook()
    
    %% 2.3.2 刷新之后
    SpringApplication->>SpringApplication:afterRefresh()
    
    %% 2.4 监听器启动
    SpringApplication->>SpringApplicationRunListeners:finished()
```

## context
* [AnnotationConfigEmbeddedWebApplicationContext](./context/embedded/AnnotationConfigEmbeddedWebApplicationContext.md)
* [AnnotationConfigServletWebServerApplicationContext](./web/servlet/ServletWebServerApplicationContext.md)
