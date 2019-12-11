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

## define
* 反射的方法调用器

```plantuml
@startuml

'''''''''''''''''''''''''Joinpoint''''''''''''''''''''''''''''''
interface Joinpoint {
    + Object proceed()
    + Object getThis()
    + AccessibleObject getStaticPart()
}

interface Invocation {
    + Object[] getArguments()                   
}

interface MethodInvocation {
    + Method getMethod()
}

interface ProxyMethodInvocation {
	+ Object getProxy()
	+ MethodInvocation invocableClone()
	+ MethodInvocation invocableClone(Object... arguments)
	+ void setArguments(Object... arguments)
	+ void setUserAttribute(String key, Object value)
	+ Object getUserAttribute(String key)
}

Joinpoint ^-- Invocation
Invocation ^-- MethodInvocation
MethodInvocation ^-- ProxyMethodInvocation

'''''''''''''''''''''''''父类''''''''''''''''''''''''''''''
ProxyMethodInvocation ^.. ReflectiveMethodInvocation

class ReflectiveMethodInvocation #orange {
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


'''''''''''''''''''''''''CglibAopProxy''''''''''''''''''''''''''''''
ReflectiveMethodInvocation ^-- CglibMethodInvocation

class CglibAopProxy #lime
CglibAopProxy +-- CglibMethodInvocation
class CglibMethodInvocation {
    + invokeJoinpoint()
}

'''''''''''''''''''''''''JdkDynamicAopProxy''''''''''''''''''''''''''''''
class JdkDynamicAopProxy #pink
JdkDynamicAopProxy ..> ReflectiveMethodInvocation

@enduml
```