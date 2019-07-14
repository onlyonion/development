org.springframework.transaction.interceptor.TransactionAttributeSourceAdvisor

## hierarchy
```
AbstractPointcutAdvisor (org.springframework.aop.support)
    TransactionAttributeSourceAdvisor (org.springframework.transaction.interceptor)
```

## define
```plantuml
@startuml

interface Advisor
interface PointcutAdvisor

Advisor ^-- PointcutAdvisor
PointcutAdvisor ^.. AbstractPointcutAdvisor

abstract class AbstractPointcutAdvisor {
}

AbstractPointcutAdvisor ^-- TransactionAttributeSourceAdvisor
class TransactionAttributeSourceAdvisor {
    - TransactionInterceptor transactionInterceptor
}

@enduml
```