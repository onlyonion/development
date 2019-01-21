org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext

## 类图
```yuml
// {type:class}

[AbstractApplicationContext{bg:tomato}]
[GenericApplicationContext{bg:wheat}]
[GenericWebApplicationContext{bg:wheat}]
[EmbeddedWebApplicationContext{bg:wheat}]
[AnnotationConfigEmbeddedWebApplicationContext{bg:wheat}]

[TomcatEmbeddedServletContainer{bg:thistle}]

// 1. 资源加载器
[ResourceLoader]^-.-[DefaultResourceLoader]

[DefaultResourceLoader]++-[ClassLoader]

// 2. 抽象应用环境
[DefaultResourceLoader]^-[AbstractApplicationContext]
[ConfigurableApplicationContext]^-.-[AbstractApplicationContext]
[DisposableBean]^-.-[AbstractApplicationContext]

// 3. 通用应用环境 bean定义注册表
[AbstractApplicationContext]^-[GenericApplicationContext]
[BeanDefinitionRegistry]^-.-[GenericApplicationContext]

// beanFactory的默认实现
[GenericApplicationContext]++-[DefaultListableBeanFactory]
[GenericApplicationContext]++-[ResourceLoader]

// 4. 通用web应用环境
[GenericApplicationContext]^-[GenericWebApplicationContext]
[ConfigurableWebApplicationContext]^-.-[GenericWebApplicationContext]
[ThemeSource]^-.-[GenericWebApplicationContext]

// servlet环境、主题资源
[GenericWebApplicationContext]++-[ServletContext]
[GenericWebApplicationContext]++-[ThemeSource]

// 5. 嵌入式web应用环境
[GenericWebApplicationContext]^-[EmbeddedWebApplicationContext]

// 嵌入式servlet容器、servlet配置
[EmbeddedWebApplicationContext]++-[EmbeddedServletContainer]
[EmbeddedWebApplicationContext]++-[ServletConfig]

// 嵌入式servlet容器实现
[EmbeddedServletContainer]^-.-[UndertowEmbeddedServletContainer]
[EmbeddedServletContainer]^-.-[TomcatEmbeddedServletContainer]
[EmbeddedServletContainer]^-.-[JettyEmbeddedServletContainer]

// 6. 注解配置的嵌入式web应用环境
[EmbeddedWebApplicationContext]^-[AnnotationConfigEmbeddedWebApplicationContext]


// 注解bean定义读取器、类路径bean定义扫描器
[AnnotationConfigEmbeddedWebApplicationContext]++-[AnnotatedBeanDefinitionReader]
[AnnotationConfigEmbeddedWebApplicationContext]++-[ClassPathBeanDefinitionScanner]
```

## 嵌入式web容器


## idea class
![AnnotationConfigEmbeddedWebApplicationContext](../../../img/spring-idea-AnnotationConfigEmbeddedWebApplicationContext.png)