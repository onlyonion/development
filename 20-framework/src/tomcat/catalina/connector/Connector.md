org.apache.catalina.connector.Connector

## hierarchy
```
LifecycleBase (org.apache.catalina.util)
    LifecycleMBeanBase (org.apache.catalina.util)
        Connector (org.apache.catalina.connector)
```

## define
```plantuml
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