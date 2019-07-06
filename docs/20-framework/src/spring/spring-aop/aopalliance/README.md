org.aopalliance.aop

## define
```
aop
    Advice
    AspectException
intercept
    ConstructorInterceptor
    ConstructorInvocation
    Interceptor
    Invocation
    Joinpoint
    MethodInterceptor
    MethodInvocation
```

## overview
```plantuml
@startuml

interface Advice
interface Interceptor 
interface MethodInterceptor
interface ConstructorInterceptor

Advice ^-- Interceptor
Interceptor ^-- MethodInterceptor
Interceptor ^-- ConstructorInterceptor

MethodInterceptor ..> MethodInvocation
ConstructorInterceptor ..> ConstructorInvocation

interface Joinpoint
interface Invocation
interface MethodInvocation
interface ConstructorInvocation

Joinpoint ^-- Invocation
Invocation ^-- MethodInvocation
Invocation ^-- ConstructorInvocation

@enduml
```