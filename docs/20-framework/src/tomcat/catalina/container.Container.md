org.apache.catalina.Container

## hierarchy
```
Container (org.apache.catalina)
    Host (org.apache.catalina)
        StandardHost (org.apache.catalina.core)
    ContainerBase (org.apache.catalina.core)
        StandardEngine (org.apache.catalina.core)
        StandardWrapper (org.apache.catalina.core)
        StandardHost (org.apache.catalina.core)
        StandardContext (org.apache.catalina.core)
    Wrapper (org.apache.catalina)
        StandardWrapper (org.apache.catalina.core)
    Engine (org.apache.catalina)
        StandardEngine (org.apache.catalina.core)
    Context (org.apache.catalina)
        FailedContext (org.apache.catalina.startup)
        StandardContext (org.apache.catalina.core)
Container (org.apache.catalina)
    Lifecycle (org.apache.catalina)
```

## define
```
@startuml

interface Lifecycle
Lifecycle ^-- Container

interface Container {
    + Pipeline getPipeline()
    + Cluster getCluster()
    + Container getParent()
    + ClassLoader getParentClassLoader()
    + void addChild(Container child)
    + void fireContainerEvent(String type, Object data)
}

interface Engine
interface Host
interface Context
interface Wrapper

Container ^-- Engine
Container ^-- Host
Container ^-- Context
Container ^-- Wrapper

@enduml
```