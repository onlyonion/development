org.springframework.data.mongodb.core.MongoTemplate

## Hierarchy
```
MongoTemplate (org.springframework.data.mongodb.core)
    MongoOperations (org.springframework.data.mongodb.core)
    ApplicationContextAware (org.springframework.context)
        Aware (org.springframework.beans.factory)
```
## Define
```plantuml
@startuml

interface MongoOperations
interface ApplicationContextAware
class MongoTemplate

MongoOperations ^.. MongoTemplate
ApplicationContextAware ^.. MongoTemplate

interface DbCallback<T>
MongoOperations ..> DbCallback
@enduml
```