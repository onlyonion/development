org.apache.catalina.Context

## hierarchy
```
Context (org.apache.catalina)
    FailedContext (org.apache.catalina.startup)
    StandardContext (org.apache.catalina.core)
        TomcatEmbeddedContext (org.springframework.boot.web.embedded.tomcat)
Context (org.apache.catalina)
    Container (org.apache.catalina)
        Lifecycle (org.apache.catalina)
    ContextBind (org.apache.tomcat)
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