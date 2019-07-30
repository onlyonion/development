org.apache.rocketmq.remoting.RemotingService

## hierarchy
```
RemotingService (org.apache.rocketmq.remoting)
    RemotingClient (org.apache.rocketmq.remoting)
        NettyRemotingClient (org.apache.rocketmq.remoting.netty)
    RemotingServer (org.apache.rocketmq.remoting)
        NettyRemotingServer (org.apache.rocketmq.remoting.netty)
```

## define
```java
public interface RemotingService {
    void start();
    void shutdown();
    void registerRPCHook(RPCHook rpcHook);
}

public interface RPCHook {
    void doBeforeRequest(final String remoteAddr, final RemotingCommand request);

    void doAfterResponse(final String remoteAddr, final RemotingCommand request,
        final RemotingCommand response);
}
```
