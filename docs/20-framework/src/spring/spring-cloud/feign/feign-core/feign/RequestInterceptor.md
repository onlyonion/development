feign.RequestInterceptor

## Hierarchy
```
RequestInterceptor (feign)
    BasicAuthRequestInterceptor (feign.auth)
    BaseRequestInterceptor (org.springframework.cloud.netflix.feign.encoding)
        FeignAcceptGzipEncodingInterceptor (org.springframework.cloud.netflix.feign.encoding)
        FeignContentGzipEncodingInterceptor (org.springframework.cloud.netflix.feign.encoding)
    BaseRequestInterceptor (org.springframework.cloud.openfeign.encoding)
        FeignAcceptGzipEncodingInterceptor (org.springframework.cloud.openfeign.encoding)
        FeignContentGzipEncodingInterceptor (org.springframework.cloud.openfeign.encoding)
```

## Define
```plantuml
@startuml

interface RequestInterceptor

@enduml
```