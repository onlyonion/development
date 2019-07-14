
```
AbstractConfig (com.alibaba.dubbo.config)
    AbstractMethodConfig (com.alibaba.dubbo.config)
        AbstractInterfaceConfig (com.alibaba.dubbo.config)
            AbstractReferenceConfig (com.alibaba.dubbo.config)
                ReferenceConfig (com.alibaba.dubbo.config)
```

```
@startuml

class ReferenceConfig {
    Class<?> interfaceClass
    List<MethodConfig> methods
    ConsumerConfig consumer
    volatile T ref
    volatile Invoker<?> invoker
}


@enduml
```