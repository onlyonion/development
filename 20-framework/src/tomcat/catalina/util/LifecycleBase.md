## define

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

abstract class LifecycleBase {
    - final List<LifecycleListener> lifecycleListeners
    - volatile LifecycleState state = LifecycleState.NEW
    
    + final synchronized void init()
    # abstract void initInternal()
    + final synchronized void start()
    # abstract void startInternal()
    + final synchronized void stop()
    # abstract void stopInternal()
    + final synchronized void destroy()
    # abstract void destroyInternal()
    - synchronized void setStateInternal(LifecycleState state, Object data, boolean check)
}

Lifecycle <|.. LifecycleBase

@enduml
```