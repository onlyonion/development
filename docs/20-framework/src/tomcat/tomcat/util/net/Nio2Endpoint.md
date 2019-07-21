org.apache.tomcat.util.net.Nio2Endpoint

## hierarchy
```
AbstractEndpoint (org.apache.tomcat.util.net)
    AbstractJsseEndpoint (org.apache.tomcat.util.net)
        Nio2Endpoint (org.apache.tomcat.util.net)
```

## define
```plantuml
@startuml

class Nio2Endpoint 

Nio2Endpoint *-- AsynchronousServerSocketChannel
Nio2Endpoint *-- AsynchronousChannelGroup

@enduml
```

## fields


## methods