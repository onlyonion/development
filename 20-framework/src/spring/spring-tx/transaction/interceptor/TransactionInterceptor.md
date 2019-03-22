org.springframework.transaction.interceptor.TransactionInterceptor

## hierarchy
```
TransactionAspectSupport (org.springframework.transaction.interceptor)
    TransactionInterceptor (org.springframework.transaction.interceptor)
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

'''''''''''''基类''''''''''''''
abstract class TransactionAspectSupport {
    - PlatformTransactionManager transactionManager
	- TransactionAttributeSource transactionAttributeSource
	- BeanFactory beanFactory
}
TransactionAspectSupport ^-- TransactionInterceptor

class TransactionInterceptor {
    + Object invoke(final MethodInvocation invocation)
}

@enduml
```