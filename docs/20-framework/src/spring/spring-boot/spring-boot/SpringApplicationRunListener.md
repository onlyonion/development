org.springframework.boot.SpringApplicationRunListener

## package
```
SpringApplicationRunListener (org.springframework.boot)
    EventPublishingRunListener (org.springframework.boot.context.event)
```

## define
```java
public interface SpringApplicationRunListener {
	void starting();
	// 环境准备
	void environmentPrepared(ConfigurableEnvironment environment); 
	// 上下文准备、加载
	void contextPrepared(ConfigurableApplicationContext context);
	void contextLoaded(ConfigurableApplicationContext context);
	void started(ConfigurableApplicationContext context); // srping-boot-2
	void running(ConfigurableApplicationContext context); // srping-boot-2
	void failed(ConfigurableApplicationContext context, Throwable exception);
}
```

```plantuml
@startuml

interface SpringApplicationRunListener
class EventPublishingRunListener

SpringApplicationRunListener ^.. EventPublishingRunListener 

'''''''''''''''''''''''''事件发布运行监听''''''''''''''''''''''''''''''
EventPublishingRunListener *-- SpringApplication
EventPublishingRunListener *-- SimpleApplicationEventMulticaster

class SpringApplication
class SimpleApplicationEventMulticaster 

SimpleApplicationEventMulticaster *-- Executor

@enduml
```