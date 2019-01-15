
```yuml
// {type:class}

[Joinpoint||+proceed();+getThis();getStaticPart()]

[Joinpoint]^-[Invocation]

[Invocation]^-[MethodInvocation||+getMethod()]

[MethodInvocation]^-[ProxyMethodInvocation]

[ProxyMethodInvocation]^-[ReflectiveMethodInvocation]
```