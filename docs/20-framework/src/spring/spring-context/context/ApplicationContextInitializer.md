org.springframework.context.ApplicationContextInitializer

## hierarchy
```
ApplicationContextInitializer (org.springframework.context)
    ParentContextApplicationContextInitializer (org.springframework.boot.builder)
    ConditionEvaluationReportLoggingListener (org.springframework.boot.autoconfigure.logging)
    DelegatingApplicationContextInitializer (org.springframework.boot.context.config)
    ServerPortInfoApplicationContextInitializer (org.springframework.boot.web.context)
    ServletContextApplicationContextInitializer (org.springframework.boot.web.servlet.support)
    SharedMetadataReaderFactoryContextInitializer (org.springframework.boot.autoconfigure)
    ContextIdApplicationContextInitializer (org.springframework.boot.context)
    ConfigurationWarningsApplicationContextInitializer (org.springframework.boot.context)
    BeanDefinitionDsl (org.springframework.context.support)
```

## define
```java
public interface ApplicationContextInitializer<C extends ConfigurableApplicationContext> {
	void initialize(C applicationContext);
}
```