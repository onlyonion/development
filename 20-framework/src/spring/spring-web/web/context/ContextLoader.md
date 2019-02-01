org.springframework.web.context.ContextLoaderListener


## 启动

```mermaid
sequenceDiagram
    %% 环境监听
    StandardContext ->> ContextLoaderListener: contextInitialized()
    ContextLoaderListener ->> ContextLoader: initWebApplicationContext()

    %% 创建web应用环境
    ContextLoader ->> ContextLoader: createWebApplicationContext()实例化
    
    %% 刷新web应用环境
    ContextLoader ->> ContextLoader: configureAndRefreshWebApplicationContext()
    ContextLoader->>ConfigurableWebApplicationContext:refresh()
    
    %% 上下文关联
    ContextLoader ->> ServletContext: setAttribute()
```

## 销毁

```mermaid
sequenceDiagram
    %% 环境监听
    StandardContext ->> ContextLoaderListener: contextDestroyed()
    
    %% 关闭web应用环境
    ContextLoaderListener ->> ContextLoader: closeWebApplicationContext()
    ContextLoader ->> ConfigurableWebApplicationContext: close()
    
    %% 上下文去关联
    ContextLoader ->> ServletContext: removeAttribute()
```

## 各种Loader
* ClassLoader 各种类加载器
* ContextLoader spring 上下文加载器
* ResourceLoader spring 资源加载器
* ServiceLoader jdk spi 加载器
* ExtensionLoader dubbo spi 加载器
* FileLoader 文件加载器
* WebappLoader web应用加载器
* SecureLoader
