org.springframework.aop.Pointcut

### 在哪里 where
决定Adavice通知应该作用于哪些连接点，也就是说Pointcut来定义需要增强的方法的集合，这些集合的选取可以按照一定的规则来完成。

```yuml
// {type:class}

[note:定义需要增强的方法的集合{bg:cornsilk}]

[Pointcut||+getClassFilter();+getMethodMatcher()]
[MethodMatcher||+matches(Method,Class);+matches(Method,Class,Object...);+isRuntime()]

// 表达式
[Pointcut]^-[ExpressionPointcut{bg:thistle}]
[ExpressionPointcut]^-[AbstractExpressionPointcut]
[AbstractExpressionPointcut]^-[AspectJExpressionPointcut]

// 注解匹配
[Pointcut]^-.-[AnnotationMatchingPointcut{bg:whitesmoke}]

// 静态方法匹配器切点
[Pointcut]^-.-[StaticMethodMatcherPointcut{bg:steelblue}]
[StaticMethodMatcherPointcut]^-[AbstractRegexpMethodPointcut]
[AbstractRegexpMethodPointcut]^-[JdkRegexpMethodPointcut]
    
```