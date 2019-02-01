org.apache.catalina.Lifecycle

## define
* init()
* start()
* stop()
* destroy()

```
@startuml

interface Lifecycle {
    + void addLifecycleListener(LifecycleListener listener)
    + LifecycleListener[] findLifecycleListeners()
    + void removeLifecycleListener(LifecycleListener listener)
    + void init()
    + void start()
    + void stop()
    + void destroy()
    + LifecycleState getState()
    + String getStateName()
}

interface SingleUse

Lifecycle +- SingleUse

@enduml
```