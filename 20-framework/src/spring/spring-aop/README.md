
## concept

* 切面（Aspect）：一个关注点的模块化，这个关注点实现可能另外横切多个对象。事务管理是J2EE应用中一个很好的横切关注点例子。方面用 Spring的Advisor或拦截 器实现。
* 连接点（Joinpoint）：程序执行过程中明确的点，如方法的调用或特定的异常被抛出。
* 通知（Advice）：在特定的连接点，AOP框架执行的动作。各种类型的通知包括“around”、“before”和“throws”通知。
* 切入点（Pointcut）：指定一个通知将被引发的一系列连接点的集合。AOP框架必须允许开发者指定切入点

### aop:advisor和aop:aspect
[aop:advisor和aop:aspect](https://blog.csdn.net/Chinahahaha/article/details/62217735)
* aop:advisor只能有一个通知比如(before前置通知),需要使用的时候还需要实现一个接口MethodBeforeAdvice
* aop:aspect可以有多个通知需要使用的时候不需要继承接口直接在IOC容器里面配置就好

### Advice、Advisor、Advised
* Advice 通知拦截器
* Advisor 通知 + 切入点的适配器
* Advised 包含所有的Advisor 和 Advice

## org.aopalliance
```
    aop
        Advice
    intercept
        ConstructorInterceptor
        ConstructorInvocation
        Interceptor
        Invocation
        Joinpoint
        MethodInterceptor
        MethodInvocation
```

## org.aspectj
```

```

## org.springframework.aop
```

```