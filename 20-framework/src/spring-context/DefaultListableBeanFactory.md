org.springframework.beans.factory.support.DefaultListableBeanFactory

```
DefaultListableBeanFactory
    AbstractAutowireCapableBeanFactory
        AbstractBeanFactory
            FactoryBeanRegistrySupport
                DefaultSingletonBeanRegistry
                    SimpleAliasRegistry
```

 //Alice->>John: Hello John, how are you?
    //Note right of John: Rational thoughts<br/>prevail...
    //John-->>Alice: Great!
    //John->>Bob: How about you?
    //Bob-->>John: Jolly good!

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