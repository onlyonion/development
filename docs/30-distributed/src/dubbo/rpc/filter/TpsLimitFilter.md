com.alibaba.dubbo.rpc.filter.TpsLimitFilter


Dubbo的限流算法使用了最简单的计数器算法，如果并发流量刚好在上个计数器最后一秒和下个计数器第一秒来临，也不能完全预防突发流量，
所以推荐自己使用令牌桶算法或漏桶算法实现自定义限流Filter，并且也可以考虑分布式限流。

## define
- 服务提供者

```java
/**
 * 限制 service 或方法的 tps.
 *
 * @author <a href="mailto:gang.lvg@alibaba-inc.com">kimi</a>
 */
@Activate(group = Constants.PROVIDER, value = Constants.TPS_LIMIT_RATE_KEY)
public class TpsLimitFilter implements Filter {

    private final TPSLimiter tpsLimiter = new DefaultTPSLimiter();

    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

        if (!tpsLimiter.isAllowable(invoker.getUrl(), invocation)) {
            throw new RpcException(
                    new StringBuilder(64)
                            .append("Failed to invoke service ")
                            .append(invoker.getInterface().getName())
                            .append(".")
                            .append(invocation.getMethodName())
                            .append(" because exceed max service tps.")
                            .toString());
        }

        return invoker.invoke(invocation);
    }

}
```

## DefaultTPSLimiter
```java

public interface TPSLimiter {

    /**
     * 根据 tps 限流规则判断是否限制此次调用.
     *
     * @param url        url
     * @param invocation invocation
     * @return true 则允许调用，否则不允许
     */
    boolean isAllowable(URL url, Invocation invocation);

}

public class DefaultTPSLimiter implements TPSLimiter {

    private final ConcurrentMap<String, StatItem> stats
            = new ConcurrentHashMap<String, StatItem>();

    public boolean isAllowable(URL url, Invocation invocation) {
        int rate = url.getParameter(Constants.TPS_LIMIT_RATE_KEY, -1);
        long interval = url.getParameter(Constants.TPS_LIMIT_INTERVAL_KEY, // tps.interval
                Constants.DEFAULT_TPS_LIMIT_INTERVAL); // 60 * 1000 即60秒，1分钟
        String serviceKey = url.getServiceKey();
        if (rate > 0) {
            StatItem statItem = stats.get(serviceKey);
            if (statItem == null) {
                stats.putIfAbsent(serviceKey,
                        new StatItem(serviceKey, rate, interval));
                statItem = stats.get(serviceKey);
            }
            return statItem.isAllowable(url, invocation);
        } else {
            StatItem statItem = stats.get(serviceKey);
            if (statItem != null) {
                stats.remove(serviceKey);
            }
        }

        return true;
    }

}
```

## StatItem

```java
class StatItem {

    private String name;

    private long lastResetTime;

    private long interval;

    private AtomicInteger token;

    private int rate;

    StatItem(String name, int rate, long interval) {
        this.name = name;
        this.rate = rate;
        this.interval = interval;
        this.lastResetTime = System.currentTimeMillis();
        this.token = new AtomicInteger(rate);
    }

    public boolean isAllowable(URL url, Invocation invocation) {
        long now = System.currentTimeMillis();
        // 若到达下一个周期，恢复可用令牌数，设置最后重置时间
        if (now > lastResetTime + interval) {
            token.set(rate);
            lastResetTime = now;
        }

        int value = token.get();
        boolean flag = false;
        while (value > 0 && !flag) {
            // 取令牌
            flag = token.compareAndSet(value, value - 1);
            value = token.get();
        }

        return flag;
    }

    long getLastResetTime() {
        return lastResetTime;
    }

    int getToken() {
        return token.get();
    }

}
```
