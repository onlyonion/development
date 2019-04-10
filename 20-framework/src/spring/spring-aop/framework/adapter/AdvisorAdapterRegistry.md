org.springframework.aop.framework.adapter.AdvisorAdapterRegistry

## hierarchy
```
AdvisorAdapterRegistry (org.springframework.aop.framework.adapter)
    DefaultAdvisorAdapterRegistry (org.springframework.aop.framework.adapter)
```

## define
```plantuml
@startuml

''''''''''''''''''''''''适配器注册表''''''''''''''''''''''''
interface AdvisorAdapterRegistry {
    + Advisor wrap(Object advice)
    + MethodInterceptor[] getInterceptors(Advisor advisor)
    + void registerAdvisorAdapter(AdvisorAdapter adapter)
}

AdvisorAdapterRegistry ^.. DefaultAdvisorAdapterRegistry

class DefaultAdvisorAdapterRegistry {
    - final List<AdvisorAdapter> adapters
}
DefaultAdvisorAdapterRegistry o-- AdvisorAdapter

''''''''''''''''''''''''AdvisorAdapter''''''''''''''''''''''''
interface AdvisorAdapter {
    + boolean supportsAdvice(Advice advice)
	+ MethodInterceptor getInterceptor(Advisor advisor)
}

AdvisorAdapter ^.. AfterReturningAdviceAdapter
AdvisorAdapter ^.. MethodBeforeAdviceAdapter
AdvisorAdapter ^.. ThrowsAdviceAdapter

''''''''''''''''''''''''全局单例''''''''''''''''''''''''
abstract class GlobalAdvisorAdapterRegistry {
    - static AdvisorAdapterRegistry instance
}
GlobalAdvisorAdapterRegistry o-- DefaultAdvisorAdapterRegistry

@enduml
```