
```
@startuml

interface Service {
    + Engine getContainer()
    + Server getServer()
    + Connector[] findConnectors()
    + Executor[] findExecutors()
    + Executor getExecutor(String name)
    + Mapper getMapper()
}

interface Lifecycle

Lifecycle <|-- Service

@enduml
```