org.springframework.amqp.rabbit.connection.CachingConnectionFactory

## hierarchy
```
AbstractConnectionFactory (org.springframework.amqp.rabbit.connection)
    CachingConnectionFactory (org.springframework.amqp.rabbit.connection)
CachingConnectionFactory (org.springframework.amqp.rabbit.connection)
    AbstractConnectionFactory (org.springframework.amqp.rabbit.connection)
        ConnectionFactory (org.springframework.amqp.rabbit.connection)
        DisposableBean (org.springframework.beans.factory)
        BeanNameAware (org.springframework.beans.factory)
    InitializingBean (org.springframework.beans.factory)
    ShutdownListener (com.rabbitmq.client)
    ApplicationContextAware (org.springframework.context)
    ApplicationListener (org.springframework.context)
    PublisherCallbackChannelConnectionFactory (org.springframework.amqp.rabbit.connection)
    SmartLifecycle (org.springframework.context)
```


## inner class

### CachedChannelInvocationHandler
```java
    private final class CachedChannelInvocationHandler implements InvocationHandler {
        
    }
```

### ChannelCachingConnectionProxy

### CacheMode
```java
	public enum CacheMode {
		CHANNEL,
		CONNECTION
	}
```