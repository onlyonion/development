org.springframework.transaction.interceptor.TransactionInterceptor

## hierarchy
```
TransactionAspectSupport (org.springframework.transaction.interceptor)
    TransactionInterceptor (org.springframework.transaction.interceptor)

// extends 
TransactionInterceptor (org.springframework.transaction.interceptor)
    MethodInterceptor (org.aopalliance.intercept)
        Interceptor (org.aopalliance.intercept)
            Advice (org.aopalliance.aop)
    Serializable (java.io)
    TransactionAspectSupport (org.springframework.transaction.interceptor)
        BeanFactoryAware (org.springframework.beans.factory)
            Aware (org.springframework.beans.factory)
        InitializingBean (org.springframework.beans.factory)
        Object (java.lang)
```

## define

```plantuml
@startuml

'''''''''''''接口''''''''''''''
interface Advice
interface Interceptor
interface MethodInterceptor
Advice ^-- Interceptor
Interceptor ^-- MethodInterceptor
MethodInterceptor ^.. TransactionInterceptor

'''''''''''''事务切面支撑''''''''''''''
abstract class TransactionAspectSupport {
    - PlatformTransactionManager transactionManager
	- TransactionAttributeSource transactionAttributeSource
	- BeanFactory beanFactory
}
TransactionAspectSupport ^-- TransactionInterceptor

'''''''''''''事务拦截器''''''''''''''
class TransactionInterceptor {
    + Object invoke(final MethodInvocation invocation)
}

@enduml
```