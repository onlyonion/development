Maven: org.springframework.cloud:spring-cloud-context:2.2.9.RELEASE


org.springframework.cloud
```
autoconfigure
bootstrap
context
    refresh
        ContextRefresher
    restart
    scope
        refresh
            RefreshScope
            RefreshScopeRefreshedEvent
        thread
            ThreadLocalScopeCache
            ThreadScope
        GenericScope
        ScopeCache
        StandardScopeCache
endpoint
    event
        RefreshEvent
        RefreshEventListener
    RefreshEndpoint
env
health
logging
util
```

### RefreshScope
```yaml
management:
  endpoint:
    refresh:
      enabled: true #默认true
  endpoints:
    web:
      exposure:
        include: '*' #默认情况下只对外暴露了health和info端点
```
