org.springframework.aop.Advisor

```java
public interface Advisor {
    // 只有一个通知器
	Advice getAdvice();
	boolean isPerInstance();
}
```
### advice + pointcut

```yuml
// {type:class}

[Advisor]++->[Advice]
[PointcutAdvisor{bg:thistle}]++->[Pointcut]

// 通过advisor，把advice和pointcut结合起来
// 1. 切点通知器
[Advisor]^-[PointcutAdvisor]
[PointcutAdvisor]^-.-[AbstractPointcutAdvisor]
// 1.1 bean工厂使用
[AbstractPointcutAdvisor]^-.-[AbstractBeanFactoryPointcutAdvisor]
// 1.2 通用
[AbstractPointcutAdvisor]^-.-[AbstractGenericPointcutAdvisor]
[AbstractGenericPointcutAdvisor]^-[DefaultPointcutAdvisor]

// 2. 引入通知器
[Advisor]^-.-[IntroductionAdvisor{bg:whitesmoke}]

[note:切面增强设计（Advice）和关注点的设计（Pointcut）连接起来{bg:cornsilk}]
```