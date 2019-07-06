org.springframework.boot.autoconfigure.transaction
## package
```
jta
    AtomikosJtaConfiguration
    BitronixJtaConfiguration
    JndiJtaConfiguration
    JtaAutoConfiguration
    JtaProperties
    NarayanaJtaConfiguration
PlatformTransactionManagerCustomizer
TransactionAutoConfiguration
TransactionManagerCustomizers
TransactionProperties
```


## overview
* Properties
* AutoConfiguration
* spring.factories

### Properties
```java

@ConfigurationProperties(prefix = "spring.transaction")
public class TransactionProperties implements
		PlatformTransactionManagerCustomizer<AbstractPlatformTransactionManager> {
	@DurationUnit(ChronoUnit.SECONDS)
	private Duration defaultTimeout;
	private Boolean rollbackOnCommitFailure;

	public Duration getDefaultTimeout() {
		return this.defaultTimeout;
	}

	public void setDefaultTimeout(Duration defaultTimeout) {
		this.defaultTimeout = defaultTimeout;
	}

	public Boolean getRollbackOnCommitFailure() {
		return this.rollbackOnCommitFailure;
	}

	public void setRollbackOnCommitFailure(Boolean rollbackOnCommitFailure) {
		this.rollbackOnCommitFailure = rollbackOnCommitFailure;
	}

	@Override
	public void customize(AbstractPlatformTransactionManager transactionManager) {
		if (this.defaultTimeout != null) {
			transactionManager.setDefaultTimeout((int) this.defaultTimeout.getSeconds());
		}
		if (this.rollbackOnCommitFailure != null) {
			transactionManager.setRollbackOnCommitFailure(this.rollbackOnCommitFailure);
		}
	}

}
```
### AutoConfiguration
```java
@Configuration
@ConditionalOnClass(PlatformTransactionManager.class)
@AutoConfigureAfter({ JtaAutoConfiguration.class, HibernateJpaAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class,
		Neo4jDataAutoConfiguration.class })
@EnableConfigurationProperties(TransactionProperties.class)
public class TransactionAutoConfiguration {

	@Bean
	@ConditionalOnMissingBean
	public TransactionManagerCustomizers platformTransactionManagerCustomizers(
			ObjectProvider<List<PlatformTransactionManagerCustomizer<?>>> customizers) {
		return new TransactionManagerCustomizers(customizers.getIfAvailable());
	}

	@Configuration
	@ConditionalOnSingleCandidate(PlatformTransactionManager.class)
	public static class TransactionTemplateConfiguration {

		private final PlatformTransactionManager transactionManager;

		public TransactionTemplateConfiguration(
				PlatformTransactionManager transactionManager) {
			this.transactionManager = transactionManager;
		}

		@Bean
		@ConditionalOnMissingBean
		public TransactionTemplate transactionTemplate() {
			return new TransactionTemplate(this.transactionManager);
		}

	}

	@Configuration
	@ConditionalOnBean(PlatformTransactionManager.class)
	@ConditionalOnMissingBean(AbstractTransactionManagementConfiguration.class)
	public static class EnableTransactionManagementConfiguration {

		@Configuration
		@EnableTransactionManagement(proxyTargetClass = false)
		@ConditionalOnProperty(prefix = "spring.aop", name = "proxy-target-class", havingValue = "false", matchIfMissing = false)
		public static class JdkDynamicAutoProxyConfiguration {

		}

		@Configuration
		@EnableTransactionManagement(proxyTargetClass = true)
		@ConditionalOnProperty(prefix = "spring.aop", name = "proxy-target-class", havingValue = "true", matchIfMissing = true)
		public static class CglibAutoProxyConfiguration {

		}

	}

}
```
### spring.factories
```
# Auto Configure
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration
```
