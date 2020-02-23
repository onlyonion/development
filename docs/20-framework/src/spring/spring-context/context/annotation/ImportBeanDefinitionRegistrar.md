org.springframework.context.annotation.ImportBeanDefinitionRegistrar

## hierarchy
```
ImportBeanDefinitionRegistrar (org.springframework.context.annotation)
    MapperScannerRegistrar (org.mybatis.spring.annotation)
    AutoProxyRegistrar (org.springframework.context.annotation)
    AspectJAutoProxyRegistrar (org.springframework.context.annotation)
    ServiceScanConfiguration (org.springframework.cloud.config.java)
```

## define
```java
public interface ImportBeanDefinitionRegistrar {

	public void registerBeanDefinitions(
			AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry);

}
```

## links
- [BeanDefinitionRegistry](/docs/20-framework/src/spring/spring-beans/factory/support/BeanDefinitionRegistry.md)