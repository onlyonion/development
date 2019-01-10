org.springframework.beans.factory.support.DefaultListableBeanFactory

```
DefaultListableBeanFactory
    AbstractAutowireCapableBeanFactory
        AbstractBeanFactory
            FactoryBeanRegistrySupport
                DefaultSingletonBeanRegistry
                    SimpleAliasRegistry
```

```mermaid
graph BT
    DefaultListableBeanFactory --> AbstractAutowireCapableBeanFactory
```


```mermaid
sequenceDiagram
    participant DefaultListableBeanFactory
    participant AbstractBeanFactory
    participant AbstractAutowireCapableBeanFactory

    DefaultListableBeanFactory ->> AbstractBeanFactory: doGetBean()
    AbstractBeanFactory ->> AbstractAutowireCapableBeanFactory: createBean()
    AbstractAutowireCapableBeanFactory ->> AbstractAutowireCapableBeanFactory: doCreateBean()
    AbstractAutowireCapableBeanFactory ->> InstantiationStrategy: instantiate()
    InstantiationStrategy -->> AbstractAutowireCapableBeanFactory: populateBean()
    AbstractAutowireCapableBeanFactory ->> AbstractAutowireCapableBeanFactory: applyPropertyValues()
    
```