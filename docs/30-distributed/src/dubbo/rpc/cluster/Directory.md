com.alibaba.dubbo.rpc.cluster.Directory
## hierarchy
```
Directory (com.alibaba.dubbo.rpc.cluster)
    AbstractDirectory (com.alibaba.dubbo.rpc.cluster.directory)
        StaticDirectory (com.alibaba.dubbo.rpc.cluster.directory)
        RegistryDirectory (com.alibaba.dubbo.registry.integration)
```

## define
```java
public interface Directory<T> extends Node {
    Class<T> getInterface();
    List<Invoker<T>> list(Invocation invocation) throws RpcException;
}
```