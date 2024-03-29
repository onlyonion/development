org.apache.catalina.Engine

## hierarchy
```
Engine (org.apache.catalina)
    StandardEngine (org.apache.catalina.core)
Engine (org.apache.catalina)
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

```java
public interface Engine extends Container {
    public String getDefaultHost();
    public void setDefaultHost(String defaultHost);
    public String getJvmRoute();
    public void setJvmRoute(String jvmRouteId);
    public Service getService();
    public void setService(Service service);
}
```

## fields


## methods