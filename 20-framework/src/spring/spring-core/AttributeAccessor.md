org.springframework.core.AttributeAccessor

属性访问器、属性存取器

```java
public interface AttributeAccessor {
	void setAttribute(String name, Object value);
	Object getAttribute(String name);
	Object removeAttribute(String name);
	boolean hasAttribute(String name);
	String[] attributeNames();
}
```