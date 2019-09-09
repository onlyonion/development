org.apache.catalina.Host
## hierarchy
```
Host (org.apache.catalina)
    StandardHost (org.apache.catalina.core)
Host (org.apache.catalina)
    Container (org.apache.catalina)
        Lifecycle (org.apache.catalina)
```

## define
```plantuml
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

## fields


## methods