org.springframework.aop.framework.CglibAopProxy

## hierarchy
```
AopProxy (org.springframework.aop.framework)
    JdkDynamicAopProxy (org.springframework.aop.framework)
    CglibAopProxy (org.springframework.aop.framework)
        ObjenesisCglibAopProxy (org.springframework.aop.framework)
```

## define
* 内部类
  * DynamicAdvisedInterceptor cglib的拦截器，顶层是回调接口，与aopalliance的Advice类似
  * CglibMethodInvocation Invocation本质上是Jointpoint

```plantuml
@startuml

interface AopProxy

'''''''''''''''''''''''' CglibAopProxy '''''''''''''''''''''''''''''
class CglibAopProxy #orange {
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

'''''''''''''''''''''''' DynamicAdvisedInterceptor '''''''''''''''''''''''''''''
interface Callback
interface MethodInterceptor

Callback ^-- MethodInterceptor
MethodInterceptor ^.. DynamicAdvisedInterceptor

class DynamicAdvisedInterceptor {
    - final AdvisedSupport advised;
    + Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy)
} 
CglibAopProxy +-- DynamicAdvisedInterceptor
DynamicAdvisedInterceptor o-- AdvisedSupport

'''''''''''''''''''''''' CglibMethodInvocation '''''''''''''''''''''''''''''
interface Joinpoint
interface Invocation
interface MethodInvocation
interface ProxyMethodInvocation

Joinpoint ^-- Invocation
Invocation ^-- MethodInvocation
MethodInvocation ^-- ProxyMethodInvocation
ProxyMethodInvocation ^.. ReflectiveMethodInvocation
class ReflectiveMethodInvocation
ReflectiveMethodInvocation ^-- CglibMethodInvocation

class CglibMethodInvocation {
    - final MethodProxy methodProxy
    # Object invokeJoinpoint() throws Throwable
}

CglibAopProxy +-- CglibMethodInvocation


@enduml
```

## DynamicAdvisedInterceptor.intercept()
* 获得拦截器链 advised.getInterceptorsAndDynamicInterceptionAdvice(method, targetClass)
 - 拦截器链为空，直接调用
 - 拦截器链不为空，执行拦截器链
* 结果处理、返回