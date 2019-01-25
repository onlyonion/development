com.alibaba.dubbo.config 配置中心

## package

```
annotation
spring
support
utils
AbstractConfig
AbstractInterfaceConfig
AbstractMethodConfig
AbstractReferenceConfig
AbstractServiceConfig
ApplicationConfig
ArgumentConfig
ConsumerConfig
MethodConfig
ModuleConfig
MonitorConfig
ProtocolConfig
ProviderConfig
ReferenceConfig
RegistryConfig
ServiceConfig
```

### 类图
```yuml
// {type:class}

// 1. 通信协议配置 封装协议名称、地址、端口、上下文路径、线程池、序列化、网络传输、io等
[AbstractConfig{bg:thistle}]^-[ProtocolConfig]

// 2. 注册中心配置 封装注册中心地址、端口、注册协议；超时
[AbstractConfig]^-[RegistryConfig]

// 3. 方法配置 超时、重试、最大并发、负载均衡、是否异步、mock、合并器、自定义参数
[AbstractConfig]^-[AbstractMethodConfig{bg:thistle}]

// 3.1 接口配置
[AbstractMethodConfig]^-[AbstractInterfaceConfig{bg:thistle}]

// 聚合了注册配置、应用配置、模块配置
[AbstractInterfaceConfig]++-[ApplicationConfig]
[AbstractInterfaceConfig]++-[ModuleConfig]
[AbstractInterfaceConfig]++1-*[RegistryConfig]

// 3.1.1 引入接口配置
[AbstractInterfaceConfig]^-[AbstractReferenceConfig{bg:thistle}]
[AbstractReferenceConfig]^-[ConsumerConfig]
[AbstractReferenceConfig]^-[ReferenceConfig{bg:orange}]

// 与spring集成，bean的方式
[ReferenceConfig]^-[ReferenceBean{bg:orange}]

[ReferenceConfig]++1-*[MethodConfig]
[ReferenceConfig]++-[ConsumerConfig]

// 3.1.2 导出接口配置
[AbstractInterfaceConfig]^-[AbstractServiceConfig{bg:thistle}]
[AbstractServiceConfig]++1-*[ProtocolConfig]

[AbstractServiceConfig]^-[ProviderConfig]
[AbstractServiceConfig]^-[ServiceConfig{bg:orange}]

// 与spring集成，bean的方式
[ServiceConfig]^-[ServiceBean{bg:orange}]

[ServiceConfig]++1-*[MethodConfig]
[ServiceConfig]++-[ProviderConfig]

// 3.2 方法配置
[AbstractMethodConfig]^-[MethodConfig]
[MethodConfig]++1-*[ArgumentConfig]


// 3.2 
[AbstractConfig]^-[MethodConfig]

// 4. 应用层配置
[AbstractConfig]^-[ApplicationConfig]

// 5. 模块配置
[AbstractConfig]^-[ModuleConfig]

// 6. 注解配置bean，与spring集成
[AbstractConfig]^-[AnnotationBean{bg:beige}]

// 7. 监控器配置
[AbstractConfig]^-[MonitorConfig]

```

### xml配置解析与spring集成

```yuml
// {type:class}

// schema解析
[BeanDefinitionParser]^-.-[DubboBeanDefinitionParser]
[NamespaceHandlerSupport]^-[DubboNamespaceHandler]

[DubboNamespaceHandler]uses-.->[DubboBeanDefinitionParser]
```

### ServiceBean
```yuml
// {type:class}

// 导出 
[ServiceConfig{bg:orange}]^-[ServiceBean{bg:beige}]

[ApplicationListener]^-.-[ServiceBean]
[BeanNameAware]^-.-[ServiceBean]
[ApplicationContextAware]^-.-[ServiceBean]
[InitializingBean]^-.-[ServiceBean]
[DisposableBean]^-.-[ServiceBean]

```

### ReferenceBean
```yuml
// {type:class}

// 引入 
[ReferenceConfig{bg:orange}]^-[ReferenceBean{bg:beige}]

[FactoryBean]^-.-[ReferenceBean]
[ApplicationContextAware]^-.-[ReferenceBean]
[InitializingBean]^-.-[ReferenceBean]
[DisposableBean]^-.-[ReferenceBean]

```

### AnnotationBean

```yuml
// {type:class}

//  注解配置 
[AbstractConfig{bg:thistle}]^-[AnnotationBean{bg:beige}]

[DisposableBean]^-.-[AnnotationBean]
[BeanFactoryPostProcessor]^-.-[AnnotationBean]
[BeanPostProcessor]^-.-[AnnotationBean]
[ApplicationContextAware]^-.-[AnnotationBean]

```