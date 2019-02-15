org.springframework.transaction.interceptor.TransactionInterceptor

## 事务拦截器


```yuml
// {type:class}

// 事务方面支持类
[Aware]^-[BeanFactoryAware]
[BeanFactoryAware]^-.-[TransactionAspectSupport]
[InitializingBean]^-.-[TransactionAspectSupport]

// 事务拦截器
[Advice]^-.-[Interceptor]
[Interceptor]^-.-[MethodInterceptor]
[MethodInterceptor]^-.-[TransactionInterceptor]
[TransactionAspectSupport]^-[TransactionInterceptor]

// 事务拦截器内部属性
[TransactionInterceptor]<>--[PlatformTransactionManager]
[TransactionInterceptor]<>--[beanFactory]
```