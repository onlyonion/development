
## define
TCP协议：端对端的、面向字节流、面向连接的、全双工可靠的传输层协议

### AbstractEndpoint
```java
public abstract class AbstractEndpoint<S> {
    protected Acceptor[] acceptors;
    private int maxConnections = 10000;
    private Executor executor = null;
    
    private int port;
    private InetAddress address;
    
    private int maxThreads = 200;
    
    private Handler<S> handler = null;
}
```


```
AbstractEndpoint (org.apache.tomcat.util.net)
    AprEndpoint (org.apache.tomcat.util.net)
    AbstractJsseEndpoint (org.apache.tomcat.util.net)
        NioEndpoint (org.apache.tomcat.util.net)
        Nio2Endpoint (org.apache.tomcat.util.net)
```

### AbstractEndpoint.Handler

```
Handler in AbstractEndpoint (org.apache.tomcat.util.net)
    ConnectionHandler in AbstractProtocol (org.apache.coyote)
```

### AbstractEndpoint.Acceptor
```
Acceptor in AbstractEndpoint (org.apache.tomcat.util.net)
    Acceptor in Nio2Endpoint (org.apache.tomcat.util.net)
    Acceptor in AprEndpoint (org.apache.tomcat.util.net)
    Acceptor in NioEndpoint (org.apache.tomcat.util.net)
```