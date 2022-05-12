ribbon


com.netflix.client
```
config
    CommonClientConfigKey
    DefaultClientConfigImpl
http
ssl
util
ClientException
ClientRequest
DefaultLoadBalancerRetryHandler
IClient
IClientConfigAware
IResponse
RequestSpecificRetryHandler
RetryHandler
SimpleVipAddressResolver
Utils
VipAddressResolver
```

```conf
# 同一实例最大重试次数，不包括首次调用
ribbon.MaxAutoRetries=1
# 重试其他实例的最大重试次数，不包括首次所选的server
ribbon.MaxAutoRetriesNextServer= 2
# 是否所有操作都进行重试
ribbon.OkToRetryOnAllOperations=true
```