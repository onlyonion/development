org.springframework.context.support.GenericApplicationContext

## hierarchy
```
DefaultResourceLoader (org.springframework.core.io)
    AbstractApplicationContext (org.springframework.context.support)
        GenericApplicationContext (org.springframework.context.support)
            GenericXmlApplicationContext (org.springframework.context.support)
            StaticApplicationContext (org.springframework.context.support)
                StaticWebApplicationContext (org.springframework.web.context.support)
            GenericWebApplicationContext (org.springframework.web.context.support)
                EmbeddedWebApplicationContext (org.springframework.boot.context.embedded)
                    AnnotationConfigEmbeddedWebApplicationContext (org.springframework.boot.context.embedded)
                    XmlEmbeddedWebApplicationContext (org.springframework.boot.context.embedded)
            ResourceAdapterApplicationContext (org.springframework.jca.context)
            GenericGroovyApplicationContext (org.springframework.context.support)
            AnnotationConfigApplicationContext (org.springframework.context.annotation)
```

## define

```plantuml
@startuml

abstract class AbstractApplicationContext
AbstractApplicationContext ^-- GenericApplicationContext

interface BeanDefinitionRegistry
BeanDefinitionRegistry ^.. GenericApplicationContext

class GenericApplicationContext {
	- final DefaultListableBeanFactory beanFactory
    - ResourceLoader resourceLoader
    - boolean customClassLoader = false
    - final AtomicBoolean refreshed
}



@enduml
```