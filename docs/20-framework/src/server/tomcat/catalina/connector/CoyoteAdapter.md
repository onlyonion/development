org.apache.catalina.connector.CoyoteAdapter

## define

```plantuml
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

```plantuml
@startuml
Http11Processor -> CoyoteAdapter: service()

opt
' 请求响应对象转换  CoyoteRequest -> ServletReqest
CoyoteAdapter -> Connector: createRequest()
CoyoteAdapter -> Connector: createResponse()
end


@enduml
```

## methods

### service
```java
    @Override
    public void service(org.apache.coyote.Request req, org.apache.coyote.Response res)
            throws Exception{

    }
```