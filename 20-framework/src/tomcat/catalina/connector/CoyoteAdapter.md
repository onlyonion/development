
## define

```
@startuml

interface Adapter {
    + void service(Request req, Response res)
}

class CoyoteAdapter {
    - final Connector connector
}

Adapter <|.. CoyoteAdapter

@enduml

```


## service
```
connector.getService().getContainer().getPipeline().getFirst().invoke(request, response)
```

## seq

```
@startuml
Http11Processor -> CoyoteAdapter: service()

opt
' 请求响应对象转换  CoyoteRequest -> ServletReqest
CoyoteAdapter -> Connector: createRequest()
CoyoteAdapter -> Connector: createResponse()
end


@enduml
```