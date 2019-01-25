org.springframework.core.AliasRegistry

别名注册表

```java
public interface AliasRegistry {
	void registerAlias(String name, String alias);
	void removeAlias(String alias);
	boolean isAlias(String name);
	String[] getAliases(String name);
}
```