org.springframework.core.io.ResourceLoader
org.springframework.context.support.AbstractApplicationContext

```
ResourceLoader
    DefaultResourceLoader
```

```java
public interface ResourceLoader {
	String CLASSPATH_URL_PREFIX = ResourceUtils.CLASSPATH_URL_PREFIX;
	Resource getResource(String location);
	ClassLoader getClassLoader();
}
```