org.aopalliance.intercept.Joinpoint

```java
public interface Joinpoint {
	Object proceed() throws Throwable;
	Object getThis();
	AccessibleObject getStaticPart();
}
```

```yuml
// {type:class}
[Joinpoint||+proceed();+getThis();getStaticPart()]
[Joinpoint]^-[Invocation]

// 构造方法
[Invocation]^-[ConstructorInvocation||+getConstructor()]

// 方法
[Invocation]^-[MethodInvocation||+getMethod()]
[MethodInvocation]^-[ProxyMethodInvocation]
[ProxyMethodInvocation]^-.-[ReflectiveMethodInvocation]
[ReflectiveMethodInvocation]^-[CglibAopProxy$CglibMethodInvocation]
```