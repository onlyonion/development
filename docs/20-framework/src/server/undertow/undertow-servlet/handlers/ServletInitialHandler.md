io.undertow.servlet.handlers.ServletInitialHandler

## hierarchy
```

```

## define
```plantuml
@startuml

interface HttpHandler
interface ServletDispatcher
class ServletInitialHandler

HttpHandler ^.. ServletInitialHandler
ServletDispatcher ^.. ServletInitialHandler

@enduml
```