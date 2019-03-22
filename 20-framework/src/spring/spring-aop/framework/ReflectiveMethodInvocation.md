org.springframework.aop.framework.ReflectiveMethodInvocation

## hierachy
```
Joinpoint (org.aopalliance.intercept)
    Invocation (org.aopalliance.intercept)
        ConstructorInvocation (org.aopalliance.intercept)
        MethodInvocation (org.aopalliance.intercept)
            ProxyMethodInvocation (org.springframework.aop)
                ReflectiveMethodInvocation (org.springframework.aop.framework)
                    CglibMethodInvocation in CglibAopProxy (org.springframework.aop.framework)
```

## class
```plantuml
@startuml

interface Joinpoint
interface Invocation
interface MethodInvocation
interface ProxyMethodInvocation

Joinpoint ^-- Invocation
Invocation ^-- MethodInvocation
MethodInvocation ^-- ProxyMethodInvocation
ProxyMethodInvocation ^.. ReflectiveMethodInvocation

class ReflectiveMethodInvocation {
    # final Object proxy;
    # final Object target;
    # final Method method;
    # Object[] arguments;
    - final Class<?> targetClass;
    - Map<String, Object> userAttributes;
    # final List<?> interceptorsAndDynamicMethodMatchers;
    - int currentInterceptorIndex = -1;
    
    + Object proceed()
    + invokeJoinpoint()
}

ReflectiveMethodInvocation ^-- CglibMethodInvocation

class CglibAopProxy
CglibAopProxy +-- CglibMethodInvocation
class CglibMethodInvocation {
    + invokeJoinpoint()
}

@enduml
```