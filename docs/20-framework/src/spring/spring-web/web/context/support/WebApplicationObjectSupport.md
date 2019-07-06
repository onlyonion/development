org.springframework.web.context.support.WebApplicationObjectSupport

## define
```plantuml
@startuml

abstract class ApplicationObjectSupport {
    - ApplicationContext applicationContext
    - MessageSourceAccessor messageSourceAccessor
}

abstract class WebApplicationObjectSupport {
    - ServletContext servletContext
}
ApplicationObjectSupport ^.. WebApplicationObjectSupport

@enduml
```