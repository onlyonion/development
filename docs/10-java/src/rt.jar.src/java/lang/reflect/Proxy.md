java.lang.reflect.Proxy

## define
```plantuml
@startuml

class Proxy
Proxy *-- InvocationHandler
Proxy *-- WeakCache
Proxy *-- ConcurrentHashMap

@enduml
```