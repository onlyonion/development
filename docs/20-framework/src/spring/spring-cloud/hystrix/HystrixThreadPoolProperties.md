com.netflix.hystrix.HystrixThreadPoolProperties

## define
```java
public abstract class HystrixThreadPoolProperties {

    /* defaults */
    static int default_coreSize = 10;            // core size of thread pool
    static int default_maximumSize = 10;         // maximum size of thread pool
    private final HystrixProperty<Boolean> allowMaximumSizeToDivergeFromCoreSize;

    //it only gets applied if allowMaximumSizeToDivergeFromCoreSize is true
}
```

## methods
```java
    protected HystrixThreadPoolProperties(HystrixThreadPoolKey key, Setter builder, String propertyPrefix) {
        this.allowMaximumSizeToDivergeFromCoreSize = getProperty(propertyPrefix, key, "allowMaximumSizeToDivergeFromCoreSize",
                builder.getAllowMaximumSizeToDivergeFromCoreSize(), default_allow_maximum_size_to_diverge_from_core_size);

        this.corePoolSize = getProperty(propertyPrefix, key, "coreSize", builder.getCoreSize(), default_coreSize);
        //this object always contains a reference to the configuration value for the maximumSize of the threadpool
        //it only gets applied if allowMaximumSizeToDivergeFromCoreSize is true
        this.maximumPoolSize = getProperty(propertyPrefix, key, "maximumSize", builder.getMaximumSize(), default_maximumSize);

        this.keepAliveTime = getProperty(propertyPrefix, key, "keepAliveTimeMinutes", builder.getKeepAliveTimeMinutes(), default_keepAliveTimeMinutes);
        this.maxQueueSize = getProperty(propertyPrefix, key, "maxQueueSize", builder.getMaxQueueSize(), default_maxQueueSize);
        this.queueSizeRejectionThreshold = getProperty(propertyPrefix, key, "queueSizeRejectionThreshold", builder.getQueueSizeRejectionThreshold(), default_queueSizeRejectionThreshold);
        this.threadPoolRollingNumberStatisticalWindowInMilliseconds = getProperty(propertyPrefix, key, "metrics.rollingStats.timeInMilliseconds", builder.getMetricsRollingStatisticalWindowInMilliseconds(), default_threadPoolRollingNumberStatisticalWindow);
        this.threadPoolRollingNumberStatisticalWindowBuckets = getProperty(propertyPrefix, key, "metrics.rollingStats.numBuckets", builder.getMetricsRollingStatisticalWindowBuckets(), default_threadPoolRollingNumberStatisticalWindowBuckets);
    }

```