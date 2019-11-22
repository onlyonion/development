com.orbitz.consul

- async 异步回调
- cache 缓存
- model 模型
- option
- util

## package
```
async                           异步回调机制
    Callback
    ConsulResponseCallback
    EventResponseCallback
cache
    ConsulCache
    CacheDescriptor
config
    CacheConfig
    ClientConfig
model
    acl         访问控制
    agent       代理
    catalog     类别
    coordinate  协调
    event       事件
    health      健康检查
    kv          键值
    operator    操作
    query
    session
    ConsulResponse
    EventResponse
    ImmutableEventResponse
    State
monitoring
    ClientEventHandler
    ClientEventCallback
option
util
    bookend
    failover
AclClient
AgentClient
BaseClient
CatalogClient
Consul
ConsulException
CoordinateClient
EventClient
HealthClient
KeyValueClient
NotRegisteredException
OperatorClient
PreparedQueryClient
SessionClient
SnapshotClient
StatusClient
```

## dependecy
- retrofit2
- okhttp3