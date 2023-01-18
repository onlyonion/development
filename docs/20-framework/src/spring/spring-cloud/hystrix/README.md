hysrix

```
Maven: com.netflix.hystrix:hystrix-core:1.5.18
Maven: com.netflix.hystrix:hystrix-javanica:1.5.18
Maven: com.netflix.hystrix:hystrix-metrics-event-stream:1.5.18
Maven: com.netflix.hystrix:hystrix-serialization:1.5.18

Maven: org.springframework.cloud:spring-cloud-starter-netflix-hystrix:2.2.9.RELEASE
Maven: org.springframework.cloud:spring-cloud-netflix-hystrix:2.2.9.RELEASE
Maven: io.github.openfeign:feign-hystrix:10.12
```


### config
https://blog.csdn.net/fengyuyeguirenenen/article/details/124813532

```yml
hystrix:
  command:
    default:
      execution:
        isolation:
          thread:
            timeoutInMilliseconds: 10000
          strategy: THREAD #SEMAPHORE
  threadpool:
    default:
      coreSize: 200
      maximumSize: 200
      allowMaximumSizeToDivergeFromCoreSize: true
```