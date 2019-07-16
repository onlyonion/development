com.alibaba.druid.pool.DruidAbstractDataSource

* volatile
* ScheduledExecutorService
* CopyOnWriteArrayList
* AtomicIntegerFieldUpdater.newUpdater(DruidAbstractDataSource.class, "createErrorCount");

## hierarchy
```
WrapperAdapter (com.alibaba.druid.pool)
    DruidAbstractDataSource (com.alibaba.druid.pool)
        DruidDataSource (com.alibaba.druid.pool)
            DruidQuartzConnectionProvider (com.alibaba.druid.support.quartz)
            DruidXADataSource (com.alibaba.druid.pool.xa)
DruidAbstractDataSource (com.alibaba.druid.pool)
    WrapperAdapter (com.alibaba.druid.pool)
    DruidAbstractDataSourceMBean (com.alibaba.druid.pool)
    DataSource (javax.sql)
    DataSourceProxy (com.alibaba.druid.proxy.jdbc)
    Serializable (java.io)
```