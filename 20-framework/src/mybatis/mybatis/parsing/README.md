
## package
```
GenericTokenParser
ParsingException
PropertyParser
TokenHandler
XNode
XPathParser
```

## overview
```plantuml
@startuml

class XNode
class XPathParser

XNode o-- XPathParser
XNode ..> PropertyParser

interface TokenHandler
class GenericTokenParser

GenericTokenParser o-- TokenHandler

@enduml
```