com.lmax.disruptor.util.DaemonThreadFactory

枚举单例模式

## define
```java
public enum DaemonThreadFactory implements ThreadFactory {
    INSTANCE;

    @Override
    public Thread newThread(final Runnable r)
    {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    }
}
```