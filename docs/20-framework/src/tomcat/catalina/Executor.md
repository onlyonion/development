org.apache.catalina.Executor
## hierarchy
```
Executor (org.apache.catalina)
    StandardThreadExecutor (org.apache.catalina.core)
Executor (org.apache.catalina)
    Executor (java.util.concurrent)
    Lifecycle (org.apache.catalina)
```

## define
```plantuml
@startuml

interface Lifecycle
interface java.util.concurrent.Executor
interface Executor
class StandardThreadExecutor

Lifecycle ^-- Executor
java.util.concurrent.Executor ^-- Executor

Executor ^.. StandardThreadExecutor

@enduml
```

## fields


## methods