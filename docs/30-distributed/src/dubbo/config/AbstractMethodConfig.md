com.alibaba.dubbo.config.AbstractMethodConfig

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
public abstract class AbstractMethodConfig extends AbstractConfig {

    private static final long serialVersionUID = 1L;

    // 远程调用超时时间(毫秒)
    protected Integer timeout;

    // 重试次数
    protected Integer retries;

    // 最大并发调用
    protected Integer actives;

    // 负载均衡
    protected String loadbalance;

    // 是否异步
    protected Boolean async;

    // 异步发送是否等待发送成功
    protected Boolean sent;

    // 服务接口的失败mock实现类名
    protected String mock;

    // 合并器
    protected String merger;

    // 服务接口的失败mock实现类名
    protected String cache;

    // 服务接口的失败mock实现类名
    protected String validation;

    // 自定义参数
    protected Map<String, String> parameters;
}

```

## MethodConfig
```java
public class MethodConfig extends AbstractMethodConfig {

    private static final long serialVersionUID = 884908855422675941L;

    // 方法名
    private String name;

    // 统计参数
    private Integer stat;

    // 是否重试
    private Boolean retry;

    // 是否为可靠异步
    private Boolean reliable;

    // 方法使用线程数限制
    private Integer executes;

    // 是否过时
    private Boolean deprecated;

    // 是否需要开启stiky策略
    private Boolean sticky;

    // 是否需要返回
    private Boolean isReturn;

    //异步调用回调实例
    private Object oninvoke;

    //异步调用回调方法
    private String oninvokeMethod;

    //异步调用回调实例
    private Object onreturn;

    //异步调用回调方法
    private String onreturnMethod;

    //异步调用异常回调实例
    private Object onthrow;

    //异步调用异常回调方法
    private String onthrowMethod;

    private List<ArgumentConfig> arguments;
}    
```