com.alibaba.dubbo.remoting.transport.dispatcher.WrappedChannelHandler
## hierarchy
```
WrappedChannelHandler (com.alibaba.dubbo.remoting.transport.dispatcher)
    AllChannelHandler (com.alibaba.dubbo.remoting.transport.dispatcher.all)
    ConnectionOrderedChannelHandler (com.alibaba.dubbo.remoting.transport.dispatcher.connection)
    ExecutionChannelHandler (com.alibaba.dubbo.remoting.transport.dispatcher.execution)
    MessageOnlyChannelHandler (com.alibaba.dubbo.remoting.transport.dispatcher.message)
```

## define
```java
public class WrappedChannelHandler implements ChannelHandlerDelegate {
    protected static final Logger logger = LoggerFactory.getLogger(WrappedChannelHandler.class);
    protected static final ExecutorService SHARED_EXECUTOR = Executors.newCachedThreadPool(new NamedThreadFactory("DubboSharedHandler", true));
    protected final ExecutorService executor;
    protected final ChannelHandler handler;
    protected final URL url;
}    
```

## methods

### WrappedChannelHandler
```java
    public WrappedChannelHandler(ChannelHandler handler, URL url) {
        this.handler = handler;
        this.url = url;
        executor = (ExecutorService) ExtensionLoader.getExtensionLoader(ThreadPool.class).getAdaptiveExtension().getExecutor(url);

        String componentKey = Constants.EXECUTOR_SERVICE_COMPONENT_KEY;
        if (Constants.CONSUMER_SIDE.equalsIgnoreCase(url.getParameter(Constants.SIDE_KEY))) {
            componentKey = Constants.CONSUMER_SIDE;
        }
        DataStore dataStore = ExtensionLoader.getExtensionLoader(DataStore.class).getDefaultExtension();
        dataStore.put(componentKey, Integer.toString(url.getPort()), executor);
    }
```