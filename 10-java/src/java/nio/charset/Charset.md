java.nio.charset.Charset


## overview
```plantuml
@startuml

abstract class Charset
abstract class CharsetDecoder
abstract class CharsetEncoder

CharsetDecoder o-- Charset
CharsetEncoder o-- Charset
CharsetDecoder ..> CoderResult
CharsetEncoder ..> CoderResult

@enduml
```

