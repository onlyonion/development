com.alibaba.dubbo.rpc.cluster.loadbalance.LeastActiveLoadBalance

## hierarchy
```
AbstractLoadBalance (com.alibaba.dubbo.rpc.cluster.loadbalance)
    RandomLoadBalance (com.alibaba.dubbo.rpc.cluster.loadbalance)
    LeastActiveLoadBalance (com.alibaba.dubbo.rpc.cluster.loadbalance)
    RoundRobinLoadBalance (com.alibaba.dubbo.rpc.cluster.loadbalance)
    ConsistentHashLoadBalance (com.alibaba.dubbo.rpc.cluster.loadbalance)
```

## define
```java
public class LeastActiveLoadBalance extends AbstractLoadBalance {
    public static final String NAME = "leastactive";
    private final Random random = new Random();
}
```

```java
    int active = RpcStatus.getStatus(invoker.getUrl(), invocation.getMethodName()).getActive(); // 活跃数
    int weight = invoker.getUrl().getMethodParameter(invocation.getMethodName(), Constants.WEIGHT_KEY, Constants.DEFAULT_WEIGHT); // 权重
```