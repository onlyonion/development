org.springframework.web.context.support.AbstractRefreshableWebApplicationContext

## hierarchy
```
DefaultResourceLoader (org.springframework.core.io)
    AbstractApplicationContext (org.springframework.context.support)
        AbstractRefreshableApplicationContext (org.springframework.context.support)
            AbstractRefreshableConfigApplicationContext (org.springframework.context.support)
                AbstractRefreshableWebApplicationContext (org.springframework.web.context.support)
                    AnnotationConfigWebApplicationContext (org.springframework.web.context.support)
                    GroovyWebApplicationContext (org.springframework.web.context.support)
                    XmlWebApplicationContext (org.springframework.web.context.support)
```

## define
```plantuml
@startuml

AbstractRefreshableConfigApplicationContext ^-- AbstractRefreshableWebApplicationContext
ConfigurableWebApplicationContext ^.. AbstractRefreshableWebApplicationContext
ThemeSource ^.. AbstractRefreshableWebApplicationContext

abstract class AbstractRefreshableWebApplicationContext #orange {

}

AbstractRefreshableWebApplicationContext o- ServletContext
AbstractRefreshableWebApplicationContext o- ServletConfig
ThemeSource o-- AbstractRefreshableWebApplicationContext

AbstractRefreshableWebApplicationContext ^-- AnnotationConfigWebApplicationContext
AbstractRefreshableWebApplicationContext ^-- GroovyWebApplicationContext
AbstractRefreshableWebApplicationContext ^-- XmlWebApplicationContext
@enduml
```