org.springframework.web.servlet.HandlerExecutionChain


## 定义
处理器执行链，持有拦截器的集合。

```java
public class HandlerExecutionChain {
	// 字段
	private final Object handler;
	private HandlerInterceptor[] interceptors;
	private List<HandlerInterceptor> interceptorList;
	private int interceptorIndex = -1;

	// 方法略
}
```

