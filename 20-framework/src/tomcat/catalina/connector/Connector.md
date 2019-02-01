```
@startuml

class Connector {
    # Service service
    # final ProtocolHandler protocolHandler
    # Adapter adapter
    + Request createRequest()
    + Response createResponse()
}

LifecycleMBeanBase <|-- Connector

@enduml
```