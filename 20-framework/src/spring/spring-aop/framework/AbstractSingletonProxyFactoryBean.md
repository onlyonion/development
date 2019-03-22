org.springframework.aop.framework.AbstractSingletonProxyFactoryBean

## define

```plantuml
@startuml
class ProxyConfig

abstract class AbstractSingletonProxyFactoryBean {
    - AdvisorAdapterRegistry advisorAdapterRegistry
    # abstract Object createMainInterceptor()
}

@enduml
```