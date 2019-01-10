org.springframework.boot.context.embedded.EmbeddedServletContainer


```java
public interface EmbeddedServletContainer {
	void start() throws EmbeddedServletContainerException;
	void stop() throws EmbeddedServletContainerException;
	int getPort();
}
```