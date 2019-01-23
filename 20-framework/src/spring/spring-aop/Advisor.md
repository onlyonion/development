org.springframework.aop.Advisor

## 1. Advisor 定义
* advice + pointcut，通过advisor，把advice和pointcut结合起来
* spring里切面的实现，advisor

```java
public interface Advisor {
    // 只有一个通知器
	Advice getAdvice();
	boolean isPerInstance();
}
public interface PointcutAdvisor extends Advisor {
	Pointcut getPointcut();
}
```
## 2. Advisor 类图
* IntroductionAdvisor 只能应用于类级别的拦截，只能使用Introduction型的Advice，纯粹就是为Introduction而生的
* PointcutAdvisor 可以使用任何类型的Pointcut，以及差不多任何类型的Advice

```yuml
// {type:class}

[PointcutAdvisor{bg:thistle}]
[IntroductionAdvisor{bg:whitesmoke}]

// 1. 切点通知器
[Advisor]^-[PointcutAdvisor]

// 2. 引入通知器
[Advisor]^-[IntroductionAdvisor]
[IntroductionInfo]^-[IntroductionAdvisor]
[IntroductionAdvisor]++-[ClassFilter]

// 3. Advisor将切面增强设计（Advice）和关注点的设计（Pointcut）连接起来
[Advisor]++->[Advice]
[PointcutAdvisor]++->[Pointcut]
[Pointcut]++->[ClassFilter]
[Pointcut]++->[MethodMatcher]
```
### 2.1 PointcutAdvisor 类图
```yuml
// {type:class}
[PointcutAdvisor]^-.-[AbstractPointcutAdvisor]

// 1.1 bean工厂切点通知器
[AbstractPointcutAdvisor]^-[AbstractBeanFactoryPointcutAdvisor]

// 1.2 通用切点通知器
[AbstractPointcutAdvisor]^-[AbstractGenericPointcutAdvisor]
[AbstractGenericPointcutAdvisor]^-[DefaultPointcutAdvisor]
```