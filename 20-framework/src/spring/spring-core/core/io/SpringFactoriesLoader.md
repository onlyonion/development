org.springframework.core.io.support.SpringFactoriesLoader

* META-INF/spring.factories
* LinkedMultiValueMap
* ConcurrentReferenceHashMap

## define
```plantuml
@startuml

abstract class SpringFactoriesLoader
SpringFactoriesLoader *.. ConcurrentReferenceHashMap
SpringFactoriesLoader ..> LinkedMultiValueMap

@enduml
```