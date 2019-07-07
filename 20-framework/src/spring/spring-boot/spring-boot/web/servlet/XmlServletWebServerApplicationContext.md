org.springframework.boot.web.servlet.context.XmlServletWebServerApplicationContext

## hierarchy

## define
```plantuml
@startuml

class ServletWebServerApplicationContext {

}
ServletWebServerApplicationContext ^.. XmlServletWebServerApplicationContext

class XmlServletWebServerApplicationContext {
    - final XmlBeanDefinitionReader reader
}


@enduml
```