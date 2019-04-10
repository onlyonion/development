org.aopalliance.intercept.Joinpoint

## hierachy
spring 实现了方法级别的拦截

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
1. JoinPoint对象封装了SpringAop中切面方法的信息,在切面方法中添加JoinPoint参数,就可以获取到封装了该方法信息的JoinPoint对象.
2. 被拦截到的点，因为Spring只支持方法类型的连接点，所以在Spring中连接点指的就是被拦截到的方法，实际上连接点还可以是字段或者构造器
3. Joinpoint这个接口表示一个通用的运行时连接点。运行时连接点是一个静态连接点发生的一个事件。

```plantuml
@startuml


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

@enduml
```

## yuml

```yuml
// {type:class}

[Joinpoint{bg:thistle}]
[MethodInvocation{bg:thistle}]
[ReflectiveMethodInvocation{bg:tomato}]

// 1. 连接点
[Joinpoint]^-[Invocation]
[Invocation]-.-[note:调用器是一个连接点，可被拦截器拦截{bg:cornsilk}]

// 2.1 构造方法调用器
[Invocation]^-[ConstructorInvocation]

// 2.2 普通方法调用器
[Invocation]^-[MethodInvocation]

// 2.2.1 代理的普通方法调用器
[MethodInvocation]^-[ProxyMethodInvocation]

// 2.2.2 反射的普通方法调用器 对连接点的实现
[ProxyMethodInvocation]^-.-[ReflectiveMethodInvocation]

// 2.2.3 cglib的普通方法调用器
[ReflectiveMethodInvocation]^-[CglibAopProxy$CglibMethodInvocation]

```


# org.aspectj.lang.JoinPoint

```yuml
// {type:class}

[Joinpoint{bg:thistle}]^-[ProceedingJoinPoint]

[ProceedingJoinPoint]^-.-[JoinPointImpl]
[ProceedingJoinPoint]^-.-[MethodInvocationProceedingJoinPoint{bg:tomato}]

```