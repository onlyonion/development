org.springframework.aop.ProxyMethodInvocation
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


```
@startuml

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

class CglibMethodInvocation {
    + invokeJoinpoint()
}

@enduml
```