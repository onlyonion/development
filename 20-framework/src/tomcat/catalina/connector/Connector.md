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

'''''''''''''''''''''''''Connector'''''''''''''''''''''''''
LifecycleMBeanBase <|-- Connector

class Connector {
    # Service service
    # final ProtocolHandler protocolHandler
    # Adapter adapter
    + Request createRequest()
    + Response createResponse()
}
Connector *- Adapter
Connector *- ProtocolHandler

interface Adapter #yellow
Adapter ^.. CoyoteAdapter

'''''''''''''''''''''''''AbstractProtocol'''''''''''''''''''''''''
interface ProtocolHandler #yellow
abstract class AbstractProtocol

AbstractProtocol +-- AbstractProtocol.ConnectionHandler
class AbstractProtocol.ConnectionHandler<S>

abstract class AbstractHttp11Protocol
abstract class AbstractAjpProtocol

ProtocolHandler ^.. AbstractProtocol
AbstractProtocol ^-- AbstractHttp11Protocol
AbstractProtocol ^-- AbstractAjpProtocol

AbstractProtocol *-- Processor
AbstractProtocol *-- AbstractEndpoint

'''''''''''''''''''''''''AbstractEndpoint'''''''''''''''''''''''''
abstract class AbstractEndpoint #orange
interface AbstractEndpoint.Handler<S>

AbstractEndpoint *- AbstractEndpoint.Handler
AbstractEndpoint.Handler ^.. AbstractProtocol.ConnectionHandler

'''''''''''''''''''''''''Processor'''''''''''''''''''''''''
interface Processor #orange
abstract class AbstractProcessorLight
abstract class AbstractProcessor 

Processor ^.. AbstractProcessorLight
AbstractProcessorLight ^-- AbstractProcessor

AbstractProcessor *-- Adapter
AbstractProcessor *- AbstractEndpoint

@enduml
```