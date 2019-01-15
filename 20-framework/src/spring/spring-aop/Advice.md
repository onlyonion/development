org.aopalliance.aop.Advice

### 做什么 what

```yuml
// {type:class}
// before
[Advice]^-[BeforeAdvice{bg:thistle}]
[BeforeAdvice]^-[MethodBeforeAdvice]

// after
[Advice]^-[AfterAdvice{bg:whitesmoke}]
[AfterAdvice]^-[ThrowsAdvice]
[AfterAdvice]^-[AfterReturningAdvice]

// 动态织入
[Advice]^-[DynamicIntroductionAdvice{bg:wheat}]
[DynamicIntroductionAdvice]^-[IntroductionInterceptor]

// interceptor
[Advice]^-[Interceptor{bg:turquoise}]
[Interceptor]^-[MethodInterceptor]
[Interceptor]^-[ConstructorInterceptor]
    
[note:定义在连接点做什么，为切面增强提供织入接口{bg:cornsilk}]
```