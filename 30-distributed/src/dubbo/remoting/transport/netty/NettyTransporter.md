


```java
public class NettyTransporter implements Transporter {

    public static final String NAME = "netty";
    
    // 服务端绑定
    public Server bind(URL url, ChannelHandler listener) throws RemotingException {
        return new NettyServer(url, listener);
    }

    // 连接服务端
    public Client connect(URL url, ChannelHandler listener) throws RemotingException {
        return new NettyClient(url, listener);
    }

}
```