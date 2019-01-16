org.aopalliance.aop.Advice

### 做什么 what
* 前置通知
* 后置通知、异常通知、环绕通知
* 动态织入通知 织入拦截器
* 拦截器通知、构造方法拦截器、普通方法拦截器

```yuml
// {type:class}
// 1. before通知 方法前通知
[Advice]^-[BeforeAdvice{bg:thistle}]
[BeforeAdvice]^-[MethodBeforeAdvice]

// 2. after通知 异常、返回通知
[Advice]^-[AfterAdvice{bg:whitesmoke}]
[AfterAdvice]^-[ThrowsAdvice]
[AfterAdvice]^-[AfterReturningAdvice]

// 3. 动态织通知 织入拦截器
[Advice]^-[DynamicIntroductionAdvice{bg:wheat}]
[DynamicIntroductionAdvice]^-[IntroductionInterceptor]

// 4. interceptor 拦截器
[Advice]^-[Interceptor{bg:tomato}]
[Interceptor]^-[MethodInterceptor]
[Interceptor]^-[ConstructorInterceptor]

// 注释
[note:定义在连接点做什么，为切面增强提供织入接口{bg:cornsilk}]
[Interceptor]-.-[note:通知是对拦截器更高级别的抽象{bg:cornsilk}]
```