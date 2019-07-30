com.alibaba.dubbo.config.AbstractInterfaceConfig

## hierarchy
```
AbstractConfig (com.alibaba.dubbo.config)
    AbstractMethodConfig (com.alibaba.dubbo.config)
        AbstractInterfaceConfig (com.alibaba.dubbo.config)
            AbstractReferenceConfig (com.alibaba.dubbo.config)
            AbstractServiceConfig (com.alibaba.dubbo.config)
        MethodConfig (com.alibaba.dubbo.config)
```

## define
```java
public abstract class AbstractInterfaceConfig extends AbstractMethodConfig {

    private static final long serialVersionUID = -1559314110797223229L;

    // 服务接口的本地实现类名
    protected String local;

    // 服务接口的本地实现类名
    protected String stub;

    // 服务监控
    protected MonitorConfig monitor;

    // 代理类型
    protected String proxy;

    // 集群方式
    protected String cluster;

    // 过滤器
    protected String filter;

    // 监听器
    protected String listener;

    // 负责人
    protected String owner;

    // 连接数限制,0表示共享连接，否则为该服务独享连接数
    protected Integer connections;

    // 连接数限制
    protected String layer;

    // 应用信息
    protected ApplicationConfig application;

    // 模块信息
    protected ModuleConfig module;

    // 注册中心
    protected List<RegistryConfig> registries;
    // 连接事件
    protected String onconnect;
    // 断开事件
    protected String ondisconnect;
    // callback实例个数限制
    private Integer callbacks;
    // 服务暴露或引用的scope,如果为local，则表示只在当前JVM内查找.
    private String scope;
}    
```