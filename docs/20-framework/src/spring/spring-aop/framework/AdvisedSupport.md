org.springframework.aop.framework.AdvisedSupport

## package
```
ProxyConfig (org.springframework.aop.framework)
    AdvisedSupport (org.springframework.aop.framework)
        ProxyCreatorSupport (org.springframework.aop.framework)
            ProxyFactoryBean (org.springframework.aop.framework)
            ProxyFactory (org.springframework.aop.framework)
            AspectJProxyFactory (org.springframework.aop.aspectj.annotation)
```

## define

```plantuml
@startuml

''''''''''''''''''''''''''''''''' AdvisedSupport '''''''''''''''''''''''''''''''''''
class ProxyConfig
interface Advised 
class AdvisedSupport {
    AdvisorChainFactory advisorChainFactory
    + List<Object> getInterceptorsAndDynamicInterceptionAdvice(Method method, Class<?> targetClass)
}

ProxyConfig ^-- AdvisedSupport
Advised ^.. AdvisedSupport

''''''''''''''''''''''''''''''''' AdvisorChainFactory '''''''''''''''''''''''''''''''''''
interface AdvisorChainFactory
class DefaultAdvisorChainFactory {

}
AdvisorChainFactory ^.. DefaultAdvisorChainFactory
AdvisedSupport o-- DefaultAdvisorChainFactory

''''''''''''''''''''''''''''''''' AdvisorAdapterRegistry '''''''''''''''''''''''''''''''''''
interface AdvisorAdapterRegistry {

}
class DefaultAdvisorAdapterRegistry {

}
DefaultAdvisorChainFactory ..> AdvisorAdapterRegistry
AdvisorAdapterRegistry ^.. DefaultAdvisorAdapterRegistry


@enduml
```
