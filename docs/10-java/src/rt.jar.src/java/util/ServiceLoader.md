java.util.ServiceLoader


* META-INF/services/

## define
```plantuml
@startuml

class ServiceLoader<S> {
}

ServiceLoader *-- ClassLoader
ServiceLoader *-- LinkedHashMap

@enduml
```