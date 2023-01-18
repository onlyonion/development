ribbon

```
Gradle: com.netflix.ribbon:ribbon:2.3.0
Gradle: com.netflix.ribbon:ribbon-core:2.3.0
Gradle: com.netflix.ribbon:ribbon-eureka:2.3.0
Gradle: com.netflix.ribbon:ribbon-httpclient:2.3.0
Gradle: com.netflix.ribbon:ribbon-loadbalancer:2.3.0
Gradle: com.netflix.ribbon:ribbon-transport:2.3.0
```

```
Gradle: org.springframework.cloud:spring-cloud-netflix-ribbon:2.2.3.RELEASE
Gradle: org.springframework.cloud:spring-cloud-starter-netflix-ribbon:2.2.3.RELEASE
Gradle: org.springframework.cloud:spring-cloud-starter-loadbalancer:2.2.3.RELEASE
```


```yaml
ribbon:
  ##每个host的最大连接数
  MaxConnectionPerHost: 100
  ##ribbon的最大连接数
  MaxTotalConnections: 1000
  #ribbon的最大工作线程数
  PoolMaxThreads: 1000
  ConnectTimeout: 2000
  ReadTimeout: 10000
```
