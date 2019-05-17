

```plantuml
@startuml
class StackObj
class AllStatic

class JavaCallWrapper
class JavaCallArguments
class JavaCalls

StackObj ^-- JavaCallWrapper
StackObj ^-- JavaCallArguments

AllStatic ^-- JavaCalls

@enduml
```
