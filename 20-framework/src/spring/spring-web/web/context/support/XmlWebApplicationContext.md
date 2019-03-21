org.springframework.web.context.support.XmlWebApplicationContext

## hierarchy
```
DefaultResourceLoader (org.springframework.core.io)
    AbstractApplicationContext (org.springframework.context.support)
        AbstractRefreshableApplicationContext (org.springframework.context.support)
            AbstractRefreshableConfigApplicationContext (org.springframework.context.support)
                AbstractRefreshableWebApplicationContext (org.springframework.web.context.support)
                    XmlWebApplicationContext (org.springframework.web.context.support)
                AbstractXmlApplicationContext (org.springframework.context.support)
                    ClassPathXmlApplicationContext (org.springframework.context.support)
                    FileSystemXmlApplicationContext (org.springframework.context.support)
```

## define

* loadBeanDefinitions

```plantuml
@startuml
class DefaultResourceLoader
abstract class AbstractApplicationContext
DefaultResourceLoader ^-- AbstractApplicationContext

abstract class AbstractRefreshableApplicationContext 
AbstractApplicationContext ^-- AbstractRefreshableApplicationContext

abstract class AbstractRefreshableConfigApplicationContext {
	- String[] configLocations
}
AbstractRefreshableApplicationContext ^-- AbstractRefreshableConfigApplicationContext

abstract class AbstractRefreshableWebApplicationContext {
	- ServletContext servletContext
	- ServletConfig servletConfig
	- String namespace
	- ThemeSource themeSource
}
AbstractRefreshableConfigApplicationContext ^-- AbstractRefreshableWebApplicationContext

class XmlWebApplicationContext {
    + static final String DEFAULT_CONFIG_LOCATION = "/WEB-INF/applicationContext.xml"
	+ static final String DEFAULT_CONFIG_LOCATION_PREFIX = "/WEB-INF/"
	+ static final String DEFAULT_CONFIG_LOCATION_SUFFIX = ".xml"
}
AbstractRefreshableWebApplicationContext ^-- XmlWebApplicationContext

class XmlBeanDefinitionReader {
    + int loadBeanDefinitions(String location)
}
XmlWebApplicationContext ..> XmlBeanDefinitionReader

@enduml
```
