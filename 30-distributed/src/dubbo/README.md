
SE导引目路集衡
 
## dubbo package
```
cache       缓存，分布式缓存
common      基础设施
    bytecode
    compiler
    extension
    io
    json
    logger
    serialize
    threadpool
config      配置中心
    ReferenceConfig
    ServiceConfig
container   容器、应用、上下文
monitor     监控中心
registry    注册中心
remoting    远程调用，网络通信
    exchange
    transport
rpc         远程过程调用
    cluster     集群容错
    filter      管道过滤器
    listener    事件监听
    protocol    网络协议
    proxy       代理
    service     业务逻辑
validation
 ```
 ## 分层
 
 * config 接口层、配置层、容器
 * rpc
    - Invoker 调用处理
    - Filter 过滤器链 模拟数据、协议处理、同步异步处理、异常处理
    - Cluster(join)、Director(list)、Router(route)、LoadBalance(select)
    - proxy 动态代理
 * remoting 
    - 信息交换
    - 传输层 通道与通道处理器
    - I/O处理 read(), write()
    - 序列化
 
 ## service
 
 ## reference