org.springframework.aop.framework.adapter.AdvisorAdapter

## hierarchy
```
AdvisorAdapter (org.springframework.aop.framework.adapter)
    MethodBeforeAdviceAdapter (org.springframework.aop.framework.adapter)
    ThrowsAdviceAdapter (org.springframework.aop.framework.adapter)
    AfterReturningAdviceAdapter (org.springframework.aop.framework.adapter)
```

## define

```plantuml
@startuml

''''''''''''''''''''''''AdvisorAdapter''''''''''''''''''''''''
interface AdvisorAdapter {
    + boolean supportsAdvice(Advice advice)
	+ MethodInterceptor getInterceptor(Advisor advisor)
}

AdvisorAdapter ^.. AfterReturningAdviceAdapter
AdvisorAdapter ^.. MethodBeforeAdviceAdapter
AdvisorAdapter ^.. ThrowsAdviceAdapter

interface MethodInterceptor
AdvisorAdapter ..> MethodInterceptor
MethodInterceptor ^.. AfterReturningAdviceInterceptor
MethodInterceptor ^.. MethodBeforeAdviceInterceptor
MethodInterceptor ^.. ThrowsAdviceInterceptor

''''''''''''''''''''''''实现类''''''''''''''''''''''''
class AfterReturningAdviceAdapter
class AfterReturningAdviceInterceptor
AfterReturningAdviceAdapter ..> AfterReturningAdviceInterceptor

class MethodBeforeAdviceAdapter
class MethodBeforeAdviceInterceptor
MethodBeforeAdviceAdapter ..> MethodBeforeAdviceInterceptor

class ThrowsAdviceAdapter 
class ThrowsAdviceInterceptor
ThrowsAdviceAdapter ..> ThrowsAdviceInterceptor


@enduml
```