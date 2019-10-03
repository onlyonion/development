org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource

## define

```java
public abstract class AbstractRoutingDataSource extends AbstractDataSource implements InitializingBean {
	private Map<Object, Object> targetDataSources;
	private Object defaultTargetDataSource;
	private boolean lenientFallback = true;
	private DataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
	private Map<Object, DataSource> resolvedDataSources;
	private DataSource resolvedDefaultDataSource;
}
```