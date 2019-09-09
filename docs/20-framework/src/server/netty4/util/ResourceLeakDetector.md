io.netty.util.ResourceLeakDetector

```plantuml
@startuml

class ResourceLeakDetector<T> {

}

ResourceLeakDetector +-- Level
ResourceLeakDetector o-- Level

enum Level {
    DISABLED,
    SIMPLE,
    ADVANCED,
    PARANOID
}

@enduml
```