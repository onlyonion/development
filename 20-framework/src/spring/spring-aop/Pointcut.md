org.springframework.aop.Pointcut

## 1. Pointcut 定义
* 决定Adavice通知应该作用于哪些连接点，也就是说Pointcut来定义需要增强的方法的集合，这些集合的选取可以按照一定的规则来完成。

```java
public interface Pointcut {
	ClassFilter getClassFilter();
	MethodMatcher getMethodMatcher();
	Pointcut TRUE = TruePointcut.INSTANCE;
}
public interface ClassFilter {
	boolean matches(Class<?> clazz);
	ClassFilter TRUE = TrueClassFilter.INSTANCE;
}
public interface MethodMatcher {
	boolean matches(Method method, Class<?> targetClass);
	boolean isRuntime();
	boolean matches(Method method, Class<?> targetClass, Object... args);
	MethodMatcher TRUE = TrueMethodMatcher.INSTANCE;
}
```


## 2. Pointcut 类图
* 定义需要增强的方法的集合
```yuml
// {type:class}

// 定义一系列连接点的集合
[Pointcut]
[MethodMatcher]
[ClassFilter]
[ControlFlowPointcut{bg:steelblue}]

// 1. 表达式切入点
[Pointcut]^-[ExpressionPointcut{bg:thistle}]
[ExpressionPointcut]^-[AbstractExpressionPointcut]
[AbstractExpressionPointcut]^-[AspectJExpressionPointcut]

// 2. 注解匹配切入点
[Pointcut]^-.-[AnnotationMatchingPointcut{bg:whitesmoke}]

// 3. 静态方法匹配器切入点
[Pointcut]^-.-[StaticMethodMatcherPointcut]
[StaticMethodMatcherPointcut]^-[AbstractRegexpMethodPointcut]
[AbstractRegexpMethodPointcut]^-[JdkRegexpMethodPointcut]
    
// 4. ControlFlowPointcut
[Pointcut]^-.-[ControlFlowPointcut]  

// 切点属性
[Pointcut]++-[MethodMatcher]
[Pointcut]++-[ClassFilter]

// 类过滤器
[ClassFilter]^-.-[ControlFlowPointcut]
[ClassFilter]^-.-[AnnotationClassFilter]
[ClassFilter]^-.-[TypePatternClassFilter]
[ClassFilter]^-.-[DefaultIntroductionAdvisor]
[ClassFilter]^-.-[AspectJExpressionPointcut]
[ClassFilter]^-.-[RootClassFilter]
[ClassFilter]^-.-[TrueClassFilter]

// 方法匹配器
[MethodMatcher]^-.-[ControlFlowPointcut]
[MethodMatcher]^-.-[DynamicMethodMatcher]
[MethodMatcher]^-.-[TrueMethodMatcher]
[MethodMatcher]^-[IntroductionAwareMethodMatcher]
[MethodMatcher]^-.-[StaticMethodMatcher]

```
