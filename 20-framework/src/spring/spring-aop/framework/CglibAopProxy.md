org.springframework.aop.framework.CglibAopProxy

## hierarchy
```
CglibAopProxy (org.springframework.aop.framework)
    ObjenesisCglibAopProxy (org.springframework.aop.framework)
```

## define
* 内部类
  * DynamicAdvisedInterceptor
  * CglibMethodInvocation


```plantuml
@startuml

interface AopProxy

'''''''''''''''''''''''' CglibAopProxy '''''''''''''''''''''''''''''
class CglibAopProxy {
    # final AdvisedSupport advised
}
AopProxy <|.. CglibAopProxy

'''''''''''''''''''''''' 通知支撑 '''''''''''''''''''''''''''''
class AdvisedSupport {
    TargetSource targetSource
    AdvisorChainFactory advisorChainFactory
    - List<Advisor> advisors
    
    + List<Object> getInterceptorsAndDynamicInterceptionAdvice(Method method, Class<?> targetClass)
}
CglibAopProxy o-- AdvisedSupport

'''''''''''''''''''''''' 内部类 '''''''''''''''''''''''''''''
'''''''''''''''''''''''' DynamicAdvisedInterceptor '''''''''''''''''''''''''''''
class DynamicAdvisedInterceptor {
    - final AdvisedSupport advised;
    + Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy)
} 
CglibAopProxy +-- DynamicAdvisedInterceptor
DynamicAdvisedInterceptor o-- AdvisedSupport

'''''''''''''''''''''''' CglibMethodInvocation '''''''''''''''''''''''''''''
class ReflectiveMethodInvocation
class CglibMethodInvocation 
ReflectiveMethodInvocation <|-- CglibMethodInvocation
CglibAopProxy +-- CglibMethodInvocation


@enduml
```

## DynamicAdvisedInterceptor.intercept()
* 获得拦截器链 advised.getInterceptorsAndDynamicInterceptionAdvice(method, targetClass)
    - 拦截器链为空，直接调用
    - 拦截器链不为空，执行拦截器链
* 结果处理、返回