
## 1. 动态代理

### 1.1 类图
```yuml
// {type:class}

// 代理类
[Proxy]^-[$Proxy0] 
[Proxy]++-[InvocationHandler]

// 调用处理器
[InvocationHandler]^-.-[Enhanced]
[Enhanced]++-[Target]

// 主题接口
[Subject]^-.-[$Proxy0]
[Subject]^-.-[Target]
```

### 1.2 序列图
```mermaid
sequenceDiagram

    %% 生成代理
    Actor->>Proxy:newProxyInstance(loader, interfaces, h)
    Proxy-->>Actor:返回$Proxy0
    
    %% 委托调用
    Actor->>$Proxy0: request(args)
    $Proxy0->>InvocationHandler:invoke(this, m3, args)
    InvocationHandler->>Enhanced:invoke(this, m3, args)
    
    %% 增强处理
    Enhanced->>Enhanced:beforeRequest(args)
    Enhanced->>Enhanced:反射调用目标对象方法request()，invoke(this, m3, args)
    Enhanced->>Enhanced:afterRequest(args)
    
    Enhanced-->>$Proxy0:返回结果
    $Proxy0-->>Actor:返回结果
```