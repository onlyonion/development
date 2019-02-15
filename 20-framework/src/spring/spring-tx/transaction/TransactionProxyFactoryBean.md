org.springframework.transaction.interceptor.TransactionProxyFactoryBean

```
TransactionProxyFactoryBean implements BeanFactoryAware
    AbstractSingletonProxyFactoryBean
        ProxyConfig
```

读取和处理在IoC容器中配置的事务处理属性，并转化为Spring事务处理需要的内部数据结构
org.springframework.transaction.interceptor.TransactionAttributeSourceAdvisor
```
TransactionAttributeSourceAdvisor
    AbstractPointcutAdvisor
        PointcutAdvisor
            Advisor

```


org.springframework.transaction.interceptor.TransactionInterceptor