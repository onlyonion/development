org.apache.catalina.Wrapper

## hierarchy
```
Wrapper (org.apache.catalina)
    StandardWrapper (org.apache.catalina.core)
        ExistingStandardWrapper in Tomcat (org.apache.catalina.startup)
Wrapper (org.apache.catalina)
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