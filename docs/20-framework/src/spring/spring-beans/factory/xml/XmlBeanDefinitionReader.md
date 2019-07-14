org.springframework.beans.factory.xml.XmlBeanDefinitionReader

## define
```plantuml
@startuml

class XmlBeanDefinitionReader {
    # int doLoadBeanDefinitions(InputSource inputSource, Resource resource)
    # Document doLoadDocument(InputSource inputSource, Resource resource)
    + int registerBeanDefinitions(Document doc, Resource resource) 
}

@enduml
```