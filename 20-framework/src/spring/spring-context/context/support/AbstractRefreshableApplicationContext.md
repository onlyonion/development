org.springframework.context.support.AbstractRefreshableApplicationContext

## hierarchy
```
DefaultResourceLoader (org.springframework.core.io)
    AbstractApplicationContext (org.springframework.context.support)
        AbstractRefreshableApplicationContext (org.springframework.context.support)
            AbstractRefreshableConfigApplicationContext (org.springframework.context.support)
                AnnotationConfigReactiveWebApplicationContext (org.springframework.boot.web.reactive.context)
                AbstractXmlApplicationContext (org.springframework.context.support)
                    FileSystemXmlApplicationContext (org.springframework.context.support)
                    ClassPathXmlApplicationContext (org.springframework.context.support)
                AbstractRefreshableWebApplicationContext (org.springframework.web.context.support)
                    XmlWebApplicationContext (org.springframework.web.context.support)
                    GroovyWebApplicationContext (org.springframework.web.context.support)
                    AnnotationConfigWebApplicationContext (org.springframework.web.context.support)
```

## define
* 实例域
  * DefaultListableBeanFactory
* 实例方法
  * refreshBeanFactory
* 抽象方法
  * loadBeanDefinitions 加载bean定义

```plantuml
@startuml

abstract class AbstractRefreshableApplicationContext {
    - DefaultListableBeanFactory beanFactory
    # final void refreshBeanFactory()
    # DefaultListableBeanFactory createBeanFactory()
    .. 刷新bean工厂 ..
    # final void refreshBeanFactory()
    # DefaultListableBeanFactory createBeanFactory()
    .. 加载bean定义 ..
    # abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory)
}

abstract class AbstractRefreshableConfigApplicationContext
AbstractRefreshableApplicationContext ^-- AbstractRefreshableConfigApplicationContext

''''''''''''''''''''''''''AbstractXmlApplicationContext''''''''''''''''''''''''''
abstract class AbstractXmlApplicationContext
AbstractRefreshableConfigApplicationContext ^-- AbstractXmlApplicationContext
AbstractXmlApplicationContext ^-- FileSystemXmlApplicationContext
AbstractXmlApplicationContext ^-- ClassPathXmlApplicationContext

''''''''''''''''''''''''''AbstractRefreshableWebApplicationContext''''''''''''''''''''''''''
abstract class AbstractRefreshableWebApplicationContext
AbstractRefreshableConfigApplicationContext ^-- AbstractRefreshableWebApplicationContext
AbstractRefreshableWebApplicationContext ^-- XmlWebApplicationContext
AbstractRefreshableWebApplicationContext ^-- GroovyWebApplicationContext
AbstractRefreshableWebApplicationContext ^-- AnnotationConfigWebApplicationContext

@enduml
```