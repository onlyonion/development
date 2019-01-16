
## org.aopalliance.intercept.Joinpoint
JoinPoint对象封装了SpringAop中切面方法的信息,在切面方法中添加JoinPoint参数,就可以获取到封装了该方法信息的JoinPoint对象.

```java
public interface Joinpoint {
	Object proceed() throws Throwable;
	Object getThis();
	AccessibleObject getStaticPart();
}
public interface Invocation extends Joinpoint {
   Object[] getArguments();
}
public interface MethodInvocation extends Invocation {
    Method getMethod();
}
```
## spring 实现了方法级别的拦截
org.springframework.aop.ProxyMethodInvocation
org.springframework.aop.framework.ReflectiveMethodInvocation

```yuml
// {type:class}

[Joinpoint{bg:thistle}]^-[Invocation]
[Invocation]-.-[note:调用器是一个连接点，可被拦截器拦截{bg:cornsilk}]

// 构造方法调用器
[Invocation]^-[ConstructorInvocation]

// 普通方法调用器
[Invocation]^-[MethodInvocation{bg:tomato}]

[MethodInvocation]^-[ProxyMethodInvocation]
[ProxyMethodInvocation]^-.-[ReflectiveMethodInvocation]
[ReflectiveMethodInvocation]^-[CglibAopProxy$CglibMethodInvocation]

```

## org.aspectj.lang.JoinPoint

```yuml
// {type:class}

[Joinpoint{bg:thistle}]^-[ProceedingJoinPoint]

[ProceedingJoinPoint]^-.-[JoinPointImpl]
[ProceedingJoinPoint]^-.-[MethodInvocationProceedingJoinPoint{bg:tomato}]

```