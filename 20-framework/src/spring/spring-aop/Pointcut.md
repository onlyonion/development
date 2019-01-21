org.springframework.aop.Pointcut

## 定义
决定Adavice通知应该作用于哪些连接点，也就是说Pointcut来定义需要增强的方法的集合，这些集合的选取可以按照一定的规则来完成。

## 类图

```yuml
// {type:class}

[note:定义需要增强的方法的集合{bg:cornsilk}]

// 1. 定义一系列连接点的集合
[Pointcut||+getClassFilter();+getMethodMatcher()]
[MethodMatcher||+matches(Method,Class);+matches(Method,Class,Object...);+isRuntime()]

// 2. 表达式切入点
[Pointcut]^-[ExpressionPointcut{bg:thistle}]
[ExpressionPointcut]^-[AbstractExpressionPointcut]
[AbstractExpressionPointcut]^-[AspectJExpressionPointcut]

// 3. 注解匹配切入点
[Pointcut]^-.-[AnnotationMatchingPointcut{bg:whitesmoke}]

// 4. 静态方法匹配器切入点
[Pointcut]^-.-[StaticMethodMatcherPointcut{bg:steelblue}]
[StaticMethodMatcherPointcut]^-[AbstractRegexpMethodPointcut]
[AbstractRegexpMethodPointcut]^-[JdkRegexpMethodPointcut]
    
```