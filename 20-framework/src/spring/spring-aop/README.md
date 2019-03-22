## spring-aop
* [Joinpoint](/20-framework/src/spring/spring-aop/aopalliance/intercept/Joinpoint.md)
* [Advice](/20-framework/src/spring/spring-aop/aopalliance/aop/Advice.md)
* [Pointcut](/20-framework/src/spring/spring-aop/Pointcut.md)
* [Advisor](/20-framework/src/spring/spring-aop/Advisor.md)
* [AopProxy](/20-framework/src/spring/spring-aop/framework/AopProxy.md)
  * [JdkDynamicAopProxy](/20-framework/src/spring/spring-aop/framework/JdkDynamicAopProxy.md)
  * [CglibAopProxy](/20-framework/src/spring/spring-aop/framework/CglibAopProxy.md)
  * [ProxyFactoryBean](/20-framework/src/spring/spring-aop/framework/ProxyFactoryBean.md)
* [ReflectiveMethodInvocation](/20-framework/src/spring/spring-aop/framework/ReflectiveMethodInvocation.md)

## package
* org.aopalliance
* org.aspectj
* org.springframework.aop


## org.springframework.aop
```
aspectj
config
framework
interceptor
scope
support
target

Advisor
AfterAdvice 后置通知
AfterReturningAdvice 返回通知
AopInvocationException
BeforeAdvice 前置通知
ClassFilter
DynamicIntroductionAdvice 动态织入通知
IntroductionAdvisor
IntroductionAwareMethodMatcher
IntroductionInfo
IntroductionInterceptor
MethodBeforeAdvice 方法之前通知
MethodMatcher
Pointcut 切入点
PointcutAdvisor
ProxyMethodInvocation
RawTargetAccess
SpringProxy  标记接口，标记spring生成的动态代理
TargetClassAware
TargetSource
ThrowsAdvice 抛出通知
TrueClassFilter
TrueMethodMatcher
TruePointcut
```

* Advice when
* Pointcut where
* Aspect 约等于 Advisor = Advice + Pointcut = when + where

## concept

* 切面（Aspect）：一个关注点的模块化，这个关注点实现可能另外横切多个对象。
事务管理是J2EE应用中一个很好的横切关注点例子。方面用 Spring的Advisor或拦截 器实现。
* 连接点（Joinpoint）：程序执行过程中明确的点，如方法的调用或特定的异常被抛出。
* 通知（Advice）：在特定的连接点，AOP框架执行的动作。各种类型的通知包括“around”、“before”和“throws”通知。
* 切入点（Pointcut）：指定一个通知将被引发的一系列连接点的集合。AOP框架必须允许开发者指定切入点
* 织入（weave） 将切面应用到目标对象并导致代理对象创建的过程
* 引入（introduction） 在不修改代码的前提下，引入可以在运行期为类动态地添加一些方法或字段

### aop:advisor和aop:aspect
[aop:advisor和aop:aspect](https://blog.csdn.net/Chinahahaha/article/details/62217735)
* aop:advisor只能有一个通知比如(before前置通知),需要使用的时候还需要实现一个接口MethodBeforeAdvice
* aop:aspect可以有多个通知需要使用的时候不需要继承接口直接在IOC容器里面配置就好

### Advice、Advisor、Advised
* Advice 通知拦截器
* Advisor 通知 + 切入点的适配器
* Advised 包含所有的Advisor 和 Advice