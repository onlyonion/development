org.springframework.context.event.SimpleApplicationEventMulticaster

## hierarchy
```
ApplicationEventMulticaster (org.springframework.context.event)
    AbstractApplicationEventMulticaster (org.springframework.context.event)
        SimpleApplicationEventMulticaster (org.springframework.context.event)
```

## methods
```java

	@Override
	public void multicastEvent(final ApplicationEvent event, @Nullable ResolvableType eventType) {
		ResolvableType type = (eventType != null ? eventType : resolveDefaultEventType(event));
		Executor executor = getTaskExecutor();
		for (ApplicationListener<?> listener : getApplicationListeners(event, type)) {
			if (executor != null) {
				executor.execute(() -> invokeListener(listener, event));
			}
			else {
				invokeListener(listener, event);
			}
		}
	}

```