org.springframework.web.accept.ContentNegotiationManager

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
