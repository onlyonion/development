org.springframework.aop.framework.ProxyConfig

## hierarchy
```
ProxyConfig (org.springframework.aop.framework)
    AbstractSingletonProxyFactoryBean (org.springframework.aop.framework)
        CacheProxyFactoryBean (org.springframework.cache.interceptor)
        TransactionProxyFactoryBean (org.springframework.transaction.interceptor)
    AdvisedSupport (org.springframework.aop.framework)
        ProxyCreatorSupport (org.springframework.aop.framework)
            AspectJProxyFactory (org.springframework.aop.aspectj.annotation)
            ProxyFactory (org.springframework.aop.framework)
            ProxyFactoryBean (org.springframework.aop.framework)
    ProxyProcessorSupport (org.springframework.aop.framework)
        AbstractAdvisingBeanPostProcessor (org.springframework.aop.framework)
            AbstractBeanFactoryAwareAdvisingPostProcessor (org.springframework.aop.framework.autoproxy)
                AsyncAnnotationBeanPostProcessor (org.springframework.scheduling.annotation)
                MethodValidationPostProcessor (org.springframework.validation.beanvalidation)
                PersistenceExceptionTranslationPostProcessor (org.springframework.dao.annotation)
            StatAnnotationBeanPostProcessor (com.alibaba.druid.support.spring.stat.annotation)
        AbstractAutoProxyCreator (org.springframework.aop.framework.autoproxy)
    ScopedProxyFactoryBean (org.springframework.aop.scope)
```