org.springframework.aop.Advisor

### advice + pointcut

```yuml
// {type:class}

[Advisor]++->[Advice]
[PointcutAdvisor{bg:thistle}]++->[Pointcut]

// 通过advisor，把advice和pointcut结合起来
[Advisor]^-[PointcutAdvisor]

// 1. 切点通知器
[PointcutAdvisor]^-.-[AbstractPointcutAdvisor]
// bean工厂使用
[AbstractPointcutAdvisor]^-.-[AbstractBeanFactoryPointcutAdvisor]
// 通用
[AbstractPointcutAdvisor]^-.-[AbstractGenericPointcutAdvisor]
[AbstractGenericPointcutAdvisor]^-[DefaultPointcutAdvisor]

// 2. 引入通知器
[Advisor]^-.-[IntroductionAdvisor{bg:whitesmoke}]

[note:切面增强设计（Advice）和关注点的设计（Pointcut）连接起来{bg:cornsilk}]
```