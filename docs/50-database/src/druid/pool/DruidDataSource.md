com.alibaba.druid.pool.DruidDataSource

* CountDownLatch
* volatile
* ThreadLocal
* AtomicLongFieldUpdater
* Future
* ScheduledFuture

## hierarchy
```
WrapperAdapter (com.alibaba.druid.pool)
    DruidAbstractDataSource (com.alibaba.druid.pool)
        DruidDataSource (com.alibaba.druid.pool)
            DruidQuartzConnectionProvider (com.alibaba.druid.support.quartz)
            DruidXADataSource (com.alibaba.druid.pool.xa)
DruidDataSource (com.alibaba.druid.pool)
    DruidAbstractDataSource (com.alibaba.druid.pool)
        WrapperAdapter (com.alibaba.druid.pool)
        DruidAbstractDataSourceMBean (com.alibaba.druid.pool)
        DataSource (javax.sql)
            CommonDataSource (javax.sql)
            Wrapper (java.sql)
        DataSourceProxy (com.alibaba.druid.proxy.jdbc)
        Serializable (java.io)
    DruidDataSourceMBean (com.alibaba.druid.pool)
        DruidAbstractDataSourceMBean (com.alibaba.druid.pool)
    ManagedDataSource (com.alibaba.druid.pool)
        DataSource (javax.sql)
            CommonDataSource (javax.sql)
            Wrapper (java.sql)
    Referenceable (javax.naming)
    Closeable (java.io)
        AutoCloseable (java.lang)
    Cloneable (java.lang)
    ConnectionPoolDataSource (javax.sql)
        CommonDataSource (javax.sql)
    MBeanRegistration (javax.management)
```