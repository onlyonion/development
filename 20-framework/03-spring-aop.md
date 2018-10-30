
## aop
面向切面的编程，是一种编程技术，允许程序模块化横向切割关注点，或横切典型的责任划分，如日志和事务管理

AOP核心就是切面，它将多个类的通用行为封装成可重用的模块，该模块含有一组API提供横切功能。

## concept

1. 连接点 Joinpoint	一个应用程序的某个位置
2. 通知 Advice		在方法执行前或执行后要做的动作。
	spring五种类型通知
	- before
	- after
	- after-returing
	- after-throwing
	- around
3. 切点 Pointcut	一个或一组连接点，通知将在这些位置执行。可以通过表达式或匹配的方式指明切入点
4. advice + pointcut = aspect(advisor)
5. AOP proxy 将通知应用到目标对象之后创建的动态代理对象

![spring-aop-concept.png](./img/spring-aop-concept.png "spring-aop-concept.png") 



## 实现
AOP实现的关键在于AOP框架自动创建的AOP代理，AOP代理主要分为静态代理和动态代理，静态代理的代表为AspectJ；而动态代理则以Spring AOP为代表。

动态代理就是说AOP框架不会去修改字节码，而是在内存中临时为方法生成一个AOP对象，这个AOP对象包含了目标对象的全部方法，并且在特定的切点做了增强处理，并回调原对象的方法。

两种方式
1.	jdk	通过反射来接收被代理的类，并且要求被代理的类必须实现一个接口	InvocationHandler接口和Proxy类
2.	cglib(Code Generation Library)	目标类没有实现接口，且没有被final修饰 		MethodInterceptor, Enhancer


## Spring中的Advisor，Advice，Point概述

1. Advisor：充当Advice和Pointcut的适配器，类似使用Aspect的@Aspect注解的类（前一章节所述）。一般有advice和pointcut属性。
祖先接口为org.springframework.aop.Advisor,应用中可直接使用org.springframework.aop.support.DefaultPointcutAdvisor

2. Advice：用于定义拦截行为，祖先接口为org.aopalliance.aop.Advice，该接口只是标识接口，应用中可直接实现BeforeAdvice ，ThrowsAdvice，MethodInterceptor ，AfterReturningAdvice ，IntroductionInterceptor 等子接口

3. Pointcut：用于定义拦截目标集合，祖先接口为org.springframework.aop.Pointcut