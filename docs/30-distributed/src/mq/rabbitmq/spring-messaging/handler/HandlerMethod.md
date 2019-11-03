org.springframework.messaging.handler.HandlerMethod

## hierarchy
```
HandlerMethod (org.springframework.messaging.handler)
    InvocableHandlerMethod (org.springframework.messaging.handler.invocation)
```

## define
```java
public class HandlerMethod {
	/** Logger that is available to subclasses */
	protected final Log logger = LogFactory.getLog(getClass());
	private final Object bean;
	private final BeanFactory beanFactory;
	private final Class<?> beanType;
	private final Method method;
	private final Method bridgedMethod;
	private final MethodParameter[] parameters;
	private HandlerMethod resolvedFromHandlerMethod;
}	
```