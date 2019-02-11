org.springframework.core.AliasRegistry

别名注册表

## hierachy
```
AliasRegistry (org.springframework.core)
    SimpleAliasRegistry (org.springframework.core)
        DefaultSingletonBeanRegistry (org.springframework.beans.factory.support)
            FactoryBeanRegistrySupport (org.springframework.beans.factory.support)
                AbstractBeanFactory (org.springframework.beans.factory.support)
                    AbstractAutowireCapableBeanFactory (org.springframework.beans.factory.support)
                        DefaultListableBeanFactory (org.springframework.beans.factory.support)
                            XmlBeanFactory (org.springframework.beans.factory.xml)
        SimpleBeanDefinitionRegistry (org.springframework.beans.factory.support)
    BeanDefinitionRegistry (org.springframework.beans.factory.support)
        SimpleBeanDefinitionRegistry (org.springframework.beans.factory.support)
        DefaultListableBeanFactory (org.springframework.beans.factory.support)
            XmlBeanFactory (org.springframework.beans.factory.xml)
        GenericApplicationContext (org.springframework.context.support)
            GenericXmlApplicationContext (org.springframework.context.support)
            StaticApplicationContext (org.springframework.context.support)
                StaticWebApplicationContext (org.springframework.web.context.support)
            GenericWebApplicationContext (org.springframework.web.context.support)
                ServletWebServerApplicationContext (org.springframework.boot.web.servlet.context)
                    AnnotationConfigServletWebServerApplicationContext (org.springframework.boot.web.servlet.context)
                    XmlServletWebServerApplicationContext (org.springframework.boot.web.servlet.context)
            ResourceAdapterApplicationContext (org.springframework.jca.context)
            GenericGroovyApplicationContext (org.springframework.context.support)
            AnnotationConfigApplicationContext (org.springframework.context.annotation)
            GenericReactiveWebApplicationContext (org.springframework.boot.web.reactive.context)
                ReactiveWebServerApplicationContext (org.springframework.boot.web.reactive.context)
                    AnnotationConfigReactiveWebServerApplicationContext (org.springframework.boot.web.reactive.context)
```

## class
```
@startuml

package org.springframework.core {
    interface AliasRegistry {
        + void registerAlias(String name, String alias)
        + void removeAlias(String alias)
        + boolean isAlias(String name)
        + String[] getAliases(String name)
    }
    
    class SimpleAliasRegistry {
        - final Map<String, String> aliasMap
    }
    
    AliasRegistry <|.. SimpleAliasRegistry
}

class SimpleBeanDefinitionRegistry
SimpleAliasRegistry <|-- SimpleBeanDefinitionRegistry
BeanDefinitionRegistry <|.. SimpleBeanDefinitionRegistry

interface BeanDefinitionRegistry
AliasRegistry <|-- BeanDefinitionRegistry
BeanDefinitionRegistry <|.. DefaultListableBeanFactory

abstract class FactoryBeanRegistrySupport
SimpleBeanDefinitionRegistry <|-- FactoryBeanRegistrySupport

abstract class AbstractBeanFactory
FactoryBeanRegistrySupport <|-- AbstractBeanFactory

abstract class AbstractAutowireCapableBeanFactory
AbstractBeanFactory <|-- AbstractAutowireCapableBeanFactory

class DefaultListableBeanFactory
AbstractAutowireCapableBeanFactory <|-- DefaultListableBeanFactory

'''''''''''''''''' GenericApplicationContext ''''''''''''''''''
class GenericApplicationContext
abstract class AbstractApplicationContext
AbstractApplicationContext <|-- GenericApplicationContext
BeanDefinitionRegistry <|.. GenericApplicationContext
@enduml
```