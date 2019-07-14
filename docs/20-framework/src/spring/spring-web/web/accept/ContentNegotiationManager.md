org.springframework.web.accept.ContentNegotiationManager

## hierarchy
```
ContentNegotiationManager (org.springframework.web.accept)
    ContentNegotiationStrategy (org.springframework.web.accept)
    MediaTypeFileExtensionResolver (org.springframework.web.accept)
```

## define

```plantuml
@startuml

interface ContentNegotiationStrategy
interface MediaTypeFileExtensionResolver

class ContentNegotiationManage

ContentNegotiationStrategy <|-.- ContentNegotiationManage
MediaTypeFileExtensionResolver <|-.- ContentNegotiationManage

@enduml
```
