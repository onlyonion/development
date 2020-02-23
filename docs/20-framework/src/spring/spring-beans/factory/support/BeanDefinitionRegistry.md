org.springframework.beans.factory.support.BeanDefinitionRegistry

## hierarchy
```
BeanDefinitionRegistry (org.springframework.beans.factory.support)
    SimpleBeanDefinitionRegistry (org.springframework.beans.factory.support)
    DefaultListableBeanFactory (org.springframework.beans.factory.support)
        XmlBeanFactory (org.springframework.beans.factory.xml)
    GenericApplicationContext (org.springframework.context.support)
        GenericXmlApplicationContext (org.springframework.context.support)
        StaticApplicationContext (org.springframework.context.support)
        GenericWebApplicationContext (org.springframework.web.context.support)
        ResourceAdapterApplicationContext (org.springframework.jca.context)
        GenericGroovyApplicationContext (org.springframework.context.support)
        AnnotationConfigApplicationContext (org.springframework.context.annotation)
```

## define
```java
public interface BeanDefinitionRegistry extends AliasRegistry {

	void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) throws BeanDefinitionStoreException;

	void removeBeanDefinition(String beanName) throws NoSuchBeanDefinitionException;

	BeanDefinition getBeanDefinition(String beanName) throws NoSuchBeanDefinitionException;

	boolean containsBeanDefinition(String beanName);

	String[] getBeanDefinitionNames();

	int getBeanDefinitionCount();

	boolean isBeanNameInUse(String beanName);

}
```