
## hierachy
```
AbstractProcessorLight (org.apache.coyote)
    AbstractProcessor (org.apache.coyote)
        Http11Processor (org.apache.coyote.http11)
```

## define
```
@startuml

abstract class AbstractProcessor {
    # Adapter adapter
    # final AbstractEndpoint<?> endpoint
    # final Request request
    # final Response response
    # volatile SocketWrapperBase<?> socketWrapper
}

class Http11Processor {
    # volatile boolean keepAlive = true
    + SocketState service(SocketWrapperBase<?> socketWrapper)
}

AbstractProcessor <|-- Http11Processor

@enduml
```
